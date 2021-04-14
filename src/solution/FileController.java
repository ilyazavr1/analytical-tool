package solution;

import java.io.*;


class FileController {
    //'linesAmount' - first line in file that represent amount of lines
    private String path;
    private int linesAmount;
    private Line[] lineArray;

    public FileController(String path) {
        this.path = path;
        try {
            readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //reads file and convert lines to Line object, then puts them to array
    public void readFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));

        //reads first line, checks if file is empty
        //initialize 'linesAmount' and 'lineArray'
        String temp = reader.readLine().trim();
        if (temp == null || temp.isEmpty() ) {
            System.out.println("File is empty.");
            System.exit(0);
        } else {
            linesAmount = Integer.parseInt(temp);
            lineArray = new Line[linesAmount];
        }


        //Parser method "paresLine" convert lines from input.txt to object of Line and puts them to Line type array
        Parser parser = new Parser();
        for (int i = 0; i < linesAmount; i++) {
           lineArray[i] = parser.parseLine(reader.readLine());
        }
        reader.close();


    }

    public void getWaittingTime(String output) {
        //Analyzer constructor takes lines amount and Line[] lineArray
        Analyzer analyzer = new Analyzer(linesAmount, lineArray);
        //sums the waiting time of lines that match the query and write them to the output.txt
        try {
            analyzer.processRequests(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
