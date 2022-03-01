import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

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

    public boolean isMoveLegal(Square square, Piece king) {
        return true;
    }

    public boolean isPathClear(Point pos, Point dest, Square square) {
        if(square.getPiece() != null && (isWhite() == square.getPiece().isWhite())) {
            return false;
        }
        int dirX = (int) Math.signum(dest.x - pos.x);
        int dirY = (int) Math.signum(dest.y - pos.y);
        pos.translate(dirX, dirY);
        while(!pos.equals(dest)) {
            if(squares[pos.x][pos.y].getPiece() != null) {
                return false;
            }
            pos.translate(dirX, dirY);
        }
        return true;
    }

    public boolean kingInDanger(Point pos, Point kingPos) {
        int[][] d = {{2, 1}, {2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}, {-2, 1}, {-2, -1}};
        for(int i = 0; i < 8; i++) {
            int x = d[i][0];
            int y = d[i][1];
            if(pointInBoard(kingPos.x + x, kingPos.y + y) &&
                    squares[kingPos.x + x][kingPos.y + y].getPiece() != null &&
                    Objects.equals(squares[kingPos.x + x][kingPos.y + y].getPiece().getId(), isWhite ? "n" : "N") &&
                    (kingPos.x + x != pos.x && kingPos.y + y != pos.y)) {
                return true;
            }
        }
        int[][] dir = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
        for(int i = 0; i < 8; i++) {
            int x = dir[i][0];
            int y = dir[i][1];
            Point newPos = kingPos.getLocation();
            newPos.translate(x, y);
            while(pointInBoard(newPos.x, newPos.y)) {
                if(newPos.x == pos.x && newPos.y == pos.y) {
                    break;
                }
                else if(squares[newPos.x][newPos.y].getPiece() != null) {
                    if(squares[newPos.x][newPos.y].getPiece().isWhite() == isWhite) {
                        break;
                    }
                    else if(Objects.equals(squares[newPos.x][newPos.y].getPiece().getId(), isWhite ? "q" : "Q")) {
                        return true;
                    }
                    else if(i % 2 == 0 && Objects.equals(squares[newPos.x][newPos.y].getPiece().getId(), isWhite ? "r" : "R")) {
                        return true;
                    }
                    else if(i % 2 == 1 && Objects.equals(squares[newPos.x][newPos.y].getPiece().getId(), isWhite ? "b" : "B")) {
                        return true;
                    }
                }
                newPos.translate(x, y);
            }
        }
        Piece piece = squares[kingPos.x - 1][kingPos.y + (isWhite ? -1 : 1)].getPiece();
        if(piece != null && Objects.equals(piece.getId(), isWhite ? "p" : "P") &&
                (kingPos.x - 1 != pos.x && kingPos.y + (isWhite ? -1 : 1) != pos.y)) {
            return true;
        }
        piece = squares[kingPos.x + 1][kingPos.y + (isWhite ? -1 : 1)].getPiece();
        if(piece != null && Objects.equals(piece.getId(), isWhite ? "p" : "P") &&
                (kingPos.x + 1 != pos.x && kingPos.y + (isWhite ? -1 : 1) != pos.y)) {
            return true;
        }
        for(int i = 0; i < 8; i++) {
            int x = dir[i][0];
            int y = dir[i][1];
            if(pointInBoard(kingPos.x + x, kingPos.y + y) &&
                    squares[kingPos.x + x][kingPos.y + y].getPiece() != null &&
                    squares[kingPos.x + x][kingPos.y + y].getPiece().getClass() == King.class) {
                return true;
            }
        }
        return false;
    }

    private boolean pointInBoard(int x, int y) {
        return x < 8 && x >= 0 && y < 8 && y >= 0;
    }
}
