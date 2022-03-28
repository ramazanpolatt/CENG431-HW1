package com.company.Application;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class FileOperations {

    private String productsFilePath;
    private String basketsFilePath;


    public FileOperations(String productsFilePath, String basketsFilePath) {
        this.productsFilePath = productsFilePath;
        this.basketsFilePath = basketsFilePath;
    }

    private Collection<String> getLinesInFile(String path) throws IOException {
        File productsFile = new File(path);
        FileInputStream fis = new FileInputStream(productsFile);
        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(isr);
        Collection<String> productLines = new ArrayList<String>();
        String line;
        while((line=reader.readLine())!=null)
        {
            productLines.add(line);

        }

        fis.close();
        isr.close();
        reader.close();
        return productLines;


    }

    public Collection<String> getProductLines() throws IOException {

        return getLinesInFile(productsFilePath);

    }

    public Collection<String>  getBasketLines() throws IOException {
        
        return getLinesInFile(basketsFilePath);
    }
}
