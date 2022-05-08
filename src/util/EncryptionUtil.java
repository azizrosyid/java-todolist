package util;

import java.security.MessageDigest;

public class EncryptionUtil {
    public static String encryptPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            byte[] byteData = md.digest();

            StringBuilder builder = new StringBuilder();
            for (byte b : byteData) {
                if ((b & 0xff) < 0x10) {
                    builder.append("0");
                }
                builder.append(Long.toString(b & 0xff, 16));
            }
            return builder.toString();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
