import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by ������ on 22.10.2015.
 */
public class FirstTask {

    /**
     * Z = A OR ( B \ C ) AND D
     */
    private static TreeSet<Integer>        Z = new TreeSet<>();

    private static LinkedHashSet<Integer>  A = new LinkedHashSet<>();
    private static LinkedHashSet<Integer>  B = new LinkedHashSet<>();
    private static LinkedHashSet<Integer>  C = new LinkedHashSet<>();
    private static LinkedHashSet<Integer>  D = new LinkedHashSet<>();


    private static void fillSet(Set<Integer> set, int start, int end) {
        for (int i = start < end ? start : end; i <= end; i++) {
            set.add(i);
        }
    }

    private static Set<Integer> difference(Set<Integer> set_a, Set<Integer> set_b) {
        Set<Integer> result_set = new LinkedHashSet<>();
        set_a.forEach((num) -> {
            if (!set_b.contains(num)) {
                result_set.add(num);
            }
        });

        return result_set;
    }

    private static TreeSet<Integer> intersection(Set<Integer> set_a, Set<Integer> set_b) {
        TreeSet<Integer> result_set = new TreeSet<>();

        set_a.forEach((num) -> {
            if (set_b.contains(num) && set_a.contains(num)) {
                result_set.add(num);
            }
        });

        set_b.forEach((num) -> {
            if (set_b.contains(num) && set_a.contains(num)) {
                result_set.add(num);
            }
        });

        return result_set;
    }

    private static TreeSet<Integer> union(Set<Integer> set_a, Set<Integer> set_b) {
        TreeSet<Integer> result_set = new TreeSet<>();

        set_a.forEach(result_set::add);
        set_b.forEach(result_set::add);

        return result_set;
    }

    private static void print(Set set) {
        System.out.println(" === Set output === ");
        set.forEach((elem) -> {
            System.out.print(elem + " ");
        });
        System.out.println();
    }

    public static void main(String[] args) {
        fillSet(Z, -30, 50);

        fillSet(A,   0, 40);
        fillSet(B,  25, 50);
        fillSet(C, -10, 10);
        fillSet(D, -30, 50);

        Set<Integer> result = difference(B, C);
        print(result);

        System.out.println();

        result = union(A, result);
        print(result);

        System.out.println();

        result = intersection(result, D);
        print(result);
    }
}
