package sorting;
import java.io.*;
import java.util.*;

public class Main {
    public static HashMap<String, String> parseArgs(String[] args) {
        HashMap<String, String> arguments = new HashMap<>();
        HashMap<String, String> ioInfo = new HashMap<>();
        List<String> argumentList = new ArrayList<>(Arrays.asList(args));

        if (argumentList.contains("-inputFile")) {
            ioInfo.put("-inputFile", argumentList.get(argumentList.indexOf("-inputFile") + 1));
        }

        if (argumentList.contains("-outputFile")) {
            ioInfo.put("-outputFile", argumentList.get(argumentList.indexOf("-outputFile") + 1));
        }
        List<String> invalidArguments = new ArrayList<>(argumentList);
        List<String> validArguments = new ArrayList<>
                (Arrays.asList("-sortingType", "-dataType", "long", "line", "word", "natural", "byCount"));

        if (invalidArguments.removeAll(validArguments) && !invalidArguments.isEmpty()) {
            invalidArguments.forEach(argument ->
                    System.out.printf("\"%s\" is not a valid parameter. It will be skipped", argument));
            argumentList.removeAll(invalidArguments);
        }

        if (argumentList.size() % 2 != 0) {
            if (argumentList.contains("-sortingType")) {
                System.out.println("No sorting type defined!");
            }
            if (argumentList.contains("-dataType")) {
                System.out.println("No data type defined!");
            }
        } else {
            String[] formatArgs = argumentList.toArray(new String[0]);
            for (int i = 0; i < formatArgs.length; i++) {
                arguments.put(formatArgs[i++], formatArgs[i]);
            }
        }
        arguments.putAll(ioInfo);
        return arguments;
    }

    public static void main(String[] args) {
        HashMap<String, String> arguments = parseArgs(args);

        if (arguments.containsKey("-outputFile")) {
            try {
                PrintWriter printWriter = new PrintWriter((arguments.get("-outputFile")));
                Sorter sorter = new Sorter(arguments);
                printWriter.print(sorter);
            } catch (FileNotFoundException e) {
                System.out.print("No file found");
            }
        } else {
            try {
                Sorter sorter = new Sorter(arguments);
                System.out.println(sorter);
            } catch (FileNotFoundException error) {
                System.out.println("File not found");
            }
        }
    }
}
