package Lab6;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.Random;

public class Controller {

    @FXML
    private Spinner<Integer> edgeNum;

    @FXML
    private ToggleButton figurePlacement;

    @FXML
    private ColorPicker figureColour;

    @FXML
    private Canvas canvas;

    @FXML
    private ChoiceBox<String> figures;

    @FXML
    private Button load;

    @FXML
    private Button save;

    @FXML
    private Button reset;

    @FXML
    private Button exit;

    public void loadFigure() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        boolean toggleVal = figurePlacement.isSelected();
        if (toggleVal) {
            prepareRandomPolygon(gc);
        } else {
            prepareOnClickPolygon(gc);
        }
    }

    public void prepareRandomPolygon(GraphicsContext gc) {
        int edges = edgeNum.getValue();
        Color color = figureColour.getValue();
        double canvasH = canvas.getHeight();
        double canvasW = canvas.getWidth();
        Random rand = new Random();
        int radius = rand.ints(50, 100).limit(1).findFirst().getAsInt();
        while (radius % 2 != 0)
            radius = rand.ints(50, 100).limit(1).findFirst().getAsInt();
        int radCenter = radius / 2;
        int centerX = rand.ints(radCenter + 50, (int) (canvasW - radCenter - 50)).limit(1).findFirst().getAsInt();
        int centerY = rand.ints(radCenter + 50, (int) (canvasH - radCenter - 50)).limit(1).findFirst().getAsInt();
        RegularPolygon polygon = new RegularPolygon(centerX, centerY, radius, edges);
        gc.setFill(color);
        gc.fillPolygon(polygon.xCoord, polygon.yCoord, edges);
    }

    public void prepareOnClickPolygon(GraphicsContext gc) {
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int edges = edgeNum.getValue();
                Color color = figureColour.getValue();
                Random rand = new Random();
                int radius = rand.ints(50, 100).limit(1).findFirst().getAsInt();
                while (radius % 2 != 0)
                    radius = rand.ints(50, 100).limit(1).findFirst().getAsInt();
                RegularPolygon polygon = new RegularPolygon((int) mouseEvent.getX(), (int) mouseEvent.getY(), radius, edges);
                gc.setFill(color);
                gc.fillPolygon(polygon.xCoord, polygon.yCoord, edges);
                canvas.removeEventHandler(MouseEvent.MOUSE_CLICKED, this);
            }
        });

    }

    public void saveFigure() {

    }

    public void resetCanvas() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public void exitApp() {
        System.exit(0);
    }
}
