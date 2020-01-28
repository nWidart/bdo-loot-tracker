package com.bdoloottracker.run.security;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FindUserResponse {

  private Long id;
  private String name;
  private String email;
  private boolean isAdmin;
}
