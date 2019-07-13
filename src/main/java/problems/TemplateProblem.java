package problems;

public final class TemplateProblem implements EulerProblem {

    public static void main(String[] args) {
        System.out.println(new TemplateProblem().get());
    }

    @Override
    public Number get() {
        return 0L;
    }
}
