package controller;

import datastorage.CaregiverDAO;
import datastorage.DAOFactory;
import datastorage.PatientDAO;
import datastorage.TreatmentDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Caregiver;
import model.Patient;
import model.Treatment;
import utils.DateConverter;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The <code>TreatmentController</code> contains the entire logic of the edit treatment view. It determines which data is displayed and how to react to events.
 */
public class TreatmentController {
    @FXML
    private Label lblPatientName;
    @FXML
    private Label lblCarelevel;
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
    @FXML
    private Button btnChange;
    @FXML
    private Button btnCancel;
    @FXML
    private ComboBox<String> caregiverComboBox;

    private final ObservableList<String> myComboBoxData =
            FXCollections.observableArrayList();
    private ArrayList<Caregiver> caregiverList;

    private AllTreatmentController controller;
    private Stage stage;
    private Patient patient;
    private Treatment treatment;

    /**
     * Initializes the corresponding fields in the model. Is called as soon as the corresponding FXML file is to be displayed.
     *
     * @param controller AllTreatmentController
     * @param stage      primary stage
     * @param treatment  treatment model
     */
    public void initializeController(AllTreatmentController controller, Stage stage, Treatment treatment) {
        this.stage = stage;
        this.controller = controller;
        PatientDAO pDao = DAOFactory.getDAOFactory().createPatientDAO();
        try {
            this.patient = pDao.read((int) treatment.getPid());
            this.treatment = treatment;
            showData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        createComboBoxData();
    }

    /**
     * Loads all caregiver models into the combobox which will display the surnames
     */
    private void createComboBoxData() {
        CaregiverDAO dao = DAOFactory.getDAOFactory().createCaregiverDAO();
        try {
            int key = 0;
            Caregiver c = dao.read((int) treatment.getCaregiver().getCid());
            caregiverList = (ArrayList<Caregiver>) dao.readAll();
            for(Caregiver caregiver: caregiverList) {
                this.myComboBoxData.add(caregiver.getSurname());
                if (caregiver.getCid() == c.getCid()) {
                    key = caregiverList.indexOf(caregiver);
                }
            }
            caregiverComboBox.setItems(myComboBoxData);
            caregiverComboBox.getSelectionModel().select(key);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes all fields in the View
     */
    private void showData() {
        this.lblPatientName.setText(patient.getSurname() + ", " + patient.getFirstName());
        this.lblCarelevel.setText(patient.getCareLevel());
        LocalDate date = DateConverter.convertStringToLocalDate(treatment.getDate());
        this.datepicker.setValue(date);
        this.txtBegin.setText(this.treatment.getBegin());
        this.txtEnd.setText(this.treatment.getEnd());
        this.txtDescription.setText(this.treatment.getDescription());
        this.taRemarks.setText(this.treatment.getRemarks());
    }

    /**
     * Handles changing a field in the view
     */
    @FXML
    public void handleChange() {
        this.treatment.setDate(this.datepicker.getValue().toString());
        this.treatment.setBegin(txtBegin.getText());
        this.treatment.setEnd(txtEnd.getText());
        this.treatment.setDescription(txtDescription.getText());
        this.treatment.setRemarks(taRemarks.getText());
        this.treatment.setCaregiver(caregiverList.get(caregiverComboBox.getSelectionModel().getSelectedIndex()));
        doUpdate();
        controller.readAllAndShowInTableView();
        stage.close();
    }

    /**
     * Updates the {@link TreatmentDAO}
     */
    private void doUpdate() {
        TreatmentDAO dao = DAOFactory.getDAOFactory().createTreatmentDAO();
        try {
            dao.update(treatment);
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