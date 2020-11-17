package encryption;

public class EncryptedPassword {

    /**
     * --TODO: please don't hardcode the secret here
     */
    private static final String secret = "PPSSSSSSST GEHEIM!";
    private final String password;
    private final EncryptionMethod encryptionMethod = new AES(secret);

    /**
     * encrypts and holds a password
     *
     * @param password unencrypted password
     */
    public EncryptedPassword(String password, boolean encrypt) {
        this.password = encrypt ? encryptionMethod.encrypt(password) : password;
    }

    /**
     * compares an unencrypted string to the unencrypted version of the password, held by the class
     *
     * @param toCompare unencrypted password
     * @return if it equals
     */
    public boolean is(String toCompare) {
        return decrypt().equals(toCompare);
    }

    /**
     * gets the encrypted password held by the class
     *
     * @return encrypted password
     */
    public String getEncrypted() {
        return password;
    }

    /**
     * decrypts tje current password
     *
     * @return current password decrypted
     */
    private String decrypt() {
        return encryptionMethod.decrypt(password);
    }
}
