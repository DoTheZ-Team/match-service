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
        private Double xLocation;

        @Schema(description = "스티커의 y_location")
        @JsonProperty("yLocation")
        private Double yLocation;

        @Schema(description = "스티커의 width")
        @JsonProperty("width")
        private Double width;

        @Schema(description = "스티커의 height")
        @JsonProperty("height")
        private Double height;

        @Schema(description = "스티커의 angle")
        @JsonProperty("angle")
        private Double angle;
    }

    public static PostStickerDTO.PostStickerItem toPostStickerItem(Long postStickerId, Long stickerId, Long postId, Double xLocation, Double yLocation, Double width, Double height, Double angle) {
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