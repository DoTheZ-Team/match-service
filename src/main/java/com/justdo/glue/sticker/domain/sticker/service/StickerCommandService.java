package com.justdo.glue.sticker.domain.sticker.service;
import com.justdo.glue.sticker.domain.sticker.dto.StickerResponse.*;

public interface StickerCommandService {
    StickerItem generateAndSaveSticker(String stickerPrompt, String stickerType);
}