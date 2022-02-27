import java.awt.*;

public class Pawn extends Piece {
    private boolean enPassant;

    public Pawn(boolean isWhite, int i, int j, int size, Square[][] squares) {
        super((isWhite ? "pieces/whitePawn.png" : "pieces/blackPawn.png"), (isWhite ? "P" : "p"), isWhite, i, j, size, squares);
        enPassant = false;
    }

    @Override
    public boolean isEnPassant() {
        return enPassant;
    }

    @Override
    public boolean isMoveLegal(Square square) {
        if(enPassant) {
            enPassant = false;
        }
        int dir = (isWhite() ? -1 : 1);
        Point pos = getCoords();
        Point dest = square.getCoords();
        if(pos.y + dir != dest.y) {
            if(pos.y == (isWhite() ? 6 : 1) && dest.y == (isWhite() ? 4 : 3) && pos.x == dest.x &&
                    getSquares()[pos.x][dest.y + dir].getPiece() == null &&
                    getSquares()[pos.x][dest.y + 2 * dir].getPiece() == null) {
                enPassant = true;
                return true;
            }
            return false;
        }
        if (pos.x == dest.x) {
            return square.getPiece() == null;
        }
        else if (Math.abs(pos.x - dest.x) == 1) {
            Piece piece = square.getPiece();
            if(piece != null) {
                return isWhite() != piece.isWhite();
            }
            else return getSquares()[pos.x - (pos.x - dest.x)][pos.y].getPiece() != null
                    && getSquares()[pos.x - (pos.x - dest.x)][pos.y].getPiece().isEnPassant();
        }
        else {
            return false;
        }
    }
}
