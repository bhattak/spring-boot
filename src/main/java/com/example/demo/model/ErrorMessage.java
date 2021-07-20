package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
public class ErrorMessage {

    @NotEmpty
    private String message;

    @NotEmpty
    private  String details;

    @NotEmpty
    private HttpStatus status;

    @NotEmpty
    @Temporal(TemporalType.TIME)
    private Date time;
}
