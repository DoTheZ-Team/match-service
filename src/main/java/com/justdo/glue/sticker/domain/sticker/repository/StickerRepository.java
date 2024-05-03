package com.justdo.glue.sticker.domain.sticker.repository;

import com.justdo.glue.sticker.domain.sticker.Sticker;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StickerRepository extends JpaRepository<Sticker, Long> {

    // TODO: 스티커 id 기반으로 제작한 스티커 가져오기(이부분 스티커 저장하는 로직 만들어야 될거같기도)
    Optional<Sticker> findById(Long id);
}
