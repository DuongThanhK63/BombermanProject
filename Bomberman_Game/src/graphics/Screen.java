package graphics;

public class Screen {
    protected int _width, _height;
    public int[] _pixels;
    private int defaultColor = 0xffff00ff; //default color is pink

    public static int x_offset = 0, y_offset = 0;

    public Screen(int _width, int _height) {
        this._width = _width;
        this._height = _height;
        this._pixels = new int[_width * _height];

    }
}
