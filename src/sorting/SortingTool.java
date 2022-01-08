package sorting;

public class SortingTool {
    private CustomArray customArray;
    private String sortingType;

    public SortingTool() {}

    public void addElements() {
        customArray.addElements();
    }

    public void addElementsFromFile(String filename) {
        customArray.addElementsFromFile(filename);
    }

    public void setCustomArray(String kind) {
        switch (kind) {
            case "long":
                this.customArray = new LongCustomArray();
                break;
            case "line":
                this.customArray = new LineCustomArray();
                break;
            case "word":
                this.customArray = new WordCustomArray();
                break;
        }
    }

    public void setSortingType(String kind) {
        this.sortingType = kind;
    }

    public void printMaxInfo() {
        customArray.printMaxInfo();
    }

    public void printSortNatural() {
        customArray.printSortedNatural();
    }

    public void printSortByCount() {
        customArray.printSortedByCount();
    }

    public void printSortNaturalToFile(String filename) {
        customArray.printSortedNaturalToFile(filename);
    }

    public void printSortByCountToFile(String filename) {
        customArray.printSortedByCountToFile(filename);
    }

    public void printSorted() {
        switch (sortingType) {
            case "natural":
                printSortNatural();
                break;
            case "byCount":
                printSortByCount();
                break;
        }
    }

    public void printSortedToFile(String filename) {
        switch (sortingType) {
            case "natural":
                printSortNaturalToFile(filename);
                break;
            case "byCount":
                printSortByCountToFile(filename);
                break;
        }
    }
}
