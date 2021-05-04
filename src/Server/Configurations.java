package Server;

import java.io.*;
import java.util.Properties;

public class Configurations {
    private static Configurations instance = null;
    private static Properties prop;
    private OutputStream out;
    private InputStream in;

    private Configurations() {
        try {
            in = new FileInputStream("./resources/config.properties");
            prop = new Properties();
            prop.load(in);
            out = new FileOutputStream("./resources/config.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Configurations getInstance() {
        if (instance == null) {
            instance = new Configurations();
        }
        return instance;
    }

    public static synchronized void setP(String str, String val) {
        try {
            prop.setProperty(str, val);
            prop.store(instance.out, null);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized String getP(String str) {
        return prop.getProperty(str);
    }
}