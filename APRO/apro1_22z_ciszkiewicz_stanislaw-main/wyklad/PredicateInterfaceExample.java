package wyklad;

import java.util.function.Predicate;

        public class PredicateInterfaceExample<T> {

            public static void main(String[] args) {
                //creating predicate which uses lambda expression to check if the number is even
                Predicate <Integer> predicate = (t) -> {
                    if (t % 2 == 0) {
                        return true;
                    } else {
                        return false;
                    }
                };
                System.out.println(predicate.test(9));
            }
        }

