package sorting;

public class Number implements Comparable<Number> {
    private int intVal;
    private final String stringVal;

    public Number(String num) {
        try {
            this.intVal = Integer.parseInt(num);
        } catch (Exception e) {
            this.intVal = 0;
        }
        this.stringVal = num;
    }

    @Override
    public int compareTo(Number num) {
        return Integer.compare(this.getIntVal(), num.getIntVal());
    }

    public String getStringVal() {
        return this.stringVal;
    }

    public int getIntVal() {
        return this.intVal;
    }

}
