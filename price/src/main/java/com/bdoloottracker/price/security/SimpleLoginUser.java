package com.bdoloottracker.price.security;

import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

public class SimpleLoginUser extends org.springframework.security.core.userdetails.User {

  private static final List<GrantedAuthority> USER_ROLES = AuthorityUtils.createAuthorityList("ROLE_USER");
  private static final List<GrantedAuthority> ADMIN_ROLES = AuthorityUtils
      .createAuthorityList("ROLE_ADMIN", "ROLE_USER");
  private User user;

  public SimpleLoginUser(User user) {
    super(user.getName(), user.getPassword(), determineRoles(user.getIsAdmin()));
    this.user = user;
  }

  private static List<GrantedAuthority> determineRoles(boolean isAdmin) {
    return isAdmin ? ADMIN_ROLES : USER_ROLES;
  }

  public static UserDetails from(FindUserResponse response) {
    User user = new User(response.getId(), response.getName(), "1234", response.getEmail(), response.isAdmin());

    return new SimpleLoginUser(user);
  }

  public User getUser() {
    return user;
  }
}
