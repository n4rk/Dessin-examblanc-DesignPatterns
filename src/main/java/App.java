import dessin.*;
import figures.*;
import observer.ObservableSetting;

public class App {
    public static ITraitement algorithm2 = new TraitementImpl2();
    public static void main(String[] args) {
            ObservableSetting setting = new ObservableSetting();
            Dessin dessin = new Dessin();

            Figure cercle1 = new Cercle(new Point(), 5);
            System.out.println("---- Affichage du Cercle 1 : ----");
            cercle1.display();

            Figure rectangle1 = new Rectangle(new Point(), 20, 5);
            System.out.println("---- Affichage du Rectangle 1 : ----");
            rectangle1.display();

            Figure rectangle2 = new Rectangle(new Point(), 10, 15);
            Figure rectangle3 = new Rectangle(new Point(), 25, 35);

            GroupeFigures groupe1 = new GroupeFigures();
            GroupeFigures groupe2 = new GroupeFigures();

            groupe1.addFigure(rectangle1);
            groupe1.addFigure(cercle1);
            groupe2.addFigure(rectangle2);
            groupe2.addFigure(rectangle3);
            groupe1.addFigure(groupe2);


            System.out.println("---- Affichage du Groupe 1 : ----");
            groupe1.display();
            System.out.println("Perimètre = "+groupe2.calculPerimetre());
            System.out.println("Surface = "+groupe2.calculSurface());

            setting.addFigure(cercle1);
            setting.addFigure(rectangle1);
            setting.setStrokeColor(2);
            setting.setBgColor(6);

            dessin.addFigure(groupe1);
            dessin.addFigure(groupe2);
            dessin.traiter();
            dessin.setITraitement(algorithm2);
            dessin.removeFigure(groupe1);
            dessin.traiter();
    }
}
