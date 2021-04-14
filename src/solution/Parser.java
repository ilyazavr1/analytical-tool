package solution;

import java.time.LocalDate;

public class Parser {

    String[] lineTokens;
    String[] tempToken;

    public Parser() {

    }

    public Line parseLine(String lineTokens) {
        if (lineTokens == null || lineTokens.isEmpty() || lineTokens.isBlank() ) {
            System.out.println("The file contains blank lines or the number of lines (first line) exceeds the number of lines in the file.");
            System.exit(0);
        }
        this.lineTokens = lineTokens.split("\\s");
        return new Line(parseLineType(), parseService()[0], parseService()[1], parseQuestion()[0], parseQuestion()[1], parseQuestion()[2],
                parseResponse(), parseDate()[0], parseDate()[1], parseWaitingTime());


    }

    private boolean parseLineType() {

        if (lineTokens[0].equals("C")) {
            return true;
        } else return false;

    }
    //'0' is '*'
    private byte[] parseService() {
        byte[] sR = new byte[2];

        if (!lineTokens[1].equals("*")) {
            tempToken = lineTokens[1].split("\\.");
            if (tempToken.length == 1) {
                sR[0] = Byte.parseByte(tempToken[0]);
            } else {
                sR[0] = Byte.parseByte(tempToken[0]);
                sR[1] = Byte.parseByte(tempToken[1]);
            }
        }
        return sR;
    }
    //'0' is '*'
    private byte[] parseQuestion() {
        byte[] temp = new byte[3];
        if (!lineTokens[2].equals("*")) {
            tempToken = lineTokens[2].split("\\.");
            if (tempToken.length == 1) {
                temp[0] = Byte.parseByte(tempToken[0]);
                return temp;
            } else if (tempToken.length == 2) {
                temp[0] = Byte.parseByte(tempToken[0]);
                temp[1] = Byte.parseByte(tempToken[1]);
                return temp;
            } else {
                temp[0] = Byte.parseByte(tempToken[0]);
                temp[1] = Byte.parseByte(tempToken[1]);
                temp[2] = Byte.parseByte(tempToken[2]);
                return temp;
            }
        } else return temp;
    }

    private boolean parseResponse() {
        if (lineTokens[3].equals("P")) {
            return true;
        } else return false;
    }

    private LocalDate[] parseDate() {
        //set date for C/D with one date/D with date and date to
        LocalDate[] date = new LocalDate[2];
        short day;
        short month;
        short year;
        if (lineTokens[0].equals("C") == true) {

            day = Short.parseShort(lineTokens[4].substring(0, 2));
            month = Short.parseShort(lineTokens[4].substring(3, 5));
            year = Short.parseShort(lineTokens[4].substring(6));
            date[0] = LocalDate.of(year, month, day);
            return date;
        }
        if (lineTokens[0].equals("D") == true && lineTokens[4].length() < 12) {
            day = Short.parseShort(lineTokens[4].substring(0, 2));
            month = Short.parseShort(lineTokens[4].substring(3, 5));
            year = Short.parseShort(lineTokens[4].substring(6, 10));
            date[0] = LocalDate.of(year, month, day);
            return date;

        } else if (lineTokens[0].equals("D") == true) {
            day = Short.parseShort(lineTokens[4].substring(0, 2));
            month = Short.parseShort(lineTokens[4].substring(3, 5));
            year = Short.parseShort(lineTokens[4].substring(6, 10));
            date[0] = LocalDate.of(year, month, day);

            day = Short.parseShort(lineTokens[4].substring(11, 13));
            month = Short.parseShort(lineTokens[4].substring(14, 16));
            year = Short.parseShort(lineTokens[4].substring(17));
            date[1] = LocalDate.of(year, month, day);
            return date;

        }
        return date;
    }

    private int parseWaitingTime() {
        if (lineTokens[0].equals("C")) {
            return Integer.parseInt(lineTokens[5]);
        }
        return 0;
    }

}
