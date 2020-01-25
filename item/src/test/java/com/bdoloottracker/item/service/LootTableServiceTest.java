package com.bdoloottracker.item.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.bdoloottracker.item.BaseMysqlContext;
import com.bdoloottracker.item.entity.Item;
import com.bdoloottracker.item.entity.LootTable;
import com.bdoloottracker.item.entity.Spot;
import com.bdoloottracker.item.request.CreateItemRequest;
import com.bdoloottracker.item.request.CreateLootTableRequest;
import com.bdoloottracker.item.request.CreateSpotRequest;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class LootTableServiceTest extends BaseMysqlContext {

  @Autowired
  LootTableService lootTableService;
  @Autowired
  ItemService itemService;
  @Autowired
  SpotService spotService;

  @Test
  @Transactional
  void itCanCreateALootTable() {
    Item item = itemService.create(CreateItemRequest.builder().name("Test Item").build());
    Spot spot = spotService.create(CreateSpotRequest.builder().name("Test Spot").build());

    lootTableService.create(CreateLootTableRequest.builder().itemId(item.getId()).spotId(spot.getId()).build());

    Optional<LootTable> found = lootTableService.findById(1L);

    assertEquals("Test Item", found.get().getItem().getName());
    assertEquals("Test Spot", found.get().getSpot().getName());
  }
}
