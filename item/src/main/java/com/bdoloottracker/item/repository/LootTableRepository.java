package com.bdoloottracker.item.repository;

import com.bdoloottracker.item.entity.LootTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LootTableRepository extends JpaRepository<LootTable, Long> {

}
