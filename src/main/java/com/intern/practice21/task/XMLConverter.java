package com.intern.practice21.task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XMLConverter {
    private static final String FILENAMEINPUT = "test_in.xml";
    private static final String FILENAMEOUTPUT = "test_out.xml";
    private static final String PATTERN = "\\ssurname\\s?=\\s?\"(.+?)\"";
    private static final String PATTERN1 = "(\\sname\\s?=\\s?\")(.+?)\"";

    private String surname;
    private String name;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void convertXMLFile() {
        try {
            Scanner scanner = new Scanner(new File(FILENAMEINPUT));
            StringBuilder temp = new StringBuilder();
            StringBuilder res = new StringBuilder();
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                if (str.contains("<persons>") || str.contains("</persons>")) {
                    res.append(str).append(System.lineSeparator());
                    continue;
                }
                if(str.contains("/>")) {
                    temp.append(str);
                    temp = new StringBuilder(deleteSurname(temp));  //|<--need to change
                    temp = new StringBuilder(updateFile(temp));     //|<--need to change
                    res.append(temp);
                }
                if(!str.contains("/>")) {
                    temp.append(str).append(System.lineSeparator());
                    continue;
                }
                temp = new StringBuilder();
            }
            printIntoFile(FILENAMEOUTPUT, res.toString());
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void printIntoFile(String filename, String text) {
        try {
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write(text);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String deleteSurname(StringBuilder temp) {
        Pattern p = Pattern.compile(PATTERN);
        Matcher m = p.matcher(temp);
        if (m.find()) {
            setSurname(m.group(1));
        }
        temp = new StringBuilder(m.replaceAll(""));
        return temp.toString();
    }

    private String updateFile(StringBuilder temp) {
        Pattern p = Pattern.compile(PATTERN1);
        Matcher m = p.matcher(temp);
        String whitespace = "";
        if (m.find()) {
            setName(m.group(2));
            whitespace = m.group(1);
        }
        temp = new StringBuilder(m.replaceAll(whitespace + getName() + " " + getSurname() + "\""));
        temp.append(System.lineSeparator());
        return temp.toString();
    }

    public static void main(String[] args) {
        XMLConverter xmlConverter = new XMLConverter();
        xmlConverter.convertXMLFile();
    }
}
