package com.justdo.glue.sticker.domain.userSticker.service;

import com.justdo.glue.sticker.domain.common.CustomPage;
import com.justdo.glue.sticker.domain.userSticker.UserSticker;
import com.justdo.glue.sticker.domain.userSticker.dto.UserStickerResponse;
import com.justdo.glue.sticker.domain.userSticker.repository.UserStickerRepository;
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

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserStickerQueryServiceImpl implements UserStickerQueryService {
    private final UserStickerRepository userStickerRepository;
    private final StickerQueryService stickerQueryService;

    public UserStickerResponse.UserStickerItems getStickersByUserId(Long userId) {
        List<UserSticker> userStickers = userStickerRepository.findByUserId(userId);

        if (userStickers.isEmpty()) {
            return UserStickerResponse.toUserStickerItems(userId, Collections.emptyList());
        }

        List<Long> stickerIds = userStickers.stream()
                .map(UserSticker::getStickerId)
                .collect(Collectors.toList());

        List<StickerResponse.StickerItem> stickers = stickerIds.stream()
                .map(stickerQueryService::getStickerById)
                .collect(Collectors.toList());

        return UserStickerResponse.toUserStickerItems(userId, stickers);
    }

    @Override
    public CustomPage<UserSticker, UserStickerResponse.UserStickerItems> getPageStickersByUserId(Long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UserSticker> userStickerPage = userStickerRepository.findByUserId(userId, pageable);

        if (userStickerPage.isEmpty()) {
            return new CustomPage<>(Page.empty(pageable), null);  // 빈 페이지 반환
        }

        List<Long> stickerIds = userStickerPage.stream()
                .map(UserSticker::getStickerId)
                .collect(Collectors.toList());

        List<StickerResponse.StickerItem> stickerItems = stickerIds.stream()
                .map(stickerQueryService::getStickerById)
                .collect(Collectors.toList());

        UserStickerResponse.UserStickerItems userStickerItems = UserStickerResponse.toUserStickerItems(userId, stickerItems);

        return new CustomPage<>(userStickerPage, userStickerItems);
    }
}
