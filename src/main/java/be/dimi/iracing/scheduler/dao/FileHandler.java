package be.dimi.iracing.scheduler.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by Dimitri on 15/11/2014.
 */
public class FileHandler {

    public static StringBuilder readFile(File selectedFile) {
        StringBuilder sb = new StringBuilder(1024);
        String curLine = "";
        try {
            FileReader fr = new FileReader(selectedFile);
            BufferedReader br = new BufferedReader(fr);

            while (curLine != null) {
                curLine = br.readLine();
                sb.append(curLine).append("\n");
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return sb;
    }
}
