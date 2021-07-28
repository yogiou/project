package tech.code.challenge.project.control.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.code.challenge.project.control.BaseCommand;
import tech.code.challenge.project.data.ErrorMessage;
import tech.code.challenge.project.entity.DrawOnCanvas;
import tech.code.challenge.project.entity.data.Point;
import tech.code.challenge.project.entity.impl.FillColorOnCanvas;
import tech.code.challenge.project.exception.InvalidCommandException;
import tech.code.challenge.project.utils.Util;

import java.util.List;

@Component
public class BucketFillColorCommand extends BaseCommand {
    private static final int SIZE_OF_COMMANDS = 3;

    private final FillColorOnCanvas fillColorOnCanvas;

    private final Point point;

    @Autowired
    public BucketFillColorCommand(FillColorOnCanvas fillColorOnCanvas, Point point) {
        this.fillColorOnCanvas = fillColorOnCanvas;
        this.point = point;
    }

    @Override
    public void draw(DrawOnCanvas color) throws Exception {
        color.draw(canvas);
    }

    @Override
    public Object runCommand(List<String> args) throws Exception {
        preCheck(args);

        construct(point, args.get(0), args.get(1));

        fillColorOnCanvas.construct(point, args.get(2));

        draw(fillColorOnCanvas);
        canvas.showCanvas();

        return true;
    }

    protected void preCheck(List<String> args) throws Exception {
        if (args == null || args.size() != SIZE_OF_COMMANDS) {
            throw new InvalidCommandException(ErrorMessage.INVALID_ARGUMENTS);
        }

        if (!Util.isNumeric(args.get(0)) || !Util.isNumeric(args.get(1))) {
            throw new InvalidCommandException(ErrorMessage.FIRST_TWO_MUST_BE_NUMERIC);
        }

        if (args.get(2).length() != 1 || args.get(2).length() == 1 && !Character.isLetter(args.get(2).charAt(0))) {
            throw new InvalidCommandException(ErrorMessage.THIRD_MUST_BE_SINGLE_CHARACTER);
        }
    }
}
