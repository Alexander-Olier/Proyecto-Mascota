package com.mascota.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class exception extends RuntimeException{
        public exception() {
            super();
        }

        public exception(String message) {
            super(message);
        }

        public exception(String message, Throwable cause) {
            super(message, cause);
        }

}
