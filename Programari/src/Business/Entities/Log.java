package Business.Entities;

/**
 * Representa a una fila de la vista log
 *
 * @author Ferran Tio Lopez
 * @author Matias Balzamo
 * @author Francisco Bellver
 * @author Silvia Miralles
 * @author Laia Abad
 * @version 1
 */

public class Log {
    private Character character;
    private String room;
    private int instant;

    public Log (Character character, String room, int instant) {
        this.character = character;
        this.room = room;
        this.instant = instant;
    }

    /**
     * Getters y setters
     */

    public Character getCharacter() {
        return character;
    }

    public int getInstant() {
        return instant;
    }

    public String getRoom() {
        return room;
    }
}
