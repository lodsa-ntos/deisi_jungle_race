package pt.ulusofona.lp2.deisiJungle;

public class InvalidInitialJungleException extends Exception {

    private final String message;
    private boolean isInvalidPlayer;
    private boolean isInvalidFood;
    private boolean hasDuplicateIds;



    public InvalidInitialJungleException(String message, boolean isInvalidPlayer, boolean isInvalidFood) {
        this.message = message;
        this.isInvalidPlayer = isInvalidPlayer;
        this.isInvalidFood = isInvalidFood;
    }

    public InvalidInitialJungleException(String message, boolean isInvalidPlayer, boolean isInvalidFood, boolean hasDuplicateIds) {
        this.message = message;
        this.isInvalidPlayer = isInvalidPlayer;
        this.isInvalidFood = isInvalidFood;
        this.hasDuplicateIds = hasDuplicateIds;
    }

    public String getMessage() {
        return message;
    }

    public boolean isInvalidPlayer() {
        return isInvalidPlayer;
    }

    public void setInvalidPlayer(boolean invalidPlayer) {
        isInvalidPlayer = invalidPlayer;
    }

    public boolean isInvalidFood() {
        return isInvalidFood;
    }

    public void setInvalidFood(boolean invalidFood) {
        isInvalidFood = invalidFood;
    }

    public boolean hasDuplicateIds() {
        return hasDuplicateIds;
    }

    public void setHasDuplicateIds(boolean hasDuplicateIds) {
        this.hasDuplicateIds = hasDuplicateIds;
    }
}
