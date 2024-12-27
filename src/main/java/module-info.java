module com.example.projetjavax {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
//    requires pdfbox.app;
    requires java.desktop;
    requires pdfbox;
    requires javafx.swing;
    requires jimObjModelImporterJFX;
    requires javafx.media;


    opens com.example.projetjavax to javafx.fxml;
    exports com.example.projetjavax;
    exports com.example.projetjavax.SvtControl;
    opens com.example.projetjavax.SvtControl to javafx.fxml;
}