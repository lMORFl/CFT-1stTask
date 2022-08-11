import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class SortType<T extends Comparable<? super T>> {
    public ArrayList<Scanner> inputFiles = new ArrayList<>();
    public ArrayList<T> tmpList = new ArrayList<>();
    public FileWriter outputFile;
    public T lastWritten = null;
    public String sortKey = "-a";

    public abstract boolean hasNext(Scanner file);
    public abstract T takeNext(Scanner file);

    public boolean compareByKey(T t1, T t2){
        if (sortKey.equals("-d")) {
            return t1.compareTo(t2) >= 0;
        }
        return t1.compareTo(t2) <= 0;
    }

    public int getIndexByKey(ArrayList<T> list) {
        int index = 0;

        if (list.size() > 1) {
            for (int i = 1; i < list.size(); ++i) {
                if (compareByKey(list.get(i), list.get(index))) {
                    index = i;
                }
            }
        }
        return index;
    }

    public T takeElement(int index) {
        if (hasNext(inputFiles.get(index))) {
            T element = takeNext(inputFiles.get(index));

            if (lastWritten != null) {
                while (element != null && !compareByKey(lastWritten, element)) {
                    element = takeElement(index);
                }
            }
            return element;
        }
        return null;
    }

    public void mergeSort(String key, ArrayList<String> inputFileNames, String outputFileName) throws IOException {
        sortKey = key;

        for (String input : inputFileNames) {
            inputFiles.add(new Scanner(new BufferedReader(new FileReader(input))));
        }

        outputFile = new FileWriter(outputFileName);

        for (int i = 0; i < inputFiles.size(); ++i) {
            T tmp = takeElement(i);
            if (tmp != null) {
                tmpList.add(tmp);
            } else {
                if (inputFiles.size() > 0) {
                    inputFiles.remove(i);
                    i--;
                }
            }
        }

        while (!inputFiles.isEmpty() && !tmpList.isEmpty()) {
            int index = getIndexByKey(tmpList);

            lastWritten = tmpList.get(index);
            outputFile.write(lastWritten + "\n");
            T tmp = takeElement(index);
            if (tmp != null) {
                tmpList.set(index, tmp);
            } else {
                inputFiles.remove(index);
                tmpList.remove(index);
            }
        }

        for (Scanner elem : inputFiles) {
            elem.close();
        }
        outputFile.close();
    }
}