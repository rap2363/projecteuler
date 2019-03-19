package problems;

/**
 * Every divisor d of a number n has a complementary divisor n/d.
 *
 * Let f(n) be the sum of the greatest common divisor of d and n/d over all positive divisors d of n, that is
 * f(n)=∑ gcd(d,nd), where d runs over the proper divisors of n.
 *
 * Let F be the summatory function of f, that is F(k)=∑f(n).
 *
 * You are given that F(10)=32 and F(1000)=12776.
 *
 * Find F(1015).
 */
public final class Problem530 implements EulerProblem {
    public static void main(String[] args) {
        System.out.println(new TemplateProblem().get());
    }

    @Override
    public long get() {
        return 0L;
    }
}
