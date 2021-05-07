package com.hashedin.assignment1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Task1 {
    private static Map<String,Integer> colsTitle = new HashMap<>();
    private static List<List<String>> moviesData = new ArrayList<>();
    private static Long startTime = 0l,endTime = 0l;

    public static void readDataFromCSV() throws Exception {
        String filePath = "./resources/netflix_titles.csv";
        BufferedReader csvReader = new BufferedReader(new FileReader(filePath));

        String row = null;
        int rowNo = 0;
        while((row = csvReader.readLine()) !=null){
            List<String> data = Arrays.asList(row.split( ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)"));
            moviesData.add(data);
            if(rowNo == 0) {
                for(int indx=0;indx<data.size();indx++){
                    colsTitle.put(data.get(indx),indx);
                }
            }
            rowNo++;
        }
        csvReader.close();
    }

    //Task 1. List first n Records where Type is TV Shows.
    private static void task1(int n){
        System.out.println("First "+n+"  Records with Type is Tv Shows :- ");

        startTime = System.nanoTime();
        moviesData.stream()
                .filter(movieRow -> movieRow.get(colsTitle.get("type")).equals("TV Show"))
                .limit(n)
                .forEach(System.out::println);
        endTime = System.nanoTime();
        System.out.println("Total Time to Execute Query 1 : "+(endTime-startTime) +" nano seconds");
    }
    public static void main(String[] args) {
        try {
            Task1.readDataFromCSV();
            Scanner input = new Scanner(System.in);
            System.out.println("Enter value of n :-");

            Integer n = input.nextInt();
            Task1.task1(n);
        } catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
