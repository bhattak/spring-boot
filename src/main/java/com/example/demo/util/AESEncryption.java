package com.example.demo.util;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Slf4j
public class AESEncryption {
    public static String encrypt(String strToEncrypt, SecretKey secretKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return base64Encode(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
    }

    public static String base64Encode(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    public static byte[] base64Decode(String data) {
        return Base64.getDecoder().decode(data);
    }

    public static SecretKey getSecretKey(String secretKey) {
        byte[] decodedSecretKey = base64Decode(secretKey);
        return new SecretKeySpec(decodedSecretKey,0,decodedSecretKey.length,"AES");
    }
    public static String keyToString(SecretKey secretKey) {
        byte encoded[] = secretKey.getEncoded();
        String encodedKey = base64Encode(encoded);
        return encodedKey;
    }

}
