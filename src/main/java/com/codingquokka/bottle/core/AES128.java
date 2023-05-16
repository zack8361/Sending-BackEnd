package com.codingquokka.bottle.core;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.util.Base64;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

@Component
public class AES128 {

    private String secretKey;
    private String iv;

    private String ips;
    private Key keySpec;
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";

    public AES128() {
        this.secretKey = MessageUtils.getMessage("aes.key");
        this.iv = MessageUtils.getMessage("aes.iv");
        try {
            byte[] keyBytes = new byte[16];
            byte[] b = secretKey.getBytes("UTF-8");
            System.arraycopy(b, 0, keyBytes, 0, keyBytes.length);
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
            this.ips = secretKey.substring(0, 16);
            this.keySpec = keySpec;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String decrypt(String str) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));

        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

        return new String(cipher.doFinal(Base64.getDecoder().decode(str)), "UTF-8");
    }
}
