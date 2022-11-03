# Examen Blanc - Design Pattern et Programmation Orientée Aspect

* Design Patterns utilisés : Observer - Strategy - Composite
* Diagramme de classes :

![ClassDiagram](https://user-images.githubusercontent.com/62244067/199807688-a24df937-5876-4b7e-84e6-a8e85b998317.jpg)

#### Projet :
![1](https://user-images.githubusercontent.com/62244067/199807960-2b0ace7f-95c5-4854-82ee-2fc5bebe7eba.jpg)

#### Classe Point :
```java
public class Point implements Serializable {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point() {}

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "[" + x +"," + y +"]";
    }
}
```
#### Classe abstraite Figure :
```java
public abstract class Figure implements Serializable {
    private int strokeWeight = 1;
    private int strokeColor = 4;
    private int bgColor = 7;

    public abstract float calculPerimetre();
    public abstract float calculSurface();
    public void display() {
        System.out.println("Stroke Weight = " + strokeWeight);
        System.out.println("Stroke Color = " + strokeColor);
        System.out.println("Stroke Weight = " + bgColor);
    }
    public void update(ObservableSetting observableSetting) {
        this.strokeWeight = observableSetting.getStrokeWeight();
        this.strokeColor = observableSetting.getStrokeColor();
        this.bgColor = observableSetting.getBgColor();
    }
}
```
#### Classes héritantes de Figure (Rectangle, Cercle, GroupeFigures)
```java
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
```
```java
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
```
```java
public class GroupeFigures extends Figure implements Serializable {
    private List<Figure> figures = new ArrayList<>();

    // Affichage des figures
    @Override
    public void display() {
        display(figures);
    }
    public void display(List<Figure> groupeFigures) {
        for(Figure f : groupeFigures) {
            if(f instanceof GroupeFigures)
                display(((GroupeFigures)f).figures);
            else
                f.display();
        }
    }

    // Calcul du périmètre de figures
    @Override
    public float calculPerimetre() {
        return calculPerimetre(figures);
    }
    public float calculPerimetre(List<Figure> groupeFigures) {
        float perimetre = 0;
        for (Figure f: groupeFigures) {
            if(f instanceof  GroupeFigures)
                display(((GroupeFigures)f).figures);
            else
                perimetre += f.calculPerimetre();
        }
        return perimetre;
    }

    // Calcul de surface de figures
    @Override
    public float calculSurface() {
        return calculSurface(figures);
    }
    public float calculSurface(List<Figure> groupeFigures) {
        float surface = 0;
        for (Figure f : groupeFigures) {
            if(f instanceof  GroupeFigures)
                display(((GroupeFigures)f).figures);
            else
                surface += f.calculSurface();
        }
        return surface;
    }

    // Ajout d'une figure au groupe de figures
    public void addFigure(Figure f){
        this.figures.add(f);
    }

    // Supprimer une figure du groupe de figures
    public void removeFigure(Figure f){
        this.figures.remove(f);
    }

}
```
#### Classe ObservableSetting
```java
public class ObservableSetting {
    private int strokeWeight;
    private int strokeColor;
    private int bgColor;
    private List<Figure> figures = new ArrayList<>();

    public ObservableSetting() {
        this.strokeWeight = 1;
        this.strokeColor = 4;
        this.bgColor = 7;
    }

    public void addFigure(Figure figure) {
        this.figures.add(figure);
    }

    public void removeFigure(Figure figure) {
        this.figures.remove(figure);
    }

    public void notifyFigure() {
        for (Figure f : figures) {
            f.update(this);
        }
    }

    public int getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(int strokeColor) {
        this.strokeColor = strokeColor;
        this.notifyFigure();
    }

    public int getBgColor() {
        return bgColor;
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
        this.notifyFigure();
    }

    public int getStrokeWeight() {
        return strokeWeight;
    }

    public void setStrokeWeight(int strokeWeight) {
        this.strokeWeight = strokeWeight;
        this.notifyFigure();
    }
}
```
#### Interface ITraitement et Implémentations
```java
public interface ITraitement {
    void traiter(List<Figure> figureList);
}
```
```java
public class TraitementImpl1 implements ITraitement {
    @Override
    public void traiter(List<Figure> figureList) {
        System.out.println("Traitement en utilisant l'algorithme 1 !");
    }
}
```
```java
public class TraitementImpl2 implements ITraitement {
    @Override
    public void traiter(List<Figure> figureList) {
        System.out.println("Traitement en utilisant l'algorithme 2 !");
    }
}
```
#### Classe Dessin
```java
public class Dessin implements Serializable {
    private List<Figure> figureList = new ArrayList<>();
    private ITraitement iTraitement = new TraitementImpl1();

    public ITraitement getITraitement() {
        return iTraitement;
    }
    public void setITraitement(ITraitement iTrait) {
        this.iTraitement = iTrait;
    }

    public void traiter() {
        this.iTraitement.traiter(this.figureList);
    }
    public void addFigure(Figure f) {
        this.figureList.add(f);
    }
    public void removeFigure(Figure f) {
        this.figureList.remove(f);
        //System.out.println(""+f.getClass().getSimpleName() + " removed from group!");
    }

}
```
#### Classe de Test
```java
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
```

------------------------------------------
#### Exécution
```
---- Affichage du Cercle 1 : ----
Cercle : centre = [0,0] | rayon = 5
Stroke Weight = 1
Stroke Color = 4
Stroke Weight = 7
******************************************************************
---- Affichage du Rectangle 1 : ----
Rectangle : coin = [0,0] | largeur = 20 | Hauteur = 5
Stroke Weight = 1
Stroke Color = 4
Stroke Weight = 7
******************************************************************
---- Affichage du Groupe 1 : ----
Rectangle : coin = [0,0] | largeur = 20 | Hauteur = 5
Stroke Weight = 1
Stroke Color = 4
Stroke Weight = 7
******************************************************************
Cercle : centre = [0,0] | rayon = 5
Stroke Weight = 1
Stroke Color = 4
Stroke Weight = 7
******************************************************************
Rectangle : coin = [0,0] | largeur = 10 | Hauteur = 15
Stroke Weight = 1
Stroke Color = 4
Stroke Weight = 7
******************************************************************
Rectangle : coin = [0,0] | largeur = 25 | Hauteur = 35
Stroke Weight = 1
Stroke Color = 4
Stroke Weight = 7
******************************************************************
Perimètre = 104.0
Surface = 1025.0
Traitement en utilisant l'algorithme 1 !
Traitement en utilisant l'algorithme 2 !

Process finished with exit code 0
```
