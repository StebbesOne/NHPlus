package utils;

public class EncryptedPassword {

    private String password;

    /**
     * --TODO: Bitte nicht hier hardcoden
     */
    private static final String secret = "PPSSSSSSST GEHEIM!";

    private final EncryptionMethod encryptionMethod = new AES(secret);

    public EncryptedPassword(String password) {
        this.password = password;
    }

    public boolean is(String toCompare) {
        return decrypt().equals(toCompare);
    }

    public String getEncrypted() {
        return password;
    }

    private String decrypt() {
        return encryptionMethod.decrypt(password);
    }
}
