import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class Transfer implements Runnable {

    public String outputDir = "src/test/resources/output";
    public static AtomicInteger index = new AtomicInteger(0);
    public int length;
    public CountDownLatch latch;

    public Transfer(int k, CountDownLatch latch) {
        length = 9000/k;
        this.latch = latch;
//        System.out.println("length=" + length);
    }

    public void run() {
        FileWriter file = null;
        Random random = new Random(10000);
        for (int i = 0; i < length; i++) {
            JSONObject obj = new JSONObject();
            obj.put("Name", "Crunchify.com");
            obj.put("Author", "App Shah");
            try {
                int ii = random.nextInt();
//                System.out.println("ii = " + ii);
                file = new FileWriter(outputDir + File.separator + ii + ".json");
                for (int j = 0; j < 1000; j++) {
                    file.append(obj.toJSONString() + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    assert file != null;
                    file.flush();
                    file.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        latch.countDown();
    }
}
