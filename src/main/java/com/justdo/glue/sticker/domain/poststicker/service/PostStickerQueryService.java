package com.justdo.glue.sticker.domain.poststicker.service;

import com.justdo.glue.sticker.domain.poststicker.dto.PostStickerDTO;
import com.justdo.glue.sticker.domain.poststicker.dto.PostStickerDTO.PostStickerItem;
import java.util.List;

public interface PostStickerQueryService {

    PostStickerDTO.PostStickerItem getPostStickerById(Long id);

    List<PostStickerItem> getPostStickersByPostId(Long postId);
}