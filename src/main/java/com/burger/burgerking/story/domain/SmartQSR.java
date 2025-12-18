package com.burger.burgerking.story.domain;

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
public class SmartQSR {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String title;

    @Column(unique = true, nullable = false)
    private String subTitle;

    @Column(unique = true, nullable = false)
    private String imageId;

    @Column(unique = true, nullable = false, columnDefinition = "TEXT")
    private String description;

    @Builder
    public SmartQSR(String title, String subTitle, String imageId, String description) {
        this.title = title;
        this.subTitle = subTitle;
        this.imageId = imageId;
        this.description = description;
    }
}
