package Programs;

import java.util.HashSet;

public class DuplicateElementsArray {
    public static void main(String[] args) {
        boolean flag=false;
        int a[]={1,2,3,4};
        for (int i=0;i<a.length;i++){
            for (int j=i+1;j<a.length;j++){
                if (a[i]==a[j]){
                    System.out.println("Duplicate elments   "+a[i]);
                    flag=true;
                }

            }
        }
        if (flag==false){
            System.out.println("not found");
        }
    }

//    public static void main(String[] args) {
//        HashSet<Integer> value = new HashSet<>();
//       int a[]={1,2,3,3,4};
//       for (int i=0;i<a.length;i++) {
//           if (!value.add(a[i])) {
//               System.out.println("duplicate found  "+a[i]);
//           }
//       }
//    }
}
