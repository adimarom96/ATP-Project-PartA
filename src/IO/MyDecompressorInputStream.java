package IO;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MyDecompressorInputStream extends InputStream {
    private InputStream in;

    public MyDecompressorInputStream(InputStream in) {
        this.in = in;
    }

    @Override
    public int read(byte[] b) throws IOException {
        byte[] compress = in.readAllBytes();// read all the file - 24 of MetaData and the rest is counters.
        in.close();

        //copy the first 28 cells to b
        for (int i = 0; i < 24; i++) {
            b[i] = compress[i];
        }
        int rest = b[24];
        ArrayList<Integer> toinsert = new ArrayList<>();
        int x = 0;
        for (int i = 25; i < compress.length - rest; i++) {
            x = compress[i];
            if (x < 0)
                x += 256;
            toinsert = eightBit(x);
            for (int j = 0; j < 8; j++) {
                add(b, i + j, toinsert.get(j));
            }
        }
        for (int i = compress.length - rest; i < compress.length; i++) {
            add(b, i, compress[i]);
        }
        return 0;
    }

    public ArrayList<Integer> eightBit(int num) {
        //bug!!!!!!!!!!!!!!!!!!!!!
        ArrayList<Integer> toinsert = new ArrayList<>();
        int i = 0;
        while (num / 2 != 0) {
            toinsert.add(num % 2);
            num = num / 2;
        }
        while (toinsert.size() < 8) {
            toinsert.add(0);
        }
        return toinsert;
    }

    private void add(byte[] b, int startIndex, int value) {
        b[startIndex] = 0;
        b[startIndex + 1] = 0;
        b[startIndex + 2] = 0;
        b[startIndex + 3] = (byte) value;
    }

    @Override
    public int read() throws IOException {
        return 0;
    }
}
