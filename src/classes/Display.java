package classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Random;

public class Display extends JFrame implements MouseListener {
    private Pixel[][] pixels = new Pixel[8][8];


    private String currentSerialNumber;
    private int clickCounter=0;
    private Pixel currentPixel;
    private int totalClicks=0;
    private int burnedPixelCount;

    private int phoneCounter=0;


    private MyStructure<String>brokenPhones=new MyStructure<>("broken");
    private MyStructure<String>healthyPhones=new MyStructure<>("healthy");

    public Display() throws HeadlessException {
        super.addMouseListener(this);

    }


    /**
     * start of the game
     */
    public void startProgram() {
        phoneCounter+=1;
        generateRandomString();
        generatePixels();
        windowInit();
    }

    /**
     * window init
     */
    private void windowInit() {
        super.setSize(800, 800);
        super.setVisible(true);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setTitle("Serial number: " + this.currentSerialNumber);
    }

    /**
     * generate random string
     */
    private void generateRandomString() {
        Random random = new Random();
        String randomS = "bnlkds8720ds,aka";
        String result = "";
        int count = 0;
        while (count < 9) {
            result += randomS.charAt(random.nextInt(randomS.length()));
            count++;
        }
        this.currentSerialNumber = result;
    }

    /**
     * generate pixels
     */
    private void generatePixels() {
        List<Color> colors = List.of(Color.GREEN, Color.BLUE, Color.RED);
        List<String> states = List.of("BURNED", "HALF BURNED", "HEALTHY");
        Random random = new Random();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Color randomColor = colors.get(random.nextInt(3));
                String randomState = states.get(random.nextInt(3));
                Pixel pixel = new Pixel(i, j, randomColor, randomState);
                pixels[i][j] = pixel;

            }
        }

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                pixels[row][col].renderFigure(g);
            }
        }
    }

    /**
     * show status of the phone
     * @param status status
     */
    private void showStatusOfThePhone(String status) {
        JDialog dialog = new JDialog(this, true);
        dialog.add(new JLabel(String.format("The display phone is: %s", status)));
        dialog.setLayout(new FlowLayout());
        dialog.setSize(300, 300);
        dialog.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = e.getY() / Pixel.PIXEL_SIZE;
        int col = e.getX() / Pixel.PIXEL_SIZE;

        Pixel clickedPixel = pixels[row][col];

        if (!clickedPixel.equals(currentPixel)) {
            currentPixel = clickedPixel;
            clickCounter = 1;
        }else if(!currentPixel.getColor().equals(Color.BLACK)){
            clickCounter +=1;

            if (clickCounter == 3) {
                totalClicks++;
                if (currentPixel.getState().equals("HALF BURNED") || currentPixel.getState().equals("BURNED")) {
                    currentPixel.setColor(Color.BLACK);
                    burnedPixelCount++;
                    isBrokenThePhone();
                }

                isHealthyThePhone();
                super.repaint();
            }
        }
    }

    /**
     * check for healthy phone
     */
    private void isHealthyThePhone(){
        if(totalClicks==64){
            showStatusOfThePhone("healthy");
            this.healthyPhones.addData(currentSerialNumber);
            this.nextPhone();
        }

    }

    /**
     * check for broken phone
     */
    private void isBrokenThePhone() {
        if (burnedPixelCount >31) {
            showStatusOfThePhone("broken");
            this.brokenPhones.addData(currentSerialNumber);
            this.nextPhone();
        }
    }

    /**
     * switch next phone
     */
    private void nextPhone() {

        if(phoneCounter==5){
            System.out.println(brokenPhones.toString());
            System.out.println(healthyPhones.toString());
            System.exit(0);
        }

        this.startProgram();
        this.repaint();

        resetStats();

    }

    /**
     * reset status
     */
    private void resetStats(){
        totalClicks=0;
        burnedPixelCount=0;
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
