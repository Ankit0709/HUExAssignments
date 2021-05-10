package com.hashedin.assignments.NetflixRestApiAssignment.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="netflix_show_details")
public class NetflixDataModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "show_id")
    private String showId;
    @Column(name = "type", nullable = false)
    private String type;
    @Column(name = "title",columnDefinition = "Text",nullable = false)
    private String title;
    @Column(name = "director",columnDefinition = "Text")
    private String director;
    @Column(name = "cast",columnDefinition = "Text")
    private String cast;
    @Column(name = "country")
    private String country;
    @Column(name = "date_added",nullable = false)
    private String dateAdded;
    @Column(name = "release_year",nullable = false)
    private String releaseYear;
    @Column(name = "rating")
    private String rating;
    @Column(name = "duration")
    private String duration;
    @Column(name = "listed_in")
    private String listedIn;
    @Column(name = "description",columnDefinition = "Text")
    private String description;

    public NetflixDataModel(String showId, String type, String title, String director, String cast, String country, String dateAdded, String releaseYear, String rating, String duration, String listedIn, String description) {
        this.showId = showId;
        this.type = type;
        this.title = title;
        this.director = director;
        this.cast = cast;
        this.country = country;
        this.dateAdded = dateAdded;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.duration = duration;
        this.listedIn = listedIn;
        this.description = description;
    }
}