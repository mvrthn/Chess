import java.awt.*;

public class Rook extends Piece {
    public Rook(boolean isWhite, int i, int j, int size, Square[][] squares) {
        super((isWhite ? "pieces/whiteRook.png" : "pieces/blackRook.png"), (isWhite ? "R" : "r"), isWhite, i, j, size, squares);
    }

    @Override
    public boolean isMoveLegal(Square square, Piece king) {
        Point pos = getCoords();
        Point dest = square.getCoords();
        if((pos.x == dest.x && pos.y == dest.y) || (pos.x != dest.x && pos.y != dest.y)) {
            return false;
        }
        if(!isPathClear(pos, dest, square)) {
            return false;
        }
        return !kingInDanger(dest, king.getCoords());
    }
}
