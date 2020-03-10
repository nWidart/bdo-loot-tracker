package com.bdoloottracker.item.controller;

import com.bdoloottracker.item.entity.Spot;
import com.bdoloottracker.item.request.CreateSpotRequest;
import com.bdoloottracker.item.request.UpdateSpotRequest;
import com.bdoloottracker.item.service.SpotService;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping("/api/spot")
public class SpotController {

  private SpotService spotService;

  public SpotController(SpotService spotService) {
    this.spotService = spotService;
  }

  @GetMapping("/spots")
  public List<Spot> index() {
    return spotService.all();
  }

  @GetMapping("/spots/{spotId}")
  public Optional<Spot> show(@PathVariable Long spotId) {
    return spotService.findById(spotId);
  }

  @CrossOrigin("*")
  @PostMapping("/spots")
  @ResponseStatus(code = HttpStatus.CREATED)
  public Spot create(@RequestBody CreateSpotRequest request) {
    return spotService.create(request);
  }

  @PatchMapping("/spots/{spotId}")
  @ResponseStatus(code = HttpStatus.OK)
  public Spot update(@RequestBody UpdateSpotRequest request, @PathVariable Long spotId) {
    return spotService.update(spotId, request);
  }

  @DeleteMapping("/spots/{spotId}")
  @ResponseStatus(code = HttpStatus.OK)
  public boolean delete(@PathVariable Long spotId) {
    return spotService.delete(spotId);
  }
}
