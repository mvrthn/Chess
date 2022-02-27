import java.awt.*;

public class Bishop extends Piece {
    public Bishop(boolean isWhite, int i, int j, int size, Square[][] squares) {
        super((isWhite ? "pieces/whiteBishop.png" : "pieces/blackBishop.png"), (isWhite ? "B" : "b"), isWhite, i, j, size, squares);
    }

    @Override
    public boolean moveIsLegal(Square square) {
        Point pos = getCoords();
        Point dest = square.getCoords();
        if(pos.x - pos.y != dest.x - dest.y && pos.x + pos.y != dest.x + dest.y) {
            return false;
        }
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
}
