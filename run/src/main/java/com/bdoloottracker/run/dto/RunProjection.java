package com.bdoloottracker.run.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class RunProjection {

  private Long runId;
  private LocalDateTime createdAt;
  private String comment;
  private List<RunDrop> runDrops;
  private BigDecimal totalSilver = new BigDecimal(0);
  private BigDecimal totalAfterTax;

  public void setRunDrops(List<RunDrop> runDrops) {
    this.runDrops = runDrops;
    runDrops.forEach(runDrop -> {
      BigDecimal multiplicand = BigDecimal.valueOf(runDrop.getTotal());
      this.totalSilver = this.totalSilver.add(runDrop.getCurrentPrice().multiply(multiplicand));
    });
    this.totalAfterTax = this.totalSilver.multiply(BigDecimal.valueOf(0.845));
  }

  @Data
  @AllArgsConstructor
  public static class RunDrop {

    private BigDecimal currentPrice;
    private Integer total;
    private String itemName;
    private Long itemId;
  }
}
