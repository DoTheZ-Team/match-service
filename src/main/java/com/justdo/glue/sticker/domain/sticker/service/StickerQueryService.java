package com.justdo.glue.sticker.domain.sticker.service;

import com.justdo.glue.sticker.domain.sticker.Sticker;
import com.justdo.glue.sticker.domain.sticker.repository.StickerRepository;
import com.justdo.glue.sticker.global.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.justdo.glue.sticker.domain.sticker.dto.StickerResponse.*;
import static com.justdo.glue.sticker.global.response.code.status.ErrorStatus._STICKER_NOT_FOUND;
import static com.justdo.glue.sticker.global.response.code.status.ErrorStatus._STICKER_NOT_SAVED;


//TODO: 서버에 스티커 생성 결과 저장로직 구현해야함.
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StickerQueryService {

    private final StickerRepository stickerRepository;

    public StickerItem getStickerById(Long id) {
        Sticker sticker = stickerRepository.findById(id)
                .orElseThrow(() -> new ApiException(_STICKER_NOT_FOUND));

        return toSticker(sticker.getId(), sticker.getUrl());
    }

    public StickerItem saveSticker(Sticker sticker) {
        Sticker savedSticker = stickerRepository.save(sticker);

        if(savedSticker == null){
            throw new ApiException(_STICKER_NOT_SAVED);
        }

        return toSticker(savedSticker.getId(), savedSticker.getUrl());
    }
}
