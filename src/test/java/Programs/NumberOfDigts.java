package Programs;

public class NumberOfDigts {
    public static void main(String[] args) {
        int num=1234569;
        int count=0;

        // total Number of digits
        while (num>0){
            num=num/10;
            count++;
        }
        System.out.println(count);





//Sum of digits
//        while (num>0){
//            count=count+num%10;
//            num=num/10;
//        }
//        System.out.println(count);




//
//        for (int i=num;i>0;i--){
//            count=count+num%10;
//            num=num/10;
//        }
//        System.out.println(count);
    }
}
