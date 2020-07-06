package tictactoeClient.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.transfert.MsgType;

/**
 *
 * @author selim
 */
public class LoginNio extends AnchorPane {

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnRegister;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfHost;

    @FXML
    private PasswordField tfPassword;

    @FXML
    private TextField tfPort;

    private ClientAppNio app;

    public LoginNio(ClientAppNio app) {

        initLoader();
        this.app = app;

    }
    
   

    public void setClientAppNio(ClientAppNio app) {

        this.app = app;
    }

    private void initLoader() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        } catch (IOException ex) {
            app.createNotification(MsgType.ERROR, ex.getMessage());
        }
    }

    @FXML
    private void login(ActionEvent event) {

        char[] paswd = this.tfPassword.getText().toCharArray();
        String name = this.tfName.getText();
        try {
            int port = Integer.parseInt(tfPort.getText());
            app.sendConfiguration(tfHost.getText(), port, name, paswd, true);
        } catch (NumberFormatException e) {
            app.createNotification(MsgType.ERROR, "PORT SHOULD BE A NUMBER");
        }

    }

    @FXML
    private void register(ActionEvent event) {

        char[] paswd = this.tfPassword.getText().toCharArray();
        String name = this.tfName.getText();
        try {
            int port = Integer.parseInt(tfPort.getText());
            app.sendConfiguration(tfHost.getText(), port, name, paswd, false);
        } catch (NumberFormatException e) {
            app.createNotification(MsgType.ERROR, "PORT SHOULD BE A NUMBER");
        }

    }

    
}
