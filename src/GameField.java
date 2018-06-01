import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class GameField extends JPanel {
    Game game;

    public GameField(){
        game = new Game();
        initListeners();
    }


    private void initListeners() {

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();

            }
        });

        KeyListener keyListener = new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                onKeyPressed(e);
            }
        };

        addKeyListener(keyListener);
        setFocusable(true);
    }

    private void onKeyPressed(KeyEvent e) {
        game.move(e.getKeyCode());
        repaint();
    }

    private void paintCell(Graphics g, Cell cell) {
        Image img = null;
        try {
            img = getCellImage(cell.getValue());
            g.drawImage(img, cell.getColumn() * 128, cell.getRow() * 128, null);
        } catch (IOException e) {
        }
    }

    public Image getCellImage(int value) throws IOException {
        switch(value) {
            case 0:
                return ImageIO.read(new File("res/gray.png"));
            case 2:
                return ImageIO.read(new File("res/2.png"));
            case 4:
                return ImageIO.read(new File("res/4.png"));
            case 8:
                return ImageIO.read(new File("res/8.png"));
            case 16:
                return ImageIO.read(new File("res/16.png"));
            case 32:
                return ImageIO.read(new File("res/32.png"));
            case 64:
                return ImageIO.read(new File("res/64.png"));
            case 128:
                return ImageIO.read(new File("res/128.png"));
            case 256:
                return ImageIO.read(new File("res/256.png"));
            case 512:
                return ImageIO.read(new File("res/512.png"));
            case 1024:
                return ImageIO.read(new File("res/1024.png"));
            case 2048:
                return ImageIO.read(new File("res/2048.png"));

            default:
                return ImageIO.read(new File("res/gray.png"));
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        final Cell[][] cells = game.getCells();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                paintCell(g, cells[i][j]);
            }
        }
    }
}