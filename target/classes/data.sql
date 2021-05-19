LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/stations.csv'
INTO TABLE stations
FIELDS TERMINATED BY ','
OPTIONALLY ENCLOSED BY '"'
LINES TERMINATED BY '\r\n'
IGNORE 1 ROWS
(stop_id,direction_id,stop_name,station_name,station_descriptive_name,station_id,`order`,red,blue,green);

