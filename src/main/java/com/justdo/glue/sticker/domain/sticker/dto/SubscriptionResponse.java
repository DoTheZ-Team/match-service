//package com.justdo.glue.sticker.domain.sticker.dto;
//
//import com.justdo.glue.sticker.domain.blog.dto.BlogResponse.BlogItemList;
//import com.justdo.glue.sticker.domain.post.PostResponse.PostItemList;
//import com.justdo.glue.sticker.domain.sticker.Sticker;
//import io.swagger.v3.oas.annotations.media.Schema;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDateTime;
//
//public class SubscriptionResponse {
//
//    @Schema(description = "구독 요청 응답 DTO")
//    @Builder
//    @AllArgsConstructor
//    @NoArgsConstructor
//    @Getter
//    public static class SubscriptionProc {
//
//        @Schema(description = "구독 고유 아이디")
//        private Long subscriptionId;
//
//        @Schema(description = "응답 처리 시간")
//        private LocalDateTime createdAt;
//    }
//
//    public static SubscriptionProc toSubscriptionProc(Sticker sticker) {
//
//        return SubscriptionProc.builder()
//            .subscriptionId(sticker.getId())
//            .createdAt(LocalDateTime.now())
//            .build();
//    }
//
//    public static Sticker toEntity(Long memberId, Long blogId) {
//
//        return Sticker.builder()
//            .fromMemberId(memberId)
//            .toBlogId(blogId)
//            .build();
//    }
//
//    @Schema(description = "내가 구독한 블로그의 정보 응답 DTO")
//    @Builder
//    @AllArgsConstructor
//    @NoArgsConstructor
//    @Getter
//    public static class SubscriptionResult {
//
//        BlogItemList blogItemList;
//        PostItemList postItemList;
//    }
//
//    public static SubscriptionResult toSubscriptionResult(BlogItemList blogItemList,
//        PostItemList postItemList) {
//
//        return SubscriptionResult.builder()
//            .blogItemList(blogItemList)
//            .postItemList(postItemList)
//            .build();
//    }
//}
