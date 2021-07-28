package tech.code.challenge.project.entity.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.code.challenge.project.entity.Canvas;
import tech.code.challenge.project.entity.DrawOnCanvas;
import tech.code.challenge.project.entity.data.Point;
import tech.code.challenge.project.entity.data.enums.LineSymbol;

@Component
public class LineOnCanvas extends DrawOnCanvas {
    private Point start;
    private Point end;
    private LineSymbol symbol;

    private final Point point;

    @Autowired
    public LineOnCanvas(Point point) {
        this.point = point;
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

        preCheckPoint(start, canvas);
        preCheckPoint(end, canvas);

        drawLine(canvas);
    }

    private void drawLine(Canvas canvas) {
        if (isValidForHorizontalVerticalLine(start, end)) {
            drawStrictLine(canvas);
        } else if (isValidForSlash(start, end)) {
            // TODO: slash function
            drawStrictLine(canvas);
        }
    }

    private void drawStrictLine(Canvas canvas) {
        for (int i = start.getY(); start.getY() <= end.getY()? i <= end.getY() : i >= end.getY(); i += start.getY() <= end.getY() ? 1 : -1) {
            for (int j = start.getX(); start.getX() <= end.getX()? j <= end.getX() : j >= end.getX(); j += start.getX() <= end.getX() ? 1 : -1) {
                point.construct(j, i);
                canvas.paint(point, symbol.toValue());
            }
        }
    }

    private boolean isValidForSlash(Point start, Point end) {
        return Math.abs(start.getX() - end.getX()) != 0 && Math.abs(start.getX() - end.getX()) == Math.abs(start.getY() - end.getY());
    }

    // TODO: slash function
    private void drawSlash(Canvas canvas){}

    private boolean isValidForHorizontalVerticalLine(Point start, Point end) {
        return start.getX() == end.getX() || start.getY() == end.getY();
    }
}
