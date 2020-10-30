package controller;

import datastorage.CaregiverDAO;
import datastorage.DAOFactory;
import datastorage.TreatmentDAO;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Patient;
import model.Treatment;
import utils.DateConverter;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The <code>NewTreatmentController</code> contains the entire logic of the new treatment view. It determines which data is displayed and how to react to events.
 */
public class NewTreatmentController {
    @FXML
    private Label lblSurname;
    @FXML
    private Label lblFirstname;
    @FXML
    private TextField txtBegin;
    @FXML
    private TextField txtEnd;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextArea taRemarks;
    @FXML
    private DatePicker datepicker;

    private AllTreatmentController controller;
    private Patient patient;
    private Stage stage;

    /**
     * Initializes the corresponding fields. Is called as soon as the corresponding FXML file is to be displayed.
     *
     * @param controller AllTreatmentController
     * @param stage      primary stage
     * @param patient    patient model linked to the treatment
     */
    public void initialize(AllTreatmentController controller, Stage stage, Patient patient) {
        this.controller = controller;
        this.patient = patient;
        this.stage = stage;
        showPatientData();
    }

    /**
     * Puts patient data from model into the text fields
     */
    private void showPatientData() {
        this.lblFirstname.setText(patient.getFirstName());
        this.lblSurname.setText(patient.getSurname());
    }

    /**
     * Handles adding a new treatment
     */
    @FXML
    public void handleAdd(){
        Treatment treatment = null;
        CaregiverDAO caregiverDAO = DAOFactory.getDAOFactory().createCaregiverDAO();
        LocalDate date = this.datepicker.getValue();
        String s_begin = txtBegin.getText();
        LocalTime begin = DateConverter.convertStringToLocalTime(txtBegin.getText());
        LocalTime end = DateConverter.convertStringToLocalTime(txtEnd.getText());
        String description = txtDescription.getText();
        String remarks = taRemarks.getText();
        try {
            treatment = new Treatment(patient.getPid(), 1, date,
                    begin, end, description, remarks, caregiverDAO.read(1));
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        createTreatment(treatment);
        controller.readAllAndShowInTableView();
        stage.close();
    }

    /**
     * Creates a new treatment in the treatment SQL table, using the {@link TreatmentDAO}
     *
     * @param treatment
     */
    private void createTreatment(Treatment treatment) {
        TreatmentDAO dao = DAOFactory.getDAOFactory().createTreatmentDAO();
        try {
            dao.create(treatment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles cancelling by closing the stage
     */
    @FXML
    public void handleCancel() {
        stage.close();
    }
}