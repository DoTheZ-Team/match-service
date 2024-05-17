package com.justdo.glue.sticker.domain.poststicker.dto;

import com.justdo.glue.sticker.domain.sticker.dto.StickerResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

public class PostStickerResponse {
    @Schema(description = "스티커 포스트 정보 DTO")
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class PostStickerItem {

        @Schema(description = "포스트-스티커의 id")
        private Long postStickerId;

        @Schema(description = "포스트의 id")
        private Long postId;

        @Schema(description = "스티커의 id")
        private Long stickerId;

        @Schema(description = "스티커의 x_location")
        private Double xLocation;

        @Schema(description = "스티커의 y_location")
        private Double yLocation;

        @Schema(description = "스티커의 width")
        private Double width;

        @Schema(description = "스티커의 height")
        private Double height;

        @Schema(description = "스티커의 angle")
        private Double angle;
    }

    public static PostStickerResponse.PostStickerItem toPostStickerItem(Long postStickerId, Long stickerId, Long postId, Double xLocation, Double yLocation, Double width, Double height, Double angle) {

        return PostStickerItem.builder()
                .postStickerId(postStickerId)
                .postId(postId)
                .stickerId(stickerId)
                .xLocation(xLocation)
                .yLocation(yLocation)
                .width(width)
                .height(height)
                .angle(angle)
                .build();
    }
}