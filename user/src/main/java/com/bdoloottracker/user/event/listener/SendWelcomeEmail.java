package com.bdoloottracker.user.event.listener;

import com.bdoloottracker.user.event.OnUserRegistrationCompletedEvent;
import com.bdoloottracker.user.exception.MailSendException;
import com.bdoloottracker.user.service.MailService;
import freemarker.template.TemplateException;
import java.io.IOException;
import javax.mail.MessagingException;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class SendWelcomeEmail implements ApplicationListener<OnUserRegistrationCompletedEvent> {

  private final MailService mailService;

  public SendWelcomeEmail(MailService mailService) {
    this.mailService = mailService;
  }

  @Override
  @Async
  public void onApplicationEvent(OnUserRegistrationCompletedEvent event) {
    sendEmailVerification(event);
  }

  private void sendEmailVerification(OnUserRegistrationCompletedEvent event) {
    String recipientAddress = event.getUser().getEmail();
    try {
      this.mailService.sendEmailVerification("123", recipientAddress);
    } catch (IOException | TemplateException | MessagingException e) {
      throw new MailSendException(recipientAddress, "Email Verification");
    }
  }
}
