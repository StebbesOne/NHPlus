package datastorage;

import model.Patient;
import state.State;
import utils.DateConverter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements the Interface <code>DAOImp</code>. Overrides methods to generate specific patient-SQL-queries.
 */
public class PatientDAO extends DAOimp<Patient> {

    /**
     * constructs Object. Calls the Constructor from <code>DAOImp</code> to store the connection.
     *
     * @param conn SQL Connection
     */
    public PatientDAO(Connection conn) {
        super(conn);
    }

    /**
     * generates a <code>INSERT INTO</code>-Statement for a given patient
     *
     * @param patient for which a specific INSERT INTO is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getCreateStatementString(Patient patient) {
        return String.format("INSERT INTO patient (firstname, surname, dateOfBirth, carelevel, roomnumber) VALUES ('%s', '%s', '%s', '%s', '%s')",
                patient.getFirstName(), patient.getSurname(), patient.getDateOfBirth(), patient.getCareLevel(), patient.getRoomnumber());
    }

    /**
     * generates a <code>select</code>-Statement for a given key
     *
     * @param key for which a specific SELECTis to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getReadByIDStatementString(int key) {
        return String.format("SELECT * FROM patient WHERE pid = %d", key);
    }

    /**
     * maps a <code>ResultSet</code> to a <code>Patient</code>
     *
     * @param result ResultSet with a single row. Columns will be mapped to a patient-object.
     * @return patient with the data from the resultSet.
     */
    @Override
    protected Patient getInstanceFromResultSet(ResultSet result) throws SQLException {
        Patient p = null;
        p = getPatient(result);
        return p;
    }

    /**
     * extracted method for mapping data to patient
     * @param result SQL resulst set
     * @return initialized patient model
     * @throws SQLException
     */
    private Patient getPatient(ResultSet result) throws SQLException {
        Patient p;
        LocalDate date = DateConverter.convertStringToLocalDate(result.getString(4));
        LocalDate lockedDate = null;
        if (result.getString(8) != null) {
            lockedDate = DateConverter.convertStringToLocalDate(result.getString(8));
        }
        p = new Patient(result.getInt(1), result.getString(2),
                result.getString(3), date, result.getString(5),
                result.getString(6), result.getBoolean(7), lockedDate);
        return p;
    }

    /**
     * generates a <code>SELECT</code>-Statement for all patients.
     *
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getReadAllStatementString() {
        String statement = "SELECT * FROM patient";
        if (!State.getRole().isAdmin()) {
            statement += " WHERE locked = FALSE";
        }
        return statement;
    }

    /**
     * maps a <code>ResultSet</code> to a <code>Patient-List</code>
     *
     * @param result ResultSet with a multiple rows. Data will be mapped to patient-object.
     * @return ArrayList with patients from the resultSet.
     */
    @Override
    protected ArrayList<Patient> getListFromResultSet(ResultSet result) throws SQLException {
        ArrayList<Patient> list = new ArrayList<Patient>();
        Patient p = null;
        while (result.next()) {
            p = getPatient(result);
            list.add(p);
        }
        return list;
    }

    /**
     * generates a <code>UPDATE</code>-Statement for a given patient
     *
     * @param patient for which a specific update is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getUpdateStatementString(Patient patient) {
        return String.format("UPDATE patient SET firstname = '%s', surname = '%s', dateOfBirth = '%s', carelevel = '%s', " +
                        "roomnumber = '%s', locked = '%s' WHERE pid = %d", patient.getFirstName(), patient.getSurname(), patient.getDateOfBirth(),
                patient.getCareLevel(), patient.getRoomnumber(), patient.isLocked(), patient.getPid());
    }

    /**
     * generates a <code>delete</code>-Statement for a given key
     *
     * @param key for which a specific DELETE is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getDeleteStatementString(int key) {
        return String.format("Delete FROM patient WHERE pid=%d", key);
    }

    /**
     * sets the locked field in the patient table to true
     *
     * @param key pid of patient that should be locked
     * @throws SQLException
     */
    public void lockById(int key) throws SQLException {
        Statement st = conn.createStatement();
        st.executeUpdate(String.format("Update PATIENT set LOCKED = TRUE, LOCKEDDATE = '%s' where PID = '%s'", LocalDate.now(), key));
    }

    /**
     * sets the locked field in the patient table to false
     *
     * @param key pid of patient that should be locked
     * @throws SQLException
     */
    public void unLockById(int key) throws SQLException {
        Statement st = conn.createStatement();
        st.executeUpdate(String.format("Update PATIENT set LOCKED = FALSE, LOCKEDDATE = '%s' where PID = '%s'", LocalDate.now(), key));
    }

    /**
     * deletes all records from the patient table,
     * that are locked and older than the parameter date
     *
     * @param from since when the record must be locked
     * @return all patient ids that were deleted
     * @throws SQLException
     */
    public ArrayList<Integer> deleteAllLocked(LocalDate from) throws SQLException {
        ArrayList<Integer> list = new ArrayList<>();
        Statement st = conn.createStatement();
        ResultSet set = st.executeQuery(String.format("SELECT pid FROM patient WHERE locked = TRUE AND lockeddate <= '%s'", from));
        while (set.next()) {
            list.add(set.getInt(1));
        }
        st = conn.createStatement();
        st.executeUpdate(String.format("DELETE FROM patient WHERE locked = TRUE AND lockeddate <= '%s'", from));
        return list;
    }

}
