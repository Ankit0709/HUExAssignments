package com.hashedin.assignments.NetflixRestApiAssignment.controller;


import com.hashedin.assignments.NetflixRestApiAssignment.models.NetflixDataModel;
import com.hashedin.assignments.NetflixRestApiAssignment.service.NetflixDataShowService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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
    public List<NetflixDataModel> getFirstNShows(@RequestParam Integer count){
        List<NetflixDataModel> showsList = new ArrayList<>();
        try{
            showsList = netflixDataShowService.getFirstNNetflixShows(count);
        } catch (Exception ex){
            LOGGER.error("Error is "+ex.getMessage());
        }
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
    public List<NetflixDataModel> getAllNetflixShowsOnListedInBasis(@RequestParam String movieType){
        List<NetflixDataModel> showList = new ArrayList<>();
        try{
            showList = netflixDataShowService.getAllNetflixHorrorShows(movieType);
        }catch (Exception ex){
            LOGGER.error("Error is "+ex.getMessage());
        }
        return showList;
    }

    /**
     * Method  returns All Netflix Shows which has specific Country listed from Database
     *
     * @param country
     * @return
     */
    @GetMapping(value = "/tvshows", params= "country" ,produces = "application/json")
    public List<NetflixDataModel> getAllNetflixShowsOnCountryBasis(@RequestParam String country){
        List<NetflixDataModel> showList = new ArrayList<>();
        try{
            showList = netflixDataShowService.getAllNetflixShowsOnCountryBasis(country);
        }catch (Exception ex){
            LOGGER.error("Error is "+ex.getMessage());
        }
        return showList;
    }


    @GetMapping(value = "/tvshows",params = {"startdate","enddate"},produces = "application/json")
    public List<NetflixDataModel>  getAllNetflixShowsOnStartAndEndDateBasis(@RequestParam String startdate,@RequestParam String enddate){
        List<NetflixDataModel> showList = new ArrayList<>();
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
