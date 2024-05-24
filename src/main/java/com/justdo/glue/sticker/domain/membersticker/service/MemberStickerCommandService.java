package com.justdo.glue.sticker.domain.membersticker.service;

import com.justdo.glue.sticker.domain.membersticker.dto.MemberStickerResponse;

public interface MemberStickerCommandService {
    MemberStickerResponse.MemberStickerItem saveMemberSticker(Long stickerId, Long memberId);
    void deleteMemberSticker(Long stickerId, Long memberId);
}
