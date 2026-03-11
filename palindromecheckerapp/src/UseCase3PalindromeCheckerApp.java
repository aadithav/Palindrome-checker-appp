public class UseCase3PalindromeCheckerApp {

    public static void main(String[] args) {

        String input = "malayalam";
        String reversed = "";

        for (int i = input.length() - 1; i >= 0; i--) {

            reversed = reversed + input.charAt(i);

        }

        if (input.equals(reversed)) {

            System.out.println(input + " is a Palindrome");

        } else {

            System.out.println(input + " is NOT a Palindrome");

        }

    }
}