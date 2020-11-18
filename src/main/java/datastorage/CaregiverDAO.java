package datastorage;

import model.Caregiver;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Implements the Interface <code>DAOImp</code>. Overrides methods to generate specific treatment-SQL-queries.
 */
public class CaregiverDAO extends DAOimp<Caregiver> {

    /**
     * constructs Object. Calls the Constructor from <code>DAOImp</code> to store the connection.
     *
     * @param conn SQL Connection
     */
    public CaregiverDAO(Connection conn) {
        super(conn);
    }

    /**
     * generates a <code>INSERT INTO</code>-Statement for a given patient
     *
     * @param caregiver for which a specific INSERT INTO is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getCreateStatementString(Caregiver caregiver) {
        return String.format("INSERT INTO CAREGIVER (firstname, surname, phonenumber) VALUES ('%s', '%s', '%s')",
                caregiver.getFirstName(), caregiver.getSurname(), caregiver.getPhoneNumber());
    }

    /**
     * generates a <code>select</code>-Statement for a given key
     *
     * @param key for which a specific SELECTis to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getReadByIDStatementString(int key) {
        return String.format("SELECT * FROM CAREGIVER WHERE cid = %d", key);
    }

    /**
     * maps a <code>ResultSet</code> to a <code>Patient</code>
     *
     * @param result ResultSet with a single row. Columns will be mapped to a patient-object.
     * @return patient with the data from the resultSet.
     */
    @Override
    protected Caregiver getInstanceFromResultSet(ResultSet result) throws SQLException {
        Caregiver c = null;
        c = getCaregiver(result);
        return c;
    }

    /**
     * extracted method for mapping data to caregiver
     *
     * @param result SQL resulst set
     * @return initialized caregiver model
     * @throws SQLException
     */
    private Caregiver getCaregiver(ResultSet result) throws SQLException {
        Caregiver c;
        c = new Caregiver(result.getInt(1), result.getString(2),
                result.getString(3), result.getString(4));
        return c;
    }

    /**
     * generates a <code>SELECT</code>-Statement for all caregivers.
     *
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getReadAllStatementString() {
        String statement = "SELECT * FROM CAREGIVER";
        return statement;
    }

    /**
     * maps a <code>ResultSet</code> to a <code>Caregiver-List</code>
     *
     * @param result ResultSet with a multiple rows. Data will be mapped to caregiver-object.
     * @return ArrayList with caregivers from the resultSet.
     */
    @Override
    protected ArrayList<Caregiver> getListFromResultSet(ResultSet result) throws SQLException {
        ArrayList<Caregiver> list = new ArrayList<Caregiver>();
        Caregiver c = null;
        while (result.next()) {
            c = getCaregiver(result);
            list.add(c);
        }
        return list;
    }

    /**
     * generates a <code>UPDATE</code>-Statement for a given caregiver
     *
     * @param caregiver for which a specific update is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getUpdateStatementString(Caregiver caregiver) {
        return String.format("UPDATE CAREGIVER SET cid = '%s', firstname = '%s', surname = '%s', phonenumber = '%s' WHERE cid = %d", caregiver.getCid(), caregiver.getFirstName(), caregiver.getSurname(), caregiver.getPhoneNumber());
    }

    /**
     * generates a <code>delete</code>-Statement for a given key
     *
     * @param key for which a specific DELETE is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getDeleteStatementString(int key) {
        return String.format("Delete FROM CAREGIVER WHERE cid=%d", key);
    }

}
