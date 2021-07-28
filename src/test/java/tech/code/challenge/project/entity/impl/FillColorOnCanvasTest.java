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
class FillColorOnCanvasTest {
    @Autowired
    private Point start;

    @Autowired
    private Point end;

    @Autowired
    private Canvas canvas;

    @Autowired
    private LineOnCanvas lineOnCanvas;

    @Autowired
    private RectangleOnCanvas rectangleOnCanvas;

    @Autowired
    private FillColorOnCanvas fillColorOnCanvas;

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

        start.construct(6, 3);
        end.construct(6, 4);

        lineOnCanvas.setStart(start);
        lineOnCanvas.setEnd(end);
        lineOnCanvas.draw(canvas);

        start.construct(14, 1);
        end.construct(18, 3);

        rectangleOnCanvas.setStart(start);
        rectangleOnCanvas.setEnd(end);
        rectangleOnCanvas.setSymbol(LineSymbol.LINE_SYMBOL);
        rectangleOnCanvas.draw(canvas);

        start.construct(10, 3);
        fillColorOnCanvas.construct(start, "o");
        fillColorOnCanvas.draw(canvas);

        Assertions.assertEquals("----------------------\n" +
                        "|oooooooooooooxxxxxoo|\n" +
                        "|xxxxxxooooooox   xoo|\n" +
                        "|     xoooooooxxxxxoo|\n" +
                        "|     xoooooooooooooo|\n" +
                        "----------------------",
                canvas.getCanvas());

        try {
            start.construct(-1, -1);
            fillColorOnCanvas.construct(start, "o");
            fillColorOnCanvas.draw(canvas);
        } catch (Exception e) {
            Assertions.assertEquals(new InvalidPointException(ErrorMessage.INVALID_POINT).getMessage(), e.getMessage());
        }
    }
}