package IO;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MyDecompressorInputStream extends InputStream {
    FileInputStream fileInputStream;
    public MyDecompressorInputStream(FileInputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
        // TODO: implement!
    }

    @Override
    public int read() throws IOException {
        // TODO: implement!
        return 0;
    }
}
