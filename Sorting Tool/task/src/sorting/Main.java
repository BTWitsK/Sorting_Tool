package sorting;

import java.util.*;

enum argType {
    LONG("long"),
    LINE("line"),
    WORD("word");

    argType(String param) {
        this.param = param;
    }

    String param;
}

public class Main {

    public static void main(final String[] args) {
        argType argument;
        List<String> arguments = Arrays.asList(args);

        if (arguments.contains("-sortIntegers")) {
            Sorting.sortInts();
        } else {
            try {
                argument = argType.valueOf(args[1].toUpperCase());
            } catch (ArrayIndexOutOfBoundsException error) {
                argument = argType.WORD;
            }

            switch (argument) {
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
}
