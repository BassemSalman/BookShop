module com.project.bookshop {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.sql.rowset;


    opens com.project.bookshop to javafx.fxml;
    exports com.project.bookshop;
    opens com.project.bookshop.controller to javafx.fxml;
    exports com.project.bookshop.controller;
    exports com.project.bookshop.view;
    opens com.project.bookshop.view to javafx.fxml;

}