package com.utn.pokemonunite;

import com.almasb.fxgl.core.serialization.Bundle;
import com.almasb.fxgl.entity.component.Component;

public class PositionComponent extends Component {
    private double x;
    private double y;

    public PositionComponent(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void read(Bundle bundle) {
        x = bundle.get("x");
        y = bundle.get("y");
    }

    public void write(Bundle bundle) {
        bundle.put("x", x);
        bundle.put("y", y);
    }
}
