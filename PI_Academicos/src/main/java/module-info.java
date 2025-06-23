module principal {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    requires java.desktop;
requires jakarta.mail;
    

    opens controller to javafx.fxml;
    exports principal;
}
