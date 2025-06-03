package com.example;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ToWebController implements Initializable {
private Desktop d;

@FXML
private Label messageV;
@FXML
private Button btnweb;
 

@FXML
void gotobrowser(ActionEvent event) throws IOException {
    d=Desktop.getDesktop();
    d.browse(java.net.URI.create("http://localhost/verify/login.php"));
App.setRoot("primary2");
}

@Override
public void initialize(URL location, ResourceBundle resources) {
controller d=new controller();
if(d.getIndex()==0){
messageV.setText("Good,your account created with successful, Now you");
}else{
messageV.setText("You can't login in,your email not yet verified, Now you");
}

}


}
