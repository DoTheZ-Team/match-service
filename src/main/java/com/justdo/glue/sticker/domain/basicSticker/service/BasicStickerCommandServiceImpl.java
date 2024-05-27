package com.justdo.glue.sticker.domain.basicSticker.service;

import com.justdo.glue.sticker.domain.basicSticker.BasicSticker;
import com.justdo.glue.sticker.domain.basicSticker.repository.BasicStickerRepository;
import com.justdo.glue.sticker.domain.basicSticker.dto.BasicStickerDTO;
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
public class BasicStickerCommandServiceImpl implements BasicStickerCommandService {
}
