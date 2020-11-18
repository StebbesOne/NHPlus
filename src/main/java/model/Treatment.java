package model;

import utils.DateConverter;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Treatments are linked to a patient and a caregiver
 */
public class Treatment {
    private long tid;
    private final long pid;
    private final long cid;
    private Caregiver caregiver;
    private LocalDate date;
    private LocalTime begin;
    private LocalTime end;
    private String description;
    private String remarks;
    private final String caregiverData;

    /**
     * @param pid
     * @param cid
     * @param date
     * @param begin
     * @param end
     * @param description
     * @param remarks
     */
    public Treatment(long pid, long cid, LocalDate date, LocalTime begin,
                     LocalTime end, String description, String remarks, Caregiver caregiver) {
        this.pid = pid;
        this.cid = cid;
        this.date = date;
        this.begin = begin;
        this.end = end;
        this.description = description;
        this.remarks = remarks;
        this.caregiver = caregiver;
        this.caregiverData = initCaregiverData();
    }

    /**
     * @param tid
     * @param pid
     * @param cid
     * @param date
     * @param begin
     * @param end
     * @param description
     * @param remarks
     */
    public Treatment(long tid, long pid, long cid, LocalDate date, LocalTime begin,
                     LocalTime end, String description, String remarks, Caregiver caregiver) {
        this.tid = tid;
        this.pid = pid;
        this.cid = cid;
        this.date = date;
        this.date = date;
        this.begin = begin;
        this.end = end;
        this.description = description;
        this.remarks = remarks;
        this.caregiver = caregiver;
        this.caregiverData = initCaregiverData();
    }

    private String initCaregiverData() {
        return this.caregiver.getFirstName() + " " + this.caregiver.getSurname() + " " + this.caregiver.getPhoneNumber();
    }

    public long getTid() {
        return tid;
    }

    public long getPid() {
        return this.pid;
    }

    public String getDate() {
        return date.toString();
    }

    public void setDate(String s_date) {
        LocalDate date = DateConverter.convertStringToLocalDate(s_date);
        this.date = date;
    }

    public String getBegin() {
        return begin.toString();
    }

    public void setBegin(String begin) {
        LocalTime time = DateConverter.convertStringToLocalTime(begin);
        this.begin = time;
    }

    public String getEnd() {
        return end.toString();
    }

    public void setEnd(String end) {
        LocalTime time = DateConverter.convertStringToLocalTime(end);
        this.end = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String toString() {
        return "\nBehandlung" + "\nTID: " + this.tid +
                "\nPID: " + this.pid +
                "\nDate: " + this.date +
                "\nBegin: " + this.begin +
                "\nEnd: " + this.end +
                "\nDescription: " + this.description +
                "\nRemarks: " + this.remarks + "\n";
    }

    public long getCid() {
        return cid;
    }


    public Caregiver getCaregiver() {
        return caregiver;
    }

    public void setCaregiver(Caregiver caregiver) {
        this.caregiver = caregiver;
    }

    public String getCaregiverData() {
        return caregiverData;
    }
}