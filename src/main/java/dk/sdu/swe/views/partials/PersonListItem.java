package dk.sdu.swe.views.partials;

import dk.sdu.swe.domain.models.Person;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Objects;

public class PersonListItem extends VBox {

    private Person person;

    @FXML
    private Label nameLbl, companyLbl, addressLbl;

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
    }

}
