package com.justdo.glue.sticker.domain.poststicker.service;

import com.justdo.glue.sticker.domain.poststicker.dto.PostStickerDTO;

public interface PostStickerCommandService {
    PostStickerDTO.PostStickerItem BuildPostSticker(Long postId, Long stickerId, Double xLocation, Double yLocation, Double width, Double height, Double angle);
}