package com.burger.burgerking.story.domain;

import com.burger.burgerking.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdVideo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String title;

    @Column(unique = true, nullable = false)
    private Long videoId;

    @Column(unique = true, nullable = false)
    private Long thumbnailId;

    @Builder
    public AdVideo(String title, Long videoId, Long thumbnailId) {
        this.title = title;
        this.videoId = videoId;
        this.thumbnailId = thumbnailId;
    }
}
