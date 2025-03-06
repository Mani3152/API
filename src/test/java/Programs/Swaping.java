package Programs;

import java.util.ArrayList;
import java.util.List;

public class Swaping {
    public static void main(String[] args) {
        int a = 10, b = 20;
//        int t = a;
//        a = b;
//        b = t;
//        System.out.println(a + "------" + b);

//    a=a+b;
//    b=a-b;
//    a=a-b;
//        System.out.println(a+"---"+b);

//        a=a*b;
//        b=a/b;
//        a=a/b;
//        System.out.println(a+"---"+b);

//
        b=a+b-(a=b);
        System.out.println(a+"---"+b);
        b=a;

        System.out.println(a+"---"+b);


        List<Character> letters = new ArrayList<>();
        letters.add('A');
        letters.add('C');
        letters.add('G');
        letters.add('S');
        letters.add('Y');

        // Step 2: Define the letters to print
        List<Character> targetLetters = List.of('C', 'S', 'Y');

        // Step 3: Print only the target letters
        System.out.println("Selected letters:");
        for (Character letter : letters) {
            if (targetLetters.contains(letter)) {
                System.out.println(letter);
            }
        }

//        // Step 4: Filter letters and add them to the selected list
//        for (Character letter : letters) {
//            if (targetLetters.contains(letter)) {
//                selectedLetters.add(letter);
//            }
//        }
    }
}
