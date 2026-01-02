package com.burger.burgerking.store.dao;

import com.burger.burgerking.store.domain.ImageType;
import com.burger.burgerking.store.domain.StoreImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StoreImageRepository extends JpaRepository<StoreImage,Long> {
    List<StoreImage> findByStoreIdAndImageTypeOrderBySortOrderAsc(
            Long storeId, ImageType imageType
    );
    @Query("""
        select si.imageUrl
        from StoreImage si
        where si.storeId = :storeId
          and si.imageType = 'THUMBNAIL'
    """)
    Optional<String> findThumbnailByStoreId(@Param("storeId") Long storeId);
}
