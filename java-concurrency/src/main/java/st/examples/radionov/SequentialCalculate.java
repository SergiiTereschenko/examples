package st.examples.radionov;

public class SequentialCalculate {

    public double calculate(double start, double end, double step) {

        double result = 0.0;
        double x = start;
        while (x < end) {
            result += step * (Math.sin(x) * Math.sin(x) + Math.cos(x) * Math.cos(x));
            x += step;

        }
        return result;
    }

    public static void main(String[] args) {

        SequentialCalculate calc = new SequentialCalculate();
        double calculate = calc.calculate(1, 7, 1);
        System.out.println(calculate);

    }
}
