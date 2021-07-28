package tech.code.challenge.project.control;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tech.code.challenge.project.ProjectApplication;
import tech.code.challenge.project.data.ErrorMessage;
import tech.code.challenge.project.entity.Canvas;
import tech.code.challenge.project.entity.data.Point;
import tech.code.challenge.project.entity.data.enums.LineSymbol;
import tech.code.challenge.project.entity.impl.LineOnCanvas;
import tech.code.challenge.project.exception.InvalidCanvasException;
import tech.code.challenge.project.exception.InvalidShapeException;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectApplication.class)
class BaseCommandTest {
    @Autowired
    private BaseCommand baseCommand;

    @Autowired
    private Point start;

    @Autowired
    private Point end;

    @Autowired
    private Canvas canvas;

    @Autowired
    private LineOnCanvas line;

    @Test
    void draw() {
        try {
            baseCommand.setCanvas(null);
            baseCommand.draw(null);
        } catch (Exception e) {
            Assertions.assertEquals(new InvalidCanvasException(ErrorMessage.CANVAS_IS_NULL).getMessage(), e.getMessage());
        }

        try {
            start.construct(1, 1);
            end.construct(1, 2);
            line.setStart(start);
            line.setEnd(end);
            line.setSymbol(LineSymbol.LINE_SYMBOL);

            canvas.construct(0, 0);
            baseCommand.setCanvas(canvas);
            baseCommand.draw(line);
        } catch (Exception e) {
            Assertions.assertEquals(new InvalidCanvasException(ErrorMessage.CANVAS_IS_EMPTY).getMessage(), e.getMessage());
        }

        try {
            canvas.construct(100, 4);
            baseCommand.setCanvas(canvas);
            baseCommand.draw(null);
        } catch (Exception e) {
            Assertions.assertEquals(new InvalidShapeException(ErrorMessage.SHAPE_MUST_NOT_BE_NULL).getMessage(), e.getMessage());
        }

        try {
            start.construct(1, 1);
            end.construct(1, 2);
            line.setStart(start);
            line.setEnd(end);
            line.setSymbol(LineSymbol.LINE_SYMBOL);

            baseCommand.draw(line);
        } catch (Exception e) {
            Assertions.assertEquals(new InvalidShapeException(ErrorMessage.CANVAS_IS_NULL).getMessage(), e.getMessage());
        }

        try {
            canvas.construct(0, 0);
            baseCommand.setCanvas(canvas);

            start.construct(1, 1);
            end.construct(1, 2);
            line.setStart(start);
            line.setEnd(end);
            line.setSymbol(LineSymbol.LINE_SYMBOL);

            baseCommand.draw(line);
        } catch (Exception e) {
            Assertions.assertEquals(new InvalidShapeException(ErrorMessage.CANVAS_IS_EMPTY).getMessage(), e.getMessage());
        }
    }

    @Test
    void runCommand() throws Exception {
        List<String> args = new ArrayList<>();
        args.add("B");
        args.add("1");
        args.add("2");

        Assertions.assertEquals(true, baseCommand.runCommand(args));
    }
}