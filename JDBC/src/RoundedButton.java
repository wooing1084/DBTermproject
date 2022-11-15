import java.awt.Desktop.Action;
import java.awt.EventQueue;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class RoundedButton extends JButton {

	ImageIcon profileIcon = ImageManager.GetImageUsingFileSystem("src/assets/profile_image.png",50,50);
	Image image = profileIcon.getImage();
    public RoundedButton() {
        super();
        decorate();
    }

    public RoundedButton(String text) {
        super(text);
        decorate();
    }

    public RoundedButton(Icon icon) {
        super(icon);
        decorate();
    }
    
    public RoundedButton(String text, Icon icon) {
        super(text, icon);
        decorate();
    }

    protected void decorate() {
        setBorderPainted(false);
        setOpaque(false);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        int width = getWidth();
        int height = getHeight();

        Graphics2D graphics = (Graphics2D) g;

        //graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.drawImage(image, 20,10,120,120,null);
        /*if (getModel().isArmed()) {
            graphics.setColor(getBackground().darker());
        } else if (getModel().isRollover()) {
            graphics.setColor(getBackground().brighter());
        } else {
            graphics.setColor(getBackground());
        }*/
        
        //graphics.fillRoundRect(0, 0, width, height, 500, 500);
        //graphics.fillRect(0, 0, 500, 500);
        ///FontMetrics fontMetrics = graphics.getFontMetrics();
        //Rectangle stringBounds = fontMetrics.getStringBounds(this.getText(), graphics).getBounds();

        //int textX = (width - stringBounds.width) / 2;
        //int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent();

       // graphics.setColor(getForeground());
       // graphics.setFont(getFont());
       // graphics.drawString(getText(), textX, textY);
        graphics.dispose();

        super.paintComponent(g);
    }
}
