import java.awt.*;

public class Knight extends Piece {
    public Knight(boolean isWhite, int i, int j, int size, Square[][] squares) {
        super((isWhite ? "pieces/whiteKnight.png" : "pieces/blackKnight.png"), (isWhite ? "N" : "n"), isWhite, i, j, size, squares);
    }

    @Override
    public boolean isMoveLegal(Square square, Piece king) {
        Point pos = getCoords();
        Point dest = square.getCoords();
        if(!((Math.abs(pos.x - dest.x) == 1 && Math.abs(pos.y - dest.y) == 2) ||
                (Math.abs(pos.x - dest.x) == 2 && Math.abs(pos.y - dest.y) == 1)) &&
                (square.getPiece() == null || square.getPiece().isWhite() != isWhite())) {
            return false;
        }
        return !kingInDanger(dest, king.getCoords());
    }
}
