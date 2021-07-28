package tech.code.challenge.project.control.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tech.code.challenge.project.ProjectApplication;
import tech.code.challenge.project.data.ErrorMessage;
import tech.code.challenge.project.entity.Canvas;
import tech.code.challenge.project.exception.InvalidCommandException;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectApplication.class)
class LineDrawingCommandTest {
    @Autowired
    private LineDrawingCommand lineDrawingCommand;

    @Autowired
    private Canvas canvas;

    @BeforeEach
    void setUp() {
        canvas.construct(20, 4);
    }

    @Test
    void runCommand() throws Exception {
        try {
            lineDrawingCommand.runCommand(null);
        } catch (Exception e) {
            Assertions.assertEquals(new InvalidCommandException(ErrorMessage.INVALID_ARGUMENTS).getMessage(), e.getMessage());
        }

        try {
            List<String> args = new ArrayList<>();
            args.add("1");
            args.add("c");
            args.add("c");
            args.add("c");
            lineDrawingCommand.runCommand(args);
        } catch (Exception e) {
            Assertions.assertEquals(new InvalidCommandException(ErrorMessage.ARGUMENT_MUST_BE_NUMERIC).getMessage(), e.getMessage());
        }


        List<String> args = new ArrayList<>();
        args.add("1");
        args.add("1");
        args.add("1");
        args.add("2");
        lineDrawingCommand.setCanvas(canvas);
        Assertions.assertEquals(true, lineDrawingCommand.runCommand(args));
    }
}