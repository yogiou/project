package tech.code.challenge.project.control;

import org.springframework.stereotype.Component;
import tech.code.challenge.project.data.ErrorMessage;
import tech.code.challenge.project.entity.Canvas;
import tech.code.challenge.project.entity.DrawOnCanvas;
import tech.code.challenge.project.entity.data.Point;
import tech.code.challenge.project.exception.InvalidCanvasException;
import tech.code.challenge.project.exception.InvalidShapeException;

import java.util.List;

@Component
public class BaseCommand implements RunCommand{
    protected Canvas canvas;

    @Override
    public void draw(DrawOnCanvas drawOnCanvas) throws Exception {
        preCheck(drawOnCanvas, canvas);
    }

    public Object runCommand(List<String> args) throws Exception {
        return true;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    protected void preCheck(DrawOnCanvas drawOnCanvas, Canvas canvas) throws Exception {
        if (canvas == null) {
            throw new InvalidCanvasException(ErrorMessage.CANVAS_IS_NULL);
        }

        if (!canvas.isCanvasValid()) {
            throw new InvalidCanvasException(ErrorMessage.CANVAS_IS_EMPTY);
        }

        if (drawOnCanvas == null) {
            throw new InvalidShapeException(ErrorMessage.SHAPE_MUST_NOT_BE_NULL);
        }
    }

    protected void construct(Point point, String x, String y) {
        point.construct(Integer.parseInt(x), Integer.parseInt(y));
    }
}
