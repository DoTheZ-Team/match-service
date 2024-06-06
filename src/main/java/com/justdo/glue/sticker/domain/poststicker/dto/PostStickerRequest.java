package com.justdo.glue.sticker.domain.poststicker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.justdo.glue.sticker.domain.poststicker.PostSticker;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;


public class PostStickerRequest {

    @Schema(description = "포스트의 id")
    private Long postId;

    @Schema(description = "스티커의 id")
    private Long stickerId;

    @Schema(description = "스티커의 xLocation")
    private int xLocation;

    @Schema(description = "스티커의 yLocation")
    private int yLocation;

    @Schema(description = "스티커의 scaleX")
    private double scaleX;

    @Schema(description = "스티커의 scaleY")
    private double scaleY;

    @Schema(description = "스티커의 rotation")
    private double rotation;

    public Long getPostId() {
        return postId;
    }

    public Long getStickerId() {
        return stickerId;
    }

    public int getxLocation() {
        return xLocation;
    }

    public int getyLocation() {
        return yLocation;
    }

    public double getScaleX() {
        return scaleX;
    }

    public double getScaleY() {
        return scaleY;
    }

    public double getRotation() {
        return rotation;
    }

    public static PostSticker toEntity(PostStickerRequest request) {
        return PostSticker.builder()
                .postId(request.getPostId())
                .stickerId(request.getStickerId())
                .xLocation(request.getxLocation())
                .yLocation(request.getyLocation())
                .width(request.getScaleX())
                .height(request.getScaleY())
                .angle(request.getRotation())
                .build();
    }
}