package solution;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Analyzer {

    private Line[] lineArray;
    private List<Integer> queryIndexList = new ArrayList<>();
    int[] queryIndexArray;

    private int linesAmount;
    private int lineMatchCounter = 0;
    private int timeSum = 0;

    public Analyzer(int linesAmount, Line[] lineArray) {
        this.linesAmount = linesAmount;
        this.lineArray = lineArray;
        setQueryList();
    }

    //creates array of query indexes
    private void setQueryList() {
        for (int i = 0; i < linesAmount; i++) {
            if (lineArray[i].getLineType() == false) queryIndexList.add(i);
        }
        queryIndexArray = new int[queryIndexList.size()];
        for (int i = 0; i < queryIndexList.size(); i++) {
            queryIndexArray[i] = queryIndexList.get(i);
        }
    }


    // checks if lines before query matches query then writes time to output.txt
    //first loop takes every query
    //second loop checks if line match query
    //saves waiting time and writes it to output.txt
    public void processRequests(String output) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(output));

        for (int q : queryIndexArray) {
            for (int c = 0; c < q; c++) {
                if (compareCtoD(lineArray[c], lineArray[q])) {
                    timeSum += lineArray[c].getWaitingTime();
                    lineMatchCounter++;
                }
            }

            if (lineMatchCounter != 0) writer.write(String.valueOf(timeSum / lineMatchCounter));
            else writer.write("-");


            writer.newLine();
            lineMatchCounter = 0;
            timeSum = 0;
        }
        writer.close();
    }


    //takes line and query and compare them
    private boolean compareCtoD(Line c, Line query) {
        if (c.getLineType() != true) return false;
        // check if  query with one date equals C line date ||
        //check if query interval of date-date_to match C line date  ( queryDate <= cLineDate <= queryDateTo) ||
        //check if cLineResponse == queryResponse
        if (((c.getDate().isEqual(query.getDate()) && query.getDateTo() == null) ||
                query.getDateTo() != null && (!query.getDate().isAfter(c.getDate()) && !query.getDateTo().isBefore(c.getDate()))) &&
                c.getResponse() == query.getResponse()) {

            //  service, variation  '0' is '*'
            if (query.getServiceID() == 0 ||
                    (c.getServiceID() == query.getServiceID() && query.getVariationID() == 0) ||
                    (c.getServiceID() == query.getServiceID() && c.getVariationID() == query.getVariationID())) {


                //  question, category, subcategory '0' is '*'
                if (query.getQuestionTypeID() == 0 ||
                        (c.getQuestionTypeID() == query.getQuestionTypeID() && query.getCategoryID() == 0) ||
                        (c.getQuestionTypeID() == query.getQuestionTypeID() && c.getCategoryID() == query.getCategoryID() && query.getSubCategoryID() == 0) ||
                        (c.getQuestionTypeID() == query.getQuestionTypeID() && c.getCategoryID() == query.getCategoryID() && c.getSubCategoryID() == query.getSubCategoryID()))
                    return true;

            }

        }

        return false;
    }


}
