package Programs;

public class ReverseString {
    public static void main(String[] args) {
  String name="Mani";
  String rev="";
  int len=name.length();
//  for (int i=len-1;i>=0;i--){
//      rev=rev+name.charAt(i);
//  }
//        System.out.println(rev);

      char[] a=  name.toCharArray();
      for (int i=len-1;i>=0;i--) {
          rev = rev + a[i];
      }
        System.out.println(rev);
}}
