package com.justdo.glue.sticker.domain.poststicker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.justdo.glue.sticker.domain.poststicker.PostSticker;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class PostStickerDTO {

    @Schema(description = "스티커 포스트 & 스티커 URL 정보 DTO")
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class PostStickerItem {

        @Schema(description = "포스트-스티커의 id", example = "1")
        private Long postStickerId;

        @Schema(description = "포스트의 id", example = "2")
        private Long postId;

        @Schema(description = "스티커의 id", example = "3")
        private Long stickerId;

        @Schema(description = "스티커의 url", example = "https://glue-bucket-sticker.s3.ap-northeast-2.amazonaws.com/54728a63-ff4b-4b3e-aea8-18036491b97c.png")
        private String url;

        @Schema(description = "스티커의 x_location", example = "100")
        @JsonProperty(value = "xlocation")
        private int xLocation;

        @Schema(description = "스티커의 y_location", example = "100")
        @JsonProperty(value = "ylocation")
        private int yLocation;

        @Schema(description = "스티커의 scaleX", example = "100")
        private double scaleX;

        @Schema(description = "스티커의 scaleY", example = "100")
        private double scaleY;

        @Schema(description = "스티커의 rotation", example = "100")
        private double rotation;
    }

    public static PostStickerDTO.PostStickerItem toPostStickerItem(PostSticker postSticker,
            String url) {
        return PostStickerItem.builder()
                .postStickerId(postSticker.getId())
                .postId(postSticker.getPostId())
                .stickerId(postSticker.getStickerId())
                .url(url)
                .xLocation(postSticker.getXLocation())
                .yLocation(postSticker.getYLocation())
                .scaleX(postSticker.getWidth())
                .scaleY(postSticker.getHeight())
                .rotation(postSticker.getAngle())
                .build();
    }

    @Schema(description = "포스트 스티커 요청 처리 응답 DTO")
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class PostStickerProc {

        @Schema(description = "포스트 스티커 고유 아이디")
        private Long postStickerId;

        @Schema(description = "요청 처리 응답 생성 시간")
        private LocalDateTime createdAt;
    }

    public static PostStickerProc toPostStickerProc(Long postStickerId) {

        return PostStickerProc.builder()
                .postStickerId(postStickerId)
                .createdAt(LocalDateTime.now())
                .build();
    }

}
