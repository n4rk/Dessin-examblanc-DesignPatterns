package dessin;

import figures.Figure;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
