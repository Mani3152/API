package Programs;

import java.util.Arrays;
import java.util.Collections;

public class SortingArray {
    public static void main(String[] args) {
       Integer a[]={1,2,5,8,3};
       for (int i=0;i<a.length-1;i++){
           for (int j=0;j<a.length-1;j++){
               if (a[j]>a[j+1]){
                   int temp=a[j];
                   a[j]=a[j+1];
                   a[j+1]=temp;
               }
           }
       }
        System.out.println(Arrays.toString(a));


//        Arrays.sort(a,Collections.reverseOrder());
//        System.out.println(Arrays.toString(a));


    }
}
