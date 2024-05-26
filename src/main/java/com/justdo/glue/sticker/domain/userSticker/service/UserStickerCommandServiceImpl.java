package com.justdo.glue.sticker.domain.userSticker.service;

import com.justdo.glue.sticker.domain.userSticker.UserSticker;
import com.justdo.glue.sticker.domain.userSticker.dto.UserStickerResponse;
import com.justdo.glue.sticker.domain.userSticker.repository.UserStickerRepository;
import com.justdo.glue.sticker.global.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

import static com.justdo.glue.sticker.domain.userSticker.dto.UserStickerResponse.toUserStickerItem;
import static com.justdo.glue.sticker.global.response.code.status.ErrorStatus._USER_STICKER_NOT_DELETED;
import static com.justdo.glue.sticker.global.response.code.status.ErrorStatus._USER_STICKER_NOT_SAVED;

@Service
@RequiredArgsConstructor
@Transactional
public class UserStickerCommandServiceImpl implements UserStickerCommandService {
    private final UserStickerRepository userStickerRepository;

    @Override
    @Transactional
    public UserStickerResponse.UserStickerItem saveUserSticker(Long stickerId, Long userId) {
        UserSticker userSticker = UserSticker.builder()
                .userId(userId)
                .stickerId(stickerId)
                .build();

        UserSticker savedUserSticker = Optional.of(userStickerRepository.save(userSticker))
                .orElseThrow(() -> new ApiException(_USER_STICKER_NOT_SAVED));

        return toUserStickerItem(savedUserSticker.getId(), savedUserSticker.getUserId(), savedUserSticker.getStickerId());
    }

    @Override
    @Transactional
    public void deleteUserSticker(Long stickerId, Long userId) {
        UserSticker userSticker = userStickerRepository.findByStickerIdAndUserId(stickerId, userId)
                .orElseThrow(() -> new ApiException(_USER_STICKER_NOT_DELETED));
        try {
            userStickerRepository.delete(userSticker);
        } catch (Exception e) {
            throw new ApiException(_USER_STICKER_NOT_DELETED);
        }
    }
}
