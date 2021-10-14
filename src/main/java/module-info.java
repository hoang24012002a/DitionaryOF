module com.example.dictionarypr {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.google.speech.api;
    requires jlayer;
    requires java.desktop;
    requires com.jfoenix;

    opens com.example.dictionarypr to javafx.fxml;
    exports com.example.dictionarypr;
}