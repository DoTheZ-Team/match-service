package com.justdo.glue.sticker.domain.poststicker.service;

import static com.justdo.glue.sticker.domain.poststicker.dto.PostStickerDTO.PostStickerItem;
import static com.justdo.glue.sticker.domain.poststicker.dto.PostStickerDTO.toPostStickerItem;
import static com.justdo.glue.sticker.global.response.code.status.ErrorStatus._STICKER_POST_NOT_FOUND;

import com.justdo.glue.sticker.domain.poststicker.PostSticker;
import com.justdo.glue.sticker.domain.poststicker.repository.PostStickerRepository;
import com.justdo.glue.sticker.domain.sticker.dto.StickerResponse.StickerItem;
import com.justdo.glue.sticker.domain.sticker.service.StickerQueryService;
import com.justdo.glue.sticker.global.exception.ApiException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostStickerQueryServiceImpl implements PostStickerQueryService {

    private final PostStickerRepository postStickerRepository;
    private final StickerQueryService stickerQueryService;

    @Override
    public PostStickerItem getPostStickerById(Long id) {

        PostSticker postSticker = postStickerRepository.findById(id)
                .orElseThrow(() -> new ApiException(_STICKER_POST_NOT_FOUND));

        return toPostStickerItem(postSticker, null);
    }

    @Override
    public List<PostStickerItem> getPostStickersByPostId(Long postId) {

        Optional<List<PostSticker>> postStickersOptional = postStickerRepository.findByPostId(
                postId);

        if (postStickersOptional.isEmpty()) {
            throw new ApiException(_STICKER_POST_NOT_FOUND);
        }

        List<PostSticker> postStickers = postStickersOptional.get(); // Optional에서 리스트를 추출

        return postStickers.stream()
                .map(postSticker -> {
                    StickerItem stickerItem = stickerQueryService.getStickerById(
                            postSticker.getStickerId());
                    return toPostStickerItem(
                            postSticker, stickerItem.getUrl()
                    );
                })
                .collect(Collectors.toList());
    }
}