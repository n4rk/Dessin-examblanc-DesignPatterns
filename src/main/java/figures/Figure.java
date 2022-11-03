package figures;

import observer.ObservableSetting;

import java.io.Serializable;

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
