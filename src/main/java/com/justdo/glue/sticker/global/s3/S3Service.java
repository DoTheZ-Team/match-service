package com.justdo.glue.sticker.global.s3;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import com.justdo.glue.sticker.global.exception.ApiException;
import com.justdo.glue.sticker.global.response.code.status.ErrorStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@PropertySource("classpath:application.yml")
public class S3Service {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.region.static}")
    private String region;

    // 파일 확장자 가져오기
    private String getFileExtension(String fileName) {
        try {
            return fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "잘못된 형식의 파일(" + fileName + ") 입니다.");
        }
    }

    // 고유한 파일명 생성
    private String createFileName(String fileName) {
        return UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }

    // Base64 인코딩된 이미지를 S3에 업로드
    public String uploadBase64Image(String base64Image, String originalFileName) {
        byte[] imageBytes = Base64.getDecoder().decode(base64Image);
        String fileName = createFileName(originalFileName);
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(imageBytes.length);
        objectMetadata.setContentType("image/png");

        try (InputStream inputStream = new ByteArrayInputStream(imageBytes)) {
            amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata));
            // 업로드된 파일의 URL 생성
            return getPublicUrl(fileName);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "파일 업로드에 실패했습니다.");
        }
    }

    // 업로드된 파일의 URL 생성
    private String getPublicUrl(String fileName) {
        return String.format("https://%s.s3.%s.amazonaws.com/%s", bucket, region, fileName);
    }

    // 이미지 삭제
    public void deleteImage(String imageUrl) {
        String fileName = extractFileNameFromUrl(imageUrl);
        amazonS3Client.deleteObject(new DeleteObjectRequest(bucket, fileName));
    }

    // URL에서 파일 이름 추출
    private String extractFileNameFromUrl(String url) {
        String prefix = String.format("https://%s.s3.%s.amazonaws.com/", bucket, region);
        if (url.startsWith(prefix)) {
            return url.substring(prefix.length());
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 URL 형식입니다.");
        }
    }

//    // 이미지 삭제
//    public void deleteFile(String fileName) {
//        try {
//            String s3File = extractImageFromUrl(fileName);
//            amazonS3Client.deleteObject(new DeleteObjectRequest(bucket, s3File));
//        } catch (AmazonS3Exception e) {
//            throw new ApiException(ErrorStatus._S3_IMAGE_NOT_FOUND);
//        }
//    }
//
//    // S3 주소에 이미지 URL 추출
//    private String extractImageFromUrl(String imgUrl) {
//        String bucketPrefix = "https://" + bucket + ".s3." + region + ".amazonaws.com/";
//        if (imgUrl.startsWith(bucketPrefix)) {
//            return imgUrl.substring(bucketPrefix.length());
//        } else {
//            throw new ApiException(ErrorStatus._S3_IMAGE_NOT_FOUND);
//        }
//    }
}
