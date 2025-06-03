module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.mail;
    requires java.desktop;
    requires de.jensd.fx.glyphs.fontawesome;
    requires jasperreports;
    opens com.example to javafx.fxml;
    exports com.example;
}
