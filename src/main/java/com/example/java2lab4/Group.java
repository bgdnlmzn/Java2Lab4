package com.example.java2lab4;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Collections;

public class Group extends Heo_shapes implements Tools_one{

    private static int num_gr = 1;

    private ArrayList<Heo_shapes> mas = new ArrayList<>();
    private ArrayList<ArrayList<Heo_shapes>> grMas = new ArrayList<>();
    public Group(Heo_shapes[] mas) {
        super();
        Collections.addAll(this.mas, mas);
    }
    public Group(ArrayList<Heo_shapes> mas) {
        super();
        this.mas.addAll(mas);
    }
    public Group() {
        super();
    }

    public Group(Heo_shapes element) {
        super();
        this.mas.add(element);
    }

    public void add (Heo_shapes element) {
        mas.add(element);
    }

    public void add (ArrayList<Heo_shapes> mas) {
        this.mas.addAll(mas);
    }

    public void add (Group elements) {
        grMas.add(elements.getMas());
        num_gr += 1;
        //Collections.addAll(mas, elements.getMas().toArray(new Heo_shapes[elements.getMas().size() - 1]));
    }

    public void remove(String name) {
        for (int i = 0; i < mas.size(); i++) {
            if (mas.get(i).getName().equals(name)) {
                mas.remove(i);
                break;
            }
        }
    }

    public void remove(int index) {
        if (mas.size() <= index)
            return;
        mas.remove(index);
    }

    public Heo_shapes getHeoShape(int index) {
        return mas.get(index);
    }

    @Override
    public String toString() {
        StringBuilder description = new StringBuilder();
        for (Heo_shapes ma : mas) {
            description.append(ma.toString());
        }
        return description.toString();
    }

    @Override
    public void draw(GraphicsContext gc) {
        for (Heo_shapes ma : mas) {
            ma.draw(gc);
        }
    }

    @Override
    public Heo_shapes move(int ed_x, int ed_y) {
        int len = mas.size();
        for (int i = 0; i < len; i++) {
            mas.set(i, mas.get(i).move(ed_x, ed_y));
        }

        return null;
    }

    @Override
    public double count_area() {
        double area = 0;
        for (Heo_shapes ma : mas) {
            if (ma instanceof Point)
                continue;
            else if (ma instanceof Triangle)
                area += ((Triangle) ma).count_area();
            else if (ma instanceof Rectangle)
                area += ((Rectangle) ma).count_area();
            else
                area += ((Ellipse) ma).count_area();
        }
        return area;
    }

    @Override
    public Heo_shapes enlarge(double x) {
        double area = 0;
        for (int i = 0; i < mas.size(); i++) {
            if (mas.get(i) instanceof Point)
                continue;
            else if (mas.get(i) instanceof Triangle)
                mas.set(i, ((Triangle) mas.get(i)).enlarge(x));
            else if (mas.get(i) instanceof Rectangle)
                mas.set(i, ((Rectangle) mas.get(i)).enlarge(x));
            else
                mas.set(i, ((Ellipse) mas.get(i)).enlarge(x));
        }
        return null;
    }

    public void setMas(ArrayList<Heo_shapes> mas) {
        this.mas = mas;
    }

    public ArrayList<Heo_shapes> getMas() {
        return mas;
    }
}

