
package myexam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author pikachu
 */
public class MyExam implements Callable<String>{

    @Override
    public String call() throws Exception {
        String threadName = Thread.currentThread().getName();
        String threadNumber = threadName.substring(14,Thread.currentThread().getName().length());
        return "I am "+threadNumber+" thread";
    }
    
    public static void main(String[] args) throws InterruptedException, ExecutionException {
       System.out.println("Enter k value: ");
       Scanner input = new Scanner(System.in);
       int k = input.nextInt();
       ExecutorService es = Executors.newFixedThreadPool(k);
       List<Future<String>> ResultList;
       ResultList = new ArrayList<Future<String>>();
       for (int i=1;i<=k;i++) {
            Future<String> future;
            future = es.submit(new MyExam());
            ResultList.add(future);
        }
        for (Future<String> element : ResultList) {
            System.out.println(element.get()+" from "+k+" threads!");
        }
        es.shutdown();
    }
    
}
