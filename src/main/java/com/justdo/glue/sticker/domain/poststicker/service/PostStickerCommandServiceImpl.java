package com.justdo.glue.sticker.domain.poststicker.service;

import com.justdo.glue.sticker.domain.poststicker.PostSticker;
import com.justdo.glue.sticker.domain.poststicker.dto.PostStickerDTO;
import com.justdo.glue.sticker.domain.poststicker.repository.PostStickerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostStickerCommandServiceImpl implements PostStickerCommandService{
    private final PostStickerRepository postStickerRepository;
    private final PostStickerQueryServiceImpl postStickerQueryService;
    @Override
    public PostStickerDTO.PostStickerItem BuildPostSticker(Long postId, Long stickerId, Double xLocation, Double yLocation, Double width, Double height, Double angle) {
        PostSticker newPostSticker = PostSticker.builder()
                .postId(postId)
                .stickerId(stickerId)
                .xLocation(xLocation)
                .yLocation(yLocation)
                .width(width)
                .height(height)
                .angle(angle)
                .build();

        PostStickerDTO.PostStickerItem savedPostSticker = postStickerQueryService.savePostSticker(newPostSticker);
        return PostStickerDTO.toPostStickerItem(savedPostSticker.getPostStickerId(), savedPostSticker.getPostId(), savedPostSticker.getStickerId(), savedPostSticker.getXLocation(), savedPostSticker.getYLocation(), savedPostSticker.getWidth(), savedPostSticker.getHeight(), savedPostSticker.getAngle());
    }
}