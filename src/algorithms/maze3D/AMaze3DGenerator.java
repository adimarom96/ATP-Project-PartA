package algorithms.maze3D;

public abstract class AMaze3DGenerator implements IMaze3DGenerator {


    @Override
    public long measureAlgorithmTimeMillis(int depth, int row, int column) { long start_time, end_time;
        start_time = System.currentTimeMillis();
        generate(depth,row, column); // TODO: check if need to catch the return value
//        for (int i = 0; i < 10000; i++) {
//            System.out.println(i + " A");
//        }
        end_time = System.currentTimeMillis();
        return end_time - start_time;
    }
}
