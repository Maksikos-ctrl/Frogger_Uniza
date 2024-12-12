package utils;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class ImageUtils {
    public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        Image temp = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(temp, 0, 0, null);
        g2d.dispose();

        return resizedImage;
    }



    public static BufferedImage flipImage(BufferedImage originalImage) {
        AffineTransform tx = AffineTransform.getScaleInstance(-1, -1); // Зеркальное отражение
        tx.translate(-originalImage.getWidth(), -originalImage.getHeight());

        BufferedImage flippedImage = new BufferedImage(
                originalImage.getWidth(),
                originalImage.getHeight(),
                originalImage.getType()
        );

        Graphics2D g2d = flippedImage.createGraphics();
        g2d.drawImage(originalImage, tx, null);
        g2d.dispose();

        return flippedImage;
    }

}
