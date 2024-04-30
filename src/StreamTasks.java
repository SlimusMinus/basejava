import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.partitioningBy;

public class StreamTasks {
    public static void main(String[] args) {
        int[] arr = new int[]{5, 4, 3, 2, 5, 6, 5};
        System.out.println(minValue(arr));
        List<Integer> integers = List.of(5, 4, 3, 2, 5, 6, 5);
        System.out.println(oddOrEven(integers));

    }

    private static int minValue(int[] values) {
        return Arrays.stream(values).distinct().sorted().reduce(0, (acc, sum)-> acc*10+sum);
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        Map<Boolean, List<Integer>> map = integers.stream()
                .collect(partitioningBy(num->num%2==0, Collectors.toList()));
        return map.get(map.get(false).size() % 2 != 0);
    }


}
