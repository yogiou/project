package tech.code.challenge.project.entity.impl;

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
import tech.code.challenge.project.entity.data.Point;
import tech.code.challenge.project.entity.data.enums.LineSymbol;
import tech.code.challenge.project.exception.InvalidPointException;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectApplication.class)
class LineOnCanvasTest {
    @Autowired
    private Point start;

    @Autowired
    private Point end;

    @Autowired
    private LineOnCanvas lineOnCanvas;

    @Autowired
    private Canvas canvas;

    @BeforeEach
    void setUp() {
        canvas.construct(20, 4);
    }

    @Test
    void draw() throws Exception {
        start.construct(1, 2);
        end.construct(6, 2);

        lineOnCanvas.setStart(start);
        lineOnCanvas.setEnd(end);
        lineOnCanvas.setSymbol(LineSymbol.LINE_SYMBOL);

        lineOnCanvas.draw(canvas);

        Assertions.assertEquals("----------------------\n" +
                        "|                    |\n" +
                        "|xxxxxx              |\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "----------------------",
                canvas.getCanvas());


        start.construct(6, 3);
        end.construct(6, 4);
        lineOnCanvas.setStart(start);
        lineOnCanvas.setEnd(end);
        lineOnCanvas.setSymbol(LineSymbol.LINE_SYMBOL);
        lineOnCanvas.draw(canvas);

        Assertions.assertEquals("----------------------\n" +
                        "|                    |\n" +
                        "|xxxxxx              |\n" +
                        "|     x              |\n" +
                        "|     x              |\n" +
                        "----------------------",
                canvas.getCanvas());

        canvas.resetCanvas();

        start.construct(6, 2);
        end.construct(1, 2);

        lineOnCanvas.setStart(start);
        lineOnCanvas.setEnd(end);
        lineOnCanvas.setSymbol(LineSymbol.LINE_SYMBOL);
        lineOnCanvas.draw(canvas);

        Assertions.assertEquals("----------------------\n" +
                        "|                    |\n" +
                        "|xxxxxx              |\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "----------------------",
                canvas.getCanvas());


        start.construct(6, 4);
        end.construct(6, 3);

        lineOnCanvas.setStart(start);
        lineOnCanvas.setEnd(end);
        lineOnCanvas.setSymbol(LineSymbol.LINE_SYMBOL);
        lineOnCanvas.draw(canvas);

        Assertions.assertEquals("----------------------\n" +
                        "|                    |\n" +
                        "|xxxxxx              |\n" +
                        "|     x              |\n" +
                        "|     x              |\n" +
                        "----------------------",
                canvas.getCanvas());

        try {
            start.construct(-1, -1);
            end.construct(2, 2);

            lineOnCanvas.setStart(start);
            lineOnCanvas.setEnd(end);
            lineOnCanvas.setSymbol(LineSymbol.LINE_SYMBOL);
            lineOnCanvas.draw(canvas);
        } catch (Exception e) {
            Assertions.assertEquals(new InvalidPointException(ErrorMessage.INVALID_POINT).getMessage(), e.getMessage());
        }

        try {
            start.construct(1, 1);
            end.construct(4, 5);

            lineOnCanvas.setStart(start);
            lineOnCanvas.setEnd(end);
            lineOnCanvas.setSymbol(LineSymbol.LINE_SYMBOL);
            lineOnCanvas.draw(canvas);
        } catch (Exception e) {
            Assertions.assertEquals(new InvalidPointException(ErrorMessage.INVALID_POINT).getMessage(), e.getMessage());
        }
    }
}