package utils;

public class EncryptedPassword {

    private String password;
    /**
     * --TODO: Bitte nicht hier hardcoden
     */
    private static final String secret = "PPSSSSSSST GEHEIM!";

    public EncryptedPassword(String password) {
        this.password = password;
    }

    public String getDecrypted() {
        return decrypt();
    }

    public String getEncrypted() {
        return password;
    }

    private String decrypt() {
        return AES.decrypt(password, secret);
    }

    public static EncryptedPassword encrypt(String password) {
        return new EncryptedPassword(AES.encrypt(password, secret));
    }
}
