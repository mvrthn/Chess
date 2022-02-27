import java.awt.*;

public class Rook extends Piece {
    public Rook(boolean isWhite, int i, int j, int size, Square[][] squares) {
        super((isWhite ? "pieces/whiteRook.png" : "pieces/blackRook.png"), (isWhite ? "R" : "r"), isWhite, i, j, size, squares);
    }

    @Override
    public boolean isMoveLegal(Square square) {
        Point pos = getCoords();
        Point dest = square.getCoords();
        if((pos.x == dest.x && pos.y == dest.y) || (pos.x != dest.x && pos.y != dest.y)) {
            return false;
        }
        return isPathClear(pos, dest, square);
    }
}
