package tech.code.challenge.project.entity;

import org.springframework.stereotype.Component;
import tech.code.challenge.project.data.ErrorMessage;
import tech.code.challenge.project.entity.data.Point;
import tech.code.challenge.project.exception.InvalidCanvasException;
import tech.code.challenge.project.exception.InvalidPointException;

@Component
public class DrawOnCanvas implements Drawing {
    @Override
    public void draw(Canvas canvas) throws Exception {
        preCheck(canvas);
    }

    protected void preCheck(Canvas canvas) throws Exception {
        if (canvas == null) {
            throw new InvalidCanvasException(ErrorMessage.CANVAS_IS_NULL);
        }

        if (!canvas.isCanvasValid()) {
            throw new InvalidCanvasException(ErrorMessage.CANVAS_IS_EMPTY);
        }
    }

    protected void preCheckPoint(Point point, Canvas canvas) throws Exception {
        if (!canvas.isValid(point)) {
            throw new InvalidPointException(ErrorMessage.INVALID_POINT);
        }
    }
}
