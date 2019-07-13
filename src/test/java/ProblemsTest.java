import static org.junit.Assert.assertEquals;

import com.google.common.collect.ImmutableMap;
import java.util.Map.Entry;
import java.util.function.Supplier;
import org.junit.Test;
import problems.EulerProblem;
import problems.Problem10;
import problems.Problem107;
import problems.Problem11;
import problems.Problem12;
import problems.Problem15;
import problems.Problem17;
import problems.Problem187;
import problems.Problem190;
import problems.Problem21;
import problems.Problem211;
import problems.Problem23;
import problems.Problem232;
import problems.Problem266;
import problems.Problem31;
import problems.Problem4;
import problems.Problem5;
import problems.Problem59;
import problems.Problem6;
import problems.Problem7;
import problems.Problem8;

public final class ProblemsTest {
    private final ImmutableMap<Supplier<EulerProblem>, Number> PROBLEMS_TO_SOLUTION = ImmutableMap.<Supplier<EulerProblem>, Number>builder()
        .put(Problem4::new, 906609L)
        .put(Problem5::new, 232792560L)
        .put(Problem6::new, 25164150L)
        .put(Problem7::new, 104743L)
        .put(Problem8::new, 23514624000L)
        .put(Problem10::new, 142913828922L)
        .put(Problem11::new, 70600674L)
        .put(Problem12::new, 76576500L)
        .put(Problem15::new, 137846528820L)
        .put(Problem17::new, 21124L)
        .put(Problem21::new, 31626L)
        .put(Problem23::new, 4179871L)
        .put(Problem31::new, 73682)
        .put(Problem59::new, 107359L)
        .put(Problem107::new, 259679L)
        // .put(Problem182::new, 399788195976L) // Remove comment to test, takes roughly 1 minute
        .put(Problem187::new, 17427258L)
        .put(Problem190::new, 371048281L)
        .put(Problem211::new, 1922364685L)
        .put(Problem232::new, 0.83648556)
        .put(Problem266::new, 1096883702440585L)
        .build();

    @Test
    public void testAllProblems() {
        for (final Entry<Supplier<EulerProblem>, Number> entry : PROBLEMS_TO_SOLUTION.entrySet()) {
            assertEquals(entry.getValue(), entry.getKey().get().get());
        }
    }
}
