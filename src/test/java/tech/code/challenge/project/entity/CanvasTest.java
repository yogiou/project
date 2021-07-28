package tech.code.challenge.project.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tech.code.challenge.project.ProjectApplication;
import tech.code.challenge.project.entity.data.Point;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectApplication.class)
class CanvasTest {
    @Autowired
    private Canvas canvas;

    @Autowired
    private Point point;

    @BeforeEach
    void setUp() {
        canvas.construct(20, 4);
    }

    @Test
    void isValid() {
        point.construct(1, 1);
        Assertions.assertTrue(canvas.isValid(point));
        point.construct(-1, 1);
        Assertions.assertFalse(canvas.isValid(point));
    }

    @Test
    void isPaint() {
        point.construct(1, 1);
        Assertions.assertFalse(canvas.isPaint(point));
    }

    @Test
    void getWidth() {
        Assertions.assertEquals(22, canvas.getWidth());
    }

    @Test
    void getHeight() {
        Assertions.assertEquals(6, canvas.getHeight());
    }

    @Test
    void isCanvasValid() {
        Assertions.assertTrue(canvas.isCanvasValid());
    }

    @Test
    void resetCanvas() {
        Assertions.assertEquals("----------------------\n" +
                "|                    |\n" +
                "|                    |\n" +
                "|                    |\n" +
                "|                    |\n" +
                "----------------------", canvas.getCanvas());
    }
}