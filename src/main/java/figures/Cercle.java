package figures;

import java.io.Serializable;

public class Cercle extends Figure implements Serializable {
    private Point center;
    private int R;

    public Cercle(Point center, int R) {
        this.center = center;
        this.R = R;
    }

    @Override
    public void display() {
        System.out.println("Cercle : centre = "+ center+" | rayon = "+ R);
        super.display();
        System.out.println("******************************************************************");
    }

    // Calcul du périmètre du cercle : 2 * PI * Rayon
    @Override
    public float calculPerimetre() {
        return (float) (2 * Math.PI * R);
    }

    // Calcul de surface du cercle : PI * Rayon au carré
    @Override
    public float calculSurface() {
        return (float) (Math.PI * Math.pow(R, 2));
    }
}
