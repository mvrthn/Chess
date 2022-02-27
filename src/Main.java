import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();

        JFrame frame = new JFrame("Chess");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(300, 100);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.add(board);
        frame.pack();
    }
}
