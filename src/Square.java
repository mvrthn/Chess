import javax.swing.*;
import java.awt.*;

public class Square extends JPanel {
    private final boolean isWhite;
    private int i;
    private int j;
    private boolean isOccupied;
    private Piece piece;

    public Square(int i, int j, int size) {
        this.i = i;
        this.j = j;
        this.setPreferredSize(new Dimension(size, size));
        isWhite = (i + j) % 2 == 0;
        this.setBackground(isWhite ? new Color(0xf0d9b5) : new Color(0xb58863));
        isOccupied = false;
    }

    public Point getCoords() {
        return new Point(i, j);
    }

    public Piece getPiece() {
        return (isOccupied ? piece : null);
    }

    public void addPiece(Piece piece) {
        this.piece = piece;
        isOccupied = true;
        piece.setCoords(i, j);
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
