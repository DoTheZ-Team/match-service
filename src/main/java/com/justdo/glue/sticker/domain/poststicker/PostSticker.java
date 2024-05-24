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

    @Column(nullable = false, columnDefinition = "INT")
    private int xLocation;

    @Column(nullable = false, columnDefinition = "INT")
    private int yLocation;

    @Column(nullable = false, columnDefinition = "INT")
    private int width;

    @Column(nullable = false, columnDefinition = "INT")
    private int height;

    @Column(nullable = false, columnDefinition = "INT")
    private int angle;
}
