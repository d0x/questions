package so22298328;

import java.awt.Color;
import java.awt.color.ICC_ColorSpace;
import java.awt.color.ICC_Profile;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;

import javax.imageio.ImageIO;

public class Main
{
    public static void main(final String[] args) throws Exception
    {
        final String imageFile = "/so22298328/page0.png";

        final BufferedImage pngImage = ImageIO.read(Main.class.getResourceAsStream(imageFile));

        // convert PNG to JPEG
        // http://www.mkyong.com/java/convert-png-to-jpeg-image-file-in-java/
        final BufferedImage rgbImage = new BufferedImage(pngImage.getWidth(), pngImage.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        rgbImage.createGraphics().drawImage(pngImage, 0, 0, Color.WHITE, null);

        // RGB to CMYK using ColorConvertOp
        // http://stackoverflow.com/questions/380678/how-to-set-icc-color-profile-in-java-and-change-colorspace/2804370#2804370
        final ICC_Profile ip = ICC_Profile.getInstance("so22298328/icc/USWebUncoated.icc");

        final ColorConvertOp cco = new ColorConvertOp(rgbImage.getColorModel().getColorSpace(), new ICC_ColorSpace(ip), null);

        final BufferedImage cmykImage = new BufferedImage(pngImage.getWidth(), pngImage.getHeight(), BufferedImage.TYPE_3BYTE_BGR);

        cco.filter(rgbImage, cmykImage);

        ImageIO.write(cmykImage, "JPEG", new File("page0.cmyk.jpg"));
    }
}
