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
        assertEquals(76576500L, new Problem12().run());
    }

    @Test
    public void testProblem15() {
        assertEquals(2, new Problem15(1).run());
        assertEquals(6, new Problem15(2).run());
        assertEquals(137846528820L, new Problem15(20).run());
    }

    @Test
    public void testProblem17() {
        assertEquals(21124, new Problem17().run());
    }

    @Test
    public void testProblem21() {
        assertEquals(31626, new Problem21().run());
    }

    @Test
    public void testProblem59() {
        assertEquals(107359L, new Problem59().run());
    }
}
