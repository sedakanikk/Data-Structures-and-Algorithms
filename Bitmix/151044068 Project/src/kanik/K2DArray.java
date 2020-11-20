package kanik;

/**
 * includes pixels's colors
 */
public class K2DArray {
    int red;
    int green;
    int blue;

    public K2DArray() {
    }

    /**
     * constructor, pictures has 3 different color
     * @param red first pixel value
     * @param green second pixel value
     * @param blue third pixel value
     */
    public K2DArray(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    /**
     * to convert all them string
     * @return converted type
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(red);
        sb.append(",");
        sb.append(green);
        sb.append(",");
        sb.append(blue);
        sb.append("]");
        return sb.toString();
    }
}
