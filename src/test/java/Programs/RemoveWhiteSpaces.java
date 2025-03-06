package Programs;

import java.util.Arrays;

public class RemoveWhiteSpaces {
    public static void main(String[] args) {
        String s = "Java programing Language";

        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'a') {
                count++;
                System.out.println("Index count "+i);
            }
        }
        System.out.println("No of count "+count);
    }
}
