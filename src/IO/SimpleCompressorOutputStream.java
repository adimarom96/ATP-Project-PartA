package IO;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class SimpleCompressorOutputStream extends OutputStream {
    private OutputStream out;

    public SimpleCompressorOutputStream(OutputStream out) {
        this.out = out;
    }

    public void Compress(byte[] barray) {
        int zero_count = 0;
        int one_count = 0;
        int index = 0;
        int[] compArr;
        for (int i = 24; i < barray.length - 1; i++) { // fixed the end
            if (barray[i] == barray[i + 1] && barray[i] == 0) {
                zero_count++; // if 255
            } else if (barray[i] == barray[i + 1] && barray[i] == 1) {
                one_count++;
            } else {

                zero_count = 0;
                one_count = 0;
                if (barray[i] == 0)
                    zero_count++;
                else if (barray[i] == 1)
                    one_count++;
            }
        }
    }

    @Override
    public void write(byte[] barray) throws IOException {
        // copy all the MetaData to the file.
        for (int i = 0; i < 24; i++) {
            out.write(barray[i]);
        }

        int zero_count = 0;
        int one_count = 0;
        int index = 0;
        //int[] compArr=new int[];
        ArrayList<Integer> compArr = new ArrayList<>();

        barray[23] = -1;
        if (barray[27] == 0)
            zero_count++;
        else
            compArr.add(0);
        for (int i = 27; i < barray.length + 1; i += 4) {
            if (barray[i] == 0) {
                if (barray[i - 4] == 0) {
                    zero_count++;
                } else if (barray[i - 4] == 1) {
                    compArr.add(one_count);
                    one_count = 0;
                    zero_count = 1;
                }
            } else if (barray[i] == 1) {
                if (barray[i - 4] == 1) {
                    one_count++;
                } else if (barray[i - 4] == 0) {
                    compArr.add(zero_count);
                    one_count = 1;
                    zero_count = 0;
                }
            }
        }
        if (zero_count != 0)
            compArr.add(zero_count);

        if (one_count != 0)
            compArr.add(one_count);

        for (int i = 0; i < compArr.size(); i++) {
            out.write(compArr.get(i));
        }
    }

    @Override
    public void write(int b) throws IOException {
        out.write(b);
    }
}
