package com.bdoloottracker.user.controller.advice;

import com.bdoloottracker.user.dto.ApiResponse;
import com.bdoloottracker.user.exception.MailSendException;
import com.bdoloottracker.user.exception.ResourceAlreadyInUseException;
import com.bdoloottracker.user.exception.UserRegistrationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ControllerAdvice {

  @ExceptionHandler(value = ResourceAlreadyInUseException.class)
  @ResponseStatus(HttpStatus.IM_USED)
  @ResponseBody
  public ApiResponse handleResourceAlreadyInUseException(ResourceAlreadyInUseException ex, WebRequest request) {
    return new ApiResponse(false, ex.getMessage(), ex.getClass().getName(), resolvePathFromWebRequest(request));
  }

  @ExceptionHandler(value = UserRegistrationException.class)
  @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
  @ResponseBody
  public ApiResponse handleUserRegistrationException(UserRegistrationException ex, WebRequest request) {
    return new ApiResponse(false, ex.getMessage(), ex.getClass().getName(), resolvePathFromWebRequest(request));
  }

  @ExceptionHandler(value = MailSendException.class)
  @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
  @ResponseBody
  public ApiResponse handleMailSendException(MailSendException ex, WebRequest request) {
    return new ApiResponse(false, ex.getMessage(), ex.getClass().getName(), resolvePathFromWebRequest(request));
  }

  private String resolvePathFromWebRequest(WebRequest request) {
    try {
      return ((ServletWebRequest) request).getRequest().getAttribute("javax.servlet.forward.request_uri").toString();
    } catch (Exception ex) {
      return null;
    }
  }
}
