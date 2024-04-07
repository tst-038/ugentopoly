package be.ugent.objprog.ugentopoly.ui.animations;

import be.ugent.objprog.ugentopoly.data.ResourceLoader;
import javafx.animation.Animation;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Random;

public class MoneyAnimation {
    private static final int NUM_BILLS = 20; // number of money bills
    private static final String MONEY_IMAGE_URL = "assets/money.png"; // URL of the money image
    private static final int SCREEN_WIDTH = 850; // width of the screen
    private static final int SCREEN_HEIGHT = 850; // height of the screen

    public static void start(Pane pane) {
        Random random = new Random();

        for (int i = 0; i < NUM_BILLS; i++) {
            ImageView moneyBill = new ImageView(new Image(ResourceLoader.loadResource(MONEY_IMAGE_URL)));
            int size = random.nextInt(20) + 10; // random size between 50 and 100
            moneyBill.setFitHeight(size);
            moneyBill.setPreserveRatio(true);
            moneyBill.setX(random.nextInt(SCREEN_WIDTH));
            int y = -size - random.nextInt(SCREEN_HEIGHT)*2;
            moneyBill.setY(y); // start offscreen at the top
            moneyBill.setRotate(random.nextInt(360));

            TranslateTransition transition = new TranslateTransition(Duration.seconds(10 + random.nextDouble() * 3), moneyBill);
            transition.setToY(SCREEN_HEIGHT  - y  + 100); // end offscreen at the bottom
            transition.setToX((random.nextInt(SCREEN_WIDTH))* getRandomFactor(random));
            transition.setCycleCount(Animation.INDEFINITE);

            // Add a pause before the transition
            PauseTransition pause = new PauseTransition(Duration.seconds(random.nextDouble() * 10));
            SequentialTransition seqTransition = new SequentialTransition(pause, transition);

            pane.getChildren().addFirst(moneyBill); // Make sure the money is below the other nodes
            seqTransition.play();
        }
    }

    private static double getRandomFactor(Random random) {
        double factor = random.nextDouble() * 2 - 1; // generates a number between -1 and 1
        if (factor == 0) {
            return getRandomFactor(random); // if the factor is 0, recursively call the method until a non-zero value is generated
        } else {
            return factor;
        }
    }
}