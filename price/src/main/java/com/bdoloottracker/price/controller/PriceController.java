package com.bdoloottracker.price.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/price")
public class PriceController {

  @GetMapping("bla")
  public String bla() {
    return "hello world";
  }
}
