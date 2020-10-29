package state;

import model.Account;

public class State {
    private static Role role;

    private State() {
    }

    public static void setRole(Account a) {
        role = new Role(a.getRole());
    }

    public static Role getRole() {
        return role;
    }

}
