package Programs;

public class SecondHeigestNumber {
    public static void main(String[] args) {
        int[] a = {5, 3, 1, 4, 2};
        int min = a[0]; // Initialize to the largest possible value
        int secmin = a[0];

        for (int i = 0; i < a.length; i++) {
            if (a[i] < min) {
                secmin = min;  // Update second minimum
                min = a[i];    // Update minimum
            } else if (a[i] < secmin) {
                secmin = a[i]; // Update second minimum
            }
        }

        System.out.println("Minimum: " + min);
        System.out.println("Second Minimum: " + secmin);
    }
}

