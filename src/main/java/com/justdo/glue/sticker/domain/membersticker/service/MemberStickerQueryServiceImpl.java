package com.justdo.glue.sticker.domain.membersticker.service;

import com.justdo.glue.sticker.domain.membersticker.MemberSticker;
import com.justdo.glue.sticker.domain.membersticker.dto.MemberStickerResponse;
import com.justdo.glue.sticker.domain.membersticker.repository.MemberStickerRepository;
import com.justdo.glue.sticker.domain.sticker.dto.StickerResponse;
import com.justdo.glue.sticker.domain.sticker.service.StickerQueryService;
import com.justdo.glue.sticker.global.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.justdo.glue.sticker.domain.membersticker.dto.MemberStickerResponse.toMemberStickerItem;
import static com.justdo.glue.sticker.global.response.code.status.ErrorStatus.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberStickerQueryServiceImpl implements MemberStickerQueryService {
    private final MemberStickerRepository memberStickerRepository;
    private final StickerQueryService stickerQueryService;

    public MemberStickerResponse.MemberStickerItems getStickersByMemberId(Long memberId) {
        List<MemberSticker> memberStickers = memberStickerRepository.findByMemberId(memberId);

        if (memberStickers.isEmpty()) {
            return MemberStickerResponse.toMemberStickerItems(memberId, Collections.emptyList());
        }

        List<Long> stickerIds = memberStickers.stream()
                .map(MemberSticker::getStickerId)
                .collect(Collectors.toList());

        List<StickerResponse.StickerItem> stickers = stickerIds.stream()
                .map(stickerQueryService::getStickerById)
                .collect(Collectors.toList());

        return MemberStickerResponse.toMemberStickerItems(memberId, stickers);
    }

    @Override
    public Page<MemberStickerResponse.MemberStickerItems> getPageStickersByMemberId(Long memberId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<MemberSticker> memberStickerPage = memberStickerRepository.findByMemberId(memberId, pageable);

        if (memberStickerPage.isEmpty()) {
            return Page.empty(pageable);  // 빈 페이지 반환
        }

        List<Long> stickerIds = memberStickerPage.stream()
                .map(MemberSticker::getStickerId)
                .collect(Collectors.toList());

        List<StickerResponse.StickerItem> stickerItems = stickerIds.stream()
                .map(stickerQueryService::getStickerById)
                .collect(Collectors.toList());

        MemberStickerResponse.MemberStickerItems memberStickerItems = MemberStickerResponse.toMemberStickerItems(memberId, stickerItems);

        List<MemberStickerResponse.MemberStickerItems> memberStickerItemsList = List.of(memberStickerItems);

        return new PageImpl<>(memberStickerItemsList, pageable, memberStickerPage.getTotalElements());
    }

//    @Override
//    @Transactional
//    public MemberStickerResponse.MemberStickerItem saveMemberSticker(Long stickerId, Long memberId) {
//        MemberSticker memberSticker = MemberSticker.builder()
//                .memberId(memberId)
//                .stickerId(stickerId)
//                .build();
//
//        MemberSticker savedMemberSticker = Optional.of(memberStickerRepository.save(memberSticker))
//                .orElseThrow(() -> new ApiException(_MEMBER_STICKER_NOT_SAVED));
//
//        return toMemberStickerItem(savedMemberSticker.getId(), savedMemberSticker.getMemberId(), savedMemberSticker.getStickerId());
//    }
//
//    @Override
//    @Transactional
//    public void deleteMemberSticker(Long stickerId, Long memberId) {
//        MemberSticker memberSticker = memberStickerRepository.findByStickerIdAndMemberId(stickerId, memberId)
//                .orElseThrow(() -> new ApiException(_MEMBER_STICKER_NOT_DELETED));
//        try {
//            memberStickerRepository.delete(memberSticker);
//        } catch (Exception e) {
//            throw new ApiException(_MEMBER_STICKER_NOT_DELETED);
//        }
//    }
}
