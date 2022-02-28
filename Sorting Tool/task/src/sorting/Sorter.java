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

public class Sorter {
    static Scanner scanner = new Scanner(System.in);
    private dataType data;
    sortingType sort;
    SortedMap<Number, Integer> numberMap;
    SortedMap<String, Integer> wordMap;
    List<Number> numberList;

    public Sorter(HashMap <String, String> args) {
        this.data = dataType.valueOf(args.get("-dataType").toUpperCase());
        this.sort = args.containsKey("-sortingType") ? sortingType.valueOf(args.get("-sortingType").toUpperCase())
                        : sortingType.NATURAL;

        if (sort.name().equals("BYCOUNT")) {
            this.numberMap = setMap();
        }
        this.numberList = setList(data);
    }

    public dataType getDataType() {
        return this.data;
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

    public SortedMap<Number, Integer> setMap() {
       SortedMap<Number, Integer> map = new TreeMap<>();
        while (scanner.hasNext()) {
            if (data.name().equals("LINE")) {
                map.merge(new Number(scanner.nextLine()), 1, Integer::sum);
            } else {
                map.merge(new Number(scanner.next()), 1, Integer::sum);
            }
        }
       return map;
    }

    public void sortByCount() {
        switch (this.data) {
            case WORD, LINE:
                numberMap.forEach((x,y) -> wordMap.put(x.getStringVal(), y));
                break;
        }
    }

    public void sortNaturally() {
        switch (this.data) {
            case LONG:
                numberList.sort(Comparator.comparing(Number::getIntVal));
                break;
            case WORD, LINE:
                numberList.sort(Comparator.comparing(Number::getStringVal));
                break;
        }
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
            case BYCOUNT:
                for (int value : numberMap.values()) {
                    total += value;
                }
                formattedString.append(String.format("%d.\n", total));

                if (!this.data.name().equals("LONG")) {
                    List<Map.Entry<String, Integer>> outputList = new ArrayList<>(wordMap.entrySet());
                }


                List<Map.Entry<Number, Integer>> outputList = new ArrayList<>(numberMap.entrySet());


                for (var entry : outputList) {
                    double percent = (double) entry.getValue() / total * 100;
                    System.out.printf("%s: %d times(s), %.0f%%\n"
                            , entry.getKey(), entry.getValue(), percent);
                }
                break;
            case NATURAL:
                formattedString.append(String.format("%d.\nSorted data: ", numberList.size()));
                switch (this.data) {
                    case LINE:
                        numberList.forEach(line -> formattedString.append(line).append("\n"));
                        break;
                    default:
                        numberList.forEach(number -> formattedString.append(number).append(" "));
                        break;
                }
                break;
        }
        return formattedString.toString();
    }
}

