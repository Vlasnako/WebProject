package com.vlasnako.hoaxify.user_package;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@Deprecated
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserNotValidException extends RuntimeException{

}
