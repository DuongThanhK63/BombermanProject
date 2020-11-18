package graphics;

public class Sprite {
    public final int SIZE;
    private  int x,y;
    public int[] _pixels;
    protected int _rWidth;
    protected int _rHeight;
    private SpriteSheet sheet;

    public Sprite(int SIZE, int x, int y, SpriteSheet sheet, int _rWidth, int _rHeight) {
        this.SIZE = SIZE;
        this.x = x * SIZE;
        this.y = y * SIZE;
        this._rWidth = _rWidth;
        this._rHeight = _rHeight;
        this.sheet = sheet;
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

    public Sprite(int size, int color){
        SIZE = size;
        _pixels = new int[SIZE * SIZE];
        setColor(color);
    }

    private void setColor(int color){
        for(int i = 0; i < _pixels.length; i++){
            _pixels[i] = color;
        }
    }

    public int getSIZE() {
        return SIZE;
    }

    public int[] get_pixels() {
        return _pixels;
    }

    public int get_rWidth() {
        return _rWidth;
    }

    public int get_rHeight() {
        return _rHeight;
    }

    public int get_pixels(int i){
        return _pixels[i];
    }

    public static Sprite sprites = new Sprite(16, 0xffffff); // Create white color sprite

    // enemy sprite
    public static Sprite movingSprite(Sprite normal, Sprite x1, Sprite x2, int animate, int time){
        int a = animate % time;
        int b = time / 3;
        if(a < b) return normal;
        else if(a < b * 2) return x1;
        return x2;
    }

    // player sprite
    public static Sprite movingSprite(Sprite x1, Sprite x2, int animate, int time){
        int a = time / 2;
        return (animate % time > a) ? x1 : x2;
    }

    /*
    | Lay cac doi tuong Wall, Brick, Portal, Grass tu anh classic.png
    | __________________________________________________________________________________________________________________
     */
    public static Sprite wall = new Sprite(16, 5, 0, SpriteSheet.tiles, 16, 16);
    public static Sprite grass = new Sprite(16, 6, 0, SpriteSheet.tiles, 16, 16);
    public static Sprite brick = new Sprite(16, 7, 0, SpriteSheet.tiles, 16, 16);
    public static Sprite portal = new Sprite(16, 4, 0, SpriteSheet.tiles, 16, 16);

    /*
    | Lay cac doi tuong enemy tu anh classic.png
    | __________________________________________________________________________________________________________________
     */
    // 1.Balloom
    public static Sprite balloom_left1 = new Sprite(16, 9, 0, SpriteSheet.tiles, 16, 16);
    public static Sprite balloom_left2 = new Sprite(16, 9, 1, SpriteSheet.tiles, 16, 16);
    public static Sprite balloom_left3 = new Sprite(16,9, 2, SpriteSheet.tiles, 16, 16);
    public static Sprite balloom_right1 = new Sprite(16, 10, 0, SpriteSheet.tiles, 16, 16);
    public static Sprite balloom_right2 = new Sprite(16, 10, 1, SpriteSheet.tiles, 16, 16);
    public static Sprite balloom_right3 = new Sprite(16, 10, 2, SpriteSheet.tiles, 16, 16);
    public static Sprite balloom_dead = new Sprite(16,9, 3, SpriteSheet.tiles, 16, 16);

    // 2.Oneal
    public static Sprite Oneal_left1 = new Sprite(16, 11, 0, SpriteSheet.tiles, 16, 16);
    public static Sprite Oneal_left2 = new Sprite(16, 11, 1, SpriteSheet.tiles, 16, 16);
    public static Sprite Oneal_left3 = new Sprite(16, 11, 2, SpriteSheet.tiles, 16, 16);
    public static Sprite Oneal_right1 = new Sprite(16, 12, 0, SpriteSheet.tiles, 16, 16);
    public static Sprite Oneal_right2 = new Sprite(16, 12, 1, SpriteSheet.tiles, 16, 16);
    public static Sprite Oneal_right3 = new Sprite(16, 12, 2, SpriteSheet.tiles, 16, 16);
    public static Sprite Oneal_dead = new Sprite(16, 11, 3, SpriteSheet.tiles, 16, 16);

    //3.All dead
    public static Sprite mob_dead1 = new Sprite(16, 15, 0, SpriteSheet.tiles, 16, 16);
    public static Sprite mob_dead2 = new Sprite(16, 15, 1, SpriteSheet.tiles, 16, 16);
    public static Sprite mob_dead3 = new Sprite(16, 15, 2, SpriteSheet.tiles, 16, 16);

    /*
    | Lay player tu anh classic.png
    | __________________________________________________________________________________________________________________
     */
    public static Sprite player_up1 = new Sprite(16, 0, 0, SpriteSheet.tiles, 16,16);
    public static Sprite player_up2 = new Sprite(16, 0, 1, SpriteSheet.tiles, 16, 16);
    public static Sprite player_up3 = new Sprite(16, 0, 2, SpriteSheet.tiles, 16, 16);
    public static Sprite player_right1 = new Sprite(16, 1, 0, SpriteSheet.tiles, 16, 16);
    public static Sprite player_right2 = new Sprite(16, 1, 1, SpriteSheet.tiles, 16, 16);
    public static Sprite player_right3 = new Sprite(16, 1, 2, SpriteSheet.tiles, 16, 16);
    public static Sprite player_down1 = new Sprite(16, 2, 0, SpriteSheet.tiles, 16, 16);
    public static Sprite player_down2 = new Sprite(16, 2, 1, SpriteSheet.tiles, 16, 16);
    public static Sprite player_down3 = new Sprite(16, 2, 2, SpriteSheet.tiles, 16, 16);
    public static Sprite player_left1 = new Sprite(16, 3, 0, SpriteSheet.tiles, 16, 16);
    public static Sprite player_left2 = new Sprite(16, 3, 1, SpriteSheet.tiles, 16, 16);
    public static Sprite player_left3 = new Sprite(16, 3, 2, SpriteSheet.tiles, 16, 16);
    public static Sprite player_dead1 = new Sprite(16, 4, 2, SpriteSheet.tiles, 16, 16);
    public static Sprite player_dead2 = new Sprite(16, 5, 2, SpriteSheet.tiles, 16, 16);
    public static Sprite player_dead3 = new Sprite(16, 6, 2, SpriteSheet.tiles, 16, 16);

    /*
    | Lay du lieu Bom va Items tu anh classic.png
    | __________________________________________________________________________________________________________________
     */
    // 1.Bomb
    public static Sprite bomb_1 = new Sprite(16, 0, 3, SpriteSheet.tiles, 16, 16);
    public static Sprite bomb_2 = new Sprite(16, 1, 3, SpriteSheet.tiles, 16, 16);
    public static Sprite bomb_3 = new Sprite(16, 2, 3, SpriteSheet.tiles, 16, 16);

    // 2.Items
    public static Sprite speedItem = new Sprite(16, 14, 2, SpriteSheet.tiles, 16, 16);
    public static Sprite flameItem = new Sprite(16, 14, 1, SpriteSheet.tiles, 16, 16);
    public static Sprite bombItem = new Sprite(16, 14, 0, SpriteSheet.tiles, 16, 16);

    /*
    | Du lieu hieu ung tu anh classic.png
    | __________________________________________________________________________________________________________________
     */
    // 1.Bomb exploded
    public static Sprite bomb_exploded1 = new Sprite(16, 0, 4, SpriteSheet.tiles, 16, 16);
    public static Sprite bomb_exploded2 = new Sprite(16, 0, 5, SpriteSheet.tiles, 16, 16);
    public static Sprite bomb_exploded3 = new Sprite(16, 0, 6, SpriteSheet.tiles, 16, 16);

    // 2.Explosions
    public static Sprite explosion_vertical1 = new Sprite(16, 1, 5, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_vertical2 = new Sprite(16, 2, 5, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_vertical3 = new Sprite(16, 3, 5, SpriteSheet.tiles, 16, 16);

    public static Sprite explosion_vertical_last1 = new Sprite(16, 1, 6, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_vertical_last2 = new Sprite(16, 2, 6, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_vertical_last3 = new Sprite(16, 3, 6, SpriteSheet.tiles, 16, 16);

    public static Sprite explosion_vertical_top1 = new Sprite(16, 1, 4, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_vertical_top2 = new Sprite(16, 2, 4, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_vertical_top3 = new Sprite(16, 3, 4, SpriteSheet.tiles, 16, 16);

    public static Sprite explosion_horizontal1 = new Sprite(16, 1, 7, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_horizontal2 = new Sprite(16, 1, 8, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_horizontal3 = new Sprite(16, 1, 9, SpriteSheet.tiles, 16, 16);

    public static Sprite explosion_horizontal_left1 = new Sprite(16, 0, 7, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_horizontal_left2 = new Sprite(16, 0, 8, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_horizontal_left3 = new Sprite(16, 0, 9, SpriteSheet.tiles, 16, 16);

    public static Sprite explosion_horizontal_right1 = new Sprite(16, 2, 7, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_horizontal_right2 = new Sprite(16, 2, 8, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_horizontal_right3 = new Sprite(16, 2, 9, SpriteSheet.tiles, 16, 16);

    public static Sprite brick_explosion1 = new Sprite(16, 7, 1, SpriteSheet.tiles, 16, 16);
    public static Sprite brick_explosion2 = new Sprite(16, 7, 2, SpriteSheet.tiles, 16, 16);
    public static Sprite brick_explosion3 = new Sprite(16, 7, 3, SpriteSheet.tiles, 16, 16);


}
