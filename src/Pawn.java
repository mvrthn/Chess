public class Pawn extends Piece {
    public Pawn(boolean isWhite, int i, int j, int size, Square[][] squares) {
        super((isWhite ? "pieces/whitePawn.png" : "pieces/blackPawn.png"), (isWhite ? "P" : "p"), isWhite, i, j, size, squares);
    }
}
