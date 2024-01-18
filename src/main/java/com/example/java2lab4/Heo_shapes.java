package com.example.java2lab4;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public abstract class Heo_shapes {

    protected ArrayList<int[]> coordinates;

    protected String name;
    protected int edit_num = 1;

    public Heo_shapes(String name, ArrayList<int[]> coordinates) {
        this.coordinates = new ArrayList<>(coordinates);
        this.name = name;
    }

    public Heo_shapes() {}

    public String toString() {
        StringBuilder options = new StringBuilder(name).append("\nКооординаты точек: ");
        for (int i = 0; i < coordinates.size(); i++) {
            options.append("{").append(coordinates.get(i)[0]).append(", ").append(coordinates.get(i)[1]).append("}");
            if (i != coordinates.size() - 1)
                options.append(", ");
        }
        options.append("\n\n");
        return options.toString();
    }
    public abstract void draw(GraphicsContext gc);
    public abstract Heo_shapes move(int ed_x, int ed_y);

    public ArrayList<int[]> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(ArrayList<int[]> coordinates) {
        this.coordinates = coordinates;
    }

    public String getName() {
        return name;
    }

    public ArrayList<int[]> coordinates() {
        return this.coordinates;
    }
}

