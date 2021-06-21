package Business.Entities;

/**
 * Representa a un crew (jugador bueno, opuesto del impostor).
 * Con el boolean isAlive sabemos si se tiene que seguir moviendo o no. Como
 * hereda de Character trabajamos con sus xActual, yActual para saber su posición y
 * xAnterior e yAnterior para evitar que vuelva por el mismo camino.
 *
 * @author Ferran Tio Lopez
 * @author Matias Balzamo
 * @author Francisco Bellver
 * @author Silvia Miralles
 * @author Laia Abad
 * @version 1
 */

public class Crew extends Character {
    private boolean isAlive;

    public Crew (int xInicial, int yInicial,int color) {
        super(xInicial, yInicial, color);
        setIsAlive(true);
    }

    /**
     * Getters y setters
     */
    public boolean isAlive() {
        return isAlive;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

}
