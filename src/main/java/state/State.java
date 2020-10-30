package state;

import model.Account;

/**
 * class for global data
 */
public class State {

    private static Role role;

    /**
     * private constructor, so the class never gets instantiated
     */
    private State() {
    }

    /**
     * gets the role of the currently logged in account
     *
     * @return role of account
     */
    public static Role getRole() {
        return role;
    }

    /**
     * sets the role based on the account role
     *
     * @param account account with role that will be applied to the state
     */
    public static void setRole(Account account) {
        role = new Role(account.getRole());
    }

}
