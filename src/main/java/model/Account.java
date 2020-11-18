package model;

import encryption.EncryptedPassword;

/**
 * Accounts are used for authentication
 * One Account gets initialized on Login
 */
public class Account {

    private final long id;
    private final String username;
    private final String role;
    private final EncryptedPassword password;

    public Account(String userName, String password, String role, long id, boolean encryptFirstTime) {
        this.id = id;
        this.username = userName;
        this.role = role;
        this.password = new EncryptedPassword(password, encryptFirstTime);
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public String getEncryptedPassword() {
        return password.getEncrypted();
    }

    public boolean login(String password) {
        return this.password.is(password);
    }
}