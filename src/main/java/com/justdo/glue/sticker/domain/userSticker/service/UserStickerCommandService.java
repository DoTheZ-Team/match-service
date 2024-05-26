package com.justdo.glue.sticker.domain.userSticker.service;

import com.justdo.glue.sticker.domain.userSticker.dto.UserStickerResponse;

public interface UserStickerCommandService {
    UserStickerResponse.UserStickerItem saveUserSticker(Long stickerId, Long userId);
    void deleteUserSticker(Long stickerId, Long userId);
}
