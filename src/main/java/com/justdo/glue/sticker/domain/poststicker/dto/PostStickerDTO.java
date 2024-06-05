package com.justdo.glue.sticker.domain.poststicker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.justdo.glue.sticker.domain.sticker.dto.StickerResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

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

        @Schema(description = "스티커의 scaleX")
        @JsonProperty("scaleX")
        private double scaleX;

        @Schema(description = "스티커의 scaleY")
        @JsonProperty("scaleY")
        private double scaleY;

        @Schema(description = "스티커의 rotation")
        @JsonProperty("rotation")
        private double rotation;
    }

    public static PostStickerDTO.PostStickerItem toPostStickerItem(Long postStickerId, Long stickerId, Long postId, int xLocation, int yLocation, double scaleX, double scaleY, double rotation) {
        return PostStickerItem.builder()
                .postStickerId(postStickerId)
                .postId(postId)
                .stickerId(stickerId)
                .xLocation(xLocation)
                .yLocation(yLocation)
                .scaleX(scaleX)
                .scaleY(scaleY)
                .rotation(rotation)
                .build();
    }

    @Schema(description = "포스트에 저장된 스티커 리스트 정보 DTO")
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class PostStickerItems{
        private List<PostStickerItem> postStickerItems;
    }

    public static PostStickerDTO.PostStickerItems toPostStickerItems(List<PostStickerItem> postStickerItems){
        return PostStickerItems.builder()
                .postStickerItems(postStickerItems)
                .build();
    }

    @Schema(description = "url포함 스티커 포스트 정보 DTO")
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class PostStickerUrlItem {

        @Schema(description = "스티커 item")
        private PostStickerItem postStickerItem;

        @Schema(description = "스티커의 url")
        private String url;
    }

    public static PostStickerDTO.PostStickerUrlItem toPostStickerUrlItem(PostStickerItem postStickerItem, String url) {
        return PostStickerUrlItem.builder()
                .postStickerItem(postStickerItem)
                .url(url)
                .build();
    }

    @Schema(description = "url 포함 스티커 포스트 정보 DTO")
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class PostStickerUrlItems {

        @Schema(description = "url포함 포스트-스티커 리스트")
        @JsonProperty("postStickerUrlItems")
        private List<PostStickerUrlItem> postStickerUrlItems;
    }

    public static PostStickerDTO.PostStickerUrlItems toPostStickerUrlItems(List<PostStickerUrlItem> postStickerUrlItems) {
        return PostStickerUrlItems.builder()
                .postStickerUrlItems(postStickerUrlItems)
                .build();
    }
}
