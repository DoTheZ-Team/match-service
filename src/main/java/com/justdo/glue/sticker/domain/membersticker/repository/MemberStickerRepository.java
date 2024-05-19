package com.justdo.glue.sticker.domain.membersticker.repository;

import com.justdo.glue.sticker.domain.membersticker.MemberSticker;
import com.justdo.glue.sticker.domain.poststicker.PostSticker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberStickerRepository extends JpaRepository<MemberSticker, Long> {
    Optional<MemberSticker> findById(Long id);
}