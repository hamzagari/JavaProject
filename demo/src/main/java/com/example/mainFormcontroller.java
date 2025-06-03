package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Vector;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.scene.control.ButtonType;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class mainFormcontroller {

    @FXML
    private Button admin_btn;

    @FXML
    private AnchorPane admin_form;

    @FXML
    private Button inventory_addBtn;

    @FXML
    private Button inventory_btn;

    @FXML
    private Button inventory_clearBtn;

    @FXML
    private TableView<productData> inventory_tableView;

    @FXML
    private TableColumn<productData, String> inventory_col_date;

    @FXML
    private TableColumn<productData, String> inventory_col_price;

    @FXML
    private TableColumn<productData, String> inventory_col_productID;

    @FXML
    private TableColumn<productData, String> inventory_col_productName;

    @FXML
    private TableColumn<productData, String> inventory_col_stock;

    @FXML
    private TableColumn<productData, String> inventory_col_type;

    @FXML
    private Button inventory_deleteBtn;

    @FXML
    private ImageView logo6;

    @FXML
    private AnchorPane inventory_form;

    @FXML
    private ImageView inventory_imageView;

    @FXML
    private Button inventory_importBtn;

    @FXML
    private TextField inventory_price;

    @FXML
    private TextField inventory_productID;

    @FXML
    private TextField inventory_productName;

    @FXML
    private TextField inventory_stock;

    @FXML
    private ComboBox<String> inventory_type;

    @FXML
    private Button inventory_updateBtn;

    @FXML
    private Button logout_btn;

    @FXML
    private AnchorPane bonjeur_form;

    @FXML
    private AnchorPane main_form;

    @FXML
    private TextField menu_amount;

    @FXML
    private Button menu_btn;

    @FXML
    private Label menu_change;

    @FXML
    private AnchorPane menu_page;

    @FXML
    private AnchorPane iventory_page;

    @FXML
    private TableColumn<productData, String> menu_col_price;

    @FXML
    private TableColumn<productData, String> menu_col_productName;

    @FXML
    private TableColumn<productData, String> menu_col_quantity;

    @FXML
    private AnchorPane menu_form;

    @FXML
    private GridPane menu_gridPane;

    @FXML
    private Button menu_payBtn;

    @FXML
    private Button menu_receiptBtn;

    @FXML
    private Button menu_removeBtn;

    @FXML
    private ScrollPane menu_scrollPane;

    @FXML
    private TableView<productData> menu_tableView;

    @FXML
    private Label menu_total;

    @FXML
    private Label username;

    @FXML
    private TableColumn<lignecommende, String> commande_col_nom_produit;

    @FXML
    private TableColumn<lignecommende, String> commande_col_productID;
    @FXML
    private TableColumn<lignecommende, Integer> commande_col_sucr;

    @FXML
    private TableColumn<lignecommende, String> commende_col_prix;

    @FXML
    private TableColumn<lignecommende, String> commende_col_quantiter;

    @FXML
    private TableColumn<lignecommende, String> commende_col_total;

    @FXML
    private TableColumn<commendeDate, String> cuisine_col_Date;

    @FXML
    private TableColumn<commendeDate, String> cuisine_col_Name_user;

    @FXML
    private TableColumn<commendeDate, String> cuisine_col_Num_table;

    @FXML
    private TableColumn<commendeDate, String> cuisine_col_comendeID;

    @FXML
    private TableColumn<commendeDate, String> cuisine_col_total;

    @FXML
    private TableView<commendeDate> cuisine_tableView_2;

    @FXML
    private TableView<lignecommende> cuisine_tableView_3;

    @FXML
    private Button cuisin_V_commend;
   
    private String libelle;

    private   Integer  iD ;
    private String categorie; 
    private String username2;

    @FXML
    void admin() {

        try {
            App.setRoot("dashboardController");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @FXML
    void inventory() {

        logo6.setVisible(false);
        iventory_page.setVisible(true);
        menu_page.setVisible(false);

        inventoryShowData();
    }

    private Alert alert;
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    public ObservableList<productData> inventoryDataList() {

        ObservableList<productData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM produit";

        connect = database_neve.connectDB();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            productData prodData;

            while (result.next()) {
                Integer Id = result.getInt("id_categorie");
                String sql1 = "select * from categorie where id=" + Id;
                prepare1 = connect.prepareStatement(sql1);

                result1 = prepare1.executeQuery();
                result1.next();
                prodData = new productData(result.getInt("id"),
                        result.getString("libelle"),
                        result.getInt("stock"),
                        result.getDouble("prix"),
                        result1.getString("libelle"),
                        result.getDate("date_creation"),
                        result.getString("image"));

                listData.add(prodData);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<productData> inventoryListData;

    public void inventoryShowData() {
        inventoryListData = inventoryDataList();

        inventory_col_productID.setCellValueFactory(new PropertyValueFactory<>("id"));
        inventory_col_productName.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        inventory_col_type.setCellValueFactory(new PropertyValueFactory<>("id_categorie"));
        inventory_col_stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        inventory_col_price.setCellValueFactory(new PropertyValueFactory<>("prix"));
        inventory_col_date.setCellValueFactory(new PropertyValueFactory<>("date_creation"));

        inventory_tableView.setItems(inventoryListData);

    }

    @FXML
    public void inventoryAddBtn() {

        if (inventory_productName.getText().isEmpty()
                || inventory_type.getSelectionModel().getSelectedItem() == null
                || inventory_stock.getText().isEmpty()
                || inventory_price.getText().isEmpty()

                || data.path == null) {

            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();

        } else {

            // CHECK PRODUCT ID
            // String checkProdID = "SELECT libelle FROM produit WHERE id = '"
            // + inventory_productID.getText() + "'";
            Integer id = 0;
            connect = database_neve.connectDB();
            String id_categorie = "select id from categorie where libelle='"
                    + inventory_type.getSelectionModel().getSelectedItem() + "';";
            try {
                prepare = connect.prepareStatement(id_categorie);
                result1 = prepare.executeQuery();
                result1.next();
                id = result1.getInt("id");
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            // try {

            // statement = connect.createStatement();
            // result = statement.executeQuery(checkProdID);

            // if (result.next()) {
            // alert = new Alert(AlertType.ERROR);
            // alert.setTitle("Error Message");
            // alert.setHeaderText(null);
            // alert.setContentText(inventory_productID.getText() + " is already taken");
            // alert.showAndWait();
            // } else {
            String insertData = "INSERT INTO produit "
                    + "(libelle, id_categorie, stock, prix , image,date_creation,id_caissier) "
                    + "VALUES(?,?,?,?,?,?,?)";

            try {
                prepare = connect.prepareStatement(insertData);

                prepare.setString(1, inventory_productName.getText());
                prepare.setInt(2, id);
                prepare.setString(3, inventory_stock.getText());
                prepare.setString(4, inventory_price.getText());

                String path = data.path;
                path = path.replace("\\", "\\\\");
int l=path.length();
                prepare.setString(5, path.substring(46, l));
                // TO GET CURRENT DATE
                Date date = new Date();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                prepare.setString(6, String.valueOf(sqlDate));
                prepare.setString(7,username2);

                prepare.executeUpdate();

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Added!");
                alert.showAndWait();

                inventoryShowData();
                inventoryClearBtn();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        // } catch (Exception e) {
        // e.printStackTrace();
        // }
    }

    @FXML
    public void inventoryClearBtn() {
        inventory_productName.setText("");
        inventory_stock.setText("");
        inventory_price.setText("");
        inventory_type.promptTextProperty().setValue("hoose type.....");
        libelle="choose type.....";
        data.path = "";
        data.id = 0;
        inventory_imageView.setImage(null);

    }

    @FXML
    public void inventoryUpdateBtn() {

        if (inventory_productName.getText().isEmpty()
                || inventory_stock.getText().isEmpty()
                || inventory_price.getText().isEmpty()
                || data.path == null || data.id == 0) {

            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();

        } else {

if(inventory_type.getSelectionModel().getSelectedItem()==null){

            connect = database_neve.connectDB();
            categorie = "select id from categorie where libelle='"
                    +libelle+"';";

}else{
    connect = database_neve.connectDB();
  categorie = "select id from categorie where libelle='"
    +inventory_type.getSelectionModel().getSelectedItem()+"';";

}

            try {
                prepare = connect.prepareStatement(categorie);
                result1 = prepare.executeQuery();
                result1.next();
             iD = result1.getInt("id");

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                System.out.println("ereuer hamza");
            }

            String path = data.path;
            path = path.replace("\\", "\\\\");

            String updateData = "UPDATE produit SET "
                    + " libelle = '"
                    + inventory_productName.getText() + "', id_categorie ="
                    + iD+ ", stock = '"
                    + inventory_stock.getText() + "', prix = '"
                    + inventory_price.getText()
                    + "', image = '"
                    + path + "', date_creation  = '"
                    + data.date + "' WHERE id =" + data.id;

            connect = database_neve.connectDB();

            try {

                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText(
                        "Are you sure you want to UPDATE PRoduct Name: " + inventory_productName.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    prepare = connect.prepareStatement(updateData);//
                    prepare.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    // TO UPDATE YOUR TABLE VIEW
                    inventoryShowData();
                    // TO CLEAR YOUR FIELDS
                    inventoryClearBtn();
                } else {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Cancelled.");
                    alert.showAndWait();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void inventoryDeleteBtn() {
        if (data.id == 0) {

            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();

        } else {
            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText(
                    "Are you sure you want to DELETE Product ID: " + inventory_col_productName.getText() + "?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                String deleteData = "DELETE FROM produit WHERE id = " + data.id;
                try {
                    prepare = connect.prepareStatement(deleteData);
                    prepare.executeUpdate();

                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("successfully Deleted!");
                    alert.showAndWait();

                    // TO UPDATE YOUR TABLE VIEW
                    inventoryShowData();
                    // TO CLEAR YOUR FIELDS
                    inventoryClearBtn();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Cancelled");
                alert.showAndWait();
            }
        }
    }

    private Image image;

    @FXML
    public void inventoryImportBtn() {

        FileChooser openFile = new FileChooser();
        openFile.getExtensionFilters().add(new ExtensionFilter("Open Image File", "*png", "*jpg", "*jpeg"));

        File file = openFile.showOpenDialog(main_form.getScene().getWindow());

        if (file != null) {

            data.path = file.getAbsolutePath();
            image = new Image(file.toURI().toString(), 120, 127, false, true);

            inventory_imageView.setImage(image);
        }
    }

    @FXML
    public void inventorySelectData() {
        productData prodData = inventory_tableView.getSelectionModel().getSelectedItem();
        inventory_type.promptTextProperty().setValue(prodData.getId_categorie());
        libelle=prodData.getId_categorie();
    
        inventory_productName.setText(prodData.getLibelle());
        inventory_stock.setText(String.valueOf(prodData.getStock()));
        inventory_price.setText(String.valueOf(prodData.getPrix()));
        data.path = prodData.getImage();
      

        String path = "File:C:\\xampp1\\htdocs\\project\\upload\\produit\\" + prodData.getImage();
        data.date = String.valueOf(prodData.getDate_creation());
        data.id = prodData.getId();

        image = new Image(path, 120, 127, false, true);
        inventory_imageView.setImage(image);
    }

    @FXML
    public void getcategorie() {
        String sql = "select id,libelle from categorie;";
        connect = database_neve.connectDB();
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {

                inventory_type.getItems().add(result.getString(2));
            }

        } catch (SQLException e) {

        }

    }

    @FXML
    void logout(ActionEvent event) {
        try {

            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {

                // TO HIDE MAIN FORM
           App.setRoot("primary2");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void menu() {
        iventory_page.setVisible(false);
        menu_page.setVisible(true);
        logo6.setVisible(false);
        commendeShowData();
        System.out.println(cuisine_tableView_3.getItems());
        System.out.println(cuisine_tableView_3.getColumns());

    }

    private PreparedStatement prepare1;

    private ResultSet result1;

    public ObservableList<commendeDate> commendDataList() {

        ObservableList<commendeDate> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM commande where valide=0";

        connect = database_neve.connectDB();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            commendeDate comData;

            while (result.next()) {

                Integer Id = result.getInt("id_serveur");
                String sql1 = "select * from utilisateur where id=" + Id;
                prepare1 = connect.prepareStatement(sql1);

                result1 = prepare1.executeQuery();
                result1.next();

                comData = new commendeDate(result.getInt("id"),
                        result1.getString("login"),
                        result.getInt("num_table"),
                        result.getDouble("total"),
                        result.getInt("valide"),
                        result.getString("date_creation"));

                listData.add(comData);

            }
            System.out.println(listData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<commendeDate> commendeListData;

    public void commendeShowData() {
        commendeListData = commendDataList();

        cuisine_col_comendeID.setCellValueFactory(new PropertyValueFactory<>("id"));
        cuisine_col_Name_user.setCellValueFactory(new PropertyValueFactory<>("nom_serveur"));
        cuisine_col_Num_table.setCellValueFactory(new PropertyValueFactory<>("num_table"));
        cuisine_col_total.setCellValueFactory(new PropertyValueFactory<>("total"));
        cuisine_col_Date.setCellValueFactory(new PropertyValueFactory<>("date"));

        cuisine_tableView_2.setItems(commendeListData);

    }

    private double totalP;
    private int cID;
    private int getid;

    @FXML
    void valider_commande() {
        commendeDate comData = cuisine_tableView_2.getSelectionModel().getSelectedItem();
        getid = comData.getId();
        HashMap map = new HashMap();
        map.put("getReceipt", comData.getId());
int i=(int) Math.floor(Math.random()*1000);
String requte="insert into facture(num_facture,id_caissier,id_commande) values(?,?,?);";

try {
prepare1= connect.prepareStatement(requte);
prepare1.setInt(1, i);
prepare1.setString(2,username2);
prepare1.setInt(3,getid);
prepare1.executeUpdate();



} catch (SQLException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
}

        if (getid == 0) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please select the order you want to remove");
            alert.showAndWait();
        } else {
            String deleteData = "UPDATE commande set  valide=1  WHERE id = " + getid;

            connect = database_neve.connectDB();
            try {

                JasperDesign jDesign = JRXmlLoader.load(
                        "C:\\Users\\hamza\\Desktop\\cafeproject\\demo\\src\\main\\java\\com\\example\\report.jrxml");
                JasperReport jReport = JasperCompileManager.compileReport(jDesign);
                JasperPrint jPrint = JasperFillManager.fillReport(jReport, map, connect);

                JasperViewer.viewReport(jPrint, false);

                prepare = connect.prepareStatement(deleteData);
                prepare.executeUpdate();

                cuisine_tableView_3.getItems().clear();
                menu();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @FXML
    private void cuisinSelectData() {

        cuisinShowData();
        cuisinSelect();
    }

    @FXML
    private ObservableList<lignecommende> cuisinSelect() {

        ObservableList<lignecommende> listData = FXCollections.observableArrayList();

        commendeDate comData = cuisine_tableView_2.getSelectionModel().getSelectedItem();

        String sql = "SELECT * FROM ligne_commande where id_commande=" + comData.getId();

        connect = database_neve.connectDB();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            lignecommende cuisinData;

            String ss;
            while (result.next()) {

                String sql2 = " select libelle  from produit where id=" + result.getInt("id_produit");
                prepare = connect.prepareStatement(sql2);
                result1 = prepare.executeQuery();
                result1.next();

                if (result.getInt("etat") == 1) {
                    ss = "oui";

                } else if (result.getInt("etat") == 0) {
                    ss = "non";

                } else {

                    ss = "-----";

                }

                cuisinData = new lignecommende(
                        result.getInt("id"),
                        result1.getString("libelle"),
                        result.getDouble("prix"),
                        result.getInt("quantite"),
                        result.getDouble("total"),
                        ss

                );

                listData.add(cuisinData);

            }

        } catch (Exception e) {
            System.out.println("ererexxxxxxxxxxxxxxxxxxxxxxxxxx");
        }
        return listData;
    }

    private ObservableList<lignecommende> lignecommendesListData;

    public void cuisinShowData() {
        lignecommendesListData = cuisinSelect();

        commande_col_productID.setCellValueFactory(new PropertyValueFactory<>("id"));
        commande_col_nom_produit.setCellValueFactory(new PropertyValueFactory<>("prod_name"));
        commande_col_sucr.setCellValueFactory(new PropertyValueFactory<>("etat"));

        commende_col_prix.setCellValueFactory(new PropertyValueFactory<>("price"));
        commende_col_quantiter.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        commende_col_total.setCellValueFactory(new PropertyValueFactory<>("total"));

        cuisine_tableView_3.setItems(lignecommendesListData);

    }

    @FXML
    public void initialize() {

        getcategorie();
        inventoryShowData();
       
        Image img = new Image(
                "C:\\Users\\hamza\\Desktop\\cafeproject\\demo\\src\\main\\java\\com\\example\\image\\logo6.gif");
        logo6.setImage(img);

        commendeShowData();
        controller per=new controller();
      username2=per.getName_user();

    }
}
