package com.trivago.mp.casestudy;

import javax.annotation.Generated;
import java.util.*;

/**
 * Stores all relevant information about a particular hotel.
 */
public class Hotel {
    private final int id;
    private final String name;
    private final City city;
    private final int rating;
    private final int stars;

    /**
     * The many-to-many relationship from hotels to advertisers.
     */
    private List<Advertiser> advertisers;

    public Hotel(final int id,
                 final String name,
                 final City city,
                 final int rating,
                 final int stars) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.rating = rating;
        this.stars = stars;
        advertisers = new ArrayList<>();
    }

    public void setAdvertisers(final List<Advertiser> advertisers) {
        this.advertisers = Collections.unmodifiableList(advertisers);
    }

    /**
     * A unique id as specified in the corresponding .csv file
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * The English name of the hotel
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * The city in which the hotel is found.
     *
     * @return A reference to the hotel's city.
     */
    public City getCity() {
        return city;
    }

    /**
     * The ratings given by user feedback, between 0-100
     *
     * @return
     */
    public int getRating() {
        return rating;
    }

    /**
     * The star rating for the given hotel, between 1-5
     *
     * @return
     */
    public int getStars() {
        return stars;
    }

    public List<Advertiser> getAdvertisers() {
        return new ArrayList<>(advertisers);
    }

    @Override
    public String toString() {
        return "Hotel{" + "id=" + id + ", name='" + name + '\'' + ", city=" + city + ", rating=" + rating + ", stars=" + stars + '}';
    }

    @Override
    @Generated("IDEA")
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Hotel that = (Hotel) o;
        return id == that.id;
    }

    @Override
    @Generated("IDEA")
    public int hashCode() {
        return Objects.hash(id);
    }
}
