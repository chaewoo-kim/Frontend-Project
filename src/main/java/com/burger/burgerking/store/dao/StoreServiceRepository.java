package com.burger.burgerking.store.dao;

import com.burger.burgerking.store.domain.StoreService;
import com.burger.burgerking.store.domain.StoreServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StoreServiceRepository extends JpaRepository<StoreService,Long> {
    List<StoreService> findByStoreId(Long storeId);

    @Query("""
                select ss
                from StoreService ss
                where ss.storeId in :storeIds
            """)
    List<StoreService> findByStoreIds(@Param("storeIds") List<Long> storeIds);

    @Query("""
                SELECT ss.storeId
                FROM StoreService ss
                WHERE ss.serviceType IN :types
                GROUP BY ss.storeId
                HAVING COUNT(DISTINCT ss.serviceType) = :size
            """)
    List<Long> findStoreIdsByAllServiceTypes(
            @Param("types") List<StoreServiceType> types,
            @Param("size") long size
    );
}
