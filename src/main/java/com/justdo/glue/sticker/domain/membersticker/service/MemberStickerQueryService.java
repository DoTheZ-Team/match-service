package com.justdo.glue.sticker.domain.membersticker.service;

import com.justdo.glue.sticker.domain.membersticker.MemberSticker;
import com.justdo.glue.sticker.domain.membersticker.dto.MemberStickerResponse;
import com.justdo.glue.sticker.domain.sticker.Sticker;
import org.springframework.data.domain.Page;

public interface MemberStickerQueryService {
    MemberStickerResponse.MemberStickerItems getStickersByMemberId(Long memberId);
    Page<MemberStickerResponse.MemberStickerItems> getPageStickersByMemberId(Long memberId, int page, int size);
//    MemberStickerResponse.MemberStickerItem saveMemberSticker(Long stickerId, Long memberId);
//    void deleteMemberSticker(Long stickerId, Long memberId);
}
