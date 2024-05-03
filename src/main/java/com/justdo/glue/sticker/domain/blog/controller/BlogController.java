package com.justdo.glue.sticker.domain.blog.controller;

import com.justdo.glue.sticker.domain.blog.dto.BlogRequest;
import com.justdo.glue.sticker.domain.blog.dto.BlogResponse.BlogProc;
import com.justdo.glue.sticker.domain.blog.dto.BlogResponse.ImageResult;
import com.justdo.glue.sticker.domain.blog.dto.BlogResponse.MyBlogResult;
import com.justdo.glue.sticker.domain.blog.service.BlogCommandService;
import com.justdo.glue.sticker.domain.blog.service.BlogQueryService;
import com.justdo.glue.sticker.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "Blog API")
@RestController
@RequestMapping("/blogs")
@RequiredArgsConstructor
public class BlogController {

    private final BlogCommandService blogCommandService;
    private final BlogQueryService blogQueryService;

    @Operation(summary = "블로그 이미지 업로드", description = "블로그의 프로필 & 배경사진 이미지를 업로드합니다.")
    @PostMapping("/images")
    public ApiResponse<ImageResult> uploadImage(
        @RequestPart(name = "imageUrl") MultipartFile multipartFile) {
        return ApiResponse.onSuccess(blogCommandService.imageUpload(multipartFile));
    }

    @Operation(summary = "사용자 및 블로그 정보 조회", description = "사용자 및 블로그 개인 정보를 조회합니다.")
    @Parameter(name = "blogId", description = "블로그 Id, Path Variable입니다.", required = true, example = "1", in = ParameterIn.PATH)
    @GetMapping("/mypage/{blogId}")
    public ApiResponse<MyBlogResult> myBlog(@PathVariable(name = "blogId") Long blogId) {
        return ApiResponse.onSuccess(blogQueryService.getBlogInfo(blogId));
    }

    @Operation(summary = "사용자 및 블로그 정보 수정", description = "사용자 및 블로그 정보를 수정합니다.")
    @Parameter(name = "blogId", description = "블로그 Id, Path Variable입니다.", required = true, example = "1", in = ParameterIn.PATH)
    @PatchMapping("/{blogId}")
    public ApiResponse<BlogProc> updateBlog(@RequestBody BlogRequest request,
        @PathVariable(name = "blogId") Long blogId) {

        return ApiResponse.onSuccess(blogCommandService.updateBlog(request, blogId));
    }

    @Operation(summary = "블로그 생성 요청", description = "사용자 회원가입 시 자동으로 하나의 블로그가 생성됩니다.")
    @PostMapping
    public ApiResponse<BlogProc> createBlog(@RequestParam Long memberId) {

        return ApiResponse.onSuccess(blogCommandService.createBlog(memberId));
    }

}
