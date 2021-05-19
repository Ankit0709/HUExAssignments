package com.hashedin.assignments.trainDashboard.producer.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hashedin.assignments.trainDashboard.producer.models.WeatherDataEvent;
import com.hashedin.assignments.trainDashboard.producer.producer.EventProducer;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

@Configuration
@Log4j2
@EnableScheduling
public class WeatherAPIData {
    private static String weatherTopicName = "org.station.weather";

    @Value("${weather.app.key}")
    private static String weatherAppKey = "dd3e39a7a94ca0727301c796e76b1b67";

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private EventProducer eventProducer;


    @Scheduled(fixedDelay = 1000*60*60*24)
    public void getWeatherDataEvent(){
        try{
            WeatherDataEvent weatherDataEvent = new WeatherDataEvent();
            StringBuilder apiBuilder = new StringBuilder();
                apiBuilder.append("http://api.openweathermap.org/data/2.5/weather?q=Delhi&appid=")
                        .append(weatherAppKey)
                        .append("&units=metric");
            URL url = new URL(apiBuilder.toString());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int statusCode = connection.getResponseCode();
            if(statusCode != 200){
                throw  new RuntimeException("Api is Invalid !");
            }
            Scanner scanner = new Scanner(url.openStream());
            StringBuilder responseStream = new StringBuilder();
            while(scanner.hasNext()){
                String data = scanner.next();
                responseStream.append(data);
            }
            JSONObject response = new JSONObject(responseStream.toString());
            log.info(responseStream.toString());
            Double temp = response.getJSONObject("main").getDouble("temp");
            weatherDataEvent.setTemperature(temp);
            if(temp>=25){
                weatherDataEvent.setStatus("Summer");
            }
            else if(temp>=20&&temp<25){
                weatherDataEvent.setStatus("Spring");
            }
            else{
                weatherDataEvent.setStatus("Winter");
            }
            eventProducer.sendEventProduced(weatherTopicName, weatherDataEvent);
            log.info("Weather Api Hit {}",weatherDataEvent);
        }
        catch (Exception ex){
            log.error("Error Occurs is = "+ex.getMessage());
        }
    }
}
