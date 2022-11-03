package figures;

import java.io.Serializable;

public class Rectangle extends Figure implements Serializable {
    private Point coin;
    private int L;
    private int H;

    public Rectangle(Point coin, int L, int H) {
        this.coin = coin;
        this.L = L;
        this.H = H;
    }

    @Override
    public void display() {
        System.out.println("Rectangle : coin = "+ coin +" | largeur = "+ L + " | Hauteur = "+ H);
        super.display();
        System.out.println("******************************************************************");
    }

    // Calcul du périmètre du rectangle : 2 * Hauteur+1
    @Override
    public float calculPerimetre() {
        return (float) (2*(H+1));
    }

    // Calcul de surface du rectangle : Largeur * Hauteur
    @Override
    public float calculSurface() {
        return (float) (L*H);
    }
}
