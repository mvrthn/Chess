import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class _Piece {
    private Image icon;
    private final boolean isWhite;
    private final String id;
    private int x;
    private int y;
    private int size;

    public _Piece(String address, String id, boolean isWhite, int i, int j, int size, Square[][] squares) {
        this.id = id;
        this.isWhite = isWhite;
        x = i * size;
        y = j * size;
        this.size = size;
        try {
            icon = ImageIO.read(new File(address));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        squares[i][j].addPiece(this);
    }

    public Image getIcon() {
        return icon;
    }
    public boolean isWhite() {
        return isWhite;
    }
    public String getId() {
        return id;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
