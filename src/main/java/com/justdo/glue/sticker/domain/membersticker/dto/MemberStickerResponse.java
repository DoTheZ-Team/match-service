package com.justdo.glue.sticker.domain.membersticker.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberStickerResponse {
    @Schema(description = "사용자와 사용자가 사용한 스티커 정보 리턴 DTO")
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class MemberStickerItem {

        @Schema(description = "포스트-스티커의 id")
        private Long memberStickerId;

        @Schema(description = "사용자의 id")
        private Long memberId;

        @Schema(description = "스티커의 id")
        private Long stickerId;
    }

    public static MemberStickerResponse.MemberStickerItem toMemberStickerItem(Long memberStickerId, Long memberId, Long stickerId) {

        return MemberStickerResponse.MemberStickerItem.builder()
                .memberStickerId(memberStickerId)
                .memberId(memberId)
                .stickerId(stickerId)
                .build();
    }
}
