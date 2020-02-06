package com.bdoloottracker.item.controller;

import com.bdoloottracker.item.entity.LootTable;
import com.bdoloottracker.item.request.CreateLootTableRequest;
import com.bdoloottracker.item.request.UpdateLootTableRequest;
import com.bdoloottracker.item.service.LootTableService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/loot-table")
public class LootTableController {

  private LootTableService lootTableService;

  public LootTableController(LootTableService lootTableService) {
    this.lootTableService = lootTableService;
  }

  @GetMapping("/loot-tables")
  public List<LootTable> index(@RequestParam(value = "spotId", required = false) Long spotId) {
    if (spotId != null) {
      return lootTableService.allForSpot(spotId);
    }
    return lootTableService.all();
  }

  @GetMapping("/loot-tables/{lootTableId}")
  public Optional<LootTable> show(@PathVariable Long lootTableId) {
    return lootTableService.findById(lootTableId);
  }

  @PostMapping("/loot-tables")
  @ResponseStatus(code = HttpStatus.OK)
  public LootTable create(@RequestBody CreateLootTableRequest request) {
    return lootTableService.create(request);
  }

  @PatchMapping("/loot-tables/{lootTableId}")
  @ResponseStatus(code = HttpStatus.OK)
  public LootTable update(@RequestBody UpdateLootTableRequest request, @PathVariable Long lootTableId) {
    return lootTableService.update(lootTableId, request);
  }

  @DeleteMapping("/loot-tables/{lootTableId}")
  @ResponseStatus(code = HttpStatus.OK)
  public boolean delete(@PathVariable Long lootTableId) {
    return lootTableService.delete(lootTableId);
  }
}
