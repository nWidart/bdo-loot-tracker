package com.bdoloottracker.user.event;

import com.bdoloottracker.user.entity.User;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class OnUserRegistrationCompletedEvent extends ApplicationEvent {

  private final User user;

  public OnUserRegistrationCompletedEvent(User user) {
    super(user);
    this.user = user;
  }
}
