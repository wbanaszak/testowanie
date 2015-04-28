
public class CalculatorImpl implements Calculator {
    @Override
    public double add(double a, double b) {
        return a + b;
    }

    @Override
    public double sub(double a, double b) {
        return a - b;
    }

    @Override
    public double multi(double a, double b) {
        return a * b;
    }

    @Override
    public double div(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException();
        }
        return a / b;
    }

    @Override
    public boolean greater(double a, double b) {
        return a > b;
    }
}
