package model;

public class Caregiver extends Person {

    private long pid;
    private String phoneNumber;

    public Caregiver(long pid, String firstName, String lastName, String phoneNumber) {
        super(firstName, lastName);
        this.pid = pid;
        this.phoneNumber = phoneNumber;
    }

}
