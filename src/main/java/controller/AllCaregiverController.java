package controller;

import datastorage.AccountDAO;
import datastorage.CaregiverDAO;
import datastorage.DAOFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import model.Account;
import model.Caregiver;
import state.State;

import java.sql.SQLException;
import java.util.List;

public class AllCaregiverController {

    private final ObservableList<Caregiver> tableviewContent = FXCollections.observableArrayList();
    private final AccountDAO aDao = DAOFactory.getDAOFactory().createAccountDAO();
    @FXML
    Button btnDelete;
    @FXML
    Button btnAdd;
    @FXML
    TextField txtSurname;
    @FXML
    TextField txtFirstname;
    @FXML
    TextField txtPhonenumber;
    @FXML
    TextField txtPassword;
    @FXML
    CheckBox chkAdmin;
    @FXML
    private TableView<Caregiver> tableView;
    @FXML
    private TableColumn<Caregiver, Integer> colID;
    @FXML
    private TableColumn<Caregiver, String> colFirstName;
    @FXML
    private TableColumn<Caregiver, String> colSurname;
    @FXML
    private TableColumn<Caregiver, String> colPhone;
    private CaregiverDAO dao;

    /**
     * Initializes the corresponding fields. Is called as soon as the corresponding FXML file is to be displayed.
     */

    public void initialize() {
        readAllAndShowInTableView();

        this.colID.setCellValueFactory(new PropertyValueFactory<Caregiver, Integer>("cid"));

        //CellValuefactory zum Anzeigen der Daten in der TableView
        this.colFirstName.setCellValueFactory(new PropertyValueFactory<Caregiver, String>("firstName"));
        //CellFactory zum Schreiben innerhalb der Tabelle
        this.colFirstName.setCellFactory(TextFieldTableCell.forTableColumn());

        this.colSurname.setCellValueFactory(new PropertyValueFactory<Caregiver, String>("surname"));
        this.colSurname.setCellFactory(TextFieldTableCell.forTableColumn());

        this.colPhone.setCellValueFactory(new PropertyValueFactory<Caregiver, String>("phoneNumber"));
        this.colPhone.setCellFactory(TextFieldTableCell.forTableColumn());

        this.btnAdd.setDisable(!State.getRole().isAdmin());
        this.btnDelete.setDisable(!State.getRole().isAdmin());

        //Anzeigen der Daten
        this.tableView.setItems(this.tableviewContent);
    }

    /**
     * Calls readAll in {@link CaregiverDAO} and shows Caregivers in the table
     */
    private void readAllAndShowInTableView() {
        this.tableviewContent.clear();
        this.dao = DAOFactory.getDAOFactory().createCaregiverDAO();
        List<Caregiver> allCaregivers;
        try {
            allCaregivers = dao.readAll();
            this.tableviewContent.addAll(allCaregivers);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles a add-click-event. Creates a patient and calls the create method in the {@link CaregiverDAO}
     */
    @FXML
    public void handleAdd() {

        String surname = this.txtSurname.getText();
        String firstname = this.txtFirstname.getText();
        String phonenumber = this.txtPhonenumber.getText();
        String password = this.txtPassword.getText();
        String isAdmin = this.chkAdmin.isSelected() ? "ADMIN" : "";

        try {
            Caregiver c = new Caregiver(firstname, surname, phonenumber);
            dao.create(c);
            Account a = new Account(surname, password, isAdmin, c.getCid(), true);
            aDao.create(a);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        readAllAndShowInTableView();
        clearFields();
    }

    /**
     * Handles a delete-click-event. Calls the delete methods in the {@link CaregiverDAO} and {@link AccountDAO}
     */
    @FXML
    public void handleDelete() {
        Caregiver selectedItem = this.tableView.getSelectionModel().getSelectedItem();
        try {
            dao.deleteById((int) selectedItem.getCid());
            aDao.deleteById((int) selectedItem.getCid());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        readAllAndShowInTableView();
        clearFields();
    }

    /**
     * Removes content from all Fields
     */
    private void clearFields() {
        this.txtFirstname.clear();
        this.txtSurname.clear();
        this.txtPhonenumber.clear();
        this.txtPassword.clear();
        this.chkAdmin.setSelected(false);
    }
}
