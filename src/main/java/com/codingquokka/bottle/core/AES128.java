package com.codingquokka.bottle.core;

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
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";

    public String decrypt(String str, String type) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        SecretKeySpec secretKeySpec = null;
        IvParameterSpec ivParameterSpec = null;
        if (type.equals("common")) {
            secretKeySpec = new SecretKeySpec(MessageUtils.getMessage("aes.key").getBytes(), "AES");
            ivParameterSpec = new IvParameterSpec(MessageUtils.getMessage("aes.iv").getBytes(StandardCharsets.UTF_8));
        }
        else if (type.equals("login")) {
            secretKeySpec = new SecretKeySpec(MessageUtils.getMessage("aes.login.key").getBytes(), "AES");
            ivParameterSpec = new IvParameterSpec(MessageUtils.getMessage("aes.login.iv").getBytes(StandardCharsets.UTF_8));
        }

        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

        return new String(cipher.doFinal(Base64.getDecoder().decode(str)), "UTF-8");
    }

    public String encrypt(String str, String type) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {

        SecretKeySpec secretKeySpec = null;
        IvParameterSpec ivParameterSpec = null;
        if (type.equals("common")) {
            secretKeySpec = new SecretKeySpec(MessageUtils.getMessage("aes.key").getBytes(), "AES");
            ivParameterSpec = new IvParameterSpec(MessageUtils.getMessage("aes.iv").getBytes(StandardCharsets.UTF_8));
        }
        else if (type.equals("login")) {
            secretKeySpec = new SecretKeySpec(MessageUtils.getMessage("aes.login.key").getBytes(), "AES");
            ivParameterSpec = new IvParameterSpec(MessageUtils.getMessage("aes.login.iv").getBytes(StandardCharsets.UTF_8));
        }
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

        byte[] encryptedBytes = cipher.doFinal(str.getBytes(StandardCharsets.UTF_8));

        return Base64.getEncoder().encodeToString(encryptedBytes);
    }
}
