package sorting;
import java.util.*;


public class Main {

    public static HashMap<String, String> parseArgs(String[] args) {
        HashMap<String, String> arguments = new HashMap<>();

        for (int i = 0; i < args.length; i++) {
            arguments.put(args[i++], args[i]);
        }
        return arguments;
    }

    public static void main(String[] args) {
        HashMap<String, String> arguments = parseArgs(args);
        Sorter sorter = new Sorter(arguments);

        System.out.println(sorter);


    }
}
