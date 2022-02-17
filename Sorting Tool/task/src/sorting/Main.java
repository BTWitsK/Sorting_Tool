package sorting;
import java.util.*;

enum dataType {
    LONG("long"),
    LINE("line"),
    WORD("word");

    dataType(String param) {
        this.param = param;
    }

    String param;
}

enum sortingType {
    NATURAL("natural"),
    BYCOUNT("byCount");

    sortingType(String param) {
        this.param = param;
    }

    String param;
}

public class Main {

    public static HashMap<String, String> parseArgs(String[] args) {
        HashMap<String, String> arguments = new HashMap<>();

        for (int i = 0; i < args.length; i++) {
            arguments.put(args[i++], args[i]);
        }
        return arguments;
    }

    public static void main(String[] args) {
        dataType data;
        sortingType sort;
        HashMap<String, String> arguments = parseArgs(args);

        sort = arguments.containsKey("-sortingType") ? sortingType.valueOf(arguments.get("-sortingType"))
                : sortingType.NATURAL;

        data = dataType.valueOf(arguments.get("-dataType"));

        switch (data) {
            case LONG:
                Count.longCount();
                break;
            case LINE:
                Count.lineCount();
                break;
            case WORD:
                Count.wordCount();
                break;
            default:
                break;
        }

    }
}
