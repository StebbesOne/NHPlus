package encryption;

/**
 * extend this class to make a new way of encrypting
 * that is compatible to NHPLUS
 */
public abstract class EncryptionMethod {

    private final String secret;

    /**
     * way of encrypting strings
     *
     * @param secret seed string for public and private key generation
     */
    public EncryptionMethod(String secret) {
        this.secret = secret;
    }

    /**
     * encrypts a string
     *
     * @param toEncrypt string to be encrypted
     * @return encrypted string
     */
    public abstract String encrypt(String toEncrypt);

    /**
     * decrypts a string
     *
     * @param toDecrypt string to be decrypted
     * @return decrypted string
     */
    public abstract String decrypt(String toDecrypt);

    /**
     * returns the current secret, so you can use it when extending
     *
     * @return secret of current instance
     */
    protected String getSecret() {
        return secret;
    }
}
