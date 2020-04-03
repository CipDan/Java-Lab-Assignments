package Lab6;

import Lab6.GraphModel.Edge;
import Lab6.GraphModel.Graph;
import Lab6.GraphModel.Vertex;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Controller for event handling.
 */
public class Controller {
    @FXML
    private Label edgeLabel;

    @FXML
    private Spinner<Integer> edgeNum;

    @FXML
    private Label placementLabel;

    @FXML
    private ToggleButton figurePlacement;

    @FXML
    private Label colorLabel;

    @FXML
    private ColorPicker figureColour;

    @FXML
    private Button showInstructions;

    @FXML
    private Pane canvas;

    @FXML
    private ChoiceBox<String> figures;

    @FXML
    private Button fileChooser;

    @FXML
    private Button deleteFigure;

    @FXML
    private Button load;

    @FXML
    private Button save;

    @FXML
    private Button reset;

    @FXML
    private Button exit;

    private File selectedFile = null;
    private List<Shape> selectedShapes = new ArrayList<>();
    private Graph graph = new Graph();
    private Vertex dragVertex;
    private Point2D elasticEndLocation;
    private Canvas graphCanvas = new Canvas(1170, 586);
    private GraphicsContext aPen = graphCanvas.getGraphicsContext2D();

    EventHandler<MouseEvent> canvasDoubleClicked = new EventHandler<>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            Vertex aVertex = graph.vertexAt(mouseEvent.getX(), mouseEvent.getY());
            if (mouseEvent.getClickCount() == 2) {
                if (aVertex == null) {
                    // We missed a node, now try for an edge midpoint
                    Edge anEdge = graph.edgeAt(mouseEvent.getX(), mouseEvent.getY());
                    if (anEdge == null)
                        graph.addVertex(new Vertex(mouseEvent.getX(), mouseEvent.getY()));
                    else
                        anEdge.toggleSelected();
                } else
                    aVertex.toggleSelected();
                // Update the view, by redrawing the Graph
                updateGraph(aPen);
            } else {
                if (aVertex != null) {
                    dragVertex = aVertex; // If we pressed on a vertex, store it
                }
            }
        }
    };

    EventHandler<MouseEvent> drawEdge = new EventHandler<>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            elasticEndLocation = new Point2D(mouseEvent.getX(), mouseEvent.getY());
            if (dragVertex != null) {
                if (dragVertex.isSelected())
                    dragVertex.setLocation(elasticEndLocation);
            }
            updateGraph(aPen); // We have changed the model, so now update
        }
    };

    EventHandler<MouseEvent> releaseMouse = new EventHandler<>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            Vertex aVertex = graph.vertexAt(mouseEvent.getX(), mouseEvent.getY());
            // Check to see if we have let go on a vertex
            if ((dragVertex != null) && (aVertex != null) && (aVertex != dragVertex))
                // Change the model, by adding a new Edge
                graph.addEdge(dragVertex, aVertex);
            // Update the view, by redrawing the Graph
            dragVertex = null; // No need to remember this anymore
            updateGraph(aPen);
        }
    };

    EventHandler<KeyEvent> removeSelectedVertices = keyEvent -> {
        if (keyEvent.getCode() == KeyCode.X) {
            // Delete all selected Edges
            for (Edge e : graph.selectedEdges())
                graph.deleteEdge(e);
            // Delete all selected Vertices
            for (Vertex n : graph.selectedVertices())
                graph.deleteVertex(n);
            // Update the view, by redrawing the Graph
            updateGraph(aPen);
        }
    };

    public Controller() {
        graphCanvas.requestFocus();
        graphCanvas.setOnMousePressed(canvasDoubleClicked);
        graphCanvas.setOnMouseDragged(drawEdge);
        graphCanvas.setOnMouseReleased(releaseMouse);
        graphCanvas.setOnKeyReleased(removeSelectedVertices);
    }

    public void showInstructionsWindow() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Welcome to Graph Drawing!");
        alert.setContentText("Before drawing, please press the 'Load' button for the following commands to work:" +
                "\n\t\u25CF LMB x 2: Draw Vertex/Select Vertex/Select Edge" +
                "\n\t\u25CF Hold LMB on unselected vertex and drag: Draw \n\t  Edge" +
                "\n\t\u25CF Hold LMB on selected vertex and drag: Move \n\t  Vertex" +
                "\n\t\u25CF Press X: Delete selected vertices and edges");
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(
                getClass().getResource("styling.css").toExternalForm());
        dialogPane.getStyleClass().add("info-box");
        alert.showAndWait();
    }

    /**
     * Loads the drawn Polygon.
     */
    public void loadFigure() {
        boolean toggleVal = figurePlacement.isSelected();
        String option = figures.getValue();
        Color color = figureColour.getValue();
        switch (option) {
            case "Polygon":
                canvas.getChildren().remove(graphCanvas);
                int edges = edgeNum.getValue();
                if (toggleVal) {
                    prepareRandomPolygon(color, edges);
                } else {
                    prepareOnClickPolygon(color, edges);
                }
                break;
            case "Ellipse":
                canvas.getChildren().remove(graphCanvas);
                if (toggleVal) {
                    prepareRandomNShape(color);
                } else {
                    prepareOnClickNShape(color);
                }
                break;
            case "Graph":
                if (!canvas.getChildren().contains(graphCanvas)) {
                    canvas.getChildren().add(graphCanvas);
                    graphCanvas.requestFocus();
                }
                System.out.println(graph.getVertices().size());
                System.out.println(graph.getEdges().size());
                break;
        }
    }

    public void toggleVisibility() {
        switch (figures.getValue()) {
            case "Polygon":
                placementLabel.setManaged(true);
                placementLabel.setVisible(true);
                figurePlacement.setManaged(true);
                figurePlacement.setVisible(true);
                colorLabel.setManaged(true);
                colorLabel.setVisible(true);
                figureColour.setManaged(true);
                figureColour.setVisible(true);
                deleteFigure.setManaged(true);
                deleteFigure.setVisible(true);
                reset.setManaged(true);
                reset.setVisible(true);
                edgeNum.setManaged(true);
                edgeNum.setVisible(true);
                edgeLabel.setManaged(true);
                edgeLabel.setVisible(true);
                showInstructions.setVisible(false);
                showInstructions.setManaged(false);
                break;
            case "Ellipse":
                placementLabel.setManaged(true);
                placementLabel.setVisible(true);
                figurePlacement.setManaged(true);
                figurePlacement.setVisible(true);
                colorLabel.setManaged(true);
                colorLabel.setVisible(true);
                figureColour.setManaged(true);
                figureColour.setVisible(true);
                deleteFigure.setManaged(true);
                deleteFigure.setVisible(true);
                reset.setManaged(true);
                reset.setVisible(true);
                edgeNum.setVisible(false);
                edgeNum.setManaged(false);
                edgeLabel.setVisible(false);
                edgeLabel.setManaged(false);
                showInstructions.setVisible(false);
                showInstructions.setManaged(false);
                break;
            case "Graph":
                placementLabel.setVisible(false);
                placementLabel.setManaged(false);
                figurePlacement.setVisible(false);
                figurePlacement.setManaged(false);
                colorLabel.setVisible(false);
                colorLabel.setManaged(false);
                figureColour.setVisible(false);
                figureColour.setManaged(false);
                deleteFigure.setVisible(false);
                deleteFigure.setManaged(false);
                reset.setVisible(false);
                reset.setManaged(false);
                edgeNum.setVisible(false);
                edgeNum.setManaged(false);
                edgeLabel.setVisible(false);
                edgeLabel.setManaged(false);
                showInstructions.setManaged(true);
                showInstructions.setVisible(true);
                break;
        }
    }

    public void updateGraph(GraphicsContext aPen) {
        aPen.clearRect(0, 0, graphCanvas.getWidth(), graphCanvas.getHeight());
        graph.draw(aPen);
        // Draw the elastic band
        if (dragVertex != null)
            if (!dragVertex.isSelected())
                aPen.strokeLine(dragVertex.getLocation().getX(),
                        dragVertex.getLocation().getY(),
                        elasticEndLocation.getX(),
                        elasticEndLocation.getY());
    }

    public void addEventHandlerToShape(Shape shape) {
        shape.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!selectedShapes.contains(shape)) {
                    shape.setStroke(Color.BLACK);
                    shape.setStrokeWidth(2.0);
                    selectedShapes.add(shape);
                } else {
                    shape.setStroke(null);
                    selectedShapes.remove(shape);
                }
            }
        });
    }

    public int prepareRadius() {
        Random rand = new Random();
        int radius = rand.ints(50, 100).limit(1).findFirst().getAsInt();
        while (radius % 2 != 0)
            radius = rand.ints(50, 100).limit(1).findFirst().getAsInt();
        return radius;
    }

    public void prepareRandomNShape(Color color) {
        double canvasH = canvas.getPrefHeight();
        double canvasW = canvas.getPrefWidth();
        Random rand = new Random();
        int radius = prepareRadius();
        int radCenter = radius / 2;
        int centerX = rand.ints(radCenter + 80, (int) (canvasW - radCenter - 80)).limit(1).findFirst().getAsInt();
        int centerY = rand.ints(radCenter + 80, (int) (canvasH - radCenter - 80)).limit(1).findFirst().getAsInt();
        CustomEllipse customEllipse = new CustomEllipse(centerX, centerY, radius);
        customEllipse.setFill(color);
        addEventHandlerToShape(customEllipse);
        canvas.getChildren().add(customEllipse);
    }

    public void prepareOnClickNShape(Color color) {
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int radius = prepareRadius();
                Bounds bounds = canvas.getBoundsInParent();
                if (Math.abs(bounds.getMinX() - mouseEvent.getX()) > 80 && Math.abs(bounds.getMaxX() - mouseEvent.getX()) > 80
                        && Math.abs(bounds.getMinY() - mouseEvent.getY()) > 80 && Math.abs(bounds.getMaxY() - mouseEvent.getY()) > 80) {
                    CustomEllipse customEllipse = new CustomEllipse((int) mouseEvent.getX(), (int) mouseEvent.getY(), radius);
                    customEllipse.setFill(color);
                    addEventHandlerToShape(customEllipse);
                    canvas.getChildren().add(customEllipse);
                    canvas.removeEventHandler(MouseEvent.MOUSE_CLICKED, this);
                }
            }
        });
    }

    /**
     * Draws a polygon at a random location.
     * <p>
     * the graphics context that handles the canvas.
     */
    public void prepareRandomPolygon(Color color, int edges) {
        double canvasH = canvas.getPrefHeight();
        double canvasW = canvas.getPrefWidth();
        Random rand = new Random();
        int radius = prepareRadius();
        int radCenter = radius / 2;
        int centerX = rand.ints(radCenter + 80, (int) (canvasW - radCenter - 80)).limit(1).findFirst().getAsInt();
        int centerY = rand.ints(radCenter + 80, (int) (canvasH - radCenter - 80)).limit(1).findFirst().getAsInt();
        RegularPolygon polygon = new RegularPolygon(centerX, centerY, radius, edges);
        polygon.setFill(color);
        addEventHandlerToShape(polygon);
        canvas.getChildren().add(polygon);
    }

    /**
     * Creates an event handler for drawing a polygon on mouse click.
     * <p>
     * the graphics context that handles the canvas.
     */
    public void prepareOnClickPolygon(Color color, int edges) {
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int radius = prepareRadius();
                Bounds bounds = canvas.getBoundsInParent();
                if (Math.abs(bounds.getMinX() - mouseEvent.getX()) > 80 && Math.abs(bounds.getMaxX() - mouseEvent.getX()) > 80
                        && Math.abs(bounds.getMinY() - mouseEvent.getY()) > 80 && Math.abs(bounds.getMaxY() - mouseEvent.getY()) > 80) {
                    RegularPolygon polygon = new RegularPolygon((int) mouseEvent.getX(), (int) mouseEvent.getY(), radius, edges);
                    polygon.setFill(color);
                    addEventHandlerToShape(polygon);
                    canvas.getChildren().add(polygon);
                    canvas.removeEventHandler(MouseEvent.MOUSE_CLICKED, this);
                }
            }
        });
    }

    public void chooseFileToSave() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File to Save");
        fileChooser.getExtensionFilters().add(
                new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        selectedFile = fileChooser.showSaveDialog(null);
    }

    /**
     *
     */
    public void saveFigure() {
        if (selectedFile != null) {
            WritableImage wi = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(canvas.snapshot(null, wi), null), "png", selectedFile);
            } catch (IOException e) {
                StringWriter errors = new StringWriter();
                e.printStackTrace(new PrintWriter(errors));
                Alert alert = new Alert(Alert.AlertType.ERROR, errors.toString());
                alert.setHeaderText(null);
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.getStylesheets().add(
                        getClass().getResource("styling.css").toExternalForm());
                dialogPane.getStyleClass().add("alert-box");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please choose a file before saving!");
            alert.setHeaderText(null);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(
                    getClass().getResource("styling.css").toExternalForm());
            dialogPane.getStyleClass().add("alert-box");
            alert.showAndWait();
        }
    }

    public void deleteSelection() {
        for (Shape shape : selectedShapes) {
            canvas.getChildren().remove(shape);
        }
        selectedShapes.clear();
    }

    /**
     * Clears the canvas.
     */
    public void resetCanvas() {
        canvas.getChildren().clear();
    }

    /**
     * Exits the application.
     */
    public void exitApp() {
        System.exit(0);
    }
}
