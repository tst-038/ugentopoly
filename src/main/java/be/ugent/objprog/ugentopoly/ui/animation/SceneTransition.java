package be.ugent.objprog.ugentopoly.ui.animation;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.concurrent.atomic.AtomicInteger;

public class SceneTransition {
    private Stage stage;
    private Scene currentScene;
    private Scene newScene;

    public SceneTransition(Stage stage) {
        this.stage = stage;
    }

    public void transitionToScene(Scene newScene) {
        this.currentScene = stage.getScene();
        this.newScene = newScene;
        fadeOutCurrentScene();
    }

    private void fadeOutCurrentScene() {
        AtomicInteger counter = new AtomicInteger(0);

        currentScene.getRoot().getChildrenUnmodifiable().forEach(node -> {
            FadeTransition fadeOutTransition = createFadeOutTransition(node, counter);
            fadeOutTransition.play();
        });
    }

    private FadeTransition createFadeOutTransition(Node node, AtomicInteger counter) {
        FadeTransition fadeOutTransition = new FadeTransition(Duration.seconds(1), node);
        fadeOutTransition.setFromValue(1.0);
        fadeOutTransition.setToValue(0.0);
        fadeOutTransition.setInterpolator(Interpolator.EASE_BOTH);

        counter.getAndIncrement();

        if (counter.get() == currentScene.getRoot().getChildrenUnmodifiable().size()) {
            fadeOutTransition.setOnFinished(event -> {
                stage.setScene(newScene);
                fadeInNewScene();
            });
        }

        return fadeOutTransition;
    }

    private void fadeInNewScene() {
        newScene.getRoot().getChildrenUnmodifiable().forEach(node -> {
            node.setOpacity(0.0);
            FadeTransition fadeInTransition = createFadeInTransition(node);
            fadeInTransition.play();
        });
    }

    private FadeTransition createFadeInTransition(Node node) {
        FadeTransition fadeInTransition = new FadeTransition(Duration.seconds(1), node);
        fadeInTransition.setFromValue(0.0);
        fadeInTransition.setToValue(1.0);
        fadeInTransition.setInterpolator(Interpolator.EASE_BOTH);

        return fadeInTransition;
    }
}
