package com.serviceops.assetdiscovery.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class PasswordEncoderSSH {
    private static final Logger logger = LoggerFactory.getLogger(PasswordEncoderSSH.class);
    private static final String ENCRYPTION_ALGORITHM = "AES";
    private static final String ENCODING = "UTF-8";
    private static final byte[] SECURITY_KEY =
            hexStringToByteArray("3272357538782F413F4428472B4B6250655367566B5970337336763979244226");

    private PasswordEncoderSSH() {
    }

    public static String encryptPassword(String password) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(SECURITY_KEY, ENCRYPTION_ALGORITHM);
            Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] encryptedPassword = cipher.doFinal(password.getBytes(ENCODING));
            return Base64.getEncoder().encodeToString(encryptedPassword);
        } catch (Exception e) {
            logger.error("Failed to encrypt password");
            return null;
        }
    }

    public static String decryptPassword(String encryptedPassword) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(SECURITY_KEY, ENCRYPTION_ALGORITHM);
            Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] decryptedPassword = cipher.doFinal(Base64.getDecoder().decode(encryptedPassword));
            return new String(decryptedPassword, ENCODING);
        } catch (Exception e) {
            logger.error("Failed to decrypt password");
            return null;
        }
    }

    private static byte[] hexStringToByteArray(String hexString) {
        int len = hexString.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4) + Character.digit(
                    hexString.charAt(i + 1), 16));
        }
        return data;
    }
}
