package com.bdoloottracker.item;

import org.testcontainers.containers.MySQLContainer;

public class CustomMysqlContainer extends MySQLContainer {

  @Override
  public void start() {
    super.start();
    System.setProperty("DB_URL", getJdbcUrl());
    System.setProperty("DB_USERNAME", getUsername());
    System.setProperty("DB_PASSWORD", getPassword());
  }
}
