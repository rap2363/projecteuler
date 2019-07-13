package problems;

/**
 * Let Sm = (x1, x2, ... , xm) be the m-tuple of positive real numbers with x1 + x2 + ... + xm = m for which
 * Pm = x1 * x2^2 * ... * xm^m is maximised.
 *
 * For example, it can be verified that [P10] = 4112 ([ ] is the integer part function).
 *
 * Find Σ[Pm] for 2 ≤ m ≤ 15.
 *
 * Solution: We can form this into an equivalent maximization problem:
 * max (sum(k * log(xk) s.t. sum(xk) = m.
 *
 * and solve it using Lagrangian multipliers. The lagrangian is:
 * L(x1,x2,...,xk,l) = sum(k * log(xk)) - l * (sum(xk) - m)
 *
 * From analysis of fixed points at grad(L) = 0, we know that grad (f) = l * grad(g) , where f is the maximization
 * function and g is the equality constraint. Therefore we get:
 *
 * k / x_k = l for all k, and from the equality constraint: sum(x_k) = sum(k / l) = (1/l) * sum(k) = m * (m+1) / 2l = m
 *
 * which yields: l = (m + 1) / 2, giving the x_k = 2k / (m + 1).
 */
public final class Problem190 implements EulerProblem {

    public static void main(String[] args) {
        System.out.println(new Problem190().get());
    }

    @Override
    public Number get() {
        long totalSum = 0L;
        for (int m = 2; m <= 15; m++) {
            totalSum += getOptimalProduct(m);
        }

        return totalSum;
    }

    private static long getOptimalProduct(final int m) {
        double product = 1d;
        for (int k = 1; k <= m; k++) {
            final double value = getOptimalValue(m, k);
            product *= Math.pow(value, k);
        }

        // Truncate digits
        return (long) product;
    }

    private static double getOptimalValue(final int m, final int k) {
        return 2.0 * k / (m + 1);
    }
}
