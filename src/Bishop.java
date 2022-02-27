import java.awt.*;

public class Bishop extends Piece {
    public Bishop(boolean isWhite, int i, int j, int size, Square[][] squares) {
        super((isWhite ? "pieces/whiteBishop.png" : "pieces/blackBishop.png"), (isWhite ? "B" : "b"), isWhite, i, j, size, squares);
    }

    @Override
    public boolean isMoveLegal(Square square) {
        Point pos = getCoords();
        Point dest = square.getCoords();
        if(pos.x - pos.y != dest.x - dest.y && pos.x + pos.y != dest.x + dest.y) {
            return false;
        }
        return isPathClear(pos, dest, square);
    }
}
