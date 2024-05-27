package com.justdo.glue.sticker.domain.basicSticker.repository;

import com.justdo.glue.sticker.domain.basicSticker.BasicSticker;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BasicStickerRepository extends JpaRepository<BasicSticker, Long> {
    Page<BasicSticker> findAll(Pageable pageable);

}