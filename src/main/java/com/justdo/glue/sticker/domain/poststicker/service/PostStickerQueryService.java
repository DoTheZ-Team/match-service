package com.justdo.glue.sticker.domain.poststicker.service;

import com.justdo.glue.sticker.domain.poststicker.PostSticker;
import com.justdo.glue.sticker.domain.poststicker.dto.PostStickerResponse;

public interface PostStickerQueryService {
    PostStickerResponse.PostStickerItem getPostStickerById(Long id);
    PostStickerResponse.PostStickerItem savePostSticker(PostSticker postSticker);
}