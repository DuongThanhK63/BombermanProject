package graphics;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class SpriteSheet {

    private String _path;
    public final int SIZE;
    public int[] _pixels;

    public SpriteSheet(String _path, int SIZE) {
        this._path = _path;
        this.SIZE = SIZE;
        _pixels = new int[SIZE * SIZE];
        load();
    }

    public static SpriteSheet tiles = new SpriteSheet("/textures/classic.png", 256);

    private void load(){
        try{
            URL url = SpriteSheet.class.getResource(_path);
            BufferedImage image = ImageIO.read(url);
            int width = image.getWidth();
            int height = image.getHeight();
            image.getRGB(0,0, width, height, _pixels,0, width);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
