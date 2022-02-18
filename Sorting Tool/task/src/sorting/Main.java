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

        sort = arguments.containsKey("-sortingType") ? sortingType.valueOf(arguments.get("-sortingType").toUpperCase())
                : sortingType.NATURAL;

        data = dataType.valueOf(arguments.get("-dataType").toUpperCase());

        switch (data) {
            case LONG:
                if (sort.param.equals("natural")) {
                    Sorting.naturalLongSort();
                } else {
                    Sorting.countSort("numbers");
                }
                break;
            case LINE:
                if (sort.param.equals("natural")) {
                    Sorting.naturalLineSort();
                } else {
                    Sorting.countLineSort("lines");
                }
                break;
            case WORD:
                if (sort.param.equals("natural")) {
                    Sorting.naturalWordSort();
                } else {
                    Sorting.countWordSort("words");
                }
                break;
            default:
                break;
        }

    }
}
