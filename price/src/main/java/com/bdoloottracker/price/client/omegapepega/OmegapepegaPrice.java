package com.bdoloottracker.price.client.omegapepega;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OmegapepegaPrice {

  @JsonProperty("mainKey")
  private Integer id;
  private int chooseKey;
  private int count;
  private int grade;
  private int keyType;
  private int mainCategory;
  private String name;
  private Integer pricePerOne;
  private int subCategory;
  private int subKey;
  private int totalTradeCount;
}
