package Basic;

public final class IntegerTwo {

    final int value;
    public IntegerTwo(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static void main(String[] args) {
        IntegerTwo integerTwo = new IntegerTwo(1);
        System.out.println(integerTwo.getValue());
        // integerTwo.value = 2; // it will raise error
    }
}
