package kanik;

import java.util.Comparator;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * compares according to whichever vector has the greater L2 norm is considered greater
 */
public class KPQEUC implements Comparator<K2DArray>  {

    /**
     * according to given formula, calculates the roots of the squares of the pixel values of the given two values
     * @param left left child of the parent
     * @param right right child of the parent
     * @return -1 if left child is smaller than right one, 1 if the left child is bigger than right child, 0 if equals
     */
    @Override
    public int compare(K2DArray left, K2DArray right) {

        if( sqrt(pow(left.red,2)+pow(left.green,2)+pow(left.blue,2)) < sqrt(pow(right.red,2)+pow(right.green,2)+pow(right.blue,2)) ) {
            return -1;
        }
        else if( sqrt(pow(left.red,2)+pow(left.green,2)+pow(left.blue,2)) > sqrt(pow(right.red,2)+pow(right.green,2)+pow(right.blue,2)) ) {
            return 1;
        }
        return 0;
    }
}
