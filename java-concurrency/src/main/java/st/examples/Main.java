package st.examples;

import java.util.LinkedList;
import java.util.Optional;

public class Main {

    {
        System.out.println("1");
    }

    static {
        System.out.println("2");
    }

    public Main() {
        System.out.println("3");
    }

        public static void main(String[] args) {

            Optional<String> mapOpt = Optional.of("dsd").map(String::toUpperCase);
            Optional<String> sds = Optional.of("sds").flatMap(s -> Optional.of(s.toUpperCase()));




            int i = sumDigits(5245);

//            List<String> listArr = new LinkedList<>();
//            listArr.add("1");
//            listArr.add("2");
//            listArr.add("3");
            LinkedList<String> list = new LinkedList<>();
            list.push("c");
            list.push("a");
            list.push("r");
            list.push("p");
            list.push("e");
            list.push("t");
            list.push("s");

            while (!list.isEmpty()) {
                String element = list.pop();
                System.out.print(element);
            }
        }


        public static int sumDigits(int number) {
            int sum = 0;
            while (number > 0) {
                sum += number % 10;
                number = number / 10;
            }
            return sum;
        }



}
