package com.bdoloottracker.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

  private String username;
  private String password;
}
