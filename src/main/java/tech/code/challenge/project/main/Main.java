package tech.code.challenge.project.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.code.challenge.project.boundary.Interpreter;
import tech.code.challenge.project.exception.BaseException;
import tech.code.challenge.project.exception.TerminateException;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class Main {
    private final Interpreter interpreter;

    @Autowired
    public Main(Interpreter interpreter) {
        this.interpreter = interpreter;
    }

    public void run() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.print("enter command: ");
            String command = reader.readLine();

            try {
                interpreter.interpret(command);
            } catch (BaseException e) {
                System.out.println(e.getMessage());
            } catch (TerminateException e) {
                System.out.println(e.getMessage());
                return;
            }
        }
    }
}
