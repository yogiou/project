package tech.code.challenge.project.boundary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tech.code.challenge.project.ProjectApplication;
import tech.code.challenge.project.data.ErrorMessage;
import tech.code.challenge.project.exception.InvalidCommandException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectApplication.class)
class InterpreterTest {
    @Autowired
    private Interpreter interpreter;

    @Test
    void interpret() {
        try {
            interpreter.interpret("");
        } catch (Exception e) {
            Assertions.assertEquals(new InvalidCommandException(ErrorMessage.INVALID_COMMAND_FORMAT).getMessage(), e.getMessage());
        }

        try {
            interpreter.interpret("F 1 2");
        } catch (Exception e) {
            Assertions.assertEquals(new InvalidCommandException(ErrorMessage.COMMAND_NOT_FOUND).getMessage(), e.getMessage());
        }

        try {
            interpreter.interpret("B");
        } catch (Exception e) {
            Assertions.assertEquals(new InvalidCommandException(ErrorMessage.INVALID_COMMAND_FORMAT).getMessage(), e.getMessage());
        }

        boolean hasException = false;

        try {
            interpreter.interpret("C 20 4");
            interpreter.interpret("L 1 2 1 4");
        } catch (Exception e) {
            hasException = true;
        }

        Assertions.assertFalse(hasException);
    }
}