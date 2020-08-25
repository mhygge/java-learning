import Procesor.BufferedReaderProcessor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class test {
    public void process(Runnable r){
        r.run();
    }
    public static String processFile(BufferedReaderProcessor p) throws
            IOException {
        try (BufferedReader br=
                new BufferedReader(new FileReader("D:\\Project\\j8test\\LambdaExpression\\src\\data"))){
                return p.process(br);
        }
    }


    public static void main(String[] args) throws IOException {
//       new test(). process(()-> System.out.println("This is awesome!!"));
       /*String onLine=processFile((BufferedReader br)->br.readLine()+"\n"+br.readLine());
        System.out.println(onLine);*/
       Random random=new Random();

        System.out.println((random.nextInt(900)+100)+" "+ (random.nextInt(900)+100));
    }


}
