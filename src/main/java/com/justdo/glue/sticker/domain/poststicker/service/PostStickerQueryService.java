package com.justdo.glue.sticker.domain.poststicker.service;

import com.justdo.glue.sticker.domain.poststicker.PostSticker;
import com.justdo.glue.sticker.domain.poststicker.dto.PostStickerResponse;
import org.springframework.data.domain.Page;

public interface PostStickerQueryService {
    PostStickerResponse.PostStickerItem getPostStickerById(Long id);
    PostStickerResponse.PostStickerItem savePostSticker(PostSticker postSticker);
    Page<PostStickerResponse.PostStickerItem> getPostStickersByPostId(Long postId, int page, int size);
}