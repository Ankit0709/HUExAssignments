package com.hashedin.assignments.NetflixRestApiAssignment.config;


import com.hashedin.assignments.NetflixRestApiAssignment.models.NetflixDataModel;
import com.hashedin.assignments.NetflixRestApiAssignment.repository.NetflixDataRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.BufferedReader;
import java.io.FileReader;

@Configuration
@EnableScheduling
public class DataScheduling {

    private static final Logger LOGGER = LogManager.getLogger(DataScheduling.class);
    private static final String  FILE_PATH = "src/main/resources/static/netflix_titles.csv";
    @Autowired
    private NetflixDataRepository netflixDataRepository;

    @Scheduled(fixedDelay = 5000)
    public void  storeDataFixedDelayTask(){
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(FILE_PATH));
            String row = null;
            int rowNo = 0;

            while((row = csvReader.readLine()) !=null){
                String[] data = row.split( ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                if(rowNo != 0){
                    NetflixDataModel bean = new NetflixDataModel();
                    bean.setShowId(data[0]);
                    bean.setType(data[1]);
                    bean.setTitle(data[2]);
                    bean.setDirector(data[3]);
                    bean.setCast(data[4]);
                    bean.setCountry(data[5]);
                    bean.setDateAdded(data[6]);
                    bean.setReleaseYear(data[7]);
                    bean.setRating(data[8]);
                    bean.setDuration(data[9]);
                    bean.setListedIn(data[10]);
                    bean.setDescription(data[11]);
                    netflixDataRepository.save(bean);
                }
                rowNo++;
            }
        }
        catch (Exception ex){
            LOGGER.error("Error is : "+ex.getMessage());
        }
    }
}
