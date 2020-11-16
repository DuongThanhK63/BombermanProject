package graphics;

public class Sprites {
    public final int SIZE;
    private  int x,y;
    public int[] _pixels;
    protected int _rWidth;
    protected int _rHeight;
    private SpriteSheet sheet;

    public Sprites(int SIZE, int x, int y, int _rWidth, int _rHeight) {
        this.SIZE = SIZE;
        this.x = x * SIZE;
        this.y = y * SIZE;
        this._rWidth = _rWidth;
        this._rHeight = _rHeight;
        _pixels = new int [SIZE * SIZE];
        load();
    }

    private void load(){
        for(int y = 0; y < SIZE; y++){
            for(int x = 0; x < SIZE; x++){
                _pixels[x + y * SIZE] = sheet._pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
            }
        }
    }

    public Sprites(int size, int color){
        SIZE = size;
        _pixels = new int[SIZE * SIZE];
        setColor(color);
    }

    private void setColor(int color){
        for(int i = 0; i < _pixels.length; i++){
            _pixels[i] = color;
        }
    }

    public static Sprites sprites = new Sprites(16, 0xffffff);

    
}
