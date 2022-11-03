package dessin;

import figures.Figure;

import java.util.List;

public class TraitementImpl2 implements ITraitement {

    @Override
    public void traiter(List<Figure> figureList) {
        System.out.println("Traitement en utilisant l'algorithme 2 !");
        System.out.println("taille de figures : " + figureList.size());
    }
}
