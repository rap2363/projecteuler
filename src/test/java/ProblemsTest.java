import static org.junit.Assert.assertEquals;

import com.google.common.collect.ImmutableMap;
import java.util.Map.Entry;
import org.junit.Test;
import problems.EulerProblem;
import problems.Problem10;
import problems.Problem107;
import problems.Problem11;
import problems.Problem12;
import problems.Problem15;
import problems.Problem17;
import problems.Problem187;
import problems.Problem21;
import problems.Problem211;
import problems.Problem23;
import problems.Problem232;
import problems.Problem266;
import problems.Problem4;
import problems.Problem5;
import problems.Problem59;
import problems.Problem6;
import problems.Problem7;
import problems.Problem8;

public final class ProblemsTest {
    private final ImmutableMap<Class<? extends EulerProblem>, Long> PROBLEMS_TO_SOLUTION = ImmutableMap.<Class<? extends EulerProblem>, Long>builder()
        .put(Problem4.class, 906609L)
        .put(Problem5.class, 232792560L)
        .put(Problem6.class, 25164150L)
        .put(Problem7.class, 104743L)
        .put(Problem8.class, 23514624000L)
        .put(Problem10.class, 142913828922L)
        .put(Problem11.class, 70600674L)
        .put(Problem12.class, 76576500L)
        .put(Problem15.class, 137846528820L)
        .put(Problem17.class, 21124L)
        .put(Problem21.class, 31626L)
        .put(Problem23.class, 4179871L)
        .put(Problem59.class, 107359L)
        .put(Problem107.class, 259679L)
        // .put(Problem182.class, 399788195976L) // Remove comment to test, takes roughly 1 minute
        .put(Problem187.class, 17427258L)
        .put(Problem211.class, 1922364685L)
        .put(Problem232.class, 83648556L)
        .put(Problem266.class, 1096883702440585L)
        .build();

    @Test
    public void testAllProblems() throws Exception {
        for (final Entry<Class<? extends EulerProblem>, Long> entry : PROBLEMS_TO_SOLUTION.entrySet()) {
            assertEquals((long) entry.getValue(), entry.getKey().newInstance().get());
        }
    }
}
