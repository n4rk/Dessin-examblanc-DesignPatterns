package figures;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
