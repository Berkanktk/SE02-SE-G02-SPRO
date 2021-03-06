package dk.sdu.swe.views.partials;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import dk.sdu.swe.domain.controllers.PersonController;
import dk.sdu.swe.domain.models.Person;
import dk.sdu.swe.views.modals.persons.PersonModal;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class PersonListItem extends VBox {

    private Map<String, Runnable> options = new LinkedHashMap<>() {{
        put("Rediger", PersonListItem.this::editPerson);
        put("Slet", PersonListItem.this::deletePerson);
    }};

    private Person person;

    @FXML
    private Label nameLbl, dobLbl, emailLbl;

    @FXML
    private ImageView personImageView;

    @FXML
    private JFXButton actionBtn;

    private ListView<PersonListItem> container;

    public PersonListItem(Person person, ListView<PersonListItem> container) {
        this.person = person;
        this.container = container;

        FXMLLoader fxmlLoader = new FXMLLoader(
            Objects.requireNonNull(
                getClass().getClassLoader().getResource("dk/sdu/swe/views/persons/components/PersonListItem.fxml")));
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
        PopupListMenu popupList = new PopupListMenu(options);

        actionBtn.setOnMouseClicked(e -> {
            popupList.show(actionBtn, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);
        });

        updateState();
    }

    private void updateState() {
        nameLbl.setText(person.getName());
        dobLbl.setText(person.getZonedDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        emailLbl.setText(person.getContactDetail("email"));
        personImageView.setImage(new Image(person.getImage(), true));
    }

    private void editPerson(){
        Dialog<Person> personEditDialog = new PersonModal(getScene().getWindow(), person);
        Optional<Person> person = personEditDialog.showAndWait();
        person.ifPresent(personObj -> {
            PersonController.getInstance().update(personObj);
            updateState();
        });
    }

    private void deletePerson(){
        container.getItems().remove(this);
        PersonController.getInstance().delete(person);
    }

}
