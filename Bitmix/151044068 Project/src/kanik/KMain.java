package kanik;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * test class
 */
public class KMain {

    public static void main(String[] arg) throws IOException {
        /*** to read image */
        BufferedImage image;
        File file;
        try {
            file = new File("example.jpg");
            image = ImageIO.read(file);
        } catch (IOException e) {
            file = new File("example.png");
            image = ImageIO.read(file);
        }
        /*** image's width and height */
        int image_width = image.getWidth();
        int image_height = image.getHeight();
        /*** created an array to read all pixels */
        K2DArray[][] arr = new K2DArray[image_width][image_height];
        /*** we have a queue and also 3 comparison method
         * so created a queue object and these comparison class's object*/
        PriorityQueue priorityQueueEUC = new PriorityQueue();
        PriorityQueue priorityQueueLEX = new PriorityQueue();
        KPQEUC kpqeuc = new KPQEUC();
        KPQLEX kpqlex = new KPQLEX();
        /*** to use the comparison methods of these class */
        priorityQueueEUC.setComparator(kpqeuc);
        priorityQueueLEX.setComparator(kpqlex);
        /*** among the width size and height size, reads image pixel to pixel
         * and assigns them array*/
        int temp=0;
        for(int i=0; i<image_width; i++) {
            for(int j=0; j<image_height; j++) {
                Color color = new Color (image.getRGB(i, j));
                arr[i][j] = new K2DArray(color.getRed(), color.getGreen(), color.getBlue());
                /*** then adds queue */
                priorityQueueLEX.offer(arr[i][j]);
                priorityQueueEUC.offer(arr[i][j]);
                if((i+1)*(j+1)<=100 && temp==0)
                    System.out.println("Thread 1: "+arr[i][j].toString());
                else
                    temp =1;
            }
        }
        /*** while the first element is not null, print all the elements */
        while(priorityQueueLEX.peek() != null) {
            System.out.println("Thread2-PQLEX: "+priorityQueueLEX.remove().toString());
        }
        while(priorityQueueEUC.peek() != null) {
            System.out.println("Thread3-PQEUC: "+priorityQueueEUC.poll().toString());
        }

/*
        K2DArray[] test = new K2DArray[21];
        System.out.println("***************testing***************");
        test[0] = new K2DArray(0,0,0);
        test[1] = new K2DArray(0,1,76);
        test[2] = new K2DArray(2,0,0);
        test[3] = new K2DArray(3,0,0);
        test[4] = new K2DArray(4,0,0);
        test[5] = new K2DArray(4,0,2);
        test[6] = new K2DArray(5,0,0);
        test[7] = new K2DArray(15,0,0);
        test[8] = new K2DArray(7,3,66);
        test[9] = new K2DArray(1,125,49);
        test[10] = new K2DArray(1,125,49);
        test[11] = new K2DArray(1,91,64);
        test[12] = new K2DArray(6,4,2);
        test[13] = new K2DArray(4,31,12);
        test[14] = new K2DArray(1,5,10);
        test[15] = new K2DArray(16,19,21);
        test[16] = new K2DArray(15,9,96);
        test[17] = new K2DArray(13,1,97);
        test[18] = new K2DArray(20,11,15);
        test[19] = new K2DArray(26,9,8);
        test[20] = new K2DArray(30,9,19);

        KPQEUC testeuc = new KPQEUC();
        KPQLEX testlex = new KPQLEX();
        PriorityQueue testLEX = new PriorityQueue();
        PriorityQueue testEUC = new PriorityQueue();

        testLEX.setComparator(testlex);
        testEUC.setComparator(testeuc);

        for(int i=0; i<21; i++) {
            testLEX.offer(test[i]);
            testEUC.offer(test[i]);
        }

        System.out.println("******************Thread2-PQLEX peek()******************");
        System.out.println("Thread2-PQLEX: "+testLEX.peek());
        System.out.println("******************Thread2-PQLEX element()******************");
        System.out.println("Thread2-PQLEX: "+testLEX.element());
        System.out.println("******************Thread2-PQLEX poll()******************");
        while(testLEX.peek() != null) {
            System.out.println("Thread2-PQLEX: "+testLEX.poll());
        }
        System.out.println("******************Thread3-PQEUC peek()******************");
        System.out.println("Thread3-PQEUC: "+testEUC.peek());
        System.out.println("******************Thread3-PQEUC element()******************");
        System.out.println("Thread3-PQEUC: "+testEUC.element());
        System.out.println("******************Thread3-PQEUC remove()******************");
        while(testEUC.peek() != null) {
            System.out.println("Thread3-PQEUC: "+testEUC.remove());
        }
*/
    }
}