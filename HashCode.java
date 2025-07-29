import java.util.Scanner;
public class hashcode {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String input = scanner.nextLine();

        System.out.print("Enter hash set size: ");
        int sizeofhash = scanner.nextInt();

        int result = getHashValue(input, sizeofhash);

        System.out.println("Hash value is: " + result);

        scanner.close();
    }

    
    public static int getHashValue(String input, int sizeofhash) {
        int sum = 0;
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            int ascii = (int) ch;
            sum += ascii;
        }

        int hashValue = sum * sizeofhash;
        return hashValue;
    }
}
