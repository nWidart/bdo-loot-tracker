package com.bdoloottracker.run.service;

import com.bdoloottracker.run.client.ItemServiceClient;
import com.bdoloottracker.run.client.PriceServiceClient;
import com.bdoloottracker.run.dto.ItemPrice;
import com.bdoloottracker.run.dto.LootTableProjection;
import com.bdoloottracker.run.dto.RunProjection;
import com.bdoloottracker.run.entity.Run;
import com.bdoloottracker.run.entity.RunDrop;
import com.bdoloottracker.run.entity.Session;
import com.bdoloottracker.run.exception.RunNotFoundException;
import com.bdoloottracker.run.exception.SessionNotFoundException;
import com.bdoloottracker.run.repository.RunDropRepository;
import com.bdoloottracker.run.repository.RunRepository;
import com.bdoloottracker.run.repository.SessionRepository;
import com.bdoloottracker.run.repository.SimpleRunDropProjection;
import com.bdoloottracker.run.request.CreateRunRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RunService {

  private final RunRepository runRepository;
  private final RunDropRepository runDropRepository;
  private final PriceServiceClient priceServiceClient;
  private final SessionRepository sessionRepository;
  private final ItemServiceClient itemServiceClient;

  public RunService(RunRepository runRepository, RunDropRepository runDropRepository,
      PriceServiceClient priceServiceClient, SessionRepository sessionRepository, ItemServiceClient itemServiceClient) {
    this.runRepository = runRepository;
    this.runDropRepository = runDropRepository;
    this.priceServiceClient = priceServiceClient;
    this.sessionRepository = sessionRepository;
    this.itemServiceClient = itemServiceClient;
  }

  @Transactional
  public void createRun(CreateRunRequest request, String jwt) {
    Run run = new Run();
    run.setSessionId(request.getSessionId());
    run.setComment(request.getComment());
    Run persistedRun = runRepository.save(run);

    List<RunDrop> runDropMap = new ArrayList<>();
    request.getItemDrops().forEach(itemDropMap -> {
      RunDrop runDrop = new RunDrop();
      runDrop.setItemId(itemDropMap.getItemId());
      runDrop.setTotal(itemDropMap.getTotal());
      runDrop.setRun(persistedRun);
      ItemPrice itemPrice = priceServiceClient.getPriceForItem("eu", itemDropMap.getItemId().toString(), jwt);
      runDrop.setCurrentPrice(BigDecimal.valueOf(itemPrice.getPricePerOne()));
      runDropMap.add(runDrop);
    });
    runDropRepository.saveAll(runDropMap);
  }

  public RunProjection findRun(Long runId, String jwt) {
    Run run = runRepository.findById(runId).orElseThrow(RunNotFoundException::new);
    Session session = sessionRepository.findById(run.getSessionId()).orElseThrow(SessionNotFoundException::new);
    List<SimpleRunDropProjection> runDrops = runDropRepository.findAllByRun(run);
    LootTableProjection projection = itemServiceClient.getLootTableInfoForSpot(session.getSpotId().toString(), jwt);
    RunProjection runProjection = new RunProjection();
    runProjection.setRunId(run.getId());
    runProjection.setComment(run.getComment());
    runProjection.setCreatedAt(run.getCreatedAt());
    runProjection.setRunDrops(this.hydrateItemNamesTo(projection, runDrops));
    return runProjection;
  }

  private List<RunProjection.RunDrop> hydrateItemNamesTo(LootTableProjection projection,
      List<SimpleRunDropProjection> runDrops) {
    List<RunProjection.RunDrop> runDropList = new ArrayList<>();
    runDrops.forEach(proj -> {
      runDropList.add(new RunProjection.RunDrop(proj.getCurrentPrice(), proj.getTotal(),
          this.findItemNameById(projection, proj.getItemId()), proj.getItemId()));
    });
    return runDropList;
  }

  private String findItemNameById(LootTableProjection projection, Long itemId) {
    return projection.getItems().stream()
        .filter(item -> item.getItemId().equals(itemId))
        .findFirst()
        .orElseThrow()
        .getName();
  }
}
