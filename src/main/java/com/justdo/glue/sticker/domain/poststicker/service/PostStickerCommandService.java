package com.justdo.glue.sticker.domain.poststicker.service;

import com.justdo.glue.sticker.domain.poststicker.dto.PostStickerResponse;
import com.justdo.glue.sticker.domain.sticker.dto.StickerResponse;
import org.springframework.web.bind.annotation.RequestParam;

public interface PostStickerCommandService {
    PostStickerResponse.PostStickerItem BuildPostSticker(Long post_id, Long sticker_id, Double x_location, Double y_location, Double width, Double height, Double angle);
}