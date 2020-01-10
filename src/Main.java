import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    static final String SOURCE_IMAGE="C:\\Users\\Asus\\IdeaProjects\\ParalelaProiectThreads\\data\\image";
    static final int NR_THREADS=4;

    public static void main(String[] args) throws IOException {
        Image inputImage=new Image(SOURCE_IMAGE);
        Image blurredImage=new Image(SOURCE_IMAGE);


        ExecutorService executorService = Executors.newFixedThreadPool(NR_THREADS);

        float start =  System.nanoTime() / 1000000;
        for(int j=1;j<inputImage.width-1;j++){
            for (int i=1;i<inputImage.height-1;i++){
                executorService.execute(new Convolution(inputImage,blurredImage,i,j));
            }
        }
        executorService.shutdown();

        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        float end = System.nanoTime() / 1000000;

        System.out.println ("Time for "+NR_THREADS+" threads: "+(end - start) / 1000);

        blurredImage.saveGrayscaleImage();
    }

}
