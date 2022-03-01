package sorting;
import java.util.*;

enum dataType {
    LONG("long"),
    LINE("line"),
    WORD("word");

    dataType(String param) {
        this.param = param;
    }
    final String param;
}

enum sortingType {
    NATURAL("natural"),
    BYCOUNT("byCount");

    sortingType(String param) {
        this.param = param;
    }
    final String param;
}

public class Sorter {
    static Scanner scanner = new Scanner(System.in);
    private final dataType data;
    private final sortingType sort;
    SortedMap<Number, Integer> numberMap = new TreeMap<>();
    SortedMap<String, Integer> wordMap = new TreeMap<>();
    List<Number> numberList;
    private int totalValues;

    public Sorter(HashMap <String, String> args) {
        this.data = dataType.valueOf(args.getOrDefault("-dataType","word").toUpperCase());
        this.sort = sortingType.valueOf(args.getOrDefault("-sortingType", "natural").toUpperCase());

        switch (this.sort) {
            case BYCOUNT -> {
                switch (this.data) {
                    case LONG -> this.totalValues = setMap(this.numberMap);
                    case LINE, WORD -> this.totalValues = setMap(this.wordMap, 0);
                }
            }
            case NATURAL -> {
                this.numberList = setList(this.data);
                switch (this.data) {
                    case LONG -> numberList.sort(Comparator.comparing(Number::getIntVal));
                    case LINE, WORD -> numberList.sort(Comparator.comparing(Number::getStringVal));
                }
            }
        }
    }

    public List<Number> setList(dataType data) {
        List<Number> list = new ArrayList<>();
        while (scanner.hasNext()) {
            if (data.name().equals("LINE")) {
                list.add(new Number(scanner.nextLine()));
            } else {
                list.add(new Number(scanner.next()));
            }
        }
        return list;
    }

    public int setMap(SortedMap<String, Integer> map, int num) {
        int total = 0;
        while (scanner.hasNext()) {
            if (data.name().equals("LINE")) {
                map.merge(scanner.nextLine(), 1, Integer::sum);
            } else {
                map.merge(scanner.next(), 1, Integer::sum);
            }
        }
        for (int value : map.values()) {
            total += value;
        }
        return total;
    }

    public int setMap(SortedMap<Number, Integer> map) {
        int total = 0;
        while (scanner.hasNext()) {
            try {
                long num = Long.parseLong(scanner.next());
                map.merge(new Number(String.valueOf(num)), 1, Integer::sum);
            } catch (NumberFormatException e) {
                System.out.println("\"%s\" is not a long. It will be skipped.\n");
            }
        }
        for (int value : map.values()) {
            total += value;
        }
        return total;
    }

    public String outputByCount(List<Map.Entry<String, Integer>> outputList, int total) {
        StringBuilder output = new StringBuilder();
        outputList.sort(Map.Entry.comparingByValue());
        for (var entry : outputList) {
            double percent = (double) entry.getValue() / total * 100;
            output.append(String.format("%s: %d times(s), %.0f%%\n", entry.getKey(), entry.getValue(), percent));
        }
        return output.toString();
    }

    public String outputByCount(int total, List<Map.Entry<Number, Integer>> outputList){
        StringBuilder output = new StringBuilder();
        outputList.sort(Map.Entry.comparingByValue());
        for (var entry : outputList) {
            double percent = (double) entry.getValue() / total * 100;
            output.append(String.format("%d: %d times(s), %.0f%%\n",
                    entry.getKey().getIntVal(), entry.getValue(), percent));
        }
        return output.toString();
    }

    @Override
    public String toString() {
        StringBuilder formattedString = new StringBuilder();
        String output = switch (this.data) {
            case LONG -> "numbers";
            case LINE -> "lines";
            case WORD -> "words";
        };
        formattedString.append(String.format("Total %s: ", output));

        switch (this.sort) {
            case BYCOUNT -> {
                formattedString.append(String.format("%d.\n", this.totalValues));
                switch (this.data) {
                    case LINE, WORD ->
                        formattedString.append(outputByCount(new ArrayList<>(wordMap.entrySet()), this.totalValues));
                    case LONG ->
                        formattedString.append(outputByCount(this.totalValues, new ArrayList<>(numberMap.entrySet())));
                }
            }
            case NATURAL -> {
                formattedString.append(String.format("%d.\nSorted data: ", numberList.size()));
                switch (this.data) {
                    case LINE -> numberList.forEach(line -> formattedString.append(line).append("\n"));
                    case LONG, WORD -> numberList.forEach(number -> formattedString.append(number.getIntVal()).append(" "));
                }
            }
        }
        return formattedString.toString();
    }
}