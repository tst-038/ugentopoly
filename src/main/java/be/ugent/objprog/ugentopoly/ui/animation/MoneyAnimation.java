package be.ugent.objprog.ugentopoly.ui.animation;

import be.ugent.objprog.ugentopoly.data.ResourceLoader;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Random;

public class MoneyAnimation {
    private static final String MONEY_IMAGE_URL = "assets/money.png";
    private static final int SCREEN_WIDTH = 850;
    private static final int SCREEN_HEIGHT = 850;
    private final int cycleCount;
    private final Random random = new Random();

    public MoneyAnimation(int cycleCount) {
        this.cycleCount = cycleCount;
    }

    private double getRandomFactor(Random random) {
        double factor = random.nextDouble() * 2 - 1;
        if (factor == 0) {
            return getRandomFactor(random);
        } else {
            return factor;
        }
    }

    public void play(Pane pane, int numBills) {
        for (int i = 0; i < numBills; i++) {
            ImageView moneyBill = new ImageView(new Image(ResourceLoader.loadResource(MONEY_IMAGE_URL)));
            int size = random.nextInt(20) + 10;
            moneyBill.setFitHeight(size);
            moneyBill.setPreserveRatio(true);
            moneyBill.setX(random.nextInt(SCREEN_WIDTH));
            int y = -size - random.nextInt(SCREEN_HEIGHT) * 2;
            moneyBill.setY(y);
            moneyBill.setRotate(random.nextInt(360));

            TranslateTransition transition = new TranslateTransition(Duration.seconds(10 + random.nextDouble() * 3), moneyBill);
            transition.setToY(SCREEN_HEIGHT - y + 100);
            transition.setToX((random.nextInt(SCREEN_WIDTH)) * getRandomFactor(random));
            transition.setCycleCount(cycleCount);

            PauseTransition pause = new PauseTransition(Duration.seconds(random.nextDouble() * 10));
            SequentialTransition seqTransition = new SequentialTransition(pause, transition);

            pane.getChildren().addFirst(moneyBill);
            seqTransition.play();
        }
    }
}