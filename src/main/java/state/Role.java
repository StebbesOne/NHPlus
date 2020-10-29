package state;

public class Role {

    private String role;

    public Role(String role) {
        this.role = role;
    }

    private boolean is(String toCompare) {
        return role.equals(toCompare);
    }

    public boolean isAdmin() {
        return is("ADMIN");
    }
}
