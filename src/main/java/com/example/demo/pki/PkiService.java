package com.example.demo.pki;


import com.example.demo.model.PkiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.Base64;

@Slf4j
@Service
public class PkiService {
    @Autowired
    AsyncService asyncService;

    /*
           Have to maintain two pair of keys
           One pair for encryption/decryption
           Another for signing/verification
    */




    PrivateKey privateKeyForDecryption;
    PublicKey publicKeyForEncryption;
    PrivateKey privateKeyForSigning;
    PublicKey publicKeyForVerification;

    public PkiService() throws NoSuchAlgorithmException {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        KeyPair keyPair = kpg.generateKeyPair();
        KeyPairGenerator kpg2 = KeyPairGenerator.getInstance("RSA");
        KeyPair keyPair2 = kpg2.generateKeyPair();
        privateKeyForDecryption = keyPair.getPrivate();
        publicKeyForEncryption = keyPair.getPublic();
        privateKeyForSigning = keyPair2.getPrivate();
        publicKeyForVerification = keyPair2.getPublic();
        log.error("privateKeyForDecryption ->> {}",privateKeyForDecryption.getEncoded());
    }

    public PkiResponse generateResponse(String message) throws IllegalBlockSizeException, InvalidKeyException,
            BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException,
            SignatureException {
        String encryptedData = asyncService.encrypt(message, publicKeyForEncryption);
        String signature = asyncService.generateSignature(encryptedData.getBytes("UTF-8"),
                privateKeyForSigning);
        PkiResponse responseClass = new PkiResponse();
        responseClass.setEncryptedMessage(encryptedData);
        responseClass.setSignature(signature);
        return responseClass;
    }

    public String decodeMessage(String signature, String encryptedMessage) throws NoSuchAlgorithmException,
            SignatureException, InvalidKeyException, NoSuchPaddingException, BadPaddingException,
            IllegalBlockSizeException {
        byte[] signatureInBytes = Base64.getDecoder().decode(signature);
        boolean isVerified = asyncService.verifySignature(encryptedMessage, publicKeyForVerification,
                signatureInBytes);
        if (isVerified) {
            log.info("Verified");
            return asyncService.decrypt(encryptedMessage, privateKeyForDecryption);
        } else {
            log.info("Not Verified !!!");
        }
        return "Not Verified !!!";
    }
}
