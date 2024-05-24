package com.justdo.glue.sticker.domain.membersticker.service;

import com.justdo.glue.sticker.domain.membersticker.MemberSticker;
import com.justdo.glue.sticker.domain.membersticker.dto.MemberStickerResponse;
import com.justdo.glue.sticker.domain.membersticker.repository.MemberStickerRepository;
import com.justdo.glue.sticker.global.exception.ApiException;
import com.justdo.glue.sticker.global.response.code.status.ErrorStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.justdo.glue.sticker.domain.membersticker.dto.MemberStickerResponse.toMemberStickerItem;
import static com.justdo.glue.sticker.global.response.code.status.ErrorStatus._MEMBER_STICKER_NOT_DELETED;
import static com.justdo.glue.sticker.global.response.code.status.ErrorStatus._MEMBER_STICKER_NOT_SAVED;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberStickerCommandServiceImpl implements MemberStickerCommandService {
    private final MemberStickerRepository memberStickerRepository;

    @Override
    @Transactional
    public MemberStickerResponse.MemberStickerItem saveMemberSticker(Long stickerId, Long memberId) {
        MemberSticker memberSticker = MemberSticker.builder()
                .memberId(memberId)
                .stickerId(stickerId)
                .build();

        MemberSticker savedMemberSticker = Optional.of(memberStickerRepository.save(memberSticker))
                .orElseThrow(() -> new ApiException(_MEMBER_STICKER_NOT_SAVED));

        return toMemberStickerItem(savedMemberSticker.getId(), savedMemberSticker.getMemberId(), savedMemberSticker.getStickerId());
    }

    @Override
    @Transactional
    public void deleteMemberSticker(Long stickerId, Long memberId) {
        MemberSticker memberSticker = memberStickerRepository.findByStickerIdAndMemberId(stickerId, memberId)
                .orElseThrow(() -> new ApiException(_MEMBER_STICKER_NOT_DELETED));
        try {
            memberStickerRepository.delete(memberSticker);
        } catch (Exception e) {
            throw new ApiException(_MEMBER_STICKER_NOT_DELETED);
        }
    }
}
