package com.bdoloottracker.item.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.bdoloottracker.item.BaseMysqlContext;
import com.bdoloottracker.item.entity.Spot;
import com.bdoloottracker.item.request.CreateSpotRequest;
import com.bdoloottracker.item.request.UpdateSpotRequest;
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
class SpotServiceTest extends BaseMysqlContext {

  @Autowired
  SpotService spotService;

  @Test
  @Transactional
  void itCanCreateASpot() {
    spotService.create(CreateSpotRequest.builder().name("Spot One").build());

    List<Spot> all = spotService.all();

    assertEquals(1, all.size());
  }

  @Test
  @Transactional
  void itCanFindAllSpots() {
    spotService.create(CreateSpotRequest.builder().name("Spot One").build());
    spotService.create(CreateSpotRequest.builder().name("Spot Two").build());

    List<Spot> all = spotService.all();

    assertEquals(2, all.size());
  }

  @Test
  @Transactional
  void itCanUpdateASpot() {
    Spot spotOne = spotService.create(CreateSpotRequest.builder().name("Spot One").build());

    spotService.update(spotOne.getId(), UpdateSpotRequest.builder().name("Updated Spot One").build());
    Optional<Spot> found = spotService.findById(spotOne.getId());

    assertEquals("Updated Spot One", found.get().getName());
  }

  @Test
  @Transactional
  void itCanDeleteASpot() {
    spotService.create(CreateSpotRequest.builder().name("Spot One").build());
    Spot spot = spotService.create(CreateSpotRequest.builder().name("Spot Two").build());

    spotService.delete(spot.getId());

    assertEquals(1, spotService.all().size());
  }
}
