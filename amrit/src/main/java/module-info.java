module com.example.amrit {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    requires java.sql;

    opens com.example.amrit to javafx.fxml;
    exports com.example.amrit;
}