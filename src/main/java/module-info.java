module org.example.loginjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;


    opens org.example.loginjavafx to javafx.fxml;
    exports org.example.loginjavafx;
    exports org.example.loginjavafx.controller;
    opens org.example.loginjavafx.controller to javafx.fxml;
}