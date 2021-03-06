package utils;

import datastorage.DAOFactory;
import datastorage.PatientDAO;
import datastorage.TreatmentDAO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Class for deleting old locked data based in <Code>DSGVO</Code> standards
 */
public class DeleteManager {

    /**
     * private constructor, so the class never gets instantiated
     */
    private DeleteManager() {

    }

    /**
     * deletes all patient specific data, that have to be locked for at least 10 years
     */
    public static void deleteOldData() {
        PatientDAO patientDAO = DAOFactory.getDAOFactory().createPatientDAO();
        TreatmentDAO treatmentDAO = DAOFactory.getDAOFactory().createTreatmentDAO();
        try {
            ArrayList<Integer> pids = patientDAO.readAllLockedAndOld(LocalDate.now().minusYears(10));
            for (int pid : pids) {
                treatmentDAO.deleteAllLockedWithPid(pid);
            }
            patientDAO.deleteAllLocked(LocalDate.now().minusYears(10));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
