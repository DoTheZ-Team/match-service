package com.justdo.glue.sticker.domain.blog;

import com.justdo.glue.sticker.domain.blog.dto.BlogRequest;
import com.justdo.glue.sticker.domain.common.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class Blog extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder.Default
    private String title = "블로그 제목을 입력해주세요.";

    private String description;

    private String profile;

    private String background;

    private Long memberId;

    /**
     * update 함수
     */
    public void update(BlogRequest request) {
        this.title = request.getTitle();
        this.description = request.getDescription();
        this.profile = request.getProfile();
        this.background = request.getBackground();
    }
}
