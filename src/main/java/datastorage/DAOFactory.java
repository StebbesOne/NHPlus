package datastorage;

/**
 * Makes all DAOs accessible from all classes
 */
public class DAOFactory {

    private static DAOFactory instance;

    private DAOFactory() {

    }

    /**
     * Singleton object
     * @return this
     */
    public static DAOFactory getDAOFactory() {
        if (instance == null) {
            instance = new DAOFactory();
        }
        return instance;
    }

    public TreatmentDAO createTreatmentDAO() {
        return new TreatmentDAO(ConnectionBuilder.getConnection());
    }

    public PatientDAO createPatientDAO() {
        return new PatientDAO(ConnectionBuilder.getConnection());
    }

    public AccountDAO createAccountDAO() { return new AccountDAO(ConnectionBuilder.getConnection()); }
}