package utils;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A class which can be used to generate all primes up to a certain number N. It does so by allocating a boolean
 * array of N numbers, and sieving through for root(N) multiples, marking all sieved values as not prime. The values
 * we are left with that are true in our array are prime.
 */
public final class PrimeSieve {
    private final int N;
    private final int numPrimes;
    private final boolean[] primesInArray;
    private final List<Integer> primesAsList;

    public PrimeSieve(final int N) {
        this.N = N;
        this.primesInArray = sievePrimes(N);
        this.primesAsList = getPrimesAsList(this.primesInArray);
        this.numPrimes = this.primesAsList.size();
    }

    public boolean isPrime(final int n) {
        Preconditions.checkArgument(n > 0 && n < this.N);
        return this.primesInArray[n - 1];
    }

    public int getNthPrime(final int n) {
        Preconditions.checkArgument(
                n <= this.primesAsList.size(),
                "Prime Sieve found only %s values!", this.numPrimes
        );
        return this.primesAsList.get(n - 1);
    }

    private boolean[] sievePrimes(final int N) {
        final boolean[] primes = new boolean[N];
        Arrays.fill(primes, true);
        primes[0] = false;
        for (int n = 2; n <= Math.sqrt(N); n++) {
            for (int i = 2; i <= (N / n); i++) {
                primes[(n * i) - 1] = false;
            }
        }

        return primes;
    }

    public int getNumPrimes() {
        return this.numPrimes;
    }

    public List<Integer> getAllPrimes() {
        return this.primesAsList;
    }

    private List<Integer> getPrimesAsList(final boolean[] primesInArray) {
        final List<Integer> primesAsList = new ArrayList<>();
        for (int i = 0; i < primesInArray.length; i++) {
            if (primesInArray[i]) {
                primesAsList.add(i + 1);
            }
        }
        return primesAsList;
    }
}
