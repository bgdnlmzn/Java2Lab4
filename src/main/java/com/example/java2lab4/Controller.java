package com.example.java2lab4;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {

    @FXML
    private Canvas myCanvas;

    @FXML
    protected void choiseProcess() throws IOException {
        FileChooser fileChooser = new FileChooser();
        Stage st = new Stage();

        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("txt files", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(st);

        if (selectedFile != null) {
            drawHeoShapes(selectedFile.getPath());
        }
    }

    private void drawHeoShapes(String path) throws IOException {
        Group gr = createGrTxt(path);

        GraphicsContext gc = myCanvas.getGraphicsContext2D();

        gc.clearRect(0, 0, myCanvas.getWidth(), myCanvas.getHeight());

        gr.draw(gc);
    }

    public Group createGrTxt(String path) throws IOException {
        File file = new File(path);

        List<String> allLines = Files.readAllLines(file.toPath());
        String text = String.join("\n", allLines);

        Group gr = new Group();

        String[] objectStrings = text.split("\n\n");

        for (String objStr : objectStrings) {
            int count = 0;
            String name = "?";
            List<Integer> listX = new ArrayList<>();
            List<Integer> listY = new ArrayList<>();
            int height = -1;
            int weight = -1;
            int angle = -1;

            String[] lines = objStr.split("\n");
            for (String line : lines) {
                count++;
                Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?");
                Matcher matcher = pattern.matcher(line);
                List<Integer> matches = new ArrayList<>();

                while (matcher.find()) {
                    matches.add(Integer.parseInt(matcher.group()));
                }

                switch (count) {
                    case 1:
                        name = line;
                        break;
                    case 2:
                        for (int i = 0; i < matches.size(); i++) {
                            if (i % 2 == 0) {
                                listX.add(matches.get(i));
                            } else {
                                listY.add(matches.get(i));
                            }
                        }
                        break;
                    case 3:
                        height = matches.get(0);
                        break;
                    case 4:
                        weight = matches.get(0);
                        break;
                    case 5:
                        angle = matches.get(0);
                        break;
                    default:
                        throw new IllegalStateException("Object \"" + name + "\" cannot exist");
                }
            }
            Heo_shapes figure = createFigure(name, listX, listY, height, weight, angle);
            gr.add(figure);
        }
        return gr;
    }

    public static Heo_shapes createFigure(
            String name,
            List<Integer> listX,
            List<Integer> listY,
            int height,
            int weight,
            int angle) {

        Heo_shapes figure = new Point("dwa", new int[]{1, 2});

        if (height != -1) {
            figure = new Ellipse(name, new int[]{listX.get(0), listY.get(0)}, height, weight, angle);
        } else if (listX.size() == 4) {
            figure = new Rectangle(
                    name,
                    new int[]{listX.get(0), listY.get(0)},
                    new int[]{listX.get(1), listY.get(1)},
                    new int[]{listX.get(2), listY.get(2)},
                    new int[]{listX.get(3), listY.get(3)}
            );
        } else if (listX.size() == 3) {
            figure = new Triangle(
                    name,
                    new int[]{listX.get(0), listY.get(0)},
                    new int[]{listX.get(1), listY.get(1)},
                    new int[]{listX.get(2), listY.get(2)}
            );
        } else if (listX.size() == 1) {
            figure = new Point(name, new int[]{listX.get(0), listY.get(0)});
        }

        return figure;
    }
}

