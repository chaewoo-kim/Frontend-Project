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
        select count(ss)
        from StoreService ss
        where ss.storeId = :storeId
          and ss.serviceType in :types
    """)
    long countByStoreIdAndServiceTypeIn(
            @Param("storeId") Long storeId,
            @Param("types") List<StoreServiceType> types
    );
}