package com.justdo.glue.sticker.domain.userSticker.service;

import com.justdo.glue.sticker.domain.common.CustomPage;
import com.justdo.glue.sticker.domain.userSticker.dto.UserStickerResponse;
import org.springframework.data.domain.Page;

public interface UserStickerQueryService {
    UserStickerResponse.UserStickerItems getStickersByUserId(Long userId);
    CustomPage<UserStickerResponse.UserStickerItems> getPageStickersByUserId(Long userId, int page, int size);
}
