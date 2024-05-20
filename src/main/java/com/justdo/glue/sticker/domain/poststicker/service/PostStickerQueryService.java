package com.justdo.glue.sticker.domain.poststicker.service;

import com.justdo.glue.sticker.domain.poststicker.PostSticker;
import com.justdo.glue.sticker.domain.poststicker.dto.PostStickerDTO;
import org.springframework.data.domain.Page;

public interface PostStickerQueryService {
    PostStickerDTO.PostStickerItem getPostStickerById(Long id);
    PostStickerDTO.PostStickerItem savePostSticker(PostSticker postSticker);
    Page<PostStickerDTO.PostStickerItem> getPostStickersByPostId(Long postId, int page, int size);
}