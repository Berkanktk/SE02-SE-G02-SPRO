package dk.sdu.swe;

import dk.sdu.swe.controllers.AuthController;
import javafx.application.Application;

public class Main {
    public static void main(String[] args) throws Exception {

        AuthController authController = AuthController.getInstance();
        boolean signedIn = authController.signIn("almat20", "alexander");

        Application.launch(FXEntryPoint.class);
    }
}
