public class StringIterator {
    public static void main(String[] args) {
        System.out.println("Saying " + args[0] + " for " + args[1] + " times.\n");
        for (int i = 0; i < Integer.parseInt(args[1]); i++) {
            System.out.println(args[0]);
        }
        System.out.println("\rDone.");
    }
}
