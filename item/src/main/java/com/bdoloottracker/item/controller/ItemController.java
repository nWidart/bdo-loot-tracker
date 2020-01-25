package com.bdoloottracker.item.controller;

import com.bdoloottracker.item.entity.Item;
import com.bdoloottracker.item.request.CreateItemRequest;
import com.bdoloottracker.item.request.UpdateItemRequest;
import com.bdoloottracker.item.service.ItemService;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/item")
public class ItemController {

  private ItemService itemService;

  public ItemController(ItemService itemService) {
    this.itemService = itemService;
  }

  @GetMapping("/items")
  public List<Item> index() {
    return itemService.all();
  }

  @GetMapping("/items/{itemId}")
  public Optional<Item> show(@PathVariable Long itemId) {
    return itemService.findById(itemId);
  }

  @PostMapping("/items")
  @ResponseStatus(code = HttpStatus.CREATED)
  public Item create(@RequestBody CreateItemRequest request) {
    return itemService.create(request);
  }

  @PatchMapping("/items/{idemId}")
  @ResponseStatus(code = HttpStatus.OK)
  public Item update(@RequestBody UpdateItemRequest request, @PathVariable Long idemId) {
    return itemService.update(idemId, request);
  }

  @DeleteMapping("/items/{itemId}")
  @ResponseStatus(code = HttpStatus.OK)
  public boolean delete(@PathVariable Long itemId) {
    return itemService.delete(itemId);
  }
}
