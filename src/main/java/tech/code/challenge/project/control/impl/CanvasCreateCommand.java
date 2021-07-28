package tech.code.challenge.project.control.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.code.challenge.project.control.BaseCommand;
import tech.code.challenge.project.data.ErrorMessage;
import tech.code.challenge.project.entity.Canvas;
import tech.code.challenge.project.exception.InvalidCommandException;
import tech.code.challenge.project.utils.Util;

import java.util.List;

@Component
public class CanvasCreateCommand extends BaseCommand {
    private static final int SIZE_OF_COMMANDS = 2;

    private final Canvas canvas;

    @Autowired
    public CanvasCreateCommand(Canvas canvas) {
        this.canvas = canvas;
    }

    private Canvas createCanvas(int width, int height) {
        canvas.construct(width, height);
        return canvas;
    }

    @Override
    public Object runCommand(List<String> args) throws Exception {
        preCheck(args);
        return createCanvas(Integer.parseInt(args.get(0)), Integer.parseInt(args.get(1)));
    }

    protected void preCheck(List<String> args) throws Exception {
        if (args == null || args.size() != SIZE_OF_COMMANDS) {
            throw new InvalidCommandException(ErrorMessage.INVALID_ARGUMENTS);
        }

        for (String element : args) {
            if (!Util.isNumeric(element)) {
                throw new InvalidCommandException(ErrorMessage.ARGUMENT_MUST_BE_NUMERIC);
            }

            if (Integer.parseInt(element) <= 0) {
                throw new InvalidCommandException(ErrorMessage.ARGUMENT_MUST_BE_LARGER_THAN_ZERO);
            }
        }
    }
}
