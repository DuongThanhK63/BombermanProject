package graphics;

import Tiles.Entity;
import sample.Game;
import sun.java2d.loops.GraphicsPrimitiveMgr;

import java.awt.*;

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

    public int get_width() {
        return _width * Game.GAME_SCALE;
    }

    public int get_height() {
        return _height * Game.GAME_SCALE;
    }

    public void clear(){
        for(int i = 0; i< _pixels.length; i++){
            _pixels[i] = 0;
        }
    }

    public void renderEntity(int xPoint, int yPoint, Entity entity){
        xPoint -= x_offset;
        yPoint -= y_offset;
        for(int y = 0; y < entity.get_sprite().getSIZE(); y++){
            int y1 = yPoint + y;
            for(int x = 0; x < entity.get_sprite().getSIZE(); x++){
                int x1 = xPoint + x;
                if(x1 >= _width || y1 < 0 || y1 > _height) break;
                if(x1 < 0) x1 = 0;
                int color = entity.get_sprite().get_pixels(x + y * entity.get_sprite().getSIZE());
                if(color != defaultColor) _pixels[x1 + y1 * _width] = color;
            }
        }
    }

    public void renderEntityWithGrassSprite(int xPoint, int yPoint, Entity entity, Sprite grassSprite){
        xPoint -= x_offset;
        yPoint -= y_offset;
        for(int y = 0; y < entity.get_sprite().getSIZE(); y++){
            int y1 = yPoint + y;
            for(int x = 0; x < entity.get_sprite().getSIZE(); x++){
                int x1 = xPoint + x;
                if(x1 >= _width || y1 < 0 || y1 > _height) break;
                if(x1 < 0) x1 = 0;
                int color = entity.get_sprite().get_pixels(x + y * entity.get_sprite().getSIZE());
                if(color != defaultColor){
                    _pixels[x1 + y1 * _width] = color;
                } else {
                    _pixels[x1 + y1 * _width] = grassSprite.get_pixels(x + y * grassSprite.getSIZE());
                }
            }
        }
    }

    /*
   | Game Screen
   | __________________________________________________________________________________________________________________
    */
    public void drawEndGame(Graphics graphics, int points){
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, get_width(), get_height());

        Font font = new Font("Times New Roman", Font.PLAIN, 20 * Game.GAME_SCALE);
        graphics.setFont(font);
        graphics.setColor(Color.WHITE);
        drawCenterString("GAME OVER", get_width(), get_height(), graphics);

        font = new Font("Times New Roman", Font.PLAIN, 10);
        graphics.setFont(font);
        graphics.setColor(Color.YELLOW);
        drawCenterString("POINT: " + points, get_width(), get_height(), graphics);
    }

    public void drawCenterString(String s, int width, int height, Graphics graphics){
        FontMetrics fontMetrics = graphics.getFontMetrics();
        int x = (width - fontMetrics.stringWidth(s)) / 2;
        int y = (fontMetrics.getAscent() + (height - (fontMetrics.getAscent() + fontMetrics.getDescent())) / 2);
        graphics.drawString(s, x, y);
    }

    public void drawPause(Graphics graphics){
        Font font = new Font("Times New Roman", Font.PLAIN, 20);
        graphics.setFont(font);
        graphics.setColor(Color.WHITE);
        drawCenterString("PAUSE", get_width(), get_height(), graphics);
    }

    public void drawChangeLevel(Graphics graphics, int level){
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, get_width(), get_height());

        Font font = new Font("Times New Roman", Font.PLAIN, 20);
        graphics.setFont(font);
        graphics.setColor(Color.WHITE);
        drawCenterString("Level: " + level, get_width(), get_height(), graphics);
    }

    /*
    | Offset
    | __________________________________________________________________________________________________________________
     */
    public static void setOffset(int x0, int y0){
        x_offset = x0;
        y_offset = y0;
    }

//    public static int calculateOffset(){
//
//    }

}
