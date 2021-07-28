package tech.code.challenge.project.control.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tech.code.challenge.project.ProjectApplication;
import tech.code.challenge.project.data.ErrorMessage;
import tech.code.challenge.project.exception.TerminateException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectApplication.class)
class QuitCommandTest {
    @Autowired
    private QuitCommand quitCommand;

    @Test
    void runCommand() {
        try {
            quitCommand.runCommand(null);
        } catch (Exception e) {
            Assertions.assertEquals(new TerminateException(ErrorMessage.PROGRAM_TERMINATED).getMessage(), e.getMessage());
        }
    }
}