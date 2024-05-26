package com.justdo.glue.sticker.domain.userSticker.repository;

import com.justdo.glue.sticker.domain.userSticker.UserSticker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserStickerRepository extends JpaRepository<UserSticker, Long> {
    List<UserSticker> findByUserId(Long userId);
    Page<UserSticker> findByUserId(Long userId, Pageable pageable);
    Optional<UserSticker> findByStickerIdAndUserId(Long stickerId, Long userId);

}