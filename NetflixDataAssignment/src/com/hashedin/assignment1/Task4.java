package com.hashedin.assignment1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class Task4 {
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
    //Task 4: List first n Records according to duration for all above tasks
    private static void task4(String userSelectedStartDate, String userSelectedEndDate,int n) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        LocalDate startDate = dateFormat.parse(userSelectedStartDate).toInstant().
                atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endDate = dateFormat.parse(userSelectedEndDate).toInstant()
                .atZone(ZoneId.systemDefault()).toLocalDate();


        // List shows by duration which were added between Month,Date
        System.out.println("Top "+n+" Shows based on duration btw "+startDate.getMonthValue()+" AND "+endDate.getMonthValue()+" Month"
                +" AND btw"+ startDate.getDayOfMonth()+" AND "+endDate.getDayOfMonth()+" Date");
        System.out.println();
        //(a)  List first n Records where Type is TV Shows.
        System.out.println("First "+n+"  Records with Type is Tv Shows :- ");
        startTime = System.nanoTime();
        moviesData.stream()
                .filter(movieRow -> movieRow.get(colsTitle.get("type")).equals("TV Show"))
                .filter(movieRow ->
                {
                    try {
                        if(movieRow.get(colsTitle.get("date_added")).trim().equals("")){
                            return false;
                        }
                        LocalDate dateAdded = dateFormat.parse(movieRow.get(colsTitle.get("date_added")).replaceAll("\"","").trim())
                                .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        return dateAdded.getMonthValue() >= startDate.getMonthValue() && dateAdded.getMonthValue() <= endDate.getMonthValue()
                                &&dateAdded.getDayOfMonth() >= startDate.getDayOfMonth() && dateAdded.getDayOfMonth() <= endDate.getDayOfMonth();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return false;
                })
                .limit(n)
                .forEach(System.out::println);
        endTime = System.nanoTime();
        System.out.println("Total Time to Execute Query 1 : "+(endTime-startTime) +" nano seconds");
        System.out.println();
        System.out.println();

        // (b)   List first n Records where listed in Horror Movies
        System.out.println("First "+n+" Records with Listed In  Horror Movies :- ");
        startTime = System.nanoTime();

        moviesData.stream()
                .filter(movieRow -> movieRow.get(colsTitle.get("listed_in")).contains("Horror Movies"))
                .filter(movieRow ->
                {
                    try {
                        if(movieRow.get(colsTitle.get("date_added")).trim().equals("")){
                            return false;
                        }
                        LocalDate dateAdded = dateFormat.parse(movieRow.get(colsTitle.get("date_added")).replaceAll("\"","").trim())
                                .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        return dateAdded.getMonthValue() >= startDate.getMonthValue() && dateAdded.getMonthValue() <= endDate.getMonthValue()
                                &&dateAdded.getDayOfMonth() >= startDate.getDayOfMonth() && dateAdded.getDayOfMonth() <= endDate.getDayOfMonth();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return false;
                })
                .limit(n)
                .forEach(System.out::println);
        endTime = System.nanoTime();
        System.out.println("Total Time to Execute Query 2 : "+(endTime-startTime) +" nano seconds");
        System.out.println();
        System.out.println();
        //(c) List first n Records where type is Movie and Country is India
        System.out.println("First "+n+" Records with Type is Movie and Country is India :- ");
        startTime = System.nanoTime();

        moviesData.stream()
                .filter(movieRow -> movieRow.get(colsTitle.get("type")).equals("Movie"))
                .filter(movieRow -> movieRow.get(colsTitle.get("country")).equals("India"))
                .filter(movieRow ->
                {
                    try {
                        if(movieRow.get(colsTitle.get("date_added")).trim().equals("")){
                            return false;
                        }
                        LocalDate dateAdded = dateFormat.parse(movieRow.get(colsTitle.get("date_added")).replaceAll("\"","").trim())
                                .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        return dateAdded.getMonthValue() >= startDate.getMonthValue() && dateAdded.getMonthValue() <= endDate.getMonthValue()
                                &&dateAdded.getDayOfMonth() >= startDate.getDayOfMonth() && dateAdded.getDayOfMonth() <= endDate.getDayOfMonth();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return false;
                })
                .limit(n)
                .forEach(System.out::println);
        endTime = System.nanoTime();
        System.out.println("Total Time to Execute Query 3 : "+(endTime-startTime) +" nano seconds");
        System.out.println();
        System.out.println();
        System.out.println();



        // List shows by duration which were added between Year, Month,Date
        System.out.println("Top "+n+" Shows based on duration btw "+startDate+" AND "+endDate+" Year, Month And Date ");
        System.out.println();
        //(a)  List first n Records where Type is TV Shows.
        System.out.println("First "+n+"  Records with Type is Tv Shows :- ");
        startTime = System.nanoTime();
        moviesData.stream()
                .filter(movieRow -> movieRow.get(colsTitle.get("type")).equals("TV Show"))
                .filter(movieRow ->
                {
                    try {
                        if(movieRow.get(colsTitle.get("date_added")).trim().equals("")){
                            return false;
                        }
                        LocalDate dateAdded = dateFormat.parse(movieRow.get(colsTitle.get("date_added")).replaceAll("\"","").trim())
                                .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        return  dateAdded.isAfter(startDate) && dateAdded.isBefore(endDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return false;
                })
                .limit(n)
                .forEach(System.out::println);
        endTime = System.nanoTime();
        System.out.println("Total Time to Execute Query 1 : "+(endTime-startTime) +" nano seconds");
        System.out.println();
        System.out.println();

        // (b)   List first n Records where listed in Horror Movies
        System.out.println("First "+n+" Records with Listed In  Horror Movies :- ");
        startTime = System.nanoTime();

        moviesData.stream()
                .filter(movieRow -> movieRow.get(colsTitle.get("listed_in")).contains("Horror Movies"))
                .filter(movieRow ->
                {
                    try {
                        if(movieRow.get(colsTitle.get("date_added")).trim().equals("")){
                            return false;
                        }
                        LocalDate dateAdded = dateFormat.parse(movieRow.get(colsTitle.get("date_added")).replaceAll("\"","").trim())
                                .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        return  dateAdded.isAfter(startDate) && dateAdded.isBefore(endDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return false;
                })
                .limit(n)
                .forEach(System.out::println);
        endTime = System.nanoTime();
        System.out.println("Total Time to Execute Query 2 : "+(endTime-startTime) +" nano seconds");
        System.out.println();
        System.out.println();
        //(c) List first n Records where type is Movie and Country is India
        System.out.println("First "+n+" Records with Type is Movie and Country is India :- ");
        startTime = System.nanoTime();

        moviesData.stream()
                .filter(movieRow -> movieRow.get(colsTitle.get("type")).equals("Movie"))
                .filter(movieRow -> movieRow.get(colsTitle.get("country")).equals("India"))
                .filter(movieRow ->
                {
                    try {
                        if(movieRow.get(colsTitle.get("date_added")).trim().equals("")){
                            return false;
                        }
                        LocalDate dateAdded = dateFormat.parse(movieRow.get(colsTitle.get("date_added")).replaceAll("\"","").trim())
                                .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        return  dateAdded.isAfter(startDate) && dateAdded.isBefore(endDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return false;
                })
                .limit(n)
                .forEach(System.out::println);
        endTime = System.nanoTime();
        System.out.println("Total Time to Execute Query 3 : "+(endTime-startTime) +" nano seconds");
        System.out.println();
        System.out.println();
        System.out.println();


        // List shows by duration which were added between Year
        System.out.println("Top "+n+" Shows based on duration btw "+startDate.getYear()+" AND "+endDate.getYear()+" Years ");
        System.out.println();
        //(a)  List first n Records where Type is TV Shows.
        System.out.println("First "+n+"  Records with Type is Tv Shows :- ");
        startTime = System.nanoTime();
        moviesData.stream()
                .filter(movieRow -> movieRow.get(colsTitle.get("type")).equals("TV Show"))
                .filter(movieRow ->
                {
                    try {
                        if(movieRow.get(colsTitle.get("date_added")).trim().equals("")){
                            return false;
                        }
                        LocalDate dateAdded = dateFormat.parse(movieRow.get(colsTitle.get("date_added")).replaceAll("\"","").trim())
                                .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        return dateAdded.getYear() >= startDate.getYear() && dateAdded.getYear() <= endDate.getYear();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return false;
                })
                .limit(n)
                .forEach(System.out::println);
        endTime = System.nanoTime();
        System.out.println("Total Time to Execute Query 1 : "+(endTime-startTime) +" nano seconds");
        System.out.println();
        System.out.println();

        // (b)   List first n Records where listed in Horror Movies
        System.out.println("First "+n+" Records with Listed In  Horror Movies :- ");
        startTime = System.nanoTime();

        moviesData.stream()
                .filter(movieRow -> movieRow.get(colsTitle.get("listed_in")).contains("Horror Movies"))
                .filter(movieRow ->
                {
                    try {
                        if(movieRow.get(colsTitle.get("date_added")).trim().equals("")){
                            return false;
                        }
                        LocalDate dateAdded = dateFormat.parse(movieRow.get(colsTitle.get("date_added")).replaceAll("\"","").trim())
                                .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                         return dateAdded.getYear() >= startDate.getYear() && dateAdded.getYear() <= endDate.getYear();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return false;
                })
                .limit(n)
                .forEach(System.out::println);
        endTime = System.nanoTime();
        System.out.println("Total Time to Execute Query 2 : "+(endTime-startTime) +" nano seconds");
        System.out.println();
        System.out.println();
        //(c) List first n Records where type is Movie and Country is India
        System.out.println("First "+n+" Records with Type is Movie and Country is India :- ");
        startTime = System.nanoTime();

        moviesData.stream()
                .filter(movieRow -> movieRow.get(colsTitle.get("type")).equals("Movie"))
                .filter(movieRow -> movieRow.get(colsTitle.get("country")).equals("India"))
                .filter(movieRow ->
                {
                    try {
                        if(movieRow.get(colsTitle.get("date_added")).trim().equals("")){
                            return false;
                        }
                        LocalDate dateAdded = dateFormat.parse(movieRow.get(colsTitle.get("date_added")).replaceAll("\"","").trim())
                                .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        return dateAdded.getYear() >= startDate.getYear() && dateAdded.getYear() <= endDate.getYear();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return false;
                })
                .limit(n)
                .forEach(System.out::println);
        endTime = System.nanoTime();
        System.out.println("Total Time to Execute Query 3 : "+(endTime-startTime) +" nano seconds");
        System.out.println();
        System.out.println();
        System.out.println();

    }

    public static void main(String[] args) {
        try{
            Task4.readDataFromCSV();
            Scanner input = new Scanner(System.in);
            Scanner number = new Scanner(System.in);
            System.out.println("Enter value of n :-");

            Integer n = number.nextInt();
            System.out.println("Enter Start Date :-");
            String userSelectedStartDate = input.nextLine().trim();
            System.out.println("Enter End Date :-");
            String userSelectedEndDate = input.nextLine().trim();
            Task4.task4(userSelectedStartDate,userSelectedEndDate,n);
        }catch (Exception ex){
            ex.printStackTrace();
        }


    }
}
