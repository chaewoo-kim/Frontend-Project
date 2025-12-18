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
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String title;

    @Column(unique = true, nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(unique = true)
    private Long imageId;

    @Builder
    public Brand(String title, String description, Long imageId) {
        this.title = title;
        this.description = description;
        this.imageId = imageId;
    }
}
