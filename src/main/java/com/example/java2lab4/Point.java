package com.example.java2lab4;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collections;

public class Point extends Heo_shapes {
    public Point(String name, int[] numbers) {
        super(name, new ArrayList<>(Collections.singletonList(numbers)));
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(super.coordinates().get(0)[0], super.coordinates().get(0)[1], 3, 3);
    }

    @Override
    public Heo_shapes move(int ed_x, int ed_y) {
        return new Point(super.name + " (" + (super.edit_num + 1) + ")", new int[] {coordinates.get(0)[0] + ed_x, coordinates.get(0)[1] + ed_y});
    }
}

