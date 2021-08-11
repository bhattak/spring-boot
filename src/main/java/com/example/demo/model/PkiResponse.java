package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PkiResponse {
    private String encryptedMessage;
    private String signature;
}
