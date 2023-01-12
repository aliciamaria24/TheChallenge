package FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController implements Initializable {

    private Label label;

    private TextField txtname;

    private Button btn;

    private PasswordField txtpassword;

    void login(ActionEvent event){

        String username = txtname.getText();
        String password = txtpassword.getText();

        if(username.equals("") && password.equals("")){

            JOptionPane.showMessageDialog(null, "Hi");
        }
        else{
            try {
                Class.forName("ccm.mysql.jdbc.Driver");


            } catch (ClassNotFoundException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }
}
