package com.bdoloottracker.item.service;

import static com.bdoloottracker.item.utils.BeanUtils.copyNonNullProperties;

import com.bdoloottracker.item.entity.Spot;
import com.bdoloottracker.item.repository.SpotRepository;
import com.bdoloottracker.item.request.CreateSpotRequest;
import com.bdoloottracker.item.request.UpdateSpotRequest;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class SpotService {

  private SpotRepository spotRepository;

  public SpotService(SpotRepository spotRepository) {
    this.spotRepository = spotRepository;
  }

  public List<Spot> all() {
    return spotRepository.findAll();
  }

  public Optional<Spot> findById(Long spotId) {
    return spotRepository.findById(spotId);
  }

  public Spot create(CreateSpotRequest request) {
    return spotRepository.save(Spot.builder().name(request.getName()).build());
  }

  public Spot update(Long spotId, UpdateSpotRequest request) {
    return this.findById(spotId).map(spot -> {
      copyNonNullProperties(request.toModel(), spot);
      spotRepository.save(spot);
      return spot;
    }).orElseThrow();
  }

  public boolean delete(Long spotId) {
    return this.findById(spotId).map(spot -> {
      spotRepository.delete(spot);
      return true;
    }).orElse(false);
  }
}
