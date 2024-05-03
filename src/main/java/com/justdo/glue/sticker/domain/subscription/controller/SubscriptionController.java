package com.justdo.glue.sticker.domain.subscription.controller;

import com.justdo.glue.sticker.domain.subscription.dto.SubscriptionResponse;
import com.justdo.glue.sticker.domain.subscription.service.SubscriptionCommandService;
import com.justdo.glue.sticker.domain.subscription.service.SubscriptionQueryService;
import com.justdo.glue.sticker.domain.blog.dto.BlogResponse.BlogItemList;
import com.justdo.glue.sticker.domain.blog.service.BlogQueryService;
import com.justdo.glue.sticker.domain.post.PostResponse.PostItemList;
import com.justdo.glue.sticker.domain.subscription.dto.SubscriptionResponse.SubscriptionProc;
import com.justdo.glue.sticker.global.response.ApiResponse;
import com.justdo.glue.sticker.global.utils.JwtProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Subscription API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/blogs/subscriptions")
public class SubscriptionController {

    private final JwtProvider jwtProvider;
    private final SubscriptionCommandService subscriptionCommandService;
    private final SubscriptionQueryService subscriptionQueryService;
    private final BlogQueryService blogQueryService;

    @Operation(summary = "블로그 구독 요청 및 취소 요청", description = "다른 블로그를 구독 요청합니다. / 구독 상태에서 누르면 취소됩니다.")
    @Parameter(name = "blogId", description = "블로그 Id, Path Variable입니다.", required = true, example = "1", in = ParameterIn.PATH)
    @PostMapping("/{blogId}")
    public ApiResponse<SubscriptionProc> subscribe(HttpServletRequest request,
        @PathVariable(name = "blogId") Long blogId) {

        Long memberId = jwtProvider.getUserIdFromToken(request);

        return ApiResponse.onSuccess(subscriptionCommandService.subscribe(memberId, blogId));
    }

    @Operation(summary = "내가 구독한 블로그와 포스트 정보 모두 조회", description = "내가 구독한 블로그와 포스트를 조회합니다.")
    @Parameter(name = "page", description = "페이지 번호, Query Parameter입니다.", example = "0", in = ParameterIn.QUERY)
    @GetMapping
    public ApiResponse<SubscriptionResponse.SubscriptionResult> getSubscriptionFrom(
        HttpServletRequest request, @RequestParam(defaultValue = "0") int page) {

        Long memberId = jwtProvider.getUserIdFromToken(request);
        BlogItemList blogInfoList = blogQueryService.getBlogInfoList(memberId, page);
        PostItemList postItemList = subscriptionQueryService.getSubscriptionPostFrom(
            memberId, page);

        return ApiResponse.onSuccess(
            SubscriptionResponse.toSubscriptionResult(blogInfoList, postItemList));
    }

    @Operation(summary = "내가 구독한 블로그 정보 조회", description = "내가 구독한 블로그의 정보를 조회합니다.")
    @Parameter(name = "page", description = "페이지 번호, Query Parameter입니다.", example = "0", in = ParameterIn.QUERY)
    @GetMapping("/followers")
    public ApiResponse<BlogItemList> getFollowers(HttpServletRequest request,
        @RequestParam(defaultValue = "0") int page) {

        Long memberId = jwtProvider.getUserIdFromToken(request);
        return ApiResponse.onSuccess(blogQueryService.getBlogInfoList(memberId, page));
    }

    @Operation(summary = "내가 구독한 블로그의 포스트 정보 조회", description = "내가 구독한 블로그의 포스트 정보를 조회합니다.")
    @Parameter(name = "page", description = "페이지 번호, Query Parameter입니다.", example = "0", in = ParameterIn.QUERY)
    @GetMapping("/followers/posts")
    public ApiResponse<PostItemList> getFollowersPosts(HttpServletRequest request,
        @RequestParam(defaultValue = "0") int page) {

        Long memberId = jwtProvider.getUserIdFromToken(request);
        return ApiResponse.onSuccess(
            subscriptionQueryService.getSubscriptionPostFrom(memberId, page));
    }


    @Operation(summary = "나를 구독한 블로그와 포스트 모두 정보 조회", description = "나를 구독한 블로그와 포스트 정보를 모두 조회합니다.")
    @Parameter(name = "page", description = "페이지 번호, Query Parameter입니다.", example = "0", in = ParameterIn.QUERY)
    @GetMapping("/subscribers")
    public ApiResponse<SubscriptionResponse.SubscriptionResult> getSubscriptionTo(
        HttpServletRequest request, @RequestParam(defaultValue = "0") int page) {

        Long memberId = jwtProvider.getUserIdFromToken(request);
        Long blogId = blogQueryService.findBlogIdByMemberId(memberId);

        BlogItemList blogInfoList = blogQueryService.getSubscriberBlogList(blogId, page);
        PostItemList postItemList = subscriptionQueryService.getSubscriptionPostTo(
            blogId, page);

        return ApiResponse.onSuccess(
            SubscriptionResponse.toSubscriptionResult(blogInfoList, postItemList));
    }

    @Operation(summary = "나를 구독한 블로그 정보 조회", description = "나를 구독한 블로그 정보를 조회합니다.")
    @Parameter(name = "page", description = "페이지 번호, Query Parameter입니다.", example = "0", in = ParameterIn.QUERY)
    @GetMapping("/follows")
    public ApiResponse<BlogItemList> getFollows(HttpServletRequest request,
        @RequestParam(defaultValue = "0") int page) {

        Long memberId = jwtProvider.getUserIdFromToken(request);
        Long blogId = blogQueryService.findBlogIdByMemberId(memberId);

        return ApiResponse.onSuccess(blogQueryService.getSubscriberBlogList(blogId, page));
    }

    @Operation(summary = "나를 구독한 블로그의 포스트 정보 조회", description = "나를 구독한 블로그의 포스트 정보를 조회합니다.")
    @Parameter(name = "page", description = "페이지 번호, Query Parameter입니다.", example = "0", in = ParameterIn.QUERY)
    @GetMapping("/follows/posts")
    public ApiResponse<PostItemList> getFollowsPosts(HttpServletRequest request,
        @RequestParam(defaultValue = "0") int page) {

        Long memberId = jwtProvider.getUserIdFromToken(request);
        Long blogId = blogQueryService.findBlogIdByMemberId(memberId);

        return ApiResponse.onSuccess(subscriptionQueryService.getSubscriptionPostTo(
            blogId, page));
    }
}
