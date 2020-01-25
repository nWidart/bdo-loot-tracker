package com.bdoloottracker.item.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.bdoloottracker.item.BaseMysqlContext;
import com.bdoloottracker.item.entity.Item;
import com.bdoloottracker.item.request.CreateItemRequest;
import com.bdoloottracker.item.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Disabled
@WithMockUser(roles = {"ADMIN"})
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ItemControllerTest extends BaseMysqlContext {

  @Autowired
  TestRestTemplate restTemplate;
  @Autowired
  ItemService itemService;

  @BeforeEach
  void setUp() {
    itemService.create(CreateItemRequest.builder().name("Test Item").build());
  }

  @Test
  void something() {
    ResponseEntity<Item[]> entity = restTemplate.getForEntity("/api/item/items", Item[].class);
    Item[] body = entity.getBody();

    assertEquals(HttpStatus.OK, entity.getStatusCode());
  }
}
