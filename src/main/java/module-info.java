module com.warneriveris.metronome {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
	requires java.desktop;

    opens com.warneriveris.metronome to javafx.fxml;
    exports com.warneriveris.metronome;
}
