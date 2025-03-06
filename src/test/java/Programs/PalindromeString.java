package Programs;

public class PalindromeString {
    public static void main(String[] args) {
        String name="Madam";
        String orgString=name;
        String rev="";
      int len=  name.length();
      for (int i=len-1;i>=0;i--){
          rev=rev+name.charAt(i);

      }
      if (orgString.equalsIgnoreCase(rev)){
          System.out.println(orgString+" Palindrome");
      }else {
          System.out.println(orgString+" not palindrome");
      }
    }
}
