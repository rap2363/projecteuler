import org.junit.Test;
import problems.*;

import static org.junit.Assert.assertEquals;

public final class ProblemsTest {

    @Test
    public void testProblem4() {
        assertEquals(906609L, new Problem4().run());
    }

    @Test
    public void testProblem5() {
        assertEquals(232792560L, new Problem5().run());
    }

    @Test
    public void testProblem6() {
        assertEquals(25164150L, new Problem6().run());
    }

    @Test
    public void testProblem7() {
        assertEquals(104743L, new Problem7().run());
    }

    @Test
    public void testProblem8() {
        assertEquals(23514624000L, new Problem8().run());
    }

    @Test
    public void testProblem10() {
        assertEquals(142913828922L, new Problem10().run());
    }

    @Test
    public void testProblem11() {
        assertEquals(70600674L, new Problem11().run());
    }

    @Test
    public void testProblem12() {
        assertEquals(-1, new Problem12().run());
    }
}