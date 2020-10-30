package model;

import encryption.EncryptedPassword;

/**
 * Patients live in a NURSING home and are treated by nurses.
 */
public class Account {

    private final long id;
    private final String username;
    private final String role;
    private final EncryptedPassword password;

    public Account(String userName, String password, String role, long id) {
        this.id = id;
        this.username = userName;
        this.role = role;
        this.password = new EncryptedPassword(password);
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