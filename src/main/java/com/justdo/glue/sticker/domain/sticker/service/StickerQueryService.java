package com.justdo.glue.sticker.domain.sticker.service;

import com.justdo.glue.sticker.domain.sticker.Sticker;
import com.justdo.glue.sticker.domain.sticker.dto.StickerResponse.StickerItem;


public interface StickerQueryService {
    StickerItem getStickerById(Long id);
    StickerItem saveSticker(Sticker sticker);
}