package com.justdo.glue.sticker.domain.poststicker.service;

import com.justdo.glue.sticker.domain.poststicker.PostSticker;
import com.justdo.glue.sticker.domain.poststicker.dto.PostStickerResponse;
import com.justdo.glue.sticker.domain.poststicker.repository.PostStickerRepository;
import com.justdo.glue.sticker.domain.sticker.dto.StickerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.justdo.glue.sticker.domain.sticker.dto.StickerResponse.toStickerItem;

@Service
@RequiredArgsConstructor
@Transactional
public class PostStickerCommandServiceImpl implements PostStickerCommandService{
    private final PostStickerRepository postStickerRepository;
    private final PostStickerQueryServiceImpl postStickerQueryService;
    @Override
    public PostStickerResponse.PostStickerItem BuildPostSticker(Long postId, Long stickerId, Double xLocation, Double yLocation, Double width, Double height, Double angle) {
        PostSticker newPostSticker = PostSticker.builder()
                .postId(postId)
                .stickerId(stickerId)
                .xLocation(xLocation)
                .yLocation(yLocation)
                .width(width)
                .height(height)
                .angle(angle)
                .build();

        PostStickerResponse.PostStickerItem savedPostSticker = postStickerQueryService.savePostSticker(newPostSticker);
        return PostStickerResponse.toPostStickerItem(savedPostSticker.getPostStickerId(), savedPostSticker.getPostId(), savedPostSticker.getStickerId(), savedPostSticker.getXLocation(), savedPostSticker.getYLocation(), savedPostSticker.getWidth(), savedPostSticker.getHeight(), savedPostSticker.getAngle());
    }
}