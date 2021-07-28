package tech.code.challenge.project.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.code.challenge.project.entity.data.Point;
import tech.code.challenge.project.entity.data.enums.CanvasBoundary;

@Component
public class Canvas {
    private int width;
    private int height;
    private String[][] content;

    private final Point point;

    @Autowired
    public Canvas(Point point) {
        this.point = point;
    }

    public void construct(int width, int height) {
        this.width = width + 2; // add 2 for the canvas border
        this.height = height + 2; // add 2 for the canvas border
        content = new String[this.height][this.width];
        resetCanvas(); // initialize the canvas
        showCanvas();
    }

    public void resetCanvas() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                point.construct(j, i);

                if (i == 0 || i == height - 1) {
                    paint(point, CanvasBoundary.HORIZONTAL_BOUNDARY.toValue());
                } else if (j == 0 || j == width - 1) {
                    paint(point, CanvasBoundary.VERTICAL_BOUNDARY.toValue());
                }  else {
                    paint(point, " ");
                }
            }
        }
    }

    public boolean isValid(Point point) {
        return point.getX() >= 1 && point.getX() < width - 1 && point.getY() >= 1 && point.getY() < height - 1;
    }

    public boolean isPaint(Point point) {
        return !content[point.getY()][point.getX()].equals(" ");
    }

    public void paint(Point point, String element) {
        content[point.getY()][point.getX()] = element;
    }

    public String getCanvas() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                stringBuilder.append(content[i][j]);
            }

            if (i != height - 1) {
                stringBuilder.append("\n");
            }
        }

        return stringBuilder.toString();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isCanvasValid() {
        return content != null && content.length > 2 && content[0].length > 2;
    }

    public void showCanvas() {
        System.out.println(getCanvas());
    }
}
