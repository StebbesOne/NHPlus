package controller;

import datastorage.AccountDAO;
import datastorage.DAOFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Account;
import state.State;
import utils.DeleteManager;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;

    private Stage primaryStage;

    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }

    @FXML
    public void handleLogin(){
        if (login(txtUsername.getText(), txtPassword.getText())) {
            try {
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("/MainWindowView.fxml"));
                BorderPane p = loader.load();
                Scene scene = new Scene(p);
                this.primaryStage.setTitle("NHPlus");
                this.primaryStage.setScene(scene);
                this.primaryStage.setResizable(false);
                this.primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private boolean login(String username, String password) {
        AccountDAO accountDAO = DAOFactory.getDAOFactory().createAccountDAO();
        try {
            Account a = accountDAO.getAccountByUsername(username);
            if (a != null) {
                if (a.login(password)) {
                    State.setRole(a);
                    DeleteManager.deleteOldData();
                    return true;
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @FXML
    public void handleCancel(){

    }
}