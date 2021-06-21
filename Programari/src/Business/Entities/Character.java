package Business.Entities;

/**
 * Representa a un jugador. Puede ser el propio usuario o los jugadores simulados.
 * Sus variables xActual e yActual indican la posicion en el mapa y se utilizan
 * para mostrarlos en la ViewPlayerAction.
 *
 * @author Ferran Tio Lopez
 * @author Matias Balzamo
 * @author Francisco Bellver
 * @author Silvia Miralles
 * @author Laia Abad
 * @version 1
 */

public class Character {
    private int color;
    private int xActual, yActual, xAnterior, yAnterior;

    private int sus; // 0 = unknown, 1 = sus, 2 = not sus

    public Character(int xInicial, int yInicial, int color) {
        this.xActual = xInicial;
        this.yActual = yInicial;
        this.color = color;
        sus = 0;
    }

    public Character() {
    }

    /**
     * Getters y setters
     */

    public int getxActual() {
        return xActual;
    }

    public int getyActual() {
        return yActual;
    }

    public int getColor() {
        return color;
    }

    public void setxActual(int xActual) {
        this.xActual = xActual;
    }

    public void setxAnterior(int xAnterior) {
        this.xAnterior = xAnterior;
    }

    public void setyActual(int yActual) {
        this.yActual = yActual;
    }

    public void setyAnterior(int yAnterior) {
        this.yAnterior = yAnterior;
    }

    public int getxAnterior() {
        return xAnterior;
    }

    public int getyAnterior() {
        return yAnterior;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setSus(int sus) {
        this.sus = sus;
    }

    public int getSus() {
        return sus;
    }
}
