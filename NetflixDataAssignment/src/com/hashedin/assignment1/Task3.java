package com.hashedin.assignment1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Task3 {
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
    //Task 3 : List first n Records where type is Movie and Country is India
    private  static void task3(int n){
        System.out.println("First "+n+" Records with Type is Movie and Country is India :- ");
        startTime = System.nanoTime();

        moviesData.stream()
                .filter(movieRow -> movieRow.get(colsTitle.get("type")).equals("Movie"))
                .filter(movieRow -> movieRow.get(colsTitle.get("country")).equals("India"))
                .limit(n)
                .forEach(System.out::println);
        endTime = System.nanoTime();
        System.out.println("Total Time to Execute Query 3 : "+(endTime-startTime) +" nano seconds");

    }

    public static void main(String[] args) {
        try{
            Task3.readDataFromCSV();
            Scanner input = new Scanner(System.in);
            System.out.println("Enter value of n :-");

            Integer n = input.nextInt();
            Task3.task3(n);
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
