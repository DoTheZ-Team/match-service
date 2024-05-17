package com.justdo.glue.sticker.domain.poststicker.repository;

import com.justdo.glue.sticker.domain.poststicker.PostSticker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostStickerRepository extends JpaRepository<PostSticker, Long> {
    Optional<PostSticker> findById(Long id);
}