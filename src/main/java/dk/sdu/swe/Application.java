package dk.sdu.swe;

import dk.sdu.swe.data.DB;
import dk.sdu.swe.data.SeederUtility;
import dk.sdu.swe.domain.models.User;
import dk.sdu.swe.helpers.EnvironmentSelector;
import dk.sdu.swe.helpers.Environment;
import dk.sdu.swe.views.AuthViewController;
import dk.sdu.swe.views.Router;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class Application extends javafx.application.Application {
    public static void main(String[] args) throws Exception {
        EnvironmentSelector.getInstance().setEnvironment(switch (System.getenv("DEFAULT_ENVIRONMENT")) {
            case "prod" -> Environment.PROD;
            default -> Environment.LOCAL;
        });

        if(EnvironmentSelector.getInstance().getEnvironment() == Environment.LOCAL) {
            (new Thread(() -> {
                SeederUtility.run();
            })).start();
        }

        try {
            launch();
        } catch (IllegalStateException e) {
            // Ignore JavaFX async whine
        }
    }

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param stage the primary stage for this application, onto which
     *              the application scene can be set.
     *              Applications may create other stages, if needed, but they will not be
     *              primary stages.
     * @throws Exception if something goes wrong
     */
    @Override
    public void start(Stage stage) throws Exception {
        disableWarning();

        stage.setTitle("CrMS");

        StackPane rootNode = new StackPane();
        Router sceneRouter = new Router(rootNode);
        sceneRouter.setFadeAnimation(true);
        Router.setSceneRouter(sceneRouter);

        stage.setScene(new Scene(rootNode, 1500, 900));

        Router.getSceneRouter().goTo(AuthViewController.class);

        stage.show();
    }
    public static void disableWarning() {
        System.err.close();
        System.setErr(System.out);
    }
}
