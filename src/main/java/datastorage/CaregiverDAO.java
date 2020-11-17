package datastorage;

import model.Caregiver;
import model.Patient;
import state.State;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CaregiverDAO extends DAOimp<Caregiver> {

    public CaregiverDAO(Connection conn){
            super(conn);
    }

    @Override
    protected String getCreateStatementString(Caregiver caregiver) {
        return String.format("INSERT INTO CAREGIVER (firstname, surname, phonenumber) VALUES ('%s', '%s', '%s')",
                caregiver.getFirstName(), caregiver.getSurname(), caregiver.getPhoneNumber());
    }

    @Override
    protected String getReadByIDStatementString(int key) {
        return String.format("SELECT * FROM CAREGIVER WHERE cid = %d", key);
    }

    @Override
    protected Caregiver getInstanceFromResultSet(ResultSet result) throws SQLException {
        Caregiver c = null;
        c = getCaregiver(result);
        return c;
    }

    private Caregiver getCaregiver(ResultSet result) throws SQLException {
        Caregiver c;
        c = new Caregiver(result.getInt(1), result.getString(2),
                result.getString(3), result.getString(4));
        return c;
    }

    @Override
    protected String getReadAllStatementString() {
        String statement = "SELECT * FROM CAREGIVER";
        return statement;
    }

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

    @Override
    protected String getUpdateStatementString(Caregiver caregiver) {
        return String.format("UPDATE CAREGIVER SET cid = '%s', firstname = '%s', surname = '%s', phonenumber = '%s' WHERE cid = %d", caregiver.getCid(), caregiver.getFirstName(), caregiver.getSurname(), caregiver.getPhoneNumber());
    }

    @Override
    protected String getDeleteStatementString(int key) {
        return String.format("Delete FROM CAREGIVER WHERE cid=%d", key);
    }

}
