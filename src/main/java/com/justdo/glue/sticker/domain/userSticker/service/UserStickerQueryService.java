package com.justdo.glue.sticker.domain.userSticker.service;

import com.justdo.glue.sticker.domain.userSticker.dto.UserStickerResponse;
import org.springframework.data.domain.Page;

public interface UserStickerQueryService {
    UserStickerResponse.UserStickerItems getStickersByUserId(Long userId);
    Page<UserStickerResponse.UserStickerItems> getPageStickersByUserId(Long userId, int page, int size);
}
