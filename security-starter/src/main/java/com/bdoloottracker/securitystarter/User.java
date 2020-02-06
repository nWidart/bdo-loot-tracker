package com.bdoloottracker.securitystarter;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

  private static final long serialVersionUID = -2315659388348422700L;


  private Long id;
  private String name;
  private String password;
  private String email;
  private Boolean isAdmin;

  public static User of(String name, String password, String email) {
    return User.builder().name(name).password(password).email(email).isAdmin(false).build();
  }
}
