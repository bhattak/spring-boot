package com.example.demo.controller;

import com.example.demo.util.AESEncryption;
import com.example.demo.util.RSAEncryption;
import com.example.demo.util.RandomGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

@Slf4j
@RestController
@RequestMapping(value = "/encrypt")
public class EncryptionController {
    String publicKey ="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCtuzhItwoiGiNX1AWnqUFJFsGV\n" +
            "Z4DIAEcIua7XPGWfp3HWvW/TlHNru5dWKNpxTCwLSZ7fE+5lPt1ZVtPER535bKbT\n" +
            "OaOnbDZHyyJ8ja6Tnb3djjKPXoKjmHKv3Y/4wBrYVeBYeVIdoWE8h3T4LXYjndCK\n" +
            "AlVL5RyGbsx2BmqJJwIDAQAB";
    @GetMapping
    public String doEncryptData() throws NoSuchAlgorithmException, InvalidKeyException {
        String secret = "1235";
        String message = "Hello";

        Mac sha256 = Mac.getInstance("HmacSHA256");
        SecretKeySpec sKey = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
        sha256.init(sKey);

        String hash = Base64.encodeBase64String(sha256.doFinal(message.getBytes()));
        log.info(hash);
        return hash;
    }

    /*
        Generate hash
    */
    @GetMapping("/hmac")
    public String encryptHMACSHA1() throws java.security.SignatureException {
        String result;
        String plaintext = "Alexa";
        String key = "a1b2bc3d4";
        try {
            log.info("Generating hash");
            SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(plaintext.getBytes());
            result = new Base64().encodeToString(rawHmac);
        } catch (Exception e) {
            throw new SignatureException("Failed to generate HMAC : " + e.getMessage());
        }
        log.info("Generated hash {}", result);
        return result;
    }

    /*
        Verify the hash
    */
    @GetMapping("/verify")
    public String verifyHash(@RequestParam("hash") String inputHash,
                             @RequestParam("key") String sKey,
                             @RequestParam("message") String message) {
        String outputHash;
        log.info("Beginning verification process");
        log.info("{} : {} : {}", inputHash, sKey, message);
        try {
            log.info("Inside verify");
            SecretKeySpec signingKey = new SecretKeySpec(sKey.getBytes(), "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(message.getBytes());
            outputHash = new Base64().encodeToString(rawHmac);
            if (outputHash.equals(inputHash)) {
                log.info("Hash verified successfully");
                return "Hash is Verified";
            } else {
                log.info("Hash is not verified ");
                return "Not Verified";
            }

        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
            log.info("Error occurred {}", e);
        }
        return "Default false";
    }

    /*
        Hashing is irreversible mechanism ,once you create hash it cannot be undone or t cant be converted back to
        original form.
        The recipient takes all the needed input and
        computes the HMAC on his own side and checks if the result is equal to the value on the message
        he got.
        So, to verify the message authenticity and integrity, the receiver  generates hash using message
        and secret key and compares with the received hash.
        And if hash matches the message the sender and message is verified.
     */

}
