package com.justdo.glue.sticker.domain.userSticker.dto;

import com.justdo.glue.sticker.domain.sticker.dto.StickerResponse.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class UserStickerResponse {
    @Schema(description = "사용자와 사용자가 사용한 스티커 정보 리턴 DTO")
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class UserStickerItem {

        @Schema(description = "사용자-스티커 엔티티의 id")
        private Long userStickerId;

        @Schema(description = "사용자의 id")
        private Long userId;

        @Schema(description = "스티커의 id")
        private Long stickerId;
    }

    public static UserStickerResponse.UserStickerItem toUserStickerItem(Long userStickerId, Long userId, Long stickerId) {

        return UserStickerResponse.UserStickerItem.builder()
                .userStickerId(userStickerId)
                .userId(userId)
                .stickerId(stickerId)
                .build();
    }

    @Schema(description = "사용자가 제작한 스티커 정보를 리스트 형태로 리턴하는 DTO")
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class UserStickerItems {

        private Long userId;
        private List<StickerItem> stickers;
    }

    public static UserStickerResponse.UserStickerItems toUserStickerItems(Long userId, List<StickerItem> stickers) {

        return UserStickerResponse.UserStickerItems.builder()
                .userId(userId)
                .stickers(stickers)
                .build();
    }
}
