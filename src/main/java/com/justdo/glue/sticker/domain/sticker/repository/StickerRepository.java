package com.justdo.glue.sticker.domain.sticker.repository;

import com.justdo.glue.sticker.domain.sticker.Sticker;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StickerRepository extends JpaRepository<Sticker, Long> {
    Optional<Sticker> findById(Long id);
}
