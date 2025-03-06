package Programs;

public class EvenOddNumbers {
    public static void main(String[] args) {
        int num = 1222;
        int even = 0;
        int odd = 0;

        //Find the even and odd numbers
        while (num > 0) {
            int rem = num % 10;
            if (rem % 2 == 0) {
                even++;
            } else {
                odd++;
            }
            num = num / 10;
        }
        System.out.println(even);
        System.out.println(odd);

//        String evenodd=(num%2==0)?"even":"odd";
//        System.out.println(num+"  is"+evenodd);



    }
}
