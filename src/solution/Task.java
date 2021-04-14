package solution;


public class Task {


    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();


        //IO path
        String inputPath = "src/solution/filesIO/input.txt";
        String outputPath = "src/solution/filesIO/output.txt";


        //constructor takes input.txt and reads it
        FileController fileController = new FileController(inputPath);

        //takes output.txt and writes waiting time
        fileController.getWaittingTime(outputPath);

        System.out.println("Result in output.txt");

    }

}


