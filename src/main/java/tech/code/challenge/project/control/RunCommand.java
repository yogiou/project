package tech.code.challenge.project.control;

import tech.code.challenge.project.entity.DrawOnCanvas;

public interface RunCommand {
    void draw(DrawOnCanvas drawOnCanvas) throws Exception;
}
