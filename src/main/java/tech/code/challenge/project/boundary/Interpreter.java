package tech.code.challenge.project.boundary;

import org.springframework.stereotype.Component;
import tech.code.challenge.project.control.BaseCommand;
import tech.code.challenge.project.control.impl.*;
import tech.code.challenge.project.data.Constants;
import tech.code.challenge.project.data.ErrorMessage;
import tech.code.challenge.project.entity.Canvas;
import tech.code.challenge.project.exception.InvalidCommandException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Component
public class Interpreter implements CommandInterpreter {
    private BaseCommand baseCommand;
    private Canvas canvas;
    private final HashMap<String, BaseCommand> commandHashMap;

    @Autowired
    public Interpreter(HashMap<String, BaseCommand> commandHashMap,
                       BucketFillColorCommand bucketFillColorCommand,
                       CanvasCreateCommand canvasCreateCommand,
                       LineDrawingCommand lineDrawingCommand,
                       QuitCommand quitCommand,
                       RectangleDrawingCommand rectangleDrawingCommand
    ) {
        this.commandHashMap = commandHashMap;
        commandHashMap.put("B", bucketFillColorCommand);
        commandHashMap.put("C", canvasCreateCommand);
        commandHashMap.put("L", lineDrawingCommand);
        commandHashMap.put("Q", quitCommand);
        commandHashMap.put("R", rectangleDrawingCommand);
    }

    @Override
    public void interpret(String command) throws Exception {
        String[] commandComponent = preCheck(command);

        List<String> commandList = new ArrayList<>(Arrays.asList(commandComponent).subList(1, commandComponent.length));

        if (baseCommand instanceof CanvasCreateCommand) {
            this.canvas = (Canvas) this.baseCommand.runCommand(commandList);
        } else {
            this.baseCommand.setCanvas(this.canvas);
            this.baseCommand.runCommand(commandList);
        }
    }

    private String[] preCheck(String command) throws Exception {
        if (command == null || command.trim().length() == 0) {
            throw new InvalidCommandException(ErrorMessage.INVALID_COMMAND_FORMAT);
        }

        String[] commandComponent = command.split(Constants.SPACE);

        if (!commandHashMap.containsKey(commandComponent[0])) {
            throw new InvalidCommandException(ErrorMessage.COMMAND_NOT_FOUND);
        }

        this.baseCommand = commandHashMap.get(commandComponent[0]);

        if (commandComponent.length <= 1 && !(this.baseCommand instanceof QuitCommand)) {
            throw new InvalidCommandException(ErrorMessage.INVALID_COMMAND_FORMAT);
        }

        return commandComponent;
    }
}
