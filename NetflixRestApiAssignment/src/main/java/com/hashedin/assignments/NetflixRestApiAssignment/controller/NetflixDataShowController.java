package com.hashedin.assignments.NetflixRestApiAssignment.controller;


import com.hashedin.assignments.NetflixRestApiAssignment.models.NetflixDataModel;
import com.hashedin.assignments.NetflixRestApiAssignment.service.NetflixDataShowService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
public class NetflixDataShowController {

    private static final Logger LOGGER = LogManager.getLogger(NetflixDataShowController.class);

    @Autowired
    private NetflixDataShowService netflixDataShowService;

    /**
     * Method returns First N Netflix Shows From Database
     *
     * @param count
     * @return
     */
    @GetMapping(value = "/tvshows", params ="count", produces = "application/json")
    public List<NetflixDataModel> getFirstNShows(HttpServletRequest request,
                                                 HttpServletResponse response,
                                                 @RequestParam Integer count){
        List<NetflixDataModel> showsList = new ArrayList<>();
        Instant startTime,endTime;
        startTime = Instant.now();
        try{
            showsList = netflixDataShowService.getFirstNNetflixShows(count);
        } catch (Exception ex){
            LOGGER.error("Error is "+ex.getMessage());
        }
        endTime = Instant.now();
        long time = Duration.between(startTime,endTime).toMillis();
        LOGGER.info("Time Taken to Execute the request :"+time+" milliseconds .");
        response.setHeader("X-TIME-TO-EXECUTE", String.valueOf(time));

        return showsList;
    }

    /**
     * Method returns All Netflix Shows Which contains Movie Type in Listed In Options
     * from Database
     *
     * @param movieType
     * @return
     */
    @GetMapping(value = "/tvshows", params = "movieType", produces = "application/json")
    public List<NetflixDataModel> getAllNetflixShowsOnListedInBasis(HttpServletRequest request,
                                                                    HttpServletResponse response,
                                                                    @RequestParam String movieType){
        List<NetflixDataModel> showList = new ArrayList<>();
        Instant startTime,endTime;
        startTime = Instant.now();
        try{
            showList = netflixDataShowService.getAllNetflixHorrorShows(movieType);
        }catch (Exception ex){
            LOGGER.error("Error is "+ex.getMessage());
        }
        endTime = Instant.now();
        long time = Duration.between(startTime,endTime).toMillis();
        LOGGER.info("Time Taken to Execute the request :"+time+" milliseconds .");
        response.setHeader("X-TIME-TO-EXECUTE", String.valueOf(time));

        return showList;
    }

    /**
     * Method  returns All Netflix Shows which has specific Country listed from Database
     *
     * @param country
     * @return
     */
    @GetMapping(value = "/tvshows", params= "country" ,produces = "application/json")
    public List<NetflixDataModel> getAllNetflixShowsOnCountryBasis(HttpServletRequest request,
                                                                   HttpServletResponse response,
                                                                   @RequestParam String country){
        List<NetflixDataModel> showList = new ArrayList<>();
        Instant startTime,endTime;
        startTime = Instant.now();
        try{
            showList = netflixDataShowService.getAllNetflixShowsOnCountryBasis(country);
        }catch (Exception ex){
            LOGGER.error("Error is "+ex.getMessage());
        }
        endTime = Instant.now();
        long time = Duration.between(startTime,endTime).toMillis();
        LOGGER.info("Time Taken to Execute the request :"+time+" milliseconds .");
        response.setHeader("X-TIME-TO-EXECUTE", String.valueOf(time));

        return showList;
    }

    /**
     * Method Filters all Netflix Records on Start Date and End date Basis
     * @param startdate
     * @param enddate
     * @return
     */


    @GetMapping(value = "/tvshows",params = {"startdate","enddate"},produces = "application/json")
    public List<NetflixDataModel>  getAllNetflixShowsOnStartAndEndDateBasis(HttpServletRequest request,
                                                                            HttpServletResponse response,
                                                                            @RequestParam String startdate,@RequestParam String enddate){
        List<NetflixDataModel> showList = new ArrayList<>();
        Instant startTime,endTime;
        startTime = Instant.now();
        DateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        try{
            LocalDate userselectedStartDate = dateFormat.parse(startdate).toInstant().
                    atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate userselectedEndDate = dateFormat.parse(enddate).toInstant()
                    .atZone(ZoneId.systemDefault()).toLocalDate();
            showList = netflixDataShowService.getAllNetflixShowsOnStartAndEndDateBasis(userselectedStartDate,userselectedEndDate);
        } catch (Exception ex){
            LOGGER.error("Error is "+ex.getMessage());
        }
        endTime = Instant.now();
        long time = Duration.between(startTime,endTime).toMillis();
        LOGGER.info("Time Taken to Execute the request :"+time+" milliseconds .");
        response.setHeader("X-TIME-TO-EXECUTE", String.valueOf(time));

        return  showList;
    }
    /**
     * Method in which User Saves New Netflix Show Record in Database
     *
     *
     * @param netflixDataModel
     * @return
     */
    @PostMapping(value = "/saveNetflixShow",consumes = "application/json")
    public String saveNetflixShow(@RequestBody NetflixDataModel netflixDataModel, @RequestParam(value = "target",required = true) String target){
        String response = "";
        try{
            if(target.equals("database")){
                netflixDataShowService.saveNetflixShowDataInDatabase(netflixDataModel);
            }
            else{
                netflixDataShowService.saveNetflixShowDataInCSV(netflixDataModel);
            }
            response = "Data Added Successfully.";
        }catch (Exception ex){
            LOGGER.error("Error is :"+ex.getMessage());
            response = "Failure";
        }
        return response;
    }

}
