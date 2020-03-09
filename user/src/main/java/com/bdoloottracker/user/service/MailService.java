package com.bdoloottracker.user.service;

import com.bdoloottracker.user.dto.Mail;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

@Service
public class MailService {

  private final JavaMailSender mailSender;
  private final Configuration templateConfiguration;
  @Value("${app.velocity.templates.location}")
  private String basePackagePath;

  public MailService(JavaMailSender mailSender, Configuration templateConfiguration) {
    this.mailSender = mailSender;
    this.templateConfiguration = templateConfiguration;
  }

  public void sendEmailVerification(String emailVerificationUrl, String to)
      throws IOException, TemplateException, MessagingException {
    Mail mail = new Mail();
    mail.setSubject("Email Verification [Team CEP]");
    mail.setTo(to);
    mail.setFrom("n.widart@gmail.com");
    mail.getModel().put("userName", to);
    mail.getModel().put("userEmailTokenVerificationLink", emailVerificationUrl);

    templateConfiguration.setClassForTemplateLoading(getClass(), basePackagePath);
    Template template = templateConfiguration.getTemplate("email-verification.ftl");
    String mailContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, mail.getModel());
    mail.setContent(mailContent);
    send(mail);
  }

  /**
   * Sends a simple mail as a MIME Multipart message
   */
  public void send(Mail mail) throws MessagingException {
    MimeMessage message = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
        StandardCharsets.UTF_8.name());

    helper.setTo(mail.getTo());
    helper.setText(mail.getContent(), true);
    helper.setSubject(mail.getSubject());
    helper.setFrom(mail.getFrom());
    mailSender.send(message);
  }
}
