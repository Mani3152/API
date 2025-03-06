package Programs;

import java.util.Arrays;

public class ArraysEqualOrNot {
    public static void main(String[] args) {
        int a1[]={1,2,3,4};
        int a2[]={1,2,3,4};
//      Arrays.sort(a2);
//      int a[]=a2;

//        boolean value=Arrays.equals(a1,a2);
//        if (value==true){
//            System.out.println("Arrays are equal");
//        }else {
//            System.out.println(" Arrays are not equal");
//        }

        boolean status=true;
        if (a1.length==a2.length){
            for (int i=0;i<a1.length;i++){
                if (a1[i]!=a2[i]){
                    System.out.println(a1[i]);
                    status=false;
                }
            }
            if (status == true) {
                System.out.println("Equal");
            }else {
                System.out.println("Not equal");
            }
        }
    }
}
