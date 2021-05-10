package com.hashedin.assignments.NetflixRestApiAssignment.service;

import com.hashedin.assignments.NetflixRestApiAssignment.models.NetflixDataModel;
import com.hashedin.assignments.NetflixRestApiAssignment.repository.NetflixDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class NetflixDataShowService {
    private static final String  FILE_PATH = "src/main/resources/static/netflix_titles.csv";
    @Autowired
    private NetflixDataRepository netflixDataRepository;

    public void saveNetflixShowDataInDatabase(NetflixDataModel netflixDataModel) {
        netflixDataRepository.save(netflixDataModel);
    }
    public void saveNetflixShowDataInCSV(NetflixDataModel netflixDataModel) throws IOException {
        FileWriter csvWriter = new FileWriter(FILE_PATH,true);
        csvWriter.append("\n");
        csvWriter.append(netflixDataModel.getShowId());
        csvWriter.append(",");
        csvWriter.append(netflixDataModel.getType());
        csvWriter.append(",");
        csvWriter.append(netflixDataModel.getTitle());
        csvWriter.append(",");
        csvWriter.append(netflixDataModel.getDirector());
        csvWriter.append(",");
        csvWriter.append(netflixDataModel.getCast());
        csvWriter.append(",");
        csvWriter.append(netflixDataModel.getCountry());
        csvWriter.append(",");
        csvWriter.append(netflixDataModel.getDateAdded());
        csvWriter.append(",");
        csvWriter.append(netflixDataModel.getReleaseYear());
        csvWriter.append(",");
        csvWriter.append(netflixDataModel.getRating());
        csvWriter.append(",");
        csvWriter.append(netflixDataModel.getDuration());
        csvWriter.append(",");
        csvWriter.append(netflixDataModel.getListedIn());
        csvWriter.append(",");
        csvWriter.append(netflixDataModel.getDescription());
        csvWriter.flush();
        csvWriter.close();
    }

    public List<NetflixDataModel> getFirstNNetflixShows(Integer count) {
        return netflixDataRepository.findAll()
                .stream().limit(count)
                .collect(Collectors.toList());
    }

    public List<NetflixDataModel> getAllNetflixHorrorShows(String movieType) {
        return netflixDataRepository.findAll()
                .stream().filter(show -> show.getListedIn().contains(movieType))
                .collect(Collectors.toList());
    }

    public List<NetflixDataModel> getAllNetflixShowsOnCountryBasis(String country) {
        return netflixDataRepository.findAll()
                .stream().filter(show-> show.getCountry().equals(country))
                .collect(Collectors.toList());
    }

    public List<NetflixDataModel> getAllNetflixShowsOnStartAndEndDateBasis(LocalDate startDate, LocalDate endDate) {
        DateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        return netflixDataRepository.findAll()
                .stream() .filter(movieRow ->
                {
                    try {
                        if(movieRow.getDateAdded().trim().equals("")){
                            return false;
                        }
                        LocalDate dateAdded = dateFormat.parse(movieRow.getDateAdded().trim().replaceAll("\"","").trim())
                                .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        return dateAdded.isAfter(startDate) && dateAdded.isBefore(endDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }


}
