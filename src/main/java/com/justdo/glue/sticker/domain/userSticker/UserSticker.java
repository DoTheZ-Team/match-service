package com.justdo.glue.sticker.domain.userSticker;

import com.justdo.glue.sticker.domain.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class UserSticker extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "BIGINT")
    private Long userId;

    @Column(nullable = false, columnDefinition = "BIGINT")
    private Long stickerId;
}