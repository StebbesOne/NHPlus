package model;

import utils.DateConverter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Patients live in a NURSING home and are treated by nurses.
 */
public class Patient extends Person {
    private long pid;
    private LocalDate dateOfBirth;
    private String careLevel;
    private String roomnumber;
    private boolean locked;
    private final List<Treatment> allTreatments = new ArrayList<Treatment>();
    private LocalDate lockedDate;

    /**
     * constructs a patient from the given params.
     *
     * @param firstName
     * @param surname
     * @param dateOfBirth
     * @param careLevel
     * @param roomnumber
     * @param locked
     */
    public Patient(String firstName, String surname, LocalDate dateOfBirth, String careLevel, String roomnumber, boolean locked) {
        super(firstName, surname);
        this.dateOfBirth = dateOfBirth;
        this.careLevel = careLevel;
        this.roomnumber = roomnumber;
        this.locked = locked;
        if (this.locked) {
            lockedDate = LocalDate.now();
        }
    }

    /**
     * constructs a patient from the given params.
     *
     * @param pid
     * @param firstName
     * @param surname
     * @param dateOfBirth
     * @param careLevel
     * @param roomnumber
     */
    public Patient(long pid, String firstName, String surname, LocalDate dateOfBirth, String careLevel, String roomnumber, boolean locked, LocalDate lockedDate) {
        super(firstName, surname);
        this.pid = pid;
        this.dateOfBirth = dateOfBirth;
        this.careLevel = careLevel;
        this.roomnumber = roomnumber;
        this.locked = locked;
        this.lockedDate = lockedDate;
    }

    /**
     * @return patient id
     */
    public long getPid() {
        return pid;
    }

    /**
     * @return date of birth as a string
     */
    public String getDateOfBirth() {
        return dateOfBirth.toString();
    }

    /**
     * convert given param to a localDate and store as new <code>birthOfDate</code>
     *
     * @param dateOfBirth as string in the following format: YYYY-MM-DD
     */
    public void setDateOfBirth(String dateOfBirth) {
        LocalDate birthday = DateConverter.convertStringToLocalDate(dateOfBirth);
        this.dateOfBirth = birthday;
    }

    /**
     * @return careLevel
     */
    public String getCareLevel() {
        return careLevel;
    }

    /**
     * @param careLevel new care level
     */
    public void setCareLevel(String careLevel) {
        this.careLevel = careLevel;
    }

    /**
     * @return roomNumber as string
     */
    public String getRoomnumber() {
        return roomnumber;
    }

    /**
     * @param roomnumber
     */
    public void setRoomnumber(String roomnumber) {
        this.roomnumber = roomnumber;
    }

    /**
     * adds a treatment to the treatment-list, if it does not already contain it.
     *
     * @param m Treatment
     * @return true if the treatment was not already part of the list. otherwise false
     */
    public boolean add(Treatment m) {
        if (!this.allTreatments.contains(m)) {
            this.allTreatments.add(m);
            return true;
        }
        return false;
    }

    /**
     * @return string-representation of the patient
     */
    public String toString() {
        return "Patient" + "\nMNID: " + this.pid +
                "\nFirstname: " + this.getFirstName() +
                "\nSurname: " + this.getSurname() +
                "\nBirthday: " + this.dateOfBirth +
                "\nCarelevel: " + this.careLevel +
                "\nRoomnumber: " + this.roomnumber +
                "\n";
    }

    public String isLocked() {
        if (locked) {
            return "TRUE";
        }
        return "FALSE";
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    /**
     * @return
     */
    public LocalDate getLockedDate() {
        return lockedDate;
    }

    /**
     * @param lockedDate
     */
    public void setLockedDate(LocalDate lockedDate) {
        this.lockedDate = lockedDate;
    }
}