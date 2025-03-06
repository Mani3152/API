package Programs;

public class Largestnumber {
    public static void main(String[] args) {
        int a=96, b=2, c=33;
//        if (a>b&&a>c){
//            System.out.println(a+" is largest number");
//        } else if (b>a&&b>c) {
//            System.out.println(b+" is largest number");
//        }else {
//            System.out.println(c+" is largest ");
//        }

        int lar1=a>b?a:b;
        int lar2=c>lar1?c:lar1;
        System.out.println(lar2+" is large");
    }
}
