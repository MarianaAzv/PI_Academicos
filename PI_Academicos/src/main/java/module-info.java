module com.mycompany.telasacademicosanne {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens controller to javafx.fxml;
    exports principal;
}
