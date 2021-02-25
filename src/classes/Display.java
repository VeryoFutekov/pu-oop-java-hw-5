package classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Display extends JFrame implements MouseListener {

    private String currentSerialNumber;
    private Pixel[][] pixels =new Pixel[8][8];

    public Display() throws HeadlessException {
        super.addMouseListener(this);

    }


    public void startProgram(){
        

    }

    @Override
    public void mouseClicked(MouseEvent e) {

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
