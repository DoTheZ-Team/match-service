package com.justdo.glue.sticker.domain.membersticker;

import com.justdo.glue.sticker.domain.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class MemberSticker extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "BIGINT")
    private Long memberId;

    @Column(nullable = false, columnDefinition = "BIGINT")
    private Long stickerId;
}