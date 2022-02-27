import java.awt.*;

public class Queen extends Piece {
    public Queen(boolean isWhite, int i, int j, int size, Square[][] squares) {
        super((isWhite ? "pieces/whiteQueen.png" : "pieces/blackQueen.png"), (isWhite ? "Q" : "q"), isWhite, i, j, size, squares);
    }

    @Override
    public boolean isMoveLegal(Square square) {
        Point pos = getCoords();
        Point dest = square.getCoords();
        if((pos.x - pos.y != dest.x - dest.y && pos.x + pos.y != dest.x + dest.y) || (pos.x == dest.x && pos.y == dest.y) || (pos.x != dest.x && pos.y != dest.y)) {
            return false;
        }
        return isPathClear(pos, dest, square);
    }
}
