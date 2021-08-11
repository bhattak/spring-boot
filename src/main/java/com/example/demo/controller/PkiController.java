package com.example.demo.controller;

import com.example.demo.model.PkiRequest;
import com.example.demo.model.PkiResponse;
import com.example.demo.pki.PkiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

@Slf4j
@RestController
@RequestMapping("/pki")
public class PkiController {
    private PkiService pkiService;

    public PkiController(PkiService pkiService) {
        this.pkiService = pkiService;
    }

    @PostMapping("/client")
    public PkiResponse sendRequest(@RequestBody PkiRequest req) throws BadPaddingException,
            NoSuchAlgorithmException, IllegalBlockSizeException, UnsupportedEncodingException,
            SignatureException, NoSuchPaddingException, InvalidKeyException {
        return pkiService.generateResponse(req.getMessage());
    }

    @GetMapping("/server")
    public String receiveMessage(@RequestBody PkiResponse body) throws NoSuchAlgorithmException,
            SignatureException, InvalidKeyException, NoSuchPaddingException, BadPaddingException,
            IllegalBlockSizeException {
        return pkiService.decodeMessage(body.getSignature(), body.getEncryptedMessage());
    }


}
