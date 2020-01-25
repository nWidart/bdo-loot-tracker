package com.bdoloottracker.item.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.bdoloottracker.item.BaseMysqlContext;
import com.bdoloottracker.item.entity.Item;
import com.bdoloottracker.item.request.CreateItemRequest;
import com.bdoloottracker.item.request.UpdateItemRequest;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ItemServiceTest extends BaseMysqlContext {

  @Autowired
  ItemService itemService;

  @Test
  @Transactional
  void itCanCreateAnItem() {
    Item testItem = itemService.create(CreateItemRequest.builder().name("Test Item").isTaxable(true).build());

    Optional<Item> item = itemService.findById(testItem.getId());

    assertTrue(item.isPresent());
    assertEquals("Test Item", item.get().getName());
  }

  @Test
  @Transactional
  void itCanFindAllItems() {
    itemService.create(CreateItemRequest.builder().name("Test Item").isTaxable(true).build());
    List<Item> all = itemService.all();

    assertEquals(1, all.size());
  }

  @Test
  @Transactional
  void testItCanUpdateAnItem() {
    Item testItem = itemService.create(CreateItemRequest.builder().name("Test Item").isTaxable(true).build());

    itemService.update(testItem.getId(), UpdateItemRequest.builder().name("Changed Test Item").build());
    Optional<Item> found = itemService.findById(testItem.getId());

    assertEquals("Changed Test Item", found.get().getName());
  }

  @Test
  @Transactional
  void itCanDeleteAnItem() {
    itemService.create(CreateItemRequest.builder().name("Test Item One").isTaxable(true).build());
    Item testItem = itemService.create(CreateItemRequest.builder().name("Test Item Two").isTaxable(true).build());

    itemService.delete(testItem.getId());

    assertEquals(1, itemService.all().size());
  }
}
