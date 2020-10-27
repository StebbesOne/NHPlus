package utils;

public class EncryptedPassword {

    private String password;

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
        return password;
    }
}
