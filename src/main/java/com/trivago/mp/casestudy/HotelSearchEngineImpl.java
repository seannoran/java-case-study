package com.trivago.mp.casestudy;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * TODO: Implement this class.
 * Your task will be to implement two functions, one for loading the data which is stored as .csv files in the ./data
 * folder and one for performing the actual search.
 */
public class HotelSearchEngineImpl implements HotelSearchEngine {

    /**
     * The delimiter used to indicate which values in the CSVs belong to which attributes.
     */
    private static final String COLUMN_DELIMITER = ";";

    /**
     * The set of unique available advertisers.
     */
    private final Set<Advertiser> advertisers = new HashSet<>();

    /**
     * The set of unique available cities.
     */
    private final Set<City> cities = new HashSet<>();

    /**
     * The set of unique available hotels.
     */
    private final Set<Hotel> hotels = new HashSet<>();

    @Override
    public void initialize() {
        initializeAdvertisers();
        initializeCities();
        initializeHotels();
    }

    @Override
    public List<HotelWithOffers> performSearch(String cityName, DateRange dateRange, OfferProvider offerProvider) {
        // TODO: IMPLEMENT ME
        throw new UnsupportedOperationException();
    }

    /**
     * Initializes the {@link #advertisers} from the database.
     */
    private void initializeAdvertisers() {
        try (final BufferedReader br = new BufferedReader(new FileReader("advertisers.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                final String[] values = line.split(COLUMN_DELIMITER);
                final int id = Integer.parseInt(values[0]);
                final String name = values[1];
                advertisers.add(new Advertiser(id, name));
            }
        } catch (final IOException e) {

        }
    }

    /**
     * Initializes the {@link #advertisers} from the database.
     */
    private void initializeCities() {
        try (final BufferedReader br = new BufferedReader(new FileReader("cities.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                final String[] values = line.split(COLUMN_DELIMITER);
                final int id = Integer.parseInt(values[0]);
                final String name = values[1];
                cities.add(new City(id, name));
            }
        } catch (final IOException e) {

        }
    }

    /**
     * Initializes the {@link #hotels} from the database.
     */
    private void initializeHotels() {
        try (final BufferedReader br = new BufferedReader(new FileReader("hotels.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                final String[] values = line.split(COLUMN_DELIMITER);
                final int id = Integer.parseInt(values[0]);
                final String name = values[4];
                final int rating = Integer.parseInt(values[5]);
                final int stars = Integer.parseInt(values[5]);
                hotels.add(new Hotel(id, name, rating, stars));
            }
        } catch (final IOException e) {

        }
    }
}
