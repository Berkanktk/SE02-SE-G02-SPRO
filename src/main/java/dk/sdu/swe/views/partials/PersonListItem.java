package dk.sdu.swe.views.partials;

import com.jfoenix.controls.JFXPopup;
import dk.sdu.swe.domain.models.Person;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class PersonListItem extends VBox {

    private Map<String, Runnable> options = new LinkedHashMap<>() {{
        put("Rediger", () -> {});
        put("Slet", () -> {});
    }};

    private Person person;

    @FXML
    private Label nameLbl, companyLbl, addressLbl;

    @FXML
    private ImageView personImageView;

    @FXML
    private Button actionsBtn;

    public PersonListItem(Person person) {
        this.person = person;

        FXMLLoader fxmlLoader = new FXMLLoader(
            Objects.requireNonNull(
                getClass().getClassLoader().getResource("dk/sdu/swe/ui/persons/components/person.fxml")));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        nameLbl.setText(person.getName());
        personImageView.setImage(new Image(person.getImage(), true));

        PopupListMenu popupList = new PopupListMenu(options);

        actionsBtn.setOnMouseClicked(e -> {
            popupList.show(actionsBtn, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);
        });
    }

}
