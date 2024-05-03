package com.justdo.glue.sticker.domain.sticker.dto;

import com.justdo.glue.sticker.domain.sticker.Sticker;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class StickerResponse {
    @Schema(description = "스티커 생성 요청 응답 DTO")
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class StickerGenerationResult {

        private String stickerPrompt;
        private String stickerType;
        private String stickerUrl;

    }

    public static StickerGenerationResult toEntity(String stickerPrompt, String stickerType, String stickerUrl) {

        return StickerGenerationResult.builder()
                .stickerPrompt(stickerPrompt)
                .stickerType(stickerType)
                .stickerUrl(stickerUrl)
                .build();
    }
}
