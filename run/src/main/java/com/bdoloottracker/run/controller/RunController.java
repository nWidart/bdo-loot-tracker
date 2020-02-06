package com.bdoloottracker.run.controller;

import com.bdoloottracker.run.request.CreateRunRequest;
import com.bdoloottracker.run.service.RunService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/run")
public class RunController {

  private final RunService runService;

  public RunController(RunService runService) {
    this.runService = runService;
  }

  @PostMapping("runs")
  @ResponseStatus(code = HttpStatus.CREATED)
  public void create(@RequestBody CreateRunRequest request, @RequestHeader("Authorization") String jwt) {
    this.runService.createRun(request, jwt);
  }
}
