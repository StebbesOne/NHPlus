package datastorage;

import model.Caregiver;
import model.Treatment;
import utils.DateConverter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements the Interface <code>DAOImp</code>. Overrides methods to generate specific patient-SQL-queries.
 */
public class TreatmentDAO extends DAOimp<Treatment> {

    /**
     * constructs Onbject. Calls the Constructor from <code>DAOImp</code> to store the connection.
     *
     * @param conn SQL Connection
     */
    public TreatmentDAO(Connection conn) {
        super(conn);
    }

    /**
     * generates a <code>INSERT INTO</code>-Statement for a given treatment
     *
     * @param treatment for which a specific INSERT INTO is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getCreateStatementString(Treatment treatment) {
        return String.format("INSERT INTO treatment (pid, cid, treatment_date, begin, end, description, remarks, locked) VALUES " +
                        "(%d,'%d', '%s', '%s', '%s', '%s', '%s', FALSE)", treatment.getPid(), treatment.getCid(), treatment.getDate(),
                treatment.getBegin(), treatment.getEnd(), treatment.getDescription(),
                treatment.getRemarks(), false);
    }

    /**
     * generates a <code>select</code>-Statement for a given key
     *
     * @param key for which a specific SELECTis to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getReadByIDStatementString(int key) {
        return String.format("SELECT * FROM treatment WHERE tid = %d", key);
    }

    /**
     * maps a <code>ResultSet</code> to a <code>Treatment</code>
     *
     * @param result ResultSet with a single row. Columns will be mapped to a patient-object.
     * @return patient with the data from the resultSet.
     */
    @Override
    protected Treatment getInstanceFromResultSet(ResultSet result) throws SQLException {
        LocalDate date = DateConverter.convertStringToLocalDate(result.getString(4));
        LocalTime begin = DateConverter.convertStringToLocalTime(result.getString(5));
        LocalTime end = DateConverter.convertStringToLocalTime(result.getString(6));
        Treatment m = new Treatment(result.getLong(1), result.getLong(2), result.getLong(3),
                date, begin, end, result.getString(7), result.getString(8), new Caregiver(result.getInt(9),result.getString(10),result.getString(11),result.getString(12)));
        return m;
    }

    /**
     * generates a <code>SELECT</code>-Statement for all treatments.
     *
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getReadAllStatementString() {
        return "SELECT * FROM treatment LEFT JOIN caregiver ON treatment.CID = caregiver.CID WHERE treatment.locked = FALSE";
    }

    /**
     * maps a <code>ResultSet</code> to a <code>List</code> of <code>Treatment</code>
     *
     * @param result ResultSet with a multiple rows. Data will be mapped to Treatment-object.
     * @return ArrayList with patients from the resultSet.
     * @throws SQLException
     */
    @Override
    protected ArrayList<Treatment> getListFromResultSet(ResultSet result) throws SQLException {
        ArrayList<Treatment> list = new ArrayList<Treatment>();
        Treatment t = null;
        while (result.next()) {
            LocalDate date = DateConverter.convertStringToLocalDate(result.getString(4));
            LocalTime begin = DateConverter.convertStringToLocalTime(result.getString(5));
            LocalTime end = DateConverter.convertStringToLocalTime(result.getString(6));
            t = new Treatment(result.getLong(1), result.getLong(2), result.getLong(3),
                    date, begin, end, result.getString(7), result.getString(8), new Caregiver(result.getInt(9),result.getString(10),result.getString(11),result.getString(12)));
            list.add(t);
        }
        return list;
    }

    /**
     * generates a <code>UPDATE</code>-Statement for a given treatment
     *
     * @param treatment for which a specific update is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getUpdateStatementString(Treatment treatment) {
        return String.format("UPDATE treatment SET pid = %d, treatment_date ='%s', begin = '%s', end = '%s'," +
                        "description = '%s', remarks = '%s' WHERE tid = %d", treatment.getPid(), treatment.getDate(),
                treatment.getBegin(), treatment.getEnd(), treatment.getDescription(), treatment.getRemarks(),
                treatment.getTid());
    }

    /**
     * generates a <code>delete</code>-Statement for a given key
     *
     * @param key for which a specific DELETE is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getDeleteStatementString(int key) {
        return String.format("Delete FROM treatment WHERE tid= %d", key);
    }

    /**
     * maps a <code>ResultSet</code> to a <code>List</code> of <code>Treatment</code>
     *
     * @param pid patient id
     * @return <code>List</code> with all Treatments matching the key.
     * @throws SQLException
     */
    public List<Treatment> readTreatmentsByPid(long pid) throws SQLException {
        ArrayList<Treatment> list = new ArrayList<Treatment>();
        Treatment object = null;
        Statement st = conn.createStatement();
        ResultSet result = st.executeQuery(getReadAllTreatmentsOfOnePatientByPid(pid));
        list = getListFromResultSet(result);
        return list;
    }

    /**
     * generates a <code>select</code>-Statement for a given all treatments with a given key
     *
     * @param pid patient id
     * @return <code>String</code> with the generated SQL.
     */
    private String getReadAllTreatmentsOfOnePatientByPid(long pid) {
        return String.format("SELECT * FROM treatment LEFT JOIN caregiver ON treatment.CID = caregiver.CID WHERE treatment.pid = %d and treatment.LOCKED = FALSE", pid);
    }

    /**
     * executes a <code>delete</code> statement for any treatment wich is matching the key
     *
     * @param key patient id
     * @throws SQLException
     */
    public void deleteByPid(int key) throws SQLException {
        Statement st = conn.createStatement();
        st.executeUpdate(String.format("Delete FROM treatment WHERE pid= %d", key));
    }

    /**
     * executes a <code>update</code> statement on the <code>locked</code> field for any treatment wich is matching the key
     *
     * @param key patient id
     * @throws SQLException
     */
    public void lockByPid(int key) throws SQLException {
        Statement st = conn.createStatement();
        st.executeUpdate(String.format("Update TREATMENT set LOCKED = TRUE where PID = '%s'", key));
    }

    /**
     * executes a <code>update</code> statement on the <code>locked</code> field for any treatment wich is matching the key
     *
     * @param key patient id
     * @throws SQLException
     */
    public void unLockByPid(int key) throws SQLException {
        Statement st = conn.createStatement();
        st.executeUpdate(String.format("Update TREATMENT set LOCKED = FALSE where PID = '%s'", key));
    }

    /**
     * executes a <code>delete</code> for any treatment which is matching the key and is being locked
     *
     * @param pid patient id
     * @throws SQLException
     */
    public void deleteAllLockedWithPid(int pid) throws SQLException {
        Statement st = conn.createStatement();
        st.executeUpdate(String.format("DELETE FROM TREATMENT WHERE LOCKED = TRUE AND PID = '%s'", pid));
    }


}