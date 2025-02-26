module org.example.projectdemo {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.projectdemo to javafx.fxml;
    exports org.example.projectdemo;
}