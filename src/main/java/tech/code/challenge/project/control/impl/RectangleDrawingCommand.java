package tech.code.challenge.project.control.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.code.challenge.project.control.BaseCommand;
import tech.code.challenge.project.data.ErrorMessage;
import tech.code.challenge.project.entity.DrawOnCanvas;
import tech.code.challenge.project.entity.data.Point;
import tech.code.challenge.project.entity.data.enums.LineSymbol;
import tech.code.challenge.project.entity.impl.RectangleOnCanvas;
import tech.code.challenge.project.exception.InvalidCommandException;
import tech.code.challenge.project.utils.Util;

import java.util.List;

@Component
public class RectangleDrawingCommand extends BaseCommand {
    private static final int SIZE_OF_COMMANDS = 4;

    private final RectangleOnCanvas rectangleOnCanvas;

    private final Point start;

    private final Point end;

    @Autowired
    public RectangleDrawingCommand(RectangleOnCanvas rectangleOnCanvas, Point start, Point end) {
        this.rectangleOnCanvas = rectangleOnCanvas;
        this.start = start;
        this.end = end;
    }

    @Override
    public void draw(DrawOnCanvas rectangle) throws Exception {
        rectangle.draw(canvas);
    }

    @Override
    public Object runCommand(List<String> args) throws Exception {
        preCheck(args);

        construct(start, args.get(0), args.get(1));
        construct(end, args.get(2), args.get(3));

        rectangleOnCanvas.setStart(start);
        rectangleOnCanvas.setEnd(end);
        rectangleOnCanvas.setSymbol(LineSymbol.LINE_SYMBOL);
        draw(rectangleOnCanvas);
        canvas.showCanvas();

        return true;
    }

    protected void preCheck(List<String> args) throws Exception {
        if (args == null || args.size() != SIZE_OF_COMMANDS) {
            throw new InvalidCommandException(ErrorMessage.INVALID_ARGUMENTS);
        }

        for (String element : args) {
            if (!Util.isNumeric(element)) {
                throw new InvalidCommandException(ErrorMessage.ARGUMENT_MUST_BE_NUMERIC);
            }
        }
    }
}
