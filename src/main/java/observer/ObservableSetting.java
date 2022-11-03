package observer;

import figures.Figure;
import java.util.ArrayList;
import java.util.List;

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
