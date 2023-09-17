package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.ImagePulseAnimation;
import nz.ac.auckland.se206.SceneManager;
import nz.ac.auckland.se206.SceneManager.AppUi;
import nz.ac.auckland.se206.TimeManager;

/** Controller class for the room view. */
public class ForestRoomController implements TimeManager.TimeUpdateListener {

  @FXML private Rectangle door;
  @FXML private Rectangle window;
  @FXML private Rectangle vase;
  @FXML private Label timerLbl;
  private TimeManager timeManager;
  @FXML private Button switchScenes;
  @FXML private ImageView imgViewSpiralPond;

  @FXML private Slider sldOne;
  @FXML private Slider sldTwo;
  @FXML private Slider sldThree;
  @FXML private ImageView imgViewSpiralFrog;
  @FXML private ImageView imgViewMushroom;
  @FXML private ImageView imgViewBug;

  @FXML private Pane pnFishing;

  @FXML private Pane pnFishingOpacity;

  @FXML private Button btnFishingExit;
  @FXML private Pane sldOneDisablePane;

  @FXML private Pane sldTwoDisablePane;

  @FXML private Pane sldThreeDisablePane;

  @FXML private Line threadOne;

  @FXML private Line threadTwo;

  @FXML private Line threadThree;

  @FXML private ImageView imgViewRightArrow;

  String[] images = {"bottle.png", "bottleEyes.png", "BottleM.png"};

  /** Initializes the room view, it is called when the room loads. */
  public void initialize() {

    // Initialization code goes here
    timeManager = TimeManager.getInstance();
    timeManager.registerListener(this);

    // list of images that we can select from randomly
    Image[] images = {
      new Image("/images/bottleBug.png"),
      new Image("/images/bottleEyes.png"),
      new Image("/images/BottleRedMushroom.png"),
      new Image("/images/bottleBlueMushroom.png"),
      new Image("/images/bottleSnake.png"),
      new Image("/images/bottleSeaShell.png"),
      new Image("/images/bottleGreenLiq.png")
    };
    List<Image> uniqueImages = new ArrayList<>();

    // Add unique images to the list
    for (Image image : images) {
      if (!uniqueImages.contains(image)) {
        uniqueImages.add(image);
      }
    }

    // Shuffle the list
    // Collections.shuffle(uniqueImages);

    // Convert the shuffled list back to an array
    Image[] shuffledImages = uniqueImages.toArray(new Image[0]);
    // random index to select an image from the list
    // Random rand = new Random();
    // int randomIndex = rand.nextInt(3);
    int randomIndex = 0; // for testing purposes

    Tooltip pondTooltip = new Tooltip("pondimagespiral");
    pondTooltip.setShowDelay(Duration.millis(0));
    Tooltip.install(imgViewSpiralPond, pondTooltip);

    Rotate rotate = new Rotate(-45); // Rotate by 45 degrees

    // Apply the transformation to the Slider
    sldOne.getTransforms().add(rotate);
    sldTwo.getTransforms().add(rotate);
    sldThree.getTransforms().add(rotate);

    sldOne
        .valueProperty()
        .addListener(
            (observable, oldValue, newValue) -> {
              // Add the difference between newValue and oldValue to the Y position of the frog
              imgViewSpiralFrog.setY(
                  imgViewSpiralFrog.getY() + (oldValue.doubleValue() - newValue.doubleValue()));
              // update the end point of line to be higher
              threadOne.setEndY(
                  threadOne.getEndY() + (oldValue.doubleValue() - newValue.doubleValue()));

              // if the frog is at the bottom of the slider, change the pic to be the frog with the
              // fishing rod
              if (newValue.doubleValue() == sldOne.getMax()) {
                // get image with picOne
                Image selectedImage = shuffledImages[0];
                imgViewSpiralFrog.setImage(selectedImage);
                if (randomIndex == 0) {
                  // alert the user that they have found the correct image
                  Platform.runLater(
                      () ->
                          showDialog(
                              "Congratulations!",
                              "You have found the correct ingredient in this room!",
                              "You have found the correct ingredient!"));
                  GameState.itemsCollected++;
                }

                // he slider should not move anymore
                sldOne.lookup(".thumb").setPickOnBounds(false);
                sldOneDisablePane.setVisible(true);
              }
            });

    sldTwo
        .valueProperty()
        .addListener(
            (observable, oldValue, newValue) -> {
              // Add the difference between newValue and oldValue to the Y position of the frog
              imgViewMushroom.setY(
                  imgViewMushroom.getY() + (oldValue.doubleValue() - newValue.doubleValue()));
              threadTwo.setEndY(
                  threadTwo.getEndY() + (oldValue.doubleValue() - newValue.doubleValue()));
              if (newValue.doubleValue() == sldTwo.getMax()) {
                // get an image that hasnt been selected yet
                Image selectedImage = shuffledImages[1];
                imgViewMushroom.setImage(selectedImage);
                if (randomIndex == 1) {
                  // alert the user that they have found the correct image
                  Platform.runLater(
                      () ->
                          showDialog(
                              "Congratulations!",
                              "You have found the correct ingredient in this room!",
                              "You have found the correct ingredient!"));
                  GameState.itemsCollected++;
                }
                // he slider should not move anymore
                sldTwo.lookup(".thumb").setPickOnBounds(false);
                sldTwoDisablePane.setVisible(true);
              }
            });

    sldThree
        .valueProperty()
        .addListener(
            (observable, oldValue, newValue) -> {
              // Add the difference between newValue and oldValue to the Y position of the frog
              imgViewBug.setY(
                  imgViewBug.getY() + (oldValue.doubleValue() - newValue.doubleValue()));
              threadThree.setEndY(
                  threadThree.getEndY() + (oldValue.doubleValue() - newValue.doubleValue()));

              if (newValue.doubleValue() == sldThree.getMax()) {
                // get an image that hasnt been selected yet
                Image selectedImage = shuffledImages[2];
                imgViewBug.setImage(selectedImage);
                // he slider should not move anymore
                if (randomIndex == 2) {
                  // alert the user that they have found the correct image
                  Platform.runLater(
                      () ->
                          showDialog(
                              "Congratulations!",
                              "You have found the correct ingredient in this room!",
                              "You have found the correct ingredient!"));
                  GameState.itemsCollected++;
                }
                sldThree.lookup(".thumb").setPickOnBounds(false);
                sldThreeDisablePane.setVisible(true);
              }
            });

    // Create animation task for clickable objects
    Task<Void> animationTask =
        new Task<Void>() {
          @Override
          protected Void call() throws Exception {
            // The method ImagePulseAnimation is from its own class
            ImagePulseAnimation rightArrowAnimation = new ImagePulseAnimation(imgViewRightArrow);
            rightArrowAnimation.playAnimation();
            return null;
          }
        };

    // Create threads that run each animation task. Start the main animation thread
    Thread animationThread = new Thread(animationTask, "Animation Thread");
    animationThread.start();
  }

  // .
  /**
   * Updates timer label according to the current time that has passed.
   *
   * @param formattedTime the formatted time to display
   */
  @Override
  public void onTimerUpdate(String formattedTime) {
    Platform.runLater(() -> timerLbl.setText(formattedTime));
    // when time is up, show an alert that they have lost
    if (formattedTime.equals("00:00")) {
      Platform.runLater(
          () -> showDialog("Game Over", "You have run out of time!", "You have ran out of time!"));
      timerLbl.setText("00:00");
    }
  }

  /**
   * Displays a dialog box with the given title, header text, and message.
   *
   * @param title the title of the dialog box
   * @param headerText the header text of the dialog box
   * @param message the message content of the dialog box
   */
  private void showDialog(String title, String headerText, String message) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(headerText);
    alert.setContentText(message);
    alert.showAndWait();
  }

  @FXML
  private void onPondClick(MouseEvent event) throws IOException {
    System.out.println("pond clicked");
    // set pnfishing to visible
    pnFishing.setVisible(true);
    pnFishingOpacity.setVisible(true);
  }

  @FXML
  private void onFishingExit(MouseEvent event) throws IOException {
    // set pnfishing to invisible
    pnFishing.setVisible(false);
    pnFishingOpacity.setVisible(false);
  }

  /**
   * Handles the MouseEvent 'on Mouse Clicked' for the imageView imgViewRightArrow.
   *
   * @param event
   */
  @FXML
  private void onRightArrowClicked(MouseEvent event) {
    // Switch to the Lab
    ImageView imgView = (ImageView) event.getSource();
    Scene sceneImageViewIsIn = imgView.getScene();
    sceneImageViewIsIn.setRoot(SceneManager.getUi(AppUi.LAB));
  }
}
