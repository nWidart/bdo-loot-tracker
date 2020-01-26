package com.bdoloottracker.price.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemPrice {

  private Integer id;
  private String name;
  private Integer pricePerOne;
  private int count;
  private int grade;
  private int mainCategory;
  private int subCategory;
  private int totalTradeCount;
}
