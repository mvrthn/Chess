import javax.swing.*;
import java.awt.*;

public class Square extends JPanel {
    private boolean isWhite;
    private boolean isOccupied;
    private Piece piece;

    public Square(int x, int y, int size) {
        this.setPreferredSize(new Dimension(size, size));
        isWhite = (x + y) % 2 == 0;
        this.setBackground(isWhite ? new Color(0xf0d9b5) : new Color(0xb58863));
        isOccupied = false;
    }

    public void addPiece(Piece piece) {
        this.piece = piece;
        isOccupied = true;
    }
    public Piece removePiece() {
        if(isOccupied) {
            isOccupied = false;
            return piece;
        }
        return null;
    }

    public void select() {
        this.setBackground(isWhite ? new Color(0x829769) : new Color(0x646f40));
    }
    public void unselect() {
        this.setBackground(isWhite ? new Color(0xf0d9b5) : new Color(0xb58863));
    }
}
