package solution;

import java.time.LocalDate;

public class Line {
    //C- waiting timeline; D - query
    //C - true, D - false
    private boolean lineType;
    //'0' will be '*'
    private byte serviceID = 0;
    private byte variationID = 0;
    private byte questionTypeID = 0;
    private byte categoryID = 0;
    private byte subCategoryID = 0;
    //‘P’ (first answer) or ‘N’ (next answer)
    //P - true, N - false
    private boolean response;
    //'date' - used for both types of lines C and D (for D date_from -> date)
    //'dateTo' - used only for query
    private LocalDate date;
    private LocalDate dateTo;
    //time - in minutes (short) represent waiting time.
    private int waitingTime;


    public Line(boolean lineType, byte serviceID, byte variationID, byte questionTypeID, byte categoryID, byte subCategoryID,
                boolean response, LocalDate date, LocalDate dateTo, int waitingTime) {
        this.lineType = lineType;
        this.serviceID = serviceID;
        this.variationID = variationID;
        this.questionTypeID = questionTypeID;
        this.categoryID = categoryID;
        this.subCategoryID = subCategoryID;
        this.response = response;
        this.date = date;
        this.dateTo = dateTo;
        this.waitingTime = waitingTime;
    }

    public boolean getLineType() {
        return lineType;
    }

    public byte getServiceID() {
        return serviceID;
    }

    public byte getVariationID() {
        return variationID;
    }

    public byte getQuestionTypeID() {
        return questionTypeID;
    }

    public byte getCategoryID() {
        return categoryID;
    }

    public byte getSubCategoryID() {
        return subCategoryID;
    }

    public boolean getResponse() {
        return response;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public int getWaitingTime() {
        return waitingTime;
    }


    public void print() {
        if (lineType == true) {
            System.out.println(lineType + " | " + serviceID + " " + variationID + "| " + questionTypeID + " " + categoryID +
                    " " + subCategoryID + "|res " + response + "|date " + date + "|waiting " + waitingTime);
        } else if (lineType == false && dateTo != null) {
            System.out.println(lineType + "| " + serviceID + " " + variationID + "| " + questionTypeID + " " + categoryID +
                    " " + subCategoryID + "|res " + response + "|date-dateTO " + date + "/" + dateTo);
        } else if (lineType == false && dateTo == null){
            System.out.println(lineType + "| " + serviceID + " " + variationID + "| " + questionTypeID + " " + categoryID +
                    " " + subCategoryID + "|res " + response + "|date " + date);
        }
    }

}
