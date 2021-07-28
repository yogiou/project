package tech.code.challenge.project.control.impl;

import org.junit.jupiter.api.Assertions;
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
class CanvasCreateCommandTest {
    @Autowired
    private CanvasCreateCommand canvasCreateCommand;

    @Test
    void runCommand() throws Exception {
        try {
            canvasCreateCommand.runCommand(null);
        } catch (Exception e) {
            Assertions.assertEquals(new InvalidCommandException(ErrorMessage.INVALID_ARGUMENTS).getMessage(), e.getMessage());
        }

        try {
            List<String> args = new ArrayList<>();
            args.add("c");
            args.add("1");
            canvasCreateCommand.runCommand(args);
        } catch (Exception e) {
            Assertions.assertEquals(new InvalidCommandException(ErrorMessage.ARGUMENT_MUST_BE_NUMERIC).getMessage(), e.getMessage());
        }

        try {
            List<String> args = new ArrayList<>();
            args.add("1");
            args.add("c");
            canvasCreateCommand.runCommand(args);
        } catch (Exception e) {
            Assertions.assertEquals(new InvalidCommandException(ErrorMessage.ARGUMENT_MUST_BE_NUMERIC).getMessage(), e.getMessage());
        }

        try {
            List<String> args = new ArrayList<>();
            args.add("0");
            args.add("0");
            canvasCreateCommand.runCommand(args);
        } catch (Exception e) {
            Assertions.assertEquals(new InvalidCommandException(ErrorMessage.ARGUMENT_MUST_BE_LARGER_THAN_ZERO).getMessage(), e.getMessage());
        }

        List<String> args = new ArrayList<>();
        args.add("20");
        args.add("4");
        Assertions.assertEquals(Canvas.class, canvasCreateCommand.runCommand(args).getClass());
    }
}