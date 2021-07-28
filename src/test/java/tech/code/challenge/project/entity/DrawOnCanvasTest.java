package tech.code.challenge.project.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tech.code.challenge.project.ProjectApplication;
import tech.code.challenge.project.data.ErrorMessage;
import tech.code.challenge.project.exception.InvalidCanvasException;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectApplication.class)
class DrawOnCanvasTest {
    @Autowired
    private DrawOnCanvas drawOnCanvas;

    @Autowired
    private Canvas canvas;

    @Test
    void draw() {
        try {
            drawOnCanvas.draw(null);
        } catch (Exception e) {
            Assertions.assertEquals(new InvalidCanvasException(ErrorMessage.CANVAS_IS_NULL).getMessage(), e.getMessage());
        }

        try {
            canvas.construct(0, 0);
            drawOnCanvas.draw(canvas);
        } catch (Exception e) {
            Assertions.assertEquals(new InvalidCanvasException(ErrorMessage.CANVAS_IS_EMPTY).getMessage(), e.getMessage());
        }
    }
}