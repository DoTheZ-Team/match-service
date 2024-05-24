package com.justdo.glue.sticker.domain.membersticker.repository;

import com.justdo.glue.sticker.domain.membersticker.MemberSticker;
import com.justdo.glue.sticker.domain.poststicker.PostSticker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberStickerRepository extends JpaRepository<MemberSticker, Long> {
    List<MemberSticker> findByMemberId(Long memberId);
    Page<MemberSticker> findByMemberId(Long memberId, Pageable pageable);
    Optional<MemberSticker> findByStickerIdAndMemberId(Long stickerId, Long memberId);

}