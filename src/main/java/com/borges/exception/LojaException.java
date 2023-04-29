package com.borges.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LojaException extends RuntimeException {

    private String msg;

    public LojaException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
