package main.java.cafemangement;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author amir
 */
public class orderController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    @FXML
    private void logout(ActionEvent event) {
        // Hiển thị hộp thoại xác nhận
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận đăng xuất");
        alert.setHeaderText("Bạn có muốn đăng xuất không?");
        alert.setContentText("Nhấn OK để đăng xuất hoặc Cancel để tiếp tục.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Thực hiện đăng xuất nếu người dùng chọn OK

            // Đóng cửa sổ hiện tại
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();

            // Tải lại trang login.fxml và hiển thị nó
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/resources/FXML/login.fxml"));
                stage.setTitle("Quản lý quán café - Cafe Mangement");
    	        stage.getIcons().add(new Image(getClass().getResourceAsStream( "/main/resources/img/app-icon.png" )));
                Parent root = loader.load();
                Scene scene = new Scene(root);

                Stage loginStage = new Stage();
                loginStage.setScene(scene);
                loginStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Người dùng chọn Cancel, không đăng xuất
        }
    }

}