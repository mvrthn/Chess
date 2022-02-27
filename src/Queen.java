public class Queen extends Piece {
    public Queen(boolean isWhite, int i, int j, int size, Square[][] squares) {
        super((isWhite ? "pieces/whiteQueen.png" : "pieces/blackQueen.png"), (isWhite ? "Q" : "q"), isWhite, i, j, size, squares);
    }
}
