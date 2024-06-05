package com.justdo.glue.sticker.domain.poststicker.service;

import com.justdo.glue.sticker.domain.poststicker.PostSticker;
import com.justdo.glue.sticker.domain.poststicker.dto.PostStickerDTO;
import com.justdo.glue.sticker.domain.poststicker.dto.PostStickerDTO.PostStickerProc;
import com.justdo.glue.sticker.domain.poststicker.dto.PostStickerRequest;
import com.justdo.glue.sticker.domain.poststicker.repository.PostStickerRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostStickerCommandServiceImpl implements PostStickerCommandService {

    private final PostStickerRepository postStickerRepository;

    @Override
    public void saveAllPostSticker(List<PostStickerRequest> postStickerRequests) {

        List<PostSticker> postStickers = postStickerRequests.stream()
                .map(PostStickerRequest::toEntity)
                .toList();

        postStickerRepository.saveAll(postStickers);
    }

    @Override
    public PostStickerProc savePostSticker(PostStickerRequest postStickerRequest) {

        PostSticker newPostSticker = PostStickerRequest.toEntity(postStickerRequest);

        PostSticker save = postStickerRepository.save(newPostSticker);

        return PostStickerDTO.toPostStickerProc(save.getId());
    }
}