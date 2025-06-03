package com.example;

import java.io.IOException;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.awt.Desktop;

public class controller {
    private database db;
    private String email;
    private static int index = 0;
    private Vector<user> users = new Vector<>();

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getEmail() {
        return email;

    }

    public void setEmail(String email) {
        this.email = email;
    }

    @FXML
    private ImageView Logo;
    @FXML
    private Label alert_fields;
    @FXML
    private Label alert_succesful;
    @FXML
    private Button btn_login;

    @FXML
    private Button create_acount;

    @FXML
    private FontAwesomeIconView email_warning;

    @FXML
    private Hyperlink havealready;

    @FXML
    private Label invalide_usename;

    @FXML
    private Button login_forgot;

    @FXML
    private AnchorPane login_form;

    @FXML
    private PasswordField login_password;

    @FXML
    private Label login_password_incorrect;

    @FXML
    private TextField login_username;

    @FXML
    private Label password_size;

    @FXML
    private FontAwesomeIconView password_warning;

    @FXML
    private AnchorPane side;

    @FXML
    private TextField random_hide;
    @FXML
    private Button sign_btn;
    @FXML
    private AnchorPane sign_form;

    @FXML
    private PasswordField sign_password;

    @FXML
    private TextField sign_username;

    @FXML
    private FontAwesomeIconView username_warning;

    @FXML
    private Label valide_email;

    private Desktop d;
    @FXML
    private TextField sign_email;


    static String name_user;

  

    public static String getName_user() {
        return name_user;
    }

    public static void setName_user(String name_user) {
        controller.name_user = name_user;
    }

    public TextField getSign_email() {
        return sign_email;
    }

    public void setSign_email(TextField sign_email) {
        this.sign_email = sign_email;
    }

    @FXML
    public void initialize() {
        db = new database();
        Image img = new Image(
                "C:\\Users\\hamza\\Desktop\\cafeproject\\demo\\src\\main\\java\\com\\example\\image\\Png.gif");
        Logo.setImage(img);
        alert_fields.setVisible(false);
        updateDataBase();
    }

    @FXML
    void switchForm(ActionEvent event) { // switch from login to sign up
        updateDataBase();
        alert_fields.setVisible(false);
        TranslateTransition slider = new TranslateTransition();
        slider.setOnFinished((ActionEvent e) -> {
            sign_form.setVisible(true);
            login_form.setVisible(false);

            clearlogin();
        });

        slider.play();

    }

    @FXML
    void SwitchForm(ActionEvent event) { // switch from sign up to login
        updateDataBase();
        alert_succesful.setVisible(false);
        login_password_incorrect.setVisible(false);
        clearlogin();
        TranslateTransition slider = new TranslateTransition();
        slider.setOnFinished((ActionEvent e) -> {
            sign_form.setVisible(false);
            login_form.setVisible(true);
            clear();

        });

        slider.play();
    }

    @FXML
    public void btnlogin() {// control the login form
        updateDataBase();
        login_password_incorrect.setVisible(false);
        if (login_username.getText().isEmpty() || login_password.getText().isEmpty()) {
            alert_succesful.setVisible(false);
            login_password_incorrect.setText("Please fill all blank fields");
            login_password_incorrect.setVisible(true);
        } else if (!searchUserLogin()) {
            login_password_incorrect.setText("Login or password incorrect");
            login_password_incorrect.setVisible(false);
            login_password_incorrect.setVisible(true);
            clearlogin();
        } else

        if (!valid_email()) {
            try {
                index = 1;
                App.setRoot("ToWeb");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            login_password_incorrect.setVisible(false);
            try {
                name_user=login_username.getText();
                App.setRoot("mainForm");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void signUp(ActionEvent event) { // control the sign in form
        updateDataBase();
        if (sign_username.getText().isEmpty() || sign_email.getText().isEmpty() || sign_password.getText().isEmpty()) {
            alert_fields.setText("Please fill all blank fields");
            alert_fields.setVisible(true);
            alert_succesful.setVisible(false);
            password_size.setText("");
            invalide_usename.setText("");
            valide_email.setText("");
            username_warning.setVisible(false);
            password_warning.setVisible(false);
            email_warning.setVisible(false);

        } else if (!(validation_email(sign_email.getText())) && (sign_password.getText().length() < 8)
                && (searchUser())) {

            valide_email.setText("invalid  email");
            password_size.setText("au moins 8 caracterres");
            invalide_usename.setText("the username Already exists");
            username_warning.setVisible(true);
            password_warning.setVisible(true);
            email_warning.setVisible(true);
            alert_fields.setVisible(false);
            alert_succesful.setVisible(false);
            sign_password.clear();
            sign_email.clear();
            sign_username.clear();

        } else if ((sign_password.getText().length() < 8) && (searchUser()) && SearchEmail()) {
            password_size.setText("au moins 8 caracterres ");
            invalide_usename.setText("the username Already exists");
            alert_fields.setVisible(false);
            valide_email.setText("this email already exist.");
            alert_succesful.setVisible(false);
            username_warning.setVisible(true);
            password_warning.setVisible(true);
            email_warning.setVisible(true);
            sign_email.clear();
            sign_password.clear();
            sign_username.clear();

        } else if (!(validation_email(sign_email.getText())) && (sign_password.getText().length() < 8)) {
            valide_email.setText("invalid  email");
            password_size.setText("au moins 8 caracteres ");
            alert_fields.setVisible(false);
            alert_succesful.setVisible(false);
            invalide_usename.setText("");
            username_warning.setVisible(false);
            password_warning.setVisible(true);
            email_warning.setVisible(true);
            sign_password.clear();
            sign_email.clear();

        } else if (!(validation_email(sign_email.getText())) && (searchUser())) {
            valide_email.setText("invalide  email");
            invalide_usename.setText("the username Already exists");
            alert_fields.setVisible(false);
            alert_succesful.setVisible(false);
            username_warning.setVisible(true);
            password_warning.setVisible(false);

            email_warning.setVisible(true);
            sign_username.clear();
            sign_email.clear();
            password_size.setText("");
        } else

        if (!(validation_email(sign_email.getText()))) {
            valide_email.setText("invalide  email");
            alert_fields.setVisible(false);
            password_size.setText("");
            invalide_usename.setText("");
            sign_email.clear();
            username_warning.setVisible(false);
            password_warning.setVisible(false);
            email_warning.setVisible(true);

        } else if ((sign_password.getText().length() < 8) && (SearchEmail())) {
            password_size.setText("au moins 8 caracteres ");
            valide_email.setText("this email already exist.");
            sign_email.clear();
            sign_password.clear();
            alert_fields.setVisible(false);
            alert_succesful.setVisible(false);
            invalide_usename.setText("");
            username_warning.setVisible(false);
            password_warning.setVisible(true);
            email_warning.setVisible(true);

        } else if (sign_password.getText().length() < 8) {
            password_size.setText("au moins 8 caracteres ");
            sign_password.clear();
            alert_fields.setVisible(false);
            alert_succesful.setVisible(false);
            valide_email.setText("");
            invalide_usename.setText("");
            username_warning.setVisible(false);
            password_warning.setVisible(true);
            email_warning.setVisible(false);

        } else if (searchUser() && SearchEmail()) {
            password_size.setText("");
            invalide_usename.setText("The username Already exists");
            alert_succesful.setVisible(false);
            valide_email.setText("this email already exist.");
            username_warning.setVisible(true);
            password_warning.setVisible(false);
            sign_username.clear();
            email_warning.setVisible(true);
            sign_email.clear();

        } else

        if (searchUser()) {
            password_size.setText("");
            invalide_usename.setText("The username Already exists");
            alert_succesful.setVisible(false);
            valide_email.setText("");
            username_warning.setVisible(true);
            password_warning.setVisible(false);
            email_warning.setVisible(false);
            sign_username.clear();
        } else if (SearchEmail()) {
            valide_email.setText("this email already exist.");
            password_warning.setVisible(false);
            username_warning.setVisible(false);
            password_size.setText("");
            invalide_usename.setText("");
            email_warning.setVisible(true);
            sign_email.clear();

        } else {
            alert_fields.setVisible(false);
            alert_succesful.setVisible(false);
            valide_email.setVisible(false);
            password_size.setVisible(false);
            user u = new user(sign_username.getText(), sign_email.getText(),
                    sign_password.getText(), null);
            db.insertIn(u);


            try {
                App.setRoot("ToWeb");
                SwitchForm(event);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @FXML
    void ChangePassword(ActionEvent event) {
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.browse(java.net.URI.create("http://localhost/verify/login_forgot.php"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clear() { // clear the TextField form sign in
        sign_email.clear();
        sign_username.clear();
        sign_password.clear();
    }

    public boolean searchUser() { // search a user in the list
        for (user ur : users) {
            if (sign_username.getText().equals(ur.getUsename())) {
                return true;
            }
        }
        return false;
    }

    public void clearSuccess() {
        alert_succesful.setVisible(false);

    }

    public Boolean searchUserLogin() {// verified if the user exist or not
        for (user ur : users) {
            if ((login_username.getText().equals(ur.getUsename()))
                    && (login_password.getText().equals(ur.getPassword()))) {
                return true;
            }
        }
        return false;
    }

    public void clearlogin() {// clear the textField form login
        login_password.clear();
        login_username.clear();
    }

    public boolean valid_email() {// test email verification
        for (user u : users) {
            if (login_username.getText().equals(u.getUsename())) {
                if (u.getEmail().equals(u.getValid_email())) {
                    return true;
                }
            }
        }

        return false;

    }

    public boolean validation_email(String email) { // test email validation
        String regexE = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern patternE = Pattern.compile(regexE, Pattern.CASE_INSENSITIVE);
        Matcher ma = patternE.matcher(email);
        return ma.find();

    }

    public boolean SearchEmail() { // search a email if exist before in dataBase or not .
        for (user u : users) {
            if (sign_email.getText().equals(u.getEmail())) {
                return true;
            }
        }
        return false;
    }

    public void updateDataBase() {
        users = db.getALL();
    }
}