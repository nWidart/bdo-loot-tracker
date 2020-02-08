package com.bdoloottracker.run.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Session not found")
public class SessionNotFoundException extends RuntimeException {

}
