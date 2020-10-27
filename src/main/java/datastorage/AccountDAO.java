package datastorage;

import model.Account;
import model.Patient;
import utils.DateConverter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Implements the Interface <code>DAOImp</code>. Overrides methods to generate specific patient-SQL-queries.
 */
public class AccountDAO extends DAOimp<Account> {

    public AccountDAO(Connection conn) {
        super(conn);
    }

    @Override
    protected String getCreateStatementString(Account account) {
        return String.format("INSERT INTO account (id, username, password, role) VALUES ('%s', '%s', '%s', '%s')",
                account.getId(), account.getUsername(), account.getEncrypedPassword(), account.getRole());
    }

    @Override
    protected String getReadByIDStatementString(int key) {
        return String.format("SELECT * FROM account WHERE id = %d", key);
    }

    @Override
    protected Account getInstanceFromResultSet(ResultSet set) throws SQLException {
        Account a;
        a = new Account(set.getString(1), set.getString(2),
                set.getString(3), set.getInt(4));
        return a;
    }

    @Override
    protected String getReadAllStatementString() {
        return "Select * from account";
    }

    @Override
    protected ArrayList<Account> getListFromResultSet(ResultSet set) throws SQLException {
        ArrayList<Account> list = new ArrayList<Account>();
        Account a;
        while (set.next()) {
            a = new Account(set.getString(1), set.getString(2),
                    set.getString(3), set.getInt(4));
            list.add(a);
        }
        return list;
    }

    @Override
    protected String getUpdateStatementString(Account account) {
        return String.format("UPDATE account SET username = '%s', password = '%s', role = '%s', aid = '%s', WHERE aid = %d",
                account.getUsername(), account.getEncrypedPassword(), account.getRole(), account.getId());
    }

    @Override
    protected String getDeleteStatementString(int key) {
        return String.format("Delete FROM account WHERE id=%d", key);
    }

    public Account getAccountByUsername(String username) throws SQLException {
        Statement st = conn.createStatement();
        ResultSet set =  st.executeQuery(String.format("Select * from account where username = '%s'", username));
        if (set.next()) {
            return new Account(set.getString(1), set.getString(2), set.getString(3), set.getInt(4));
        } else {
            return null;
        }
    }
}
