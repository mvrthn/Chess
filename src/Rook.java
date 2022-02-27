public class Rook extends _Piece {
    public Rook(boolean isWhite, int i, int j, int size, Square[][] squares) {
        super((isWhite ? "pieces/whiteRook.png" : "pieces/blackRook.png"), (isWhite ? "R" : "r"), isWhite, i, j, size, squares);
    }
}
