LOAD DATA  INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/stations.csv'
INTO TABLE stations
FIELDS TERMINATED BY ','
    ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES
(stop_id,direction_id,stop_name,station_name,station_descriptive_name,station_id,`order`,red,blue,green);