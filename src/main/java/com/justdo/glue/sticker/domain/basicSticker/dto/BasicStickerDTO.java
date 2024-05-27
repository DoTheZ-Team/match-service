package com.justdo.glue.sticker.domain.basicSticker.dto;

import com.justdo.glue.sticker.domain.basicSticker.BasicSticker;
import com.justdo.glue.sticker.domain.sticker.dto.StickerResponse.StickerItem;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

public class BasicStickerDTO {

    @Schema(description = "기본 스티커 정보를 리스트 형태로 리턴하는 DTO")
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class BasicStickerItems {
        private List<StickerItem> stickerItems;
    }

    public static BasicStickerItems toBasicStickerItems(List<StickerItem> StickerItems) {

        return BasicStickerItems.builder()
                .stickerItems(StickerItems)
                .build();
    }
}
