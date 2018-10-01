package com.st;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ExecuteShellCommand {

    public static void main(String[] args) {

        ExecuteShellCommand executor = new ExecuteShellCommand();

        //in mac oxs
        String command = "ping -c 3 google.com";

        //in windows
        //String command = "ping -n 3 " + domainName;

        String output = executor.executeCommand(command);

        System.out.println(output);

    }

    private String executeCommand(String command) {

        StringBuffer output = new StringBuffer();

        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine())!= null) {
                output.append(line + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return output.toString();

    }
}
