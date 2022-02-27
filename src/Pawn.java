import java.awt.*;

public class Pawn extends Piece {
    public Pawn(boolean isWhite, int i, int j, int size, Square[][] squares) {
        super((isWhite ? "pieces/whitePawn.png" : "pieces/blackPawn.png"), (isWhite ? "P" : "p"), isWhite, i, j, size, squares);
    }

    @Override
    public boolean moveIsLegal(Square square) {
        int dir = (isWhite() ? -1 : 1);
        Point pos = getCoords();
        Point dest = square.getCoords();
        if(pos.y + dir != dest.y) {
            return false;
        }
        if (pos.x == dest.x) {
            return square.getPiece() == null;
        }
        else if (Math.abs(pos.x - dest.x) == 1) {
            Piece piece = square.getPiece();
            return piece != null && (isWhite() != piece.isWhite());
        }
        else {
            return false;
        }
    }
}
