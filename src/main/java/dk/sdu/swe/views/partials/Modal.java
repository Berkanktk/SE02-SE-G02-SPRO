package dk.sdu.swe.views.partials;

import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Window;

import java.io.IOException;
import java.util.Objects;

public class Modal<T> extends JFXAlert<T> {

    public Modal(Window window) {
        super(window);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setOverlayClose(false);

        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(new Label("Test Modal"));

        FXMLLoader fxmlLoader = new FXMLLoader(
            Objects.requireNonNull(
                getClass().getClassLoader().getResource("dk/sdu/swe/ui/programmes/EditProgramme.fxml")));
        fxmlLoader.setController(this);

        try {
            layout.setBody(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

}
