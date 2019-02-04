package st.examples.radionov;

class TT {
    private void one() {
        System.out.println("one1");
    }

    public void two() {
        System.out.println("two1");
    }
}


public class SequentialCalculate {

    //https://www.youtube.com/watch?v=W82D9eUn6q8
    //https://www.slideshare.net/yandex/java-threads-37348280
//    public double calculate(double start, double end, double step) {
//
//        double result = 0.0;
//        double x = start;
//        while (x < end) {
//            result += step * (Math.sin(x) * Math.sin(x) + Math.cos(x) * Math.cos(x));
//            x += step;
//
//        }
//        return result;
//    }

    public static void main(String[] args) {

        TT tt = new TT() {
          public void one() {
              System.out.println("one over");
          }

            public void two() {
                one();
                System.out.println("two over");
            }
        };
        tt.two();
//        SequentialCalculate calc = new SequentialCalculate();
//        double calculate = calc.calculate(1, 7, 1);
//        System.out.println(calculate);

    }
}
