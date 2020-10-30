package controller;

import datastorage.AccountDAO;
import datastorage.DAOFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Account;
import state.State;
import utils.DeleteManager;

import java.io.IOException;
import java.sql.SQLException;

/**
 * The <code>LoginController</code> contains the entire logic of the login view. It determines which data is displayed and how to react to events.
 */
public class LoginController {
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;

    private Stage primaryStage;

    /**
     * Sets the primary Stage for this controller
     *
     * @param stage will always be the primary stage, containing a login view
     */
    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }

    /**
     * Handles opening NHPlus if the login data is correct
     */
    @FXML
    public void handleLogin() {
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

    /**
     * Checks if the login data is correct, using the {@link AccountDAO}
     *
     * @param username user name
     * @param password unencrpted password
     * @return success
     */
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Handles closing the window if the user doesn't want to log in
     */
    @FXML
    public void handleCancel() {
        primaryStage.close();
    }
}