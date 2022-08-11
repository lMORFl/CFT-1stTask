import java.util.Scanner;

public class IntegerSort extends SortType<Integer> {

    @Override
    public boolean hasNext(Scanner file) {
        return file.hasNextInt();
    }

    @Override
    public Integer takeNext(Scanner file) {
        return file.nextInt();
    }
}