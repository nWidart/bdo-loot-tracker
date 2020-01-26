package com.bdoloottracker.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

  private static final long serialVersionUID = -2315659388348422700L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "name", nullable = false, length = 128)
  private String name;
  @JsonIgnore
  @Column(name = "password", nullable = false, length = 255)
  private String password;
  @Column(name = "email", nullable = false, unique = true, length = 255)
  private String email;
  @Column(name = "is_admin", nullable = false)
  private Boolean isAdmin;

  public static User of(String name, String password, String email) {
    return User.builder().name(name).password(password).email(email).isAdmin(false).build();
  }
}
