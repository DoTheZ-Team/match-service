package com.justdo.glue.sticker.domain.poststicker;

import com.justdo.glue.sticker.domain.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class PostSticker extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "BIGINT")
    private Long postId;

    @Column(nullable = false, columnDefinition = "BIGINT")
    private Long stickerId;

    @Column(nullable = false, columnDefinition = "DOUBLE")
    private Double xLocation;

    @Column(nullable = false, columnDefinition = "DOUBLE")
    private Double yLocation;

    @Column(nullable = false, columnDefinition = "DOUBLE")
    private Double width;

    @Column(nullable = false, columnDefinition = "DOUBLE")
    private Double height;

    @Column(nullable = false, columnDefinition = "DOUBLE")
    private Double angle;
}
