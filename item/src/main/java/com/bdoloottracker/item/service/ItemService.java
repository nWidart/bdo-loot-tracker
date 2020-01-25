package com.bdoloottracker.item.service;

import static com.bdoloottracker.item.utils.BeanUtils.copyNonNullProperties;

import com.bdoloottracker.item.entity.Item;
import com.bdoloottracker.item.repository.ItemRepository;
import com.bdoloottracker.item.request.CreateItemRequest;
import com.bdoloottracker.item.request.UpdateItemRequest;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

  private final ItemRepository itemRepository;

  public ItemService(ItemRepository itemRepository) {
    this.itemRepository = itemRepository;
  }

  public List<Item> all() {
    return itemRepository.findAll();
  }

  public Optional<Item> findById(Long itemId) {
    return itemRepository.findById(itemId);
  }

  public Item create(CreateItemRequest request) {
    Item item = Item.builder().name(request.getName()).isTaxable(request.isTaxable()).build();

    return itemRepository.save(item);
  }

  public Item update(Long idemId, UpdateItemRequest request) {
    return this.findById(idemId).map(item -> {
      copyNonNullProperties(request.toModel(), item);
      itemRepository.save(item);
      return item;
    }).orElseThrow();
  }

  public boolean delete(Long itemId) {
    return this.findById(itemId).map(item -> {
      itemRepository.delete(item);
      return true;
    }).orElse(false);
  }
}
