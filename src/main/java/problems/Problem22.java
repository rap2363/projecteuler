package problems;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Using names.txt (right click and 'Save Link/Target As...'), a 46K text file containing over five-thousand first names,
 * begin by sorting it into alphabetical order. Then working out the alphabetical value for each name, multiply this
 * value by its alphabetical position in the list to obtain a name score.
 *
 * For example, when the list is sorted into alphabetical order, COLIN, which is worth:
 * 3 + 15 + 12 + 9 + 14 = 53, is the 938th name in the list. So, COLIN would obtain a score of 938 × 53 = 49714.
 *
 * What is the total of all the name scores in the file?
 */
public final class Problem22 implements EulerProblem {
    private final String[] sortedNames;

    public Problem22() {
        final ClassLoader classLoader = getClass().getClassLoader();
        final File namesFile = new File(classLoader.getResource("names.txt").getFile());
        try (final Scanner scanner = new Scanner(namesFile)) {
            // Just one line
            this.sortedNames = Arrays.stream(scanner.nextLine().split(","))
                .map(s -> s.substring(1, s.length() - 1))
                .sorted()
                .toArray(String[]::new);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Problem22().run());
    }

    @Override
    public long run() {
        return IntStream.range(0, sortedNames.length)
            .mapToLong(i -> getAllCharactersScore(sortedNames[i]) * (i + 1))
            .sum();
    }

    private static long getAllCharactersScore(final String name) {
        long score = 0;
        for (char c : name.toCharArray()) {
            score += (c - 'A' + 1);
        }

        return score;
    }
}
