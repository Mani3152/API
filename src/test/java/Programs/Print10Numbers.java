package Programs;

public class Print10Numbers {
        public static void printNumbes(int n){
        if (n<=10){
            System.out.println(n);
            printNumbes(n+1);

        }
    }
    public static void main(String[] args) {
       printNumbes(1);
        }
    }

