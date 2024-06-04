package com.justdo.glue.sticker.domain.basicSticker.service;

import com.justdo.glue.sticker.domain.basicSticker.BasicSticker;
import com.justdo.glue.sticker.domain.basicSticker.repository.BasicStickerRepository;
import com.justdo.glue.sticker.domain.basicSticker.dto.BasicStickerDTO.*;
import com.justdo.glue.sticker.domain.common.CustomPage;
import com.justdo.glue.sticker.domain.sticker.dto.StickerResponse;
import com.justdo.glue.sticker.domain.sticker.service.StickerQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.justdo.glue.sticker.domain.basicSticker.dto.BasicStickerDTO.toBasicStickerItems;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BasicStickerQueryServiceImpl implements BasicStickerQueryService {
    private final BasicStickerRepository basicStickerRepository;
    private final StickerQueryService StickerQueryService;

    @Override
    public CustomPage<BasicSticker, BasicStickerItems> getBasicStickersPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<BasicSticker> basicStickerPage = basicStickerRepository.findAll(pageable);

        if (basicStickerPage.isEmpty()) {
            return new CustomPage<>(Page.empty(pageable), null);  // 빈 페이지 반환
        }

        List<Long> stickerIds = basicStickerPage.stream()
                .map(BasicSticker::getStickerId)
                .collect(Collectors.toList());

        List<StickerResponse.StickerItem> stickerItems = stickerIds.stream()
                .map(StickerQueryService::getStickerById)
                .collect(Collectors.toList());

        BasicStickerItems basicStickerItems = toBasicStickerItems(stickerItems);

        return new CustomPage<>(basicStickerPage, basicStickerItems);
    }
}