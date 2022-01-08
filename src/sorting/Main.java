package sorting;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(final String[] args) {
        SortingTool sortingTool = new SortingTool();
        String validArguments = "-dataType|-sortingType|-inputFile|-outputFile|long|line|word|natural|byCount";
        List<String> inputArguments = Arrays.asList(args);
        if (args.length == 1) {
            System.out.println(args[0].equals("-sortingType") ? "No sorting type defined!" : "No data type defined!");
        } else if (args.length > 1) {
            List<String> invalidArguments = inputArguments.stream()
                    .filter(x -> !x.matches(validArguments))
                    .collect(Collectors.toList());

            if (!invalidArguments.isEmpty()) {
                invalidArguments.forEach(x -> System.out.println("\"" + x + "\" is not a valid parameter. " +
                        "It will be skipped"));
            }

            String inputFile = null;
            String outputFile = null;
            if (inputArguments.contains("-inputFile")) {
                inputFile = inputArguments.get(inputArguments.indexOf("-inputFile") + 1);
            }
            if (inputArguments.contains("-outputFile")) {
                outputFile = inputArguments.get(inputArguments.indexOf("-outputFile") + 1);
            }

            String sortingType = inputArguments.stream().filter(x -> x.matches("natural|byCount"))
                    .findFirst().orElseGet(() -> "natural");
            String dataType = inputArguments.stream().filter(x -> x.matches("long|line|word"))
                    .findFirst().orElseGet(() -> "long");

            sortingTool.setCustomArray(dataType);
            sortingTool.setSortingType(sortingType);

            if (inputFile != null) {
                sortingTool.addElementsFromFile(inputFile);
            } else {
                sortingTool.addElements();
            }

            if (outputFile != null) {
                sortingTool.printSortedToFile(outputFile);
            } else {
                sortingTool.printSorted();
            }
        }
    }
}
