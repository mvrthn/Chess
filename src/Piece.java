import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Piece {
    private Image icon;
    private final boolean isWhite;
    private final String id;
    private int x;
    private int y;
    private int size;
    private Point position;
    private Square[][] squares;

    public Piece(String address, String id, boolean isWhite, int i, int j, int size, Square[][] squares) {
        this.id = id;
        this.isWhite = isWhite;
        x = i * size;
        y = j * size;
        this.size = size;
        position = new Point(i, j);
        try {
            icon = ImageIO.read(new File(address));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        squares[i][j].addPiece(this);
        this.squares = squares;
    }

    public Image getIcon() {
        return icon;
    }
    public boolean isWhite() {
        return isWhite;
    }
    public String getId() {
        return id;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getSize() {
        return size;
    }
    public Square[][] getSquares() {
        return squares;
    }
    public Point getCoords() {
        return position;
    }

    public void setCoords(int i, int j) {
        position.setLocation(i, j);
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isEnPassant() {
        return false;
    }

    public boolean isMoveLegal(Square square) {
        return true;
    }

    public boolean isPathClear(Point pos, Point dest, Square square) {
        if(square.getPiece() != null && (isWhite() == square.getPiece().isWhite())) {
            return false;
        }
        int dirX = (int) Math.signum(dest.x - pos.x);
        int dirY = (int) Math.signum(dest.y - pos.y);
        Square[][] squares = getSquares();
        pos.translate(dirX, dirY);
        while(!pos.equals(dest)) {
            if(squares[pos.x][pos.y].getPiece() != null) {
                return false;
            }
            pos.translate(dirX, dirY);
        }
        return true;
    }

    public boolean inBoard(Point point) {
        return point.x >= 0 && point.x < 8 && point.y >= 0 && point.y < 8;
    }
}
