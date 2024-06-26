module com.example.musicplayer {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires mysql.connector.java;

    opens com.example.musicplayer to javafx.fxml;
    exports com.example.musicplayer;
}