package encryption;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

/**
 * Implements the Interface <code>EncryptionMethod</code>. Overrides methods to generate specific encryption.
 */
public class AES extends EncryptionMethod {

    private SecretKeySpec secretKey;
    private byte[] key;

    /**
     * @param secret seed string for public and private key generation
     */
    public AES(String secret) {
        super(secret);
    }

    /**
     * encrypts a string based on AES encryption
     *
     * @param toEncrypt string to be encrypted
     * @return encrypted string
     */
    @Override
    public String encrypt(String toEncrypt) {
        try {
            setKey(getSecret());
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(toEncrypt.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }


    /**
     * decrypts a string based on AES encryption
     *
     * @param toDecrypt string to be decrypted
     * @return encrypted string
     */
    @Override
    public String decrypt(String toDecrypt) {
        try {
            setKey(getSecret());
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(toDecrypt)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }

    /**
     * generates a key from secret and sets it to the key variable
     *
     * @param myKey secret
     */
    private void setKey(String myKey) {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes(StandardCharsets.UTF_8);
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

}
