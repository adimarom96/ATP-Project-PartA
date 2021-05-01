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

        int rest = compress[24];
        int x;
        int[] arr;
        int index = 24;
        for (int i = 25; i < compress.length - rest; i++) {
            x = compress[i];
            if (x < 0)
                x += 256;
            arr = eightBit(x);
            for (int j = 0; j < 8; j++) {
                add(b, index, arr[j]);
                System.out.println("write from: to " + index + " to " + (index + 3));
                index += 4;
            }
            //index++;
        }
        // the rest of the matrix that is less than 8
       /* for (int i = compress.length - rest; i < compress.length; i++) {
            add(b, i, compress[i]);
        }*/

        for (int i = 0; i < rest; i++) {
            b[index] = compress[compress.length-rest+i];
            index++;
        }
        return 0;
    }

    public int[] eightBit(int x) {
        String str = new String(Integer.toBinaryString(x));
        while (str.length() < 8)
            str = "0" + str;
        int[] arr = new int[8];
        char[] ca = str.toCharArray();
        for (int j = 0; j < 8; j++) {
            arr[j] = Character.getNumericValue(ca[j]);
            //System.out.println(arr[j]);
        }
        return arr;
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
