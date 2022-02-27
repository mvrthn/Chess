public class Knight extends Piece {
    public Knight(boolean isWhite, int i, int j, int size, Square[][] squares) {
        super((isWhite ? "pieces/whiteKnight.png" : "pieces/blackKnight.png"), (isWhite ? "N" : "n"), isWhite, i, j, size, squares);
    }
}
