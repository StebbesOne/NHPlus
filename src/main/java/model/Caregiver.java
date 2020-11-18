package model;

/**
 * Caregiver Objects contain all business information for caregivers of the nursing home
 */
public class Caregiver extends Person {

    private long cid;
    private String phoneNumber;

    public Caregiver(long cid, String firstName, String surName, String phoneNumber) {
        super(firstName, surName);
        this.cid = cid;
        this.phoneNumber = phoneNumber;
    }

    public Caregiver(String firstName, String surName, String phoneNumber) {
        super(firstName, surName);
        this.phoneNumber = phoneNumber;
    }

    public long getCid() {
        return cid;
    }

    public void setCid(long cid) {
        this.cid = cid;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


}
