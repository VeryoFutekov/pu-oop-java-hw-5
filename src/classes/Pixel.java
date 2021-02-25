package classes;


import java.awt.*;

public class Pixel {
    public static final int PIXEL_SIZE = 100;
    private int y;
    private int x;

    private Color color;
    private String state;

    public Pixel(int y, int x, Color color, String state) {
        setY(y);
        setX(x);
        this.color = color;
        this.state = state;
    }

    public void renderFigure(Graphics g) {
        fillTheFigure(g);
        drawTheFigure(g);

    }

    private void fillTheFigure(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, Constants.PIXEL_SIZE, Constants.PIXEL_SIZE);

    }

    private void drawTheFigure(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(x, y, Constants.PIXEL_SIZE, Constants.PIXEL_SIZE);
    }

    public void setY(int y) {
        this.y = y * Constants.PIXEL_SIZE;
    }

    public void setX(int x) {
        this.x = x * Constants.PIXEL_SIZE;
    }
}
