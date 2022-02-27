import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Piece {
    private Image icon;
    private String id;
    private int x;
    private int y;
    private int size;

    public Piece(String address, String id, int x, int y, int size, Square[][] squares) {
        this.id = id;
        this.size = size;
        this.x = x * size;
        this.y = y * size;
        try {
            icon = ImageIO.read(new File(address));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        squares[x][y].addPiece(this);
    }

    public Image getIcon() {
        return icon;
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
