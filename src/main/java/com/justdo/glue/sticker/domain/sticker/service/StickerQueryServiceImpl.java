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


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StickerQueryServiceImpl implements StickerQueryService {

    private final StickerRepository stickerRepository;

    @Override
    public StickerItem getStickerById(Long id) {
        Sticker sticker = stickerRepository.findById(id)
                .orElseThrow(() -> new ApiException(_STICKER_NOT_FOUND));

        return toStickerItem(sticker.getId(), sticker.getUrl(), sticker.getPrompt(), sticker.getStyle());
    }

    @Override
    @Transactional
    public StickerItem saveSticker(Sticker sticker) {
        Sticker savedSticker = Optional.ofNullable(stickerRepository.save(sticker))
                .orElseThrow(() -> new ApiException(_STICKER_NOT_SAVED));

        return toStickerItem(savedSticker.getId(), savedSticker.getUrl(), sticker.getPrompt(), sticker.getStyle());
    }
}
