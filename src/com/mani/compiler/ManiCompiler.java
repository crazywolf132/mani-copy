package com.mani.compiler;

import com.mani.lang.main.Mani;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ManiCompiler {

    public static void main(String[] args) {
        // This is where we go through the internal resources folder.
        // Inside that there will be a settings file (Generated by Mani itself)
        // It will determine what file is to be executed first (Mani file)

        Mani.compiledMode = true;
        Mani.hasInternet = true; // To make it completely un-needed to have mani installed.

        ClassLoader classLoader = ManiCompiler.class.getClassLoader();
        InputStream stream = classLoader.getResourceAsStream("main.mni");
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String inputString = "";
        try {
            // Loading the arguments first.
            inputString += "let args = [";
            for (int i = 0; i < args.length; i++) {
                if (i != 0) {
                    inputString += ",";
                }
                inputString += "\"" + args[i] + "\"";
            }
            inputString += "];\n";

            String line = reader.readLine();
            while (line != null) {
                inputString += line + "\n";
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Mani.run(inputString);
    }
}