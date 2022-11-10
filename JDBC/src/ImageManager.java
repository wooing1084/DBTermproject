import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageManager {
    public static ImageIcon GetImage(String url, int w, int h){
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
}
