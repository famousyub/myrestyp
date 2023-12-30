package com.example.crudmn.domain;


import com.example.crudmn.entity.audit.UserDateAudit;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="perfume")
public class Perfume extends UserDateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "perfume_id_seq")
    @SequenceGenerator(name = "perfume_id_seq", sequenceName = "perfume_id_seq", initialValue = 109, allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "perfume_title")
    private String perfumeTitle;

    public Perfume() {
    }

    @Override
    public String toString() {
        return "Perfume{" +
                "id=" + id +
                ", perfumeTitle='" + perfumeTitle + '\'' +
                ", perfumer='" + perfumer + '\'' +
                ", year=" + year +
                ", country='" + country + '\'' +
                ", perfumeGender='" + perfumeGender + '\'' +
                ", fragranceTopNotes='" + fragranceTopNotes + '\'' +
                ", fragranceMiddleNotes='" + fragranceMiddleNotes + '\'' +
                ", fragranceBaseNotes='" + fragranceBaseNotes + '\'' +
                ", description='" + description + '\'' +
                ", filename='" + filename + '\'' +
                ", price=" + price +
                ", volume='" + volume + '\'' +
                ", type='" + type + '\'' +
                ", perfumeRating=" + perfumeRating +
                ", reviews=" + reviews +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPerfumeTitle() {
        return perfumeTitle;
    }

    public void setPerfumeTitle(String perfumeTitle) {
        this.perfumeTitle = perfumeTitle;
    }

    public String getPerfumer() {
        return perfumer;
    }

    public void setPerfumer(String perfumer) {
        this.perfumer = perfumer;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPerfumeGender() {
        return perfumeGender;
    }

    public void setPerfumeGender(String perfumeGender) {
        this.perfumeGender = perfumeGender;
    }

    public String getFragranceTopNotes() {
        return fragranceTopNotes;
    }

    public void setFragranceTopNotes(String fragranceTopNotes) {
        this.fragranceTopNotes = fragranceTopNotes;
    }

    public String getFragranceMiddleNotes() {
        return fragranceMiddleNotes;
    }

    public void setFragranceMiddleNotes(String fragranceMiddleNotes) {
        this.fragranceMiddleNotes = fragranceMiddleNotes;
    }

    public String getFragranceBaseNotes() {
        return fragranceBaseNotes;
    }

    public void setFragranceBaseNotes(String fragranceBaseNotes) {
        this.fragranceBaseNotes = fragranceBaseNotes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPerfumeRating() {
        return perfumeRating;
    }

    public void setPerfumeRating(Double perfumeRating) {
        this.perfumeRating = perfumeRating;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @Column(name = "perfumer")
    private String perfumer;

    @Column(name = "year")
    private Integer year;

    @Column(name = "country")
    private String country;

    @Column(name = "perfume_gender")
    private String perfumeGender;

    @Column(name = "fragrance_top_notes")
    private String fragranceTopNotes;

    @Column(name = "fragrance_middle_notes")
    private String fragranceMiddleNotes;

    @Column(name = "fragrance_base_notes")
    private String fragranceBaseNotes;

    @Column(name = "description")
    private String description;

    @Column(name = "filename")
    private String filename;

    @Column(name = "price")
    private Integer price;

    @Column(name = "volume")
    private String volume;

    @Column(name = "type")
    private String type;

    @Column(name = "perfume_rating")
    private Double perfumeRating;

    @OneToMany

    private List<Review> reviews;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Perfume perfume = (Perfume) o;
        return Objects.equals(id, perfume.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
