package datastorage;

import model.Account;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Implements the Interface <code>DAOImp</code>. Overrides methods to generate specific patient-SQL-queries.
 */
public class AccountDAO extends DAOimp<Account> {

    /**
     * Constructs Object. Calls the Constructor from <code>DAOImp</code> to store the connection.
     *
     * @param conn SQL Connection
     */
    public AccountDAO(Connection conn) {
        super(conn);
    }

    /**
     * Generates a <code>INSERT INTO</code>-Statement for a given account
     *
     * @param account for which a specific INSERT INTO is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getCreateStatementString(Account account) {
        return String.format("INSERT INTO account (id, username, password, role) VALUES ('%s', '%s', '%s', '%s')",
                account.getId(), account.getUsername(), account.getEncryptedPassword(), account.getRole());
    }

    /**
     * Generates a <code>select</code>-Statement for a given key
     *
     * @param key for which a specific SELECTis to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getReadByIDStatementString(int key) {
        return String.format("SELECT * FROM account WHERE id = %d", key);
    }

    /**
     * Maps a <code>ResultSet</code> to an <code>Account</code>
     *
     * @param result ResultSet with a single row. Columns will be mapped to a patient-object.
     * @return account with the data from the resultSet.
     */
    @Override
    protected Account getInstanceFromResultSet(ResultSet result) throws SQLException {
        Account a;
        a = new Account(result.getString(1), result.getString(2),
                result.getString(3), result.getInt(4), false);
        return a;
    }

    /**
     * Generates a <code>SELECT</code>-Statement for all accounts.
     *
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getReadAllStatementString() {
        return "Select * from account";
    }

    /**
     * Maps a <code>ResultSet</code> to a <code>List</code> of <code>Account</code>
     *
     * @param result ResultSet with a multiple rows. Data will be mapped to Treatment-object.
     * @return ArrayList with patients from the resultSet.
     * @throws SQLException
     */
    @Override
    protected ArrayList<Account> getListFromResultSet(ResultSet result) throws SQLException {
        ArrayList<Account> list = new ArrayList<Account>();
        Account a;
        while (result.next()) {
            a = new Account(result.getString(1), result.getString(2),
                    result.getString(3), result.getInt(4), false);
            list.add(a);
        }
        return list;
    }

    /**
     * Generates a <code>UPDATE</code>-Statement for a given account
     *
     * @param account for which a specific update is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getUpdateStatementString(Account account) {
        return String.format("UPDATE account SET username = '%s', password = '%s', role = '%s', aid = '%s', WHERE aid = %d",
                account.getUsername(), account.getEncryptedPassword(), account.getRole(), account.getId());
    }

    /**
     * Generates a <code>delete</code>-Statement for a given key
     *
     * @param key for which a specific DELETE is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getDeleteStatementString(int key) {
        return String.format("Delete FROM account WHERE id=%d", key);
    }

    /**
     * Gets an <code>Account</code> based on username
     *
     * @param username name of the user
     * @return account of user
     * @throws SQLException
     */
    public Account getAccountByUsername(String username) throws SQLException {
        Statement st = conn.createStatement();
        ResultSet set = st.executeQuery(String.format("Select * from account where username = '%s'", username));
        if (set.next()) {
            return getInstanceFromResultSet(set);
        } else {
            return null;
        }
    }
}
