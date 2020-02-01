package com.bdoloottracker.run.request;

import com.bdoloottracker.run.entity.Session;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateSessionRequest {

  @NotNull(message = "userId is required")
  private Integer userId;
  @NotNull(message = "spotId is required")
  private Integer spotId;
  private LocalDateTime createdAt;
  private LocalDateTime stoppedAt;
  private String config;
  private int ap;
  private int dp;
  private String bdoClass;

  public Session toModel() {
    return Session.builder()
        .userId(this.userId)
        .spotId(this.spotId)
        .createdAt(this.createdAt)
        .config(this.config)
        .ap(this.ap)
        .dp(this.dp)
        .bdoClass(this.bdoClass)
        .build();
  }
}
