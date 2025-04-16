package Programs;

public class RemoveUnwanted {
    public static void main(String[] args) {


    String name="Ma45678eni#$%^&";
    for (int i=0;i<name.length();i++){
       char ch= name.charAt(i);
       if (ch>65&&ch<90||ch>96&&ch<122){
           System.out.println(ch);
       }
    }

}}
