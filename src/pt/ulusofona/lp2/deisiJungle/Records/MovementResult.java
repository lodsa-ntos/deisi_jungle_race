package pt.ulusofona.lp2.deisiJungle.Records;

public record MovementResult (MovementResultCode code, String message) {

    public String message(){
        return message;
    }

}