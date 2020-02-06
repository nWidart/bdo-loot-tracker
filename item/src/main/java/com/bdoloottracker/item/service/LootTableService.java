package com.bdoloottracker.item.service;

import static com.bdoloottracker.item.utils.BeanUtils.copyNonNullProperties;

import com.bdoloottracker.item.entity.Item;
import com.bdoloottracker.item.entity.LootTable;
import com.bdoloottracker.item.entity.Spot;
import com.bdoloottracker.item.repository.ItemRepository;
import com.bdoloottracker.item.repository.LootTableRepository;
import com.bdoloottracker.item.repository.SpotRepository;
import com.bdoloottracker.item.request.CreateLootTableRequest;
import com.bdoloottracker.item.request.UpdateLootTableRequest;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class LootTableService {

  private final ItemRepository itemRepository;
  private final SpotRepository spotRepository;
  private LootTableRepository lootTableRepository;

  public LootTableService(LootTableRepository lootTableRepository, ItemRepository itemRepository,
      SpotRepository spotRepository) {
    this.lootTableRepository = lootTableRepository;
    this.itemRepository = itemRepository;
    this.spotRepository = spotRepository;
  }

  public List<LootTable> all() {
    return lootTableRepository.findAll();
  }

  public Optional<LootTable> findById(Long lootTableId) {
    return lootTableRepository.findById(lootTableId);
  }

  public LootTable create(CreateLootTableRequest request) {
    Optional<Item> item = itemRepository.findById(request.getItemId());
    Optional<Spot> spot = spotRepository.findById(request.getItemId());
    if (item.isEmpty() || spot.isEmpty()) {
      throw new RuntimeException("something");
    }
    return lootTableRepository.save(LootTable.builder().item(item.get()).spot(spot.get()).build());
  }

  public LootTable update(Long lootTableId, UpdateLootTableRequest request) {
    Optional<Item> item = itemRepository.findById(request.getItemId());
    Optional<Spot> spot = spotRepository.findById(request.getItemId());
    if (item.isEmpty() || spot.isEmpty()) {
      throw new RuntimeException("something");
    }
    LootTable updatedSpotTable = LootTable.builder().item(item.get()).spot(spot.get()).build();

    return this.findById(lootTableId).map(lootTable -> {
      copyNonNullProperties(updatedSpotTable, lootTable);
      lootTableRepository.save(lootTable);
      return lootTable;
    }).orElseThrow();
  }

  public boolean delete(Long lootTableId) {
    return this.findById(lootTableId).map(lootTable -> {
      lootTableRepository.delete(lootTable);
      return true;
    }).orElse(false);
  }

  public List<LootTable> allForSpot(Long spotId) {
    return lootTableRepository.findAllBySpotId(spotId);
  }
}
