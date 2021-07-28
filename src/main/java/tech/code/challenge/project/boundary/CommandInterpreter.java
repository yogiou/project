package tech.code.challenge.project.boundary;;

public interface CommandInterpreter {
    void interpret(String command) throws Exception;
}
