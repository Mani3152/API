package Programs;

public class CheckLeepYear {
    public static void main(String[] args) {
        int year=2019;
        if (year%4==0){
            System.out.println("Leep year");
        } else if (year%100 == 0) {
            System.out.println("Not Leeap Year");
        } else if (year%400==0) {
            System.out.println("Leep Year");
        }else {
            System.out.println("Not leep year");
        }
    }
}
