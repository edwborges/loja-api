package com.borges.dtos;

import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ApiErrorDto {

    private String message;
    private List<String> errors;
    private HttpStatus status;

    public ApiErrorDto(String message, String error, HttpStatus status) {
        this.message = message;
        this.errors = Arrays.asList(error);
        this.status = status;
    }
}
