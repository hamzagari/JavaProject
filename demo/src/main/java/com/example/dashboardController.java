package com.example;

import java.util.ResourceBundle;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;

import javafx.scene.control.ScrollPane;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;


public class dashboardController implements Initializable{
    
  @FXML
  private AnchorPane dashboard_form;

  @FXML
  private ImageView logo2;

  @FXML
  private ImageView logo1;

  @FXML
  private Label dashboard_Total;

  

  @FXML
  private AnchorPane main_form;

  @FXML
  private Button dashboard_btn;

  @FXML
  private TableView<customersData> customers_tableView;

  @FXML
  private TableColumn<customersData, String> customers_col_customerID;

  @FXML
  private TableColumn<customersData, String> customers_col_cashier;

  @FXML
  private TableColumn<customersData, String> customers_col_date;

  @FXML
  private TableColumn<customersData, String> customers_col_total;

  @FXML
  private Label customer_title;

  @FXML
  private ImageView logo4;

  @FXML
  private ImageView logo3;

  @FXML
  private Button logout_btn;

  @FXML
  private BarChart<?, ?> dashboard_CustomerChart;

  @FXML
  private ImageView logo5;

  @FXML
  private AreaChart<?, ?> dashboard_incomeChart;
  @FXML
  private Label label_name;

  

 

  @FXML
  private Label dashboard_NSP;

  @FXML
  private Button customers_btn;

  @FXML
  private Label dashboard_NC;

  @FXML
  private Label dashboard_Tl;

 

  @FXML
  private AnchorPane dashboard;

  @FXML
  private Label username;


@FXML
public void initialize(){
    
        Image  img1= new Image("C:\\Users\\hamza\\Desktop\\cafeproject\\demo\\src\\main\\java\\com\\example\\image\\logo1.png");
        logo1.setImage(img1);
        Image  img2= new Image("C:\\Users\\hamza\\Desktop\\cafeproject\\demo\\src\\main\\java\\com\\example\\image\\logo2.png");
        logo2.setImage(img2);
        Image  img3= new Image("C:\\Users\\hamza\\Desktop\\cafeproject\\demo\\src\\main\\java\\com\\example\\image\\logo3.png");
        logo3.setImage(img3);
        Image  img4= new Image("C:\\Users\\hamza\\Desktop\\cafeproject\\demo\\src\\main\\java\\com\\example\\image\\logo4.png");
        logo4.setImage(img4);
        Image  img5= new Image("C:\\Users\\hamza\\Desktop\\cafeproject\\demo\\src\\main\\java\\com\\example\\image\\logo5.png");
        logo5.setImage(img5);
    
    }

    
    @FXML
     public void dashboard() {

dashboard.setVisible(true);
customer_title.setVisible(false);
customers_tableView.setVisible(false);

dashboardDisplayNC();
dashboardDisplayTI();
dashboardTotalI();
dashboardNSP();
dashboardIncomeChart();
dashboardCustomerChart();
    }

    @FXML
     public void customers() {
      dashboard.setVisible(false);
      customer_title.setVisible(true);
      customers_tableView.setVisible(true);
      customersShowData();
      
    }
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    public void dashboardDisplayNC() {
        
      String sql = "SELECT COUNT(DISTINCT id_commande) FROM ligne_commande WHERE date(date)=CURRENT_DATE";
      connect = database_neve.connectDB();
      
      try {
          int nc = 0;
          prepare = connect.prepareStatement(sql);
          result = prepare.executeQuery();
          
          if (result.next()) {
              nc = result.getInt("COUNT(DISTINCT id_commande)");
          }
          dashboard_NC.setText(String.valueOf(nc));
      } catch (Exception e) {
          e.printStackTrace();
      }
      
  }
  
  public void dashboardDisplayTI() {
      Date date = new Date();
      
      String sql = "SELECT SUM(total) FROM ligne_commande WHERE date(date) =CURRENT_DATE;";
              
      
      connect = database_neve.connectDB();
      
      try {
          double ti = 0;
          prepare = connect.prepareStatement(sql);
          result = prepare.executeQuery();
          
          if (result.next()) {
              ti = result.getDouble("SUM(total)");
          }
          
          dashboard_Tl.setText(ti+" DH");
          
      } catch (Exception e) {
          e.printStackTrace();
      }
  }
  
  public void dashboardTotalI() {
      String sql = "SELECT SUM(total) FROM ligne_commande WHERE MONTH(date)=MONTH(CURRENT_DATE)";
      
      connect = database_neve.connectDB();
      
      try {
          float ti = 0;
          prepare = connect.prepareStatement(sql);
          result = prepare.executeQuery();
          
          if (result.next()) {
              ti = result.getFloat("SUM(total)");
          }
          dashboard_Total.setText(ti+" DH");
          
      } catch (Exception e) {
          e.printStackTrace();
      }
  }
  
  public void dashboardNSP() {
      
      String sql = "SELECT SUM(quantite) FROM ligne_commande where date(date)=CURRENT_DATE;";
      
      connect = database_neve.connectDB();
      
      try {
          int q = 0;
          prepare = connect.prepareStatement(sql);
          result = prepare.executeQuery();
          
          if (result.next()) {
              q = result.getInt("SUM(quantite)");
          }
          dashboard_NSP.setText(String.valueOf(q));
          
      } catch (Exception e) {
          e.printStackTrace();
      }
  }
  
  public void dashboardIncomeChart() {
    dashboard_incomeChart.getData().clear();
    
    String sql = "SELECT DATE(date), SUM(total) FROM ligne_commande GROUP BY DATE(date) ORDER BY DATE(date)";
    connect = database_neve.connectDB();
    XYChart.Series chart = new XYChart.Series();
    try {
        prepare = connect.prepareStatement(sql);
        result = prepare.executeQuery();
      
        while (result.next()) {
            chart.getData().add(new XYChart.Data<>(result.getString(1), result.getFloat(2)));
        }
        
        dashboard_incomeChart.getData().add(chart);
        
    } catch (Exception e) {
        e.printStackTrace();
    }
}

public void dashboardCustomerChart(){
    dashboard_CustomerChart.getData().clear();
    
    String sql = "SELECT DATE(date),COUNT(DISTINCT id_commande) FROM ligne_commande GROUP BY DATE(date) ORDER BY DATE(date)";
    connect = database_neve.connectDB();
    XYChart.Series chart = new XYChart.Series();
    try {
        prepare = connect.prepareStatement(sql);
        result = prepare.executeQuery();
       
        while (result.next()) {
            chart.getData().add(new XYChart.Data<>(result.getString(1), result.getInt(2)));
        }
        
        dashboard_CustomerChart.getData().add(chart);
        
    } catch (Exception e) {
        e.printStackTrace();
    }
}

public ObservableList<customersData> customersDataList() {
        
    ObservableList<customersData> listData = FXCollections.observableArrayList();
    String sql = "SELECT  u.login ,COUNT(DISTINCT l.id_commande), sum(l.total),c.date_creation FROM utilisateur u,commande c,ligne_commande l \n" + //
                "WHERE l.id_commande=c.id and c.id_serveur=u.id and date(c.date_creation)=CURRENT_DATE group by u.id;";
    connect = database_neve.connectDB();
    
    try {
        
        prepare = connect.prepareStatement(sql);
        result = prepare.executeQuery();
        customersData cData;
        
        while (result.next()) {
            cData = new customersData(result.getInt(2),
                    result.getDouble(3),
                    result.getDate(4),
                    result.getString(1)
                    );
            
            listData.add(cData);
        }
        
    } catch (Exception e) {
        e.printStackTrace();
    }
    return listData;
}
@FXML
public void returnpage(){
     try {
        App.setRoot("mainForm");
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
}


private ObservableList<customersData> customersListData;
    
public void customersShowData() {
    customersListData = customersDataList();
    
    customers_col_customerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
    customers_col_total.setCellValueFactory(new PropertyValueFactory<>("total"));
    customers_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
    customers_col_cashier.setCellValueFactory(new PropertyValueFactory<>("emUsername"));
    
    customers_tableView.setItems(customersListData);
}
    @Override
    public void initialize(URL location, ResourceBundle resources) {
      dashboardDisplayNC();
      dashboardDisplayTI();
      dashboardTotalI();
      dashboardNSP();
      dashboardIncomeChart();
      dashboardCustomerChart();

      customersShowData();
      controller per=new controller();
      label_name.setText("Hi"+" "+per.getName_user());
    }

    
}
