package IO;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

public class MyCompressorOutputStream extends OutputStream {

    OutputStream out;

    public MyCompressorOutputStream(OutputStream out) {
        this.out = out;
    }

    @Override
    public void write(byte[] barray) throws IOException {
        for (int i = 0; i < 24; i++) {
            out.write(barray[i]);
        }
        int row = ((barray[0] & 0xFF) << 24) | ((barray[1] & 0xFF) << 16) | ((barray[2] & 0xFF) << 8) | ((barray[3] & 0xFF));
        int col = ((barray[4] & 0xFF) << 24) | ((barray[5] & 0xFF) << 16) | ((barray[6] & 0xFF) << 8) | ((barray[7] & 0xFF));
        int amount = col * row;
        int rest = amount % 8;

        out.write((byte) rest);

        int[] binaryArr = new int[8];
        byte[] matrix = Arrays.copyOfRange(barray, 27, barray.length);
        int index = 0;

        int num = (int) (Math.floor((matrix.length / 32) * 8));

        for (int i = 0; i < num; i++) {

            binaryArr[index] = matrix[i * 4];
            if (i * 4 % 28 == 0 && i != 0) {
                toBinary(binaryArr);
                index = 0;
                continue;
            }
            index++;
        }
        for (int i = barray.length-rest; i < barray.length; i++) {
            out.write(barray[i]);
            System.out.println(barray[i]);
        }
    }

    private void toBinary(int[] arr) throws IOException {
        int x = 0;
        for (int i = 0; i < 8; i++) {
            x += Math.pow(arr[i] * 2, 7 - i);
        }
        if (arr[7] == 0)
            x -= 1;
        out.write(x);
    }

    @Override
    public void write(int b) throws IOException {
        out.write(b);
    }
}
