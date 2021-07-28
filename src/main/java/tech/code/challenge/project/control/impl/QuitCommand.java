package tech.code.challenge.project.control.impl;

import org.springframework.stereotype.Component;
import tech.code.challenge.project.control.BaseCommand;
import tech.code.challenge.project.data.ErrorMessage;
import tech.code.challenge.project.exception.TerminateException;

import java.util.List;

@Component
public class QuitCommand extends BaseCommand {
    @Override
    public Object runCommand(List<String> args) throws Exception {
        throw new TerminateException(ErrorMessage.PROGRAM_TERMINATED);
    }
}
