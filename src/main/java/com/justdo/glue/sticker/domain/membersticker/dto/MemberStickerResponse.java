package com.justdo.glue.sticker.domain.membersticker.dto;

import com.justdo.glue.sticker.domain.sticker.dto.StickerResponse.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class MemberStickerResponse {
    @Schema(description = "사용자와 사용자가 사용한 스티커 정보 리턴 DTO")
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class MemberStickerItem {

        @Schema(description = "사용자-스티커 엔티티의 id")
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

    @Schema(description = "사용자가 제작한 스티커 정보를 리스트 형태로 리턴하는 DTO")
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class MemberStickerItems {

        private Long memberId;
        private List<StickerItem> stickers;
    }

    public static MemberStickerResponse.MemberStickerItems toMemberStickerItems(Long memberId, List<StickerItem> stickers) {

        return MemberStickerResponse.MemberStickerItems.builder()
                .memberId(memberId)
                .stickers(stickers)
                .build();
    }
}
