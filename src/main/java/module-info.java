module org.example.logoinjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;


    opens org.example.logoinjavafx to javafx.fxml;
    exports org.example.logoinjavafx;
}