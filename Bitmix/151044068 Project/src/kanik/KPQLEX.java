package kanik;

import java.util.Comparator;

/**
 * compares according to standard lexicographical comparison from discrete math
 */
public class KPQLEX implements Comparator<K2DArray> {

    /**
     * compares color's pixel's
     * @param left left child of the parent
     * @param right right child of the parent
     * @return -1 if left child's pixel's smaller than right one, 1 if left child's pixel's bigger than right one, 0 if their all color pixels are equals
     */
    @Override
    public int compare(K2DArray left, K2DArray right) {
        if (left.red < right.red) {
            return -1;
        }
        else if (left.red > right.red) {
            return 1;
        }
        else if(left.red == right.red && left.green < right.green) {
            return -1;
        }
        else if(left.red == right.red && left.green > right.green) {
            return 1;
        }
        else if(left.red == right.red && left.green == right.green && left.blue < right.blue) {
            return -1;
        }
        else if(left.red == right.red && left.green == right.green && left.blue > right.blue) {
            return 1;
        }

        return 0;
    }
}
