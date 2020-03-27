package Lab6;

public class RegularPolygon {
    double[] xCoord;
    double[] yCoord;

    public RegularPolygon(int x0, int y0, int radius, int edges) {
        xCoord = new double[edges];
        yCoord = new double[edges];
        double alpha = 2 * Math.PI / edges;
        for (int i = 0; i < edges; i++) {
            xCoord[i] = x0 + radius * Math.cos(alpha * i);
            yCoord[i] = y0 + radius * Math.sin(alpha * i);
        }
    }
}
