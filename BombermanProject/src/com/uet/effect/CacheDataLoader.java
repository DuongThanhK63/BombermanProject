package com.uet.effect;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import javax.imageio.ImageIO;

/**
 *
 * @author Admin
 */
public class CacheDataLoader {

    private static CacheDataLoader instance = null;

    private Hashtable<String, FrameImage> frameImages;
    private Hashtable<String, Animation> animations;

    private String framefile = "data/frame.txt";
    private String animationfile = "data/animation.txt";
    private String physmapfile1 = "data/phys_maplv1.txt";
    private String physmapfile2 = "data/phys_maplv2.txt";

    private int[][] phys_map;

    public int[][] getPhysicalmap() {
//        loadPhysMap();
        return instance.phys_map;
    }

    private CacheDataLoader() {
    }

    public Animation getAnimation(String name) {
        Animation animation = new Animation(instance.animations.get(name));
        return animation;
    }

    public static CacheDataLoader getInstance() {

        if (instance == null) {
            instance = new CacheDataLoader();
        }
        return instance;
    }

    public void loadData() throws IOException{

        loadPhysMap();
        LoadFrame();
        LoadAnimation();
    }

    public void LoadFrame() throws IOException {

        frameImages = new Hashtable<String, FrameImage>();

        FileReader fr = new FileReader(framefile);
        BufferedReader br = new BufferedReader(fr);

        String line = null;

        fr = new FileReader(framefile);
        br = new BufferedReader(fr);

        String path = "data/bomb.png";

        line = br.readLine();
        int n = Integer.parseInt(line);
        System.out.println("" + n);

        for(int i = 0; i < n; i++){

            FrameImage frame = new FrameImage();
            line = br.readLine();

            String[] str = line.split(" ");

            frame.setName(str[0]);
            System.out.println("" + str[0]);
            int x = Integer.parseInt(str[1]);

            int y = Integer.parseInt(str[2]);

            int w = Integer.parseInt(str[3]);

            int h = Integer.parseInt(str[4]);

            BufferedImage imageData = ImageIO.read(new File(path));
            BufferedImage image = imageData.getSubimage(x, y, w, h);
            frame.setImage(image);

            instance.frameImages.put(frame.getName(), frame);

        }

        br.close();

    }

    public FrameImage getFrameImage(String name) {
        FrameImage frameImage = new FrameImage(instance.frameImages.get(name));
        return frameImage;
    }


    public void LoadAnimation() throws IOException { ;
        animations = new Hashtable<String, Animation>();

        FileReader fr = new FileReader(animationfile);
        BufferedReader br = new BufferedReader(fr);

        String line = null;

        if(br.readLine()==null) {
            System.out.println("No data");
            throw new IOException();
        } else{

            fr = new FileReader(animationfile);
            br = new BufferedReader(fr);

            line = br.readLine();

            int m = Integer.parseInt(line);

            for(int i = 0; i < m; i++){

                Animation animation = new Animation();

               line = br.readLine();
               String[] str = line.split(" ");
                animation.setName(str[0]);
               for(int j = 1; j < str.length; j += 2){

//                   FrameImage frameImage = new FrameImage();
//                   frameImage = instance.frameImages.get(str[j]);
                   animation.add(getFrameImage(str[j]), Double.parseDouble(str[j + 1]));
//                   System.out.println(animation.getName());
                   instance.animations.put(animation.getName(),animation);
               }
//                instance.animations.put(animation.getName(),animation);
            }
        }
        br.close();

    }

    public void loadPhysMap() {

        try{
            FileReader fr = new FileReader(physmapfile2);
            BufferedReader br = new BufferedReader(fr);

            String line = null;

            line = br.readLine();
            int numberOfRows = Integer.parseInt(line);
            line = br.readLine();
            int numberOfColumns = Integer.parseInt(line);


            instance.phys_map = new int[numberOfRows][numberOfColumns];

            for(int i = 0;i < numberOfRows;i++){
                line = br.readLine();
                String [] str = line.split(" ");
                for(int j = 0;j<numberOfColumns;j++)
                    instance.phys_map[i][j] = Integer.parseInt(str[j]);
            }

            for(int i = 0;i < numberOfRows;i++){

                for(int j = 0;j<numberOfColumns;j++)
                    System.out.print(" "+instance.phys_map[i][j]);

                System.out.println();
            }

            br.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}