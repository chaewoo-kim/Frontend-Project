package com.burger.burgerking.store.dao;

import com.burger.burgerking.store.domain.ImageType;
import com.burger.burgerking.store.domain.StoreImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StoreImageRepository extends JpaRepository<StoreImage,Long> {
    List<StoreImage> findByStoreIdAndImageTypeOrderBySortOrderAsc(
            Long storeId, ImageType imageType
    );

        @Query("""
        SELECT si.storeId, si.imageUrl
        FROM StoreImage si
        WHERE si.storeId IN :storeIds
          AND si.imageType = 'THUMBNAIL'
    """)
        List<Object[]> findThumbnailTuplesByStoreIds(@Param("storeIds") List<Long> storeIds);
    }
