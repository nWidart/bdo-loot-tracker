package com.bdoloottracker.run.controller;

import com.bdoloottracker.run.dto.RunProjection;
import com.bdoloottracker.run.request.CreateRunRequest;
import com.bdoloottracker.run.service.RunService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  @GetMapping("runs/{runId}")
  public RunProjection show(@PathVariable Long runId, @RequestHeader("Authorization") String jwt) {
    return this.runService.findRun(runId, jwt);
  }

  @GetMapping("runs/by-session/{sessionId}")
  public List<RunProjection> findBySession(@PathVariable Long sessionId, @RequestHeader("Authorization") String jwt) {
    return this.runService.findAllBySession(sessionId, jwt);
  }
}
