import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class Board extends JPanel implements MouseListener, Runnable {
    private int size;
    public final Square[][] squares;
    private List<Piece> pieces;
    private Square selectedSquare;
    private Piece currentPiece;
    private boolean inMove;
    private boolean mouseOutOfBounds;
    private Thread thread;
    private boolean running;

    public Board() {
        size = 80;

        squares = new Square[8][8];
        this.setLayout(new GridLayout(8, 8));
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                Square square = new Square(i, j, size);
                this.add(square);
                squares[j][i] = square;
            }
        }

        pieces = new ArrayList<>();
        defaultSetup();

        selectedSquare = null;
        currentPiece = null;
        inMove = false;
        mouseOutOfBounds = false;

        addMouseListener(this);
    }

    private void drawPiece(Graphics g, Piece piece) {
        g.drawImage(piece.getIcon(), piece.getX(), piece.getY(), size, size, null);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        for(Piece piece : pieces) {
            drawPiece(graphics, piece);
        }
    }

    private void defaultSetup() {
        String pre = "pieces/";
        String suf = ".png";
        pieces.add(new Piece(pre + "whiteKing" + suf, "K", 3, 7, size, squares));
        pieces.add(new Piece(pre + "whiteQueen" + suf, "Q", 4, 7, size, squares));
        pieces.add(new Piece(pre + "whiteRook" + suf, "R", 0, 7, size, squares));
        pieces.add(new Piece(pre + "whiteRook" + suf, "R", 7, 7, size, squares));
        pieces.add(new Piece(pre + "whiteBishop" + suf, "B", 2, 7, size, squares));
        pieces.add(new Piece(pre + "whiteBishop" + suf, "B", 5, 7, size, squares));
        pieces.add(new Piece(pre + "whiteKnight" + suf, "N", 1, 7, size, squares));
        pieces.add(new Piece(pre + "whiteKnight" + suf, "N", 6, 7, size, squares));
        pieces.add(new Piece(pre + "blackKing" + suf, "k", 3, 0, size, squares));
        pieces.add(new Piece(pre + "blackQueen" + suf, "q", 4, 0, size, squares));
        pieces.add(new Piece(pre + "blackRook" + suf, "r", 0, 0, size, squares));
        pieces.add(new Piece(pre + "blackRook" + suf, "r", 7, 0, size, squares));
        pieces.add(new Piece(pre + "blackBishop" + suf, "b", 2, 0, size, squares));
        pieces.add(new Piece(pre + "blackBishop" + suf, "b", 5, 0, size, squares));
        pieces.add(new Piece(pre + "blackKnight" + suf, "n", 1, 0, size, squares));
        pieces.add(new Piece(pre + "blackKnight" + suf, "n", 6, 0, size, squares));
        for(int i = 0; i < 8; i++) {
            pieces.add(new Piece(pre + "whitePawn" + suf, "P", i, 6, size, squares));
        }
        for(int i = 0; i < 8; i++) {
            pieces.add(new Piece(pre + "blackPawn" + suf, "p", i, 1, size, squares));
        }
    }

    private Square getSquare(int x, int y) {
        x /= size;
        y /= size;
        return squares[x][y];
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        if(e.getButton() == MouseEvent.BUTTON1) {
//            Square square = getSquare(e.getX(), e.getY());
//            Piece piece = square.removePiece();
//            if(piece != null || inMove) {
//                if(inMove) {
//                    selectedSquare.unselect();
//                    currentPiece.setPosition(square.getX(), square.getY());
//                    square.addPiece(currentPiece);
//                    inMove = false;
//                }
//                else {
//                    inMove = true;
//                    selectedSquare = square;
//                    square.select();
//                    currentPiece = piece;
//                }
//                repaint();
//            }
//        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            Square square = getSquare(e.getX(), e.getY());
            Piece piece = square.removePiece();
            if(piece != null) {
                selectedSquare = square;
                currentPiece = piece;
                thread = new Thread(this);
                thread.start();
                repaint();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            if(!mouseOutOfBounds) {
                terminate();
                try {
                    thread.join();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                Square square = getSquare(e.getX(), e.getY());
                square.addPiece(currentPiece);
                currentPiece.setPosition(square.getX(), square.getY());
                repaint();
            }
            else {
                mouseOutOfBounds = false;
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private void terminate() {
        running = false;
    }

    @Override
    public void run() {
        running = true;
        while(running) {
            Point position = this.getMousePosition();
            if (position != null) {
                currentPiece.setPosition(position.x - size / 2, position.y - size / 2);
            } else {
                running = false;
                mouseOutOfBounds = true;
                currentPiece.setPosition(selectedSquare.getX(), selectedSquare.getY());
            }
            repaint();
        }
    }
}
