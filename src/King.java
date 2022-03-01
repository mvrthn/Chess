import java.awt.*;

public class King extends Piece {
    public King(boolean isWhite, int i, int j, int size, Square[][] squares) {
        super((isWhite ? "pieces/whiteKing.png" : "pieces/blackKing.png"), (isWhite ? "K" : "k"), isWhite, i, j, size, squares);
    }

    @Override
    public boolean isMoveLegal(Square square, Piece king) {
        Point pos = getCoords();
        Point dest = square.getCoords();
        if((Math.abs(pos.x - dest.x) > 1 || Math.abs(pos.y - dest.y) > 1) ||
                (square.getPiece() != null && square.getPiece().isWhite() == isWhite())) {
            return false;
        }
        return !kingInDanger(dest, dest);
    }
}
