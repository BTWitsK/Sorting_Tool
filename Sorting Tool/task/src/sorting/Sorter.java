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
    private dataType data;
    sortingType sort;
    SortedMap<Number, Integer> numberMap = new TreeMap<>();
    SortedMap<String, Integer> wordMap = new TreeMap<>();
    List<Number> numberList;

    public Sorter(HashMap <String, String> args) {
        this.data = dataType.valueOf(args.get("-dataType").toUpperCase());
        this.sort = args.containsKey("-sortingType") ? sortingType.valueOf(args.get("-sortingType").toUpperCase())
                        : sortingType.NATURAL;
        switch (this.sort) {
            case BYCOUNT -> {
                switch (this.data) {
                    case LONG -> setMap(this.numberMap);
                    case LINE, WORD -> setMap(this.wordMap, 0);
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

    public void setMap(SortedMap<String, Integer> map, int num) {
        while (scanner.hasNext()) {
            if (data.name().equals("LINE")) {
                map.merge(scanner.nextLine(), 1, Integer::sum);
            } else {
                map.merge(scanner.next(), 1, Integer::sum);
            }
        }
    }

    public void setMap(SortedMap<Number, Integer> map) {
        while (scanner.hasNext()) {
            if (data.name().equals("LINE")) {
                map.merge(new Number(scanner.nextLine()), 1, Integer::sum);
            } else {
                map.merge(new Number(scanner.next()), 1, Integer::sum);
            }
        }
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
        int total = 0;
        String output = switch (this.data) {
            case LONG -> "numbers";
            case LINE -> "lines";
            case WORD -> "words";
        };

        formattedString.append(String.format("Total %s: ", output));

        switch (this.sort) {
            case BYCOUNT -> {
                switch (this.data) {
                    case LINE, WORD -> {
                        for (int value : wordMap.values()) {
                            total += value;
                        }
                        formattedString.append(String.format("%d.\n", total));
                        formattedString.append(outputByCount(new ArrayList<>(wordMap.entrySet()), total));
                    }
                    case LONG -> {
                        for (int value : numberMap.values()) {
                            total += value;
                        }
                        formattedString.append(String.format("%d.\n", total));
                        formattedString.append(outputByCount(total, new ArrayList<>(numberMap.entrySet())));
                    }
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