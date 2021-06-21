package Presentation.Views;

/**
 * Thread concebido para hacer repaint de la view Player Action
 *
 * @author Ferran Tio Lopez
 * @author Matias Balzamo
 * @author Francisco Bellver
 * @author Silvia Miralles
 * @author Laia Abad
 * @version 1
 */

public class ThreadPlayerAction implements Runnable {
    private ViewPlayerAction v;
    private int contador;
    //Graphics g;

    public ThreadPlayerAction(ViewPlayerAction v) {
        this.v = v;
        //this.g=g;
    }

    @Override
    public void run() {
        contador = 1200;
        while (true) {
            v.repaint();
            try {
                Thread.sleep(50);
                contador++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public int getContador() {
        return contador;
    }
}
