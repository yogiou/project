package tech.code.challenge.project.entity.impl;

import org.springframework.stereotype.Component;
import tech.code.challenge.project.entity.Canvas;
import tech.code.challenge.project.entity.DrawOnCanvas;
import tech.code.challenge.project.entity.Drawing;
import tech.code.challenge.project.entity.data.Point;

import java.util.Stack;

@Component
public class FillColorOnCanvas extends DrawOnCanvas implements Drawing {
    private Point connectedPointToFill;
    private String colorCode;

    public void construct(Point connectedPointToFill, String colorCode) {
        this.connectedPointToFill = connectedPointToFill;
        this.colorCode = colorCode;
    }

    @Override
    public void draw(Canvas canvas) throws Exception {
        super.draw(canvas);

        preCheckPoint(connectedPointToFill, canvas);

        fill(canvas);
    }

    private void fill(Canvas canvas) {
        Stack<Point> stack = new Stack<>();

        int[][] visited = new int[canvas.getHeight()][canvas.getWidth()];

        stack.push(connectedPointToFill);

        while (!stack.isEmpty()) {
            Point currentPoint = stack.pop();

            if (canvas.isValid(currentPoint) && !canvas.isPaint(currentPoint)) {
                visited[currentPoint.getY()][currentPoint.getX()] = 1;
                canvas.paint(currentPoint, colorCode);

                Point up = new Point();
                up.construct(currentPoint.getX(), currentPoint.getY() - 1);

                Point down = new Point();
                down.construct(currentPoint.getX(), currentPoint.getY() + 1);

                Point left = new Point();
                left.construct(currentPoint.getX() - 1, currentPoint.getY());

                Point right = new Point();
                right.construct(currentPoint.getX() + 1, currentPoint.getY());

                if (visited[up.getY()][up.getX()] != 1) {
                    stack.push(up);
                }

                if (visited[down.getY()][down.getX()] != 1) {
                    stack.push(down);
                }

                if (visited[left.getY()][left.getX()] != 1) {
                    stack.push(left);
                }

                if (visited[right.getY()][right.getX()] != 1) {
                    stack.push(right);
                }
            }
        }
    }
}
