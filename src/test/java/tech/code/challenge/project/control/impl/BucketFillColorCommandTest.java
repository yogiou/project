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
class BucketFillColorCommandTest {
    @Autowired
    private BucketFillColorCommand bucketFillColorCommand;

    @Autowired
    private Canvas canvas;

    @BeforeEach
    void setUp() {
        canvas.construct(20, 4);
    }

    @Test
    void runCommand() throws Exception {
        try {
            bucketFillColorCommand.runCommand(null);
        } catch (Exception e) {
            Assertions.assertEquals(new InvalidCommandException(ErrorMessage.INVALID_ARGUMENTS).getMessage(), e.getMessage());
        }

        try {
            List<String> args = new ArrayList<>();
            args.add("1");
            args.add("c");
            args.add("c");
            bucketFillColorCommand.runCommand(args);
        } catch (Exception e) {
            Assertions.assertEquals(new InvalidCommandException(ErrorMessage.FIRST_TWO_MUST_BE_NUMERIC).getMessage(), e.getMessage());
        }

        try {
            List<String> args = new ArrayList<>();
            args.add("c");
            args.add("1");
            args.add("c");
            bucketFillColorCommand.runCommand(args);
        } catch (Exception e) {
            Assertions.assertEquals(new InvalidCommandException(ErrorMessage.FIRST_TWO_MUST_BE_NUMERIC).getMessage(), e.getMessage());
        }

        try {
            List<String> args = new ArrayList<>();
            args.add("1");
            args.add("1");
            args.add("1");
            bucketFillColorCommand.runCommand(args);
        } catch (Exception e) {
            Assertions.assertEquals(new InvalidCommandException(ErrorMessage.THIRD_MUST_BE_SINGLE_CHARACTER).getMessage(), e.getMessage());
        }

        try {
            List<String> args = new ArrayList<>();
            args.add("1");
            args.add("1");
            args.add("1c");
            bucketFillColorCommand.runCommand(args);
        } catch (Exception e) {
            Assertions.assertEquals(new InvalidCommandException(ErrorMessage.THIRD_MUST_BE_SINGLE_CHARACTER).getMessage(), e.getMessage());
        }

        try {
            List<String> args = new ArrayList<>();
            args.add("1");
            args.add("1");
            args.add("cc");
            bucketFillColorCommand.runCommand(args);
        } catch (Exception e) {
            Assertions.assertEquals(new InvalidCommandException(ErrorMessage.THIRD_MUST_BE_SINGLE_CHARACTER).getMessage(), e.getMessage());
        }

        List<String> args = new ArrayList<>();
        args.add("1");
        args.add("1");
        args.add("c");
        bucketFillColorCommand.setCanvas(canvas);
        Assertions.assertEquals(true, bucketFillColorCommand.runCommand(args));
    }
}