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
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";

    public String decrypt(String str) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
;

        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        SecretKeySpec secretKeySpec = new SecretKeySpec(MessageUtils.getMessage("aes.key").getBytes(), "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(MessageUtils.getMessage("aes.iv").getBytes(StandardCharsets.UTF_8));

        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

        return new String(cipher.doFinal(Base64.getDecoder().decode(str)), "UTF-8");
    }
}
