module principal {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    

    opens controller to javafx.fxml;
    exports principal;
}
