package Programs;

import java.nio.Buffer;

public class ReverseNumber {
    public static void main(String[] args) {
//        int rev=0;
        int num=1234;
//        while (num!=0){
//            rev=rev*10+num%10;
//            num=num/10;
//        }
//        System.out.println(rev);
//        StringBuffer sb=new StringBuffer(String.valueOf(num));
//       StringBuffer rev= sb.reverse();
//        System.out.println(rev);

        StringBuilder sb=new StringBuilder();
        sb.append(num);
       StringBuilder rev=  sb.reverse();
        System.out.println(rev);
    }
}
