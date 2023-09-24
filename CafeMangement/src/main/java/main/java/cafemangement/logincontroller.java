package main.java.CafeMangement;

import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.java.CafeMangement.mysqlconnect;

/**
 *
 * @author amir
 */
// check format chính quy email
public class logincontroller implements Initializable {

//Ma hoa password bang sha1

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] hashBytes = md.digest(password.getBytes());

        StringBuilder hashedPassword = new StringBuilder();
        for (byte b : hashBytes) {
            hashedPassword.append(String.format("%02x", b));
        }
        return hashedPassword.toString();
    }

    @FXML
    protected AnchorPane pane_login;

    @FXML
    private TextField txt_username;

    @FXML
    private PasswordField txt_password;

    @FXML
    private ComboBox type;

    @FXML
    private Button btn_login;

   

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public void LoginpaneShow() {

        pane_login.setVisible(true);
    }

    @FXML
    public void Login(ActionEvent event) {
        conn = mysqlconnect.ConnectDb();
        if (conn == null) {
            System.err.println("Failed to connect to the database.");
            return;
        }
        String sql = "SELECT * FROM users WHERE username = ? AND password = ? AND type = ?";

        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_username.getText());
            pst.setString(2, hashPassword(txt_password.getText())); // Hash the password for comparison
            pst.setString(3, type.getValue().toString());

            rs = pst.executeQuery();

            if (rs.next()) {
                String userType = rs.getString("type");
                System.out.println("Username and Password are Correct");
                JOptionPane.showMessageDialog(null, "Username and Password are Correct");

                // Close the current window
                Stage currentStage = (Stage) btn_login.getScene().getWindow();
                currentStage.hide();

                // Load different FXML files based on user type
                String fxmlFile = "";
                if ("Staff".equals(userType)) {
                    fxmlFile = "/main/resources/FXML/order.fxml";
                } else if ("Admin".equals(userType)) {
                    fxmlFile = "/main/resources/FXML/AdminScene.fxml";
                }

                try {
                    Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
                    Stage mainStage = new Stage();
                    mainStage.setTitle("Quản lý quán café - Cafe Mangement");
                    mainStage.getIcons().add(new Image(getClass().getResourceAsStream("/main/resources/img/app-icon.png")));
                    Scene scene = new Scene(root);
//                    scene.getStylesheets().add(getClass().getResource("/main/resources/css/main.css").toExternalForm()); // Load and apply the CSS file

                    mainStage.setScene(scene);
                    mainStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Invalid Username or Password");
                JOptionPane.showMessageDialog(null, "Invalid Username or Password");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        type.getItems().addAll("Admin", "Staff");
    }

}
