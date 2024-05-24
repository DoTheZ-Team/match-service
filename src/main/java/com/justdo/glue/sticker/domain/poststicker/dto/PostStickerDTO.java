package com.justdo.glue.sticker.domain.poststicker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

public class PostStickerDTO {
    @Schema(description = "스티커 포스트 정보 DTO")
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class PostStickerItem {

        @Schema(description = "포스트-스티커의 id")
        @JsonProperty("postStickerId")
        private Long postStickerId;

        @Schema(description = "포스트의 id")
        @JsonProperty("postId")
        private Long postId;

        @Schema(description = "스티커의 id")
        @JsonProperty("stickerId")
        private Long stickerId;

        @Schema(description = "스티커의 x_location")
        @JsonProperty("xLocation")
        private int xLocation;

        @Schema(description = "스티커의 y_location")
        @JsonProperty("yLocation")
        private int yLocation;

        @Schema(description = "스티커의 width")
        @JsonProperty("width")
        private int width;

        @Schema(description = "스티커의 height")
        @JsonProperty("height")
        private int height;

        @Schema(description = "스티커의 angle")
        @JsonProperty("angle")
        private int angle;
    }

    public static PostStickerDTO.PostStickerItem toPostStickerItem(Long postStickerId, Long stickerId, Long postId, int xLocation, int yLocation, int width, int height, int angle) {
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