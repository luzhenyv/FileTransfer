//import jdk.internal.org.jline.utils.ShutdownHooks;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestTransfer {

//    @Test
//    public void test() {
//        for (int i = 0; i < 16; i++) {
//            Runnable runnable = new Transfer(16);
//            runnable.run();
//        }
//    }

    @Test
    public void test2() throws InterruptedException {
        int tasks = 100;
        ExecutorService es = Executors.newFixedThreadPool(tasks);
        CountDownLatch latch = new CountDownLatch(tasks);
        for (int i = 0; i < tasks; i++) {
            es.execute(new Transfer(tasks, latch));
        }

        latch.await();
        es.shutdown();
    }
}





