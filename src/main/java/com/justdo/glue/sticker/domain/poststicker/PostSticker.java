package com.justdo.glue.sticker.domain.poststicker;

import com.justdo.glue.sticker.domain.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;


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

    @Column(nullable = false, columnDefinition = "DOUBLE")
    private double width;

    @Column(nullable = false, columnDefinition = "DOUBLE")
    private double height;

    @Column(nullable = false, columnDefinition = "DOUBLE")
    private double angle;

    public Long getId() {
        return id;
    }

    public Long getPostId() {
        return postId;
    }

    public Long getStickerId() {
        return stickerId;
    }

    public int getxLocation() {
        return xLocation;
    }

    public int getyLocation() {
        return yLocation;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getAngle() {
        return angle;
    }
}
