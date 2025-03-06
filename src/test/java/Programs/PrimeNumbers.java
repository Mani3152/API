package Programs;

public class PrimeNumbers {
//    public static void main(String[] args) {
//      int num=0;
//      int count=0;
//      for (int i=1;i<=num;i++){
//          if (num%i==0){
//              count++;
//          }
//      }
//      if (count==2){
//          System.out.println("Prime Number");
//      }else {
//          System.out.println("Not Prime");
//      }
//    }
public static void main(String[] args) {

    for (int i=1;i<=20;i++){
        int count=0;
        for (int j=1;j<=i;j++){
            if (i%j==0){
            count++;
        }}
        if (count==2){
            System.out.println(i);
        }
    }
}
}
