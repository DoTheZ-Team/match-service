package com.justdo.glue.sticker.domain.basicSticker.service;

import com.justdo.glue.sticker.domain.basicSticker.dto.BasicStickerDTO;
import com.justdo.glue.sticker.domain.common.CustomPage;
import org.springframework.data.domain.Page;

public interface BasicStickerQueryService {
    CustomPage<BasicStickerDTO.BasicStickerItems> getBasicStickersPage(int page, int size);
}
