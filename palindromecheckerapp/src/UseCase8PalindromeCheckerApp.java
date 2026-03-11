class Node {

    char data;
    Node next;

    Node(char data) {
        this.data = data;
        this.next = null;
    }

}

public class UseCase8PalindromeCheckerApp {

    static Node createList(String input) {

        Node head = null;
        Node tail = null;

        for (char c : input.toCharArray()) {

            Node newNode = new Node(c);

            if (head == null) {

                head = newNode;
                tail = newNode;

            } else {

                tail.next = newNode;
                tail = newNode;

            }

        }

        return head;

    }

    static Node reverse(Node head) {

        Node prev = null;
        Node current = head;

        while (current != null) {

            Node next = current.next;
            current.next = prev;
            prev = current;
            current = next;

        }

        return prev;

    }

    static boolean isPalindrome(Node head) {

        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {

            slow = slow.next;
            fast = fast.next.next;

        }

        Node secondHalf = reverse(slow);

        Node firstHalf = head;

        while (secondHalf != null) {

            if (firstHalf.data != secondHalf.data) {
                return false;
            }

            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;

        }

        return true;

    }

    public static void main(String[] args) {

        String input = "level";

        Node head = createList(input);

        boolean result = isPalindrome(head);

        System.out.println("Input : " + input);
        System.out.println("Is Palindrome? : " + result);

    }
}
