package com.example.demo.pki;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Slf4j
@Service
public class AsyncService {

    public String encrypt(String message, PublicKey publicKey) throws NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedDataInBytes = cipher.doFinal(message.getBytes());
        String encryptedDataInString = Base64.getEncoder().encodeToString(encryptedDataInBytes);
        return encryptedDataInString;
    }

    public String decrypt(String message, PrivateKey privateKey) throws NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(cipher.doFinal(Base64.getDecoder().decode(message)));
    }

    public String generateSignature(byte[] data, PrivateKey privateKey) throws
            SignatureException, InvalidKeyException, NoSuchAlgorithmException {
        log.info("Private Key: ",Base64.getEncoder().encodeToString(privateKey.getEncoded()));
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(data);
        byte[] signatureBytes = signature.sign();
        return Base64.getEncoder().encodeToString(signatureBytes);
    }
    public boolean verifySignature(String data, PublicKey publicKey, byte[] signature) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        Signature publicSignature = Signature.getInstance("SHA256withRSA");
        publicSignature.initVerify(publicKey);
        publicSignature.update(data.getBytes());
        return publicSignature.verify(signature);
    }

    public String decrypt(String message, String privateKey) throws NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException {
        log.warn("1. Private Key : {}", privateKey);
        byte[] pvtKeyInByte = Base64.getDecoder().decode(privateKey);
        PKCS8EncodedKeySpec pk = new PKCS8EncodedKeySpec(pvtKeyInByte);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        log.error("2. Decrypting message");
        PrivateKey pvtKey = keyFactory.generatePrivate(pk);
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, pvtKey);
        return new String(cipher.doFinal(Base64.getDecoder().decode(message)));
    }

    public PrivateKey getPrivateKey(String base64PrivateKey) {
        try {
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(base64PrivateKey.getBytes()));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
            return privateKey;
        } catch (Exception e) {
            return null;
        }
    }

//    public ResponseClass generateSignature(@RequestBody RequestClass body) throws IllegalBlockSizeException, InvalidKeyException,
//            BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, SignatureException, UnsupportedEncodingException {
//        String data = encrypt(body.getMessage(), publicKeyForEncryption);
//        String signature = generateSignature(data.getBytes("UTF-8"), privateKeyForSigning);
//        log.info("Signature : {}", signature);
//        ResponseClass responseClass = new ResponseClass();
//        responseClass.setEncryptedMessage(data);
//        responseClass.setSignature(signature);
//        return responseClass;
//    }
}
