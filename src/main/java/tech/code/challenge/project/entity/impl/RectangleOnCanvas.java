package tech.code.challenge.project.entity.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.code.challenge.project.entity.Canvas;
import tech.code.challenge.project.entity.DrawOnCanvas;
import tech.code.challenge.project.entity.data.Point;
import tech.code.challenge.project.entity.data.enums.LineSymbol;

@Component
public class RectangleOnCanvas extends DrawOnCanvas {
    private Point start;
    private Point end;
    private LineSymbol symbol;

    private final LineOnCanvas lineOnCanvas;

    private final Point topRightCorner;

    private final Point bottomLeftCorner;

    @Autowired
    public RectangleOnCanvas(LineOnCanvas lineOnCanvas, Point topRightCorner, Point bottomLeftCorner) {
        this.lineOnCanvas = lineOnCanvas;
        this.topRightCorner = topRightCorner;
        this.bottomLeftCorner = bottomLeftCorner;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public void setEnd(Point end) {
        this.end = end;
    }

    public void setSymbol(LineSymbol symbol) {
        this.symbol = symbol;
    }

    @Override
    public void draw(Canvas canvas) throws Exception {
        super.draw(canvas);

        drawRectangle(canvas);
    }

    private void drawRectangle(Canvas canvas) throws Exception {
        preCheckPoint(start, canvas);
        preCheckPoint(end, canvas);

        Point topLeftCorner = start;
//        Point topRightCorner = new Point();
        topRightCorner.construct(end.getX(), start.getY());

//        Point bottomLeftCorner = new Point();
        bottomLeftCorner.construct(start.getX(), end.getY());
        Point bottomRightCorner = end;

        lineOnCanvas.setStart(topLeftCorner);
        lineOnCanvas.setEnd(topRightCorner);
        lineOnCanvas.setSymbol(LineSymbol.LINE_SYMBOL);
        lineOnCanvas.draw(canvas);

        lineOnCanvas.setStart(topLeftCorner);
        lineOnCanvas.setEnd(bottomLeftCorner);
        lineOnCanvas.setSymbol(LineSymbol.LINE_SYMBOL);
        lineOnCanvas.draw(canvas);

        lineOnCanvas.setStart(bottomLeftCorner);
        lineOnCanvas.setEnd(bottomRightCorner);
        lineOnCanvas.setSymbol(LineSymbol.LINE_SYMBOL);
        lineOnCanvas.draw(canvas);

        lineOnCanvas.setStart(topRightCorner);
        lineOnCanvas.setEnd(bottomRightCorner);
        lineOnCanvas.setSymbol(LineSymbol.LINE_SYMBOL);
        lineOnCanvas.draw(canvas);
    }
}
