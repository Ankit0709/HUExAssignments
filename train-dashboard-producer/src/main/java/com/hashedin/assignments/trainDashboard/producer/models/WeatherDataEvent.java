package com.hashedin.assignments.trainDashboard.producer.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherDataEvent implements  Event {
    private  Double temperature;
    private String status;
}
