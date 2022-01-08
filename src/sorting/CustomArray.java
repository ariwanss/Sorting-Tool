package sorting;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

abstract class CustomArray<T extends Comparable<T>> extends ArrayList<T> {

    Scanner scanner = new Scanner(System.in);

    abstract CustomArray addElements();
    abstract CustomArray addElementsFromFile(String filename);
    abstract T getMax();
    abstract void printSortedNatural();
    abstract void printSortedNaturalToFile(String filename);
    abstract void printSortedByCount();
    abstract void printSortedByCountToFile(String filename);
    abstract void printMaxInfo();

    void sortNatural() {
        Collections.sort(this);
    }

    void sortByCount() {
        Collections.sort(this, (a, b) -> Collections.frequency(this, a) == Collections.frequency(this, b) ?
                a.compareTo(b) : Integer.compare(Collections.frequency(this, a), Collections.frequency(this, b)));
    }

    Set<T> uniqueElement() {
        sortByCount();
        return new LinkedHashSet<>(this);
    }

    int getFrequencyMax() {
        return Collections.frequency(this, getMax());
    }

    int getFrequency(T t) {
        return Collections.frequency(this, t);
    }

    double getPercentageMax() {
        return (double) getFrequencyMax() * 100 / this.size();
    }

    double getPercentage(T t) {
        return (double) getFrequency(t) * 100 / this.size();
    }
}

class LongCustomArray extends CustomArray<Long> {

    @Override
    LongCustomArray addElements() {
        while (scanner.hasNext()) {
            String input = scanner.next();
            try {
                Long toAdd = Long.parseLong(input);
                this.add(toAdd);
            } catch (NumberFormatException e) {
                System.out.println("\"" + input + "\" is not a long. It will be skipped.");
            }
        }
        return this;
    }

    @Override
    LongCustomArray addElementsFromFile(String filename) {
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNext()) {
                String input = scanner.next();
                try {
                    Long toAdd = Long.parseLong(input);
                    this.add(toAdd);
                } catch (NumberFormatException e) {
                    System.out.println("\"" + input + "\" is not a long. It will be skipped.");
                }
            }
        } catch (FileNotFoundException ignored) {}
        return this;
    }

    @Override
    Long getMax() {
        return Collections.max(this);
    }

    @Override
    void printSortedNatural() {
        sortNatural();
        System.out.println("Total numbers: " + this.size() + ".");
        System.out.print("Sorted data: ");
        this.forEach(x -> System.out.print(x + " "));
        System.out.println();
    }

    @Override
    void printSortedNaturalToFile(String filename) {
        sortNatural();
        try (PrintWriter printWriter = new PrintWriter(filename)) {
            printWriter.println("Total numbers: " + this.size() + ".");
            printWriter.print("Sorted data: ");
            this.forEach(x -> printWriter.print(x + " "));
            printWriter.println();
        } catch (FileNotFoundException ignored) {}
    }

    @Override
    void printSortedByCount() {
        Set<Long> elements = uniqueElement();
        System.out.println("Total numbers: " + this.size() + ".");
        elements.forEach(x -> System.out.println(x + ": " + getFrequency(x) + " time(s), " +
                String.format("%.0f", getPercentage(x))+ "%"));
        System.out.println();
    }

    @Override
    void printSortedByCountToFile(String filename) {
        Set<Long> elements = uniqueElement();
        try (PrintWriter printWriter = new PrintWriter(filename)) {
            printWriter.println("Total numbers: " + this.size() + ".");
            elements.forEach(x -> printWriter.println(x + ": " + getFrequency(x) + " time(s), " +
                    String.format("%.0f", getPercentage(x))+ "%"));
            printWriter.println();
        } catch (FileNotFoundException ignored) {}
    }

    @Override
    void printMaxInfo() {
        System.out.println("Total numbers: " + this.size() + ".");
        System.out.println("The greatest number: " + getMax() +
                " (" + getFrequencyMax() + " time(s), " + String.format("%.0f", getPercentageMax()) + "%).\n");
    }

}

class LineCustomArray extends CustomArray<String> {

    @Override
    LineCustomArray addElements() {
        while (scanner.hasNextLine()) {
            this.add(scanner.nextLine());
        }
        return this;
    }

    @Override
    LineCustomArray addElementsFromFile(String filename) {
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                this.add(scanner.nextLine());
            }
        } catch (FileNotFoundException ignored) {}
        return this;
    }

    @Override
    String getMax() {
        return Collections.max(this, (a, b) -> a.length() == b.length() ?
                a.compareTo(b) : Integer.compare(a.length(), b.length()));
    }

    @Override
    void printSortedNatural() {
        sortNatural();
        System.out.println("Total lines: " + this.size() + ".");
        System.out.println("Sorted data: ");
        this.forEach(System.out::println);
        System.out.println();
    }

    @Override
    void printSortedNaturalToFile(String filename) {
        sortNatural();
        try (PrintWriter printWriter = new PrintWriter(filename)) {
            printWriter.println("Total lines: " + this.size() + ".");
            printWriter.println("Sorted data: ");
            this.forEach(printWriter::println);
            printWriter.println();
        } catch (FileNotFoundException ignored) {}
    }

    @Override
    void printSortedByCount() {
        Set<String> elements = uniqueElement();
        System.out.println("Total lines: " + this.size() + ".");
        elements.forEach(x -> System.out.println(x + ": " + getFrequency(x) + " time(s), " +
                String.format("%.0f", getPercentage(x))+ "%"));
        System.out.println();
    }

    @Override
    void printSortedByCountToFile(String filename) {
        Set<String> elements = uniqueElement();
        try (PrintWriter printWriter = new PrintWriter(filename)) {
            printWriter.println("Total lines: " + this.size() + ".");
            elements.forEach(x -> printWriter.println(x + ": " + getFrequency(x) + " time(s), " +
                    String.format("%.0f", getPercentage(x))+ "%"));
            printWriter.println();
        } catch (FileNotFoundException ignored) {}
    }

    @Override
    void printMaxInfo() {
        System.out.println("Total lines: " + this.size() + ".");
        System.out.println("The longest line: " + getMax() +
                "\n(" + getFrequencyMax() + " time(s), " + String.format("%.0f", getPercentageMax()) + "%).\n");
    }
}

class WordCustomArray extends CustomArray<String> {

    @Override
    WordCustomArray addElements() {
        while (scanner.hasNext()) {
            this.add(scanner.next());
        }
        return this;
    }

    @Override
    WordCustomArray addElementsFromFile(String filename) {
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNext()) {
                this.add(scanner.next());
            }
        } catch (FileNotFoundException ignored) {}
        return this;
    }

    @Override
    String getMax() {
        return Collections.max(this, (a, b) -> a.length() == b.length() ?
                a.compareTo(b) : Integer.compare(a.length(), b.length()));
    }

    @Override
    void printSortedNatural() {
        sortNatural();
        System.out.println("Total words: " + this.size() + ".");
        System.out.print("Sorted data: ");
        this.forEach(x -> System.out.print(x + " "));
        System.out.println();
    }

    @Override
    void printSortedNaturalToFile(String filename) {
        sortNatural();
        try (PrintWriter printWriter = new PrintWriter(filename)) {
            printWriter.println("Total words: " + this.size() + ".");
            printWriter.print("Sorted data: ");
            this.forEach(x -> printWriter.print(x + " "));
            printWriter.println();
        } catch (FileNotFoundException ignored) {}
    }

    @Override
    void printSortedByCount() {
        Set<String> elements = uniqueElement();
        System.out.println("Total words: " + this.size() + ".");
        elements.forEach(x -> System.out.println(x + ": " + getFrequency(x) + " time(s), " +
                String.format("%.0f", getPercentage(x)) + "%"));
        System.out.println();
    }

    @Override
    void printSortedByCountToFile(String filename) {
        Set<String> elements = uniqueElement();
        try (PrintWriter printWriter = new PrintWriter(filename)) {
            printWriter.println("Total words: " + this.size() + ".");
            elements.forEach(x -> printWriter.println(x + ": " + getFrequency(x) + " time(s), " +
                    String.format("%.0f", getPercentage(x)) + "%"));
            printWriter.println();
        } catch (FileNotFoundException ignored) {}
    }

    @Override
    void printMaxInfo() {
        System.out.println("Total words: " + this.size() + ".");
        System.out.println("The longest word: " + getMax() +
                " (" + getFrequencyMax() + " time(s), " + String.format("%.0f", getPercentageMax()) + "%).\n");
    }
}