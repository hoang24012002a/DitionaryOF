module com.example.dictionarypr {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.google.speech.api;
    requires jlayer;

    opens com.example.dictionarypr to javafx.fxml;
    exports com.example.dictionarypr;
}