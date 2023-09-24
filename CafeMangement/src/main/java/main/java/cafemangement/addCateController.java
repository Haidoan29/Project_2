

package main.java.CafeMangement.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.java.CafeMangement.mysqlconnect;

public class addCateController {
	@FXML
	private ComboBox<String> comboBox;
	@FXML
    private TextField txt_catename;
	
	

	
	@FXML
	public void Add_cate(ActionEvent event){    
        Connection conn = mysqlconnect.ConnectDb();
        
        String sql = "insert into category (cate_name) values (?)";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, txt_catename.getText());
         
            pst.execute();
            JOptionPane.showMessageDialog(null, "Saved");
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }  
    }




}

