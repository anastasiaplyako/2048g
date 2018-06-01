import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    public Main(String s) {
        super(s);
        setSize(520, 520);
        addGameField();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void addGameField() {
        final GameField gameField = new GameField();
        gameField.setBackground(new Color(233, 231, 73));
        gameField.setPreferredSize(new Dimension(522, 522));
        this.add(gameField, BorderLayout.CENTER);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main("2048"));
    }
}