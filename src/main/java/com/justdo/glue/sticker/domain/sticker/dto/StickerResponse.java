package com.justdo.glue.sticker.domain.sticker.dto;

import com.justdo.glue.sticker.domain.sticker.Sticker;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

public class StickerResponse {
    @Schema(description = "스티커 생성 요청 응답 DTO")
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class StickerGenerationResult {

        @Schema(description = "사용자가 작성한 프롬프트")
        private String stickerPrompt;

        @Schema(description = "url이 아닌 base64 형태로 저장")
        private String stickerUrl;

    }

    public static StickerGenerationResult toStickerGenerationResult(String stickerPrompt, String stickerUrl) {

        return StickerGenerationResult.builder()
                .stickerPrompt(stickerPrompt)
                .stickerUrl(stickerUrl)
                .build();
    }

    @Schema(description = "스티커 정보 DTO")
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class StickerItem{
        private Long stickerId;

        @Schema(description = "url이 아닌 base64 형태로 저장")
        private String url;

        private String prompt;

    }

    public static StickerItem toStickerItem(Long id, String url, String prompt){
        return StickerItem.builder()
                .stickerId(id)
                .url(url)
                .prompt(prompt)
                .build();
    }
}
