import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ImageManager {
	//荤侩规过
	//ImageIcon img = ImageManager.GetImageUsingFileSystem("src/assets/logo.png", 100, 100);
    public static ImageIcon GetImageUsingFileSystem(String url, int w, int h){
        ImageIcon result = null;

        try {
            BufferedImage img = ImageIO.read(new File(url));

            result = new ImageIcon(img);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        java.awt.Image temp = result.getImage();
        java.awt.Image changeTemp = temp.getScaledInstance(w,h, java.awt.Image.SCALE_SMOOTH);
        result = new ImageIcon(changeTemp);

        return result;
    }
    
    //荤侩规过
    //ImageIcon img = ImageManager.GetImageUsingURL("https://pbs.twimg.com/profile_images/1374979417915547648/vKspl9Et_400x400.jpg", 100, 100);
    public static ImageIcon GetImageUsingURL(String url, int w, int h){
        ImageIcon result = null;

        try {
            BufferedImage img = ImageIO.read(new URL(url));

            result = new ImageIcon(img);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        java.awt.Image temp = result.getImage();
        java.awt.Image changeTemp = temp.getScaledInstance(w,h, java.awt.Image.SCALE_SMOOTH);
        result = new ImageIcon(changeTemp);

        return result;
    }
}
