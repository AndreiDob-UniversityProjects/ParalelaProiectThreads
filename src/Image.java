import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Image {
     int height;
     int width;
    private String fileName;


    public int[][] matrix;

    public Image(String fileName) throws IOException {
        this.fileName = fileName;
        this.matrix=readGrayscaleImage(fileName);
    }

    private int[][] readGrayscaleImage(String fileName) throws IOException {
        File file = new File(fileName+".jpg");
        BufferedImage img = ImageIO.read(file);
        this.width = img.getWidth();
        this.height = img.getHeight();
        int[][] imgArr = new int[width][height];
        Raster raster = img.getData();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                imgArr[i][j] = raster.getSample(i, j, 0);
            }
        }
        return imgArr;
    }

    public void saveGrayscaleImage() {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++) {
                int[] array = new int[3];
                int h=matrix.length;
                int w=matrix[0].length;
                Arrays.fill(array,0,2, matrix[j][i]);
                img.getRaster().setPixel(j, i, array);
            }
        fileName = fileName + "_processed.jpg";
        File outputfile = new File(fileName);
        try {
            ImageIO.write(img, "jpg", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
