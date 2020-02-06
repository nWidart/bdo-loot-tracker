package com.bdoloottracker.run.service;

import com.bdoloottracker.run.client.PriceServiceClient;
import com.bdoloottracker.run.dto.ItemPrice;
import com.bdoloottracker.run.entity.Run;
import com.bdoloottracker.run.entity.RunDrop;
import com.bdoloottracker.run.repository.RunDropRepository;
import com.bdoloottracker.run.repository.RunRepository;
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

  public RunService(RunRepository runRepository, RunDropRepository runDropRepository,
      PriceServiceClient priceServiceClient) {
    this.runRepository = runRepository;
    this.runDropRepository = runDropRepository;
    this.priceServiceClient = priceServiceClient;
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
}
