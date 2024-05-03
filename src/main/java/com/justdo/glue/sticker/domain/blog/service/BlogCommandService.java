package com.justdo.glue.sticker.domain.blog.service;

import static com.justdo.glue.sticker.domain.member.MemberDTO.toMemberDTO;

import com.justdo.glue.sticker.domain.blog.Blog;
import com.justdo.glue.sticker.domain.blog.dto.BlogRequest;
import com.justdo.glue.sticker.domain.blog.dto.BlogResponse;
import com.justdo.glue.sticker.domain.blog.dto.BlogResponse.BlogProc;
import com.justdo.glue.sticker.domain.blog.dto.BlogResponse.ImageResult;
import com.justdo.glue.sticker.domain.blog.repository.BlogRepository;
import com.justdo.glue.sticker.domain.blog.service.BlogQueryService;
import com.justdo.glue.sticker.domain.member.MemberClient;
import com.justdo.glue.sticker.global.s3.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional
public class BlogCommandService {

    private final BlogRepository blogRepository;
    private final BlogQueryService blogQueryService;
    private final MemberClient memberClient;
    private final S3Service s3Service;


    // 블로그 배경사진 수정
    public ImageResult imageUpload(MultipartFile multipartFile) {

        String imageUrl = s3Service.uploadFile(multipartFile);
        return BlogResponse.toImageResult(imageUrl);
    }

    public BlogProc updateBlog(BlogRequest request, Long blogId) {

        memberClient.updateMember(toMemberDTO(request));
        blogQueryService.findById(blogId).update(request);

        return BlogResponse.toBlogProc(blogId);
    }

    public BlogProc createBlog(Long memberId) {

        Blog blog = BlogRequest.toEntity(memberId);
        save(blog);
        return BlogResponse.toBlogProc(blog.getId());
    }

    public void save(Blog blog) {
        blogRepository.save(blog);
    }
}
