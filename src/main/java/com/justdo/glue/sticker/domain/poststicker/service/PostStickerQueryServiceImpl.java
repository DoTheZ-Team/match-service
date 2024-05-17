package com.justdo.glue.sticker.domain.poststicker.service;

import com.justdo.glue.sticker.domain.poststicker.PostSticker;
import com.justdo.glue.sticker.domain.poststicker.dto.PostStickerResponse;
import com.justdo.glue.sticker.domain.poststicker.repository.PostStickerRepository;
import com.justdo.glue.sticker.global.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.justdo.glue.sticker.domain.poststicker.dto.PostStickerResponse.toPostStickerItem;
import static com.justdo.glue.sticker.domain.sticker.dto.StickerResponse.toStickerItem;
import static com.justdo.glue.sticker.global.response.code.status.ErrorStatus.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostStickerQueryServiceImpl implements PostStickerQueryService{

    private final PostStickerRepository postStickerRepository;

    @Override
    public PostStickerResponse.PostStickerItem getPostStickerById(Long id) {
        PostSticker postSticker = postStickerRepository.findById(id)
                .orElseThrow(() -> new ApiException(_STICKER_POST_NOT_FOUND));

        return toPostStickerItem(postSticker.getId(), postSticker.getPostId(), postSticker.getStickerId(), postSticker.getXLocation(), postSticker.getYLocation(), postSticker.getWidth(), postSticker.getHeight(), postSticker.getAngle());
    }

    @Override
    @Transactional
    public PostStickerResponse.PostStickerItem savePostSticker(PostSticker postSticker) {
        PostSticker savedPostSticker = Optional.ofNullable(postStickerRepository.save(postSticker))
                .orElseThrow(() -> new ApiException(_STICKER_POST_NOT_SAVED));

        return toPostStickerItem(postSticker.getId(), postSticker.getPostId(), postSticker.getStickerId(), postSticker.getXLocation(), postSticker.getYLocation(), postSticker.getWidth(), postSticker.getHeight(), postSticker.getAngle());
    }
}