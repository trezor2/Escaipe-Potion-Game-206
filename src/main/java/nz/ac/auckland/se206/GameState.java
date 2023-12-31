package nz.ac.auckland.se206;

/** Represents the state of the game. */
public class GameState {

  /** Indicates whether the riddle has been resolved. */
  public static boolean isRiddleResolved = false;

  /** Indicates whether the key has been found. */
  public static boolean isKeyFound = false;

  /** Indicates whether the lever is pulled. */
  public static boolean isLeverPulled = false;

  /** Indicates how many matches have occured in the dragon match game. */
  public static int dragonMatches = 0;

  /** Indicates whether the dragon match game is won. */
  public static boolean isMatchGameWon = false;

  /** Indicates whether the user is matching tiles. */
  public static boolean matching = false;

  /** Indicates whether the user has collected the dragon scale. */
  public static boolean isScaleCollected = false;

  /** Indicates how many items the user has collected. */
  public static int itemsCollected = 0;

  /** Indicates whether the user has fished up the correct ingredient. */
  public static boolean isFishingComplete = false;

  /** Indicates whether the user has collected the correct ingredient for the forest Room. */
  public static boolean isForestCollected = false;

  /** Indicates whether the user has collected the correct ingredient for the lab Room. */
  public static boolean isLabCollected = false;

  /** Indicates the number of hints the user has used. */
  public static int numberOfHints;

  /** Indicates the level of the game. */
  public static String level; // easy, medium, hard

  /** Indicates the current room. */
  public static String currentRoom = "lab";

  /** Indicates if the potion has been created. */
  public static boolean isPotionComplete = false;

  /** Indicates if the game has been won. */
  public static boolean isWon = false;

  /** Indicates the correct answer to the riddle. */
  public static String correctAnswer;

  /** Indicates if sounds are enabled. */
  public static boolean isSoundEnabled = true;

  /** Indicates if text to speech is enabled. */
  public static boolean isTextToSpeechEnabled = true;

  /** Method that is called to reset all variables relevant to creating a new game. */
  public static void reset() {
    // reset all variables to default values
    isRiddleResolved = false;
    isKeyFound = false;
    isLeverPulled = false;
    dragonMatches = 0;
    isMatchGameWon = false;
    matching = false;
    isScaleCollected = false;
    itemsCollected = 0;
    isFishingComplete = false;
    isForestCollected = false;
    isLabCollected = false;
    numberOfHints = 0;
    currentRoom = "lab";
    isPotionComplete = false;
    isWon = false;
  }

  public static void setHints(int n) {
    numberOfHints = n;
  }

  public static int getNumberOfHints() {
    return numberOfHints;
  }

  public static void setLevel(String l) {
    level = l;
  }

  public static String getLevel() {
    return level;
  }
}
