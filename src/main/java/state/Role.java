package state;

public class Role {

    private final String role;

    public Role(String role) {
        this.role = role;
    }

    /**
     * checks if role equals a specific string
     *
     * @param toCompare string to compare
     * @return if it equals the role
     */
    private boolean is(String toCompare) {
        return role.trim().equals(toCompare.trim());
    }

    /**
     * checks if the role is ADMIN
     *
     * @return if the role is ADMIN
     */
    public boolean isAdmin() {
        return is("ADMIN");
    }
}
