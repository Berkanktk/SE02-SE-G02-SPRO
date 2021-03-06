package dk.sdu.swe.views;

import dk.sdu.swe.domain.controllers.UserController;
import dk.sdu.swe.domain.models.User;
import dk.sdu.swe.views.partials.UserListItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class UserControlViewController extends VBox {

    @FXML
    private ListView<UserListItem> userList;

    public UserControlViewController() {
        FXMLLoader fxmlLoader = new FXMLLoader(
            Objects.requireNonNull(
                getClass().getClassLoader().getResource("dk/sdu/swe/views/admin/components/UserControlView.fxml")));
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
        List<User> users = UserController.getInstance().getAll();
        for (User user : users) {
            userList.getItems().add(new UserListItem(user, userList));
        }
    }

    @FXML
    private void addUser(ActionEvent event) {

    }

}
