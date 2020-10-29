package utils;

public abstract class EncryptionMethod {

    protected String secret;

    public EncryptionMethod(String secret) {
        this.secret = secret;
    }

    public abstract String encrypt(String toEncrypt);

    public abstract String decrypt(String toDecrypt);

}
