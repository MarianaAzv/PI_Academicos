module com.mycompany.telasacademicosanne {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.telasacademicosanne to javafx.fxml;
    exports com.mycompany.telasacademicosanne;
}
