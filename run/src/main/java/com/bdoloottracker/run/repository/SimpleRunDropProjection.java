package com.bdoloottracker.run.repository;

import java.math.BigDecimal;

public interface SimpleRunDropProjection {

  Long getId();

  Long getItemId();

  Integer getTotal();

  BigDecimal getCurrentPrice();
}
