public class King extends Piece {
    public King(boolean isWhite, int i, int j, int size, Square[][] squares) {
        super((isWhite ? "pieces/whiteKing.png" : "pieces/blackKing.png"), (isWhite ? "K" : "k"), isWhite, i, j, size, squares);
    }
}
