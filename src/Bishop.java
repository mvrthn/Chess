public class Bishop extends Piece {
    public Bishop(boolean isWhite, int i, int j, int size, Square[][] squares) {
        super((isWhite ? "pieces/whiteBishop.png" : "pieces/blackBishop.png"), (isWhite ? "B" : "b"), isWhite, i, j, size, squares);
    }
}
