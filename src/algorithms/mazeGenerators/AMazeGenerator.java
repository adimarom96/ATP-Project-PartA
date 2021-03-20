package algorithms.mazeGenerators;

public abstract class AMazeGenerator implements IMazeGenerator {

    @Override
    public long measureAlgorithmTimeMillis(int row, int col) {
        long start_time, end_time;
        start_time = System.currentTimeMillis();
        generate(row, col); // TODO: check if need to catch the return value
        for (int i = 0; i < 10000; i++) {
            System.out.println(i + " A");
        }
        end_time = System.currentTimeMillis();
        return end_time - start_time;
    }
    // try to commit ??? by ohad miller
}