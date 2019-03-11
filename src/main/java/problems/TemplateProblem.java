package problems;

public final class TemplateProblem implements EulerProblem {

    public static void main(String[] args) {
        System.out.println(new TemplateProblem().get());
    }

    @Override
    public long get() {
        return 0L;
    }
}
