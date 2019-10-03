import java.util.concurrent.ExecutionException;

public class Main {

    public static String URL = "https://stackoverflow.com/questions/31462/how-to-fetch-html-in-java";

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Processor processor = new Processor(100, URL);
        processor.start();
    }

}
