package com.example.java2lab4;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Collections;

public class Ellipse extends Heo_shapes implements Tools_one {

    private int height, weight, angle;

    public Ellipse(String name, int[] numbers, int height, int weight, int angle) {
        super(name, new ArrayList<>(Collections.singletonList(numbers)));
        this.height = height;
        this.weight = weight;
        this.angle = angle;
    }

    @Override
    public String toString() {
        return super.toString().substring(0, super.toString().length() - 2) + "\nВысота: " + height + "\nШирина: " + weight + "\nУгол наклона: " + angle + "\n\n";
    }

    @Override
    public void draw(GraphicsContext gc) {
        double angleRad = Math.toRadians(angle);
        double x = super.coordinates.get(0)[0] - (this.height / 2.0) * Math.cos(angleRad) + (this.weight / 2.0) * Math.sin(angleRad);
        double y = super.coordinates.get(0)[1] - (this.height / 2.0) * Math.sin(angleRad) - (this.height / 2.0) * Math.cos(angleRad);
        gc.save();
        gc.translate(super.coordinates.get(0)[0], super.coordinates.get(0)[1]);
        gc.rotate(angle);
        gc.strokeOval(-this.height / 2.0, -this.weight / 2.0, this.height, this.weight);
        gc.restore();
    }

    @Override
    public Heo_shapes move(int ed_x, int ed_y) {
        return new Ellipse(super.name + " (" + (super.edit_num + 1) + ")", new int[] {super.coordinates.get(0)[0] + ed_x, super.coordinates.get(0)[1] + ed_y}, height, weight, angle);
    }

    @Override
    public double count_area() {
        double half_h = height / 2.0, half_w = weight / 2.0;

        return Math.PI * half_h * half_w;
    }

    @Override
    public Heo_shapes enlarge(double x) {
        return new Ellipse(super.name + " (" + (super.edit_num + 1) + ")", super.coordinates.get(0), (int) (height * x), (int) (weight * x), angle);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }
}


