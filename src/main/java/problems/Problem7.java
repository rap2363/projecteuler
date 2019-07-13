package problems;

import utils.PrimeSieve;

/**
 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.
 *
 * What is the 10 001st prime number?
 */
public final class Problem7 implements EulerProblem {
    private static final int NTH_PRIME = 10001;

    public static void main(String[] args) {
        System.out.println(new Problem7().get());
    }

    @Override
    public Long get() {
        return (long) new PrimeSieve(200000).getNthPrime(NTH_PRIME);
    }
}
