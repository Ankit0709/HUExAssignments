package com.hashedin.assignments.trainDashboard.producer.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class TrainArrivalEvent implements  Event{
    private Long station_id;
    private Long train_id;
    private char direction;
    private String line;
    private String train_status;
    private Long prev_station_id;
    private char prev_direction;
}
