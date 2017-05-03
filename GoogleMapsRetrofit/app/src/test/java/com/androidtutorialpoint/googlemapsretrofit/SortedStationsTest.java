package com.androidtutorialpoint.googlemapsretrofit;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class SortedStationsTest {
    
    
    //***************************************************/
    /* Sorted Stations test using Price                */
    /* Stations should be sorted with descending order */
   //***************************************************/
    @Test
    public void testSortedResultsForPrice(){
        /* Initializing */
        PriceSearch getStationsFromLocation = new PriceSearch();
        Location location = new Location();
        location.setLattitude(30.2948248);   /* arbitrary location */
        location.setLongitude(-97.7422602);
        
        /* Get expectedResults and actualResults */
        Results unsortedResults;
        Results actualResults, expectedResults;

        unsortedResults = getStationsFromLocation.search(location);
        
        expectedResults = merge_sort_price(unsortedResults);
        actualResults = getStationsFromLocation.sort(unsortedResults);
        
        assertEquals(expectedResults, actualResults);
    }

    

    /***************************************************/
    /* Sorted Stations test using Restaurant           */
    /* Stations should be sorted with ascending order  */
    /***************************************************/
    @Test
    public void testSortedResultsForRestaurant(){
        /* Initializing */
        RestaurantSearch getStationsFromLocation = new RestaurantSearch();
        Location location = new Location();
        location.setLattitude(30.2948248);   /* arbitrary location */
        location.setLongitude(-97.7422602);

        /* Get expectedResults and actualResults */
        Results unsortedResults;
        Results actualResults, expectedResults;

        unsortedResults = getStationsFromLocation.search(location);

        expectedResults = merge_sort_restaurant(unsortedResults);
        actualResults = getStationsFromLocation.sort(unsortedResults);

        assertEquals(expectedResults, actualResults);
    }
    
    /***************************************************/
    /* Sorting Stations with Price by descending order */
    /* Return: "Results" with sorted stations          */
    /***************************************************/
    private Results merge_sort_price(Results results) {
        
        /* Base Case */
        Results left, right, result;
        
        if (results.size() <= 1) { return results; }
        else {
            /* Initialize left, right, and result */
            left = new Results();
            right = new Results();
            result = new Results();
            
            int middle = results.size()/2;
            
            int i, j;
            /* Save left Results */
            for (i = 0; i < middle; i++) {
                left.addStation(results.getStation(i));
            }
            
            /* Save right Results */
            for (j = middle; j < results.size(); j++) {
                right.addStation(results.getStation(j));
            }
            
            
            /* Use the method recursively */
            left = merge_sort_price(left);
            right = merge_sort_price(right);
            
            
            int l = 0;
            int r = 0;
            /* Merge the left and right ArrayList to result */
            while (left.size() != l && right.size() != r) {
                if (left.getStation(l).getPrice() <= right.getStation(r).getPrice()) {
                    result.addStation(left.getStation(l));
                    l++;
                } else {
                    result.addStation(right.getStation(r));
                    r++;
                }
            }
            
            /* Save remain elements to result */
            while (left.size() != l) {
                result.addStation(left.getStation(l));
                l++;
            }
            while (right.size() != r) {
                result.addStation(right.getStation(r));
                r++;
            }
            
            /* Return result */
            return result;
        }
    }
    
    /*******************************************************/
    /* Sorting Stations with Restaurant by ascending order */
    /* Return: "Results" with sorted stations              */
    /*******************************************************/
    private Results merge_sort_restaurant(Results results) {
        
        /* Base Case */
        Results left, right, result;
        
        if (results.size() <= 1) { return results; }
        else {
            /* Initialize left, right, and result */
            left = new Results();
            right = new Results();
            result = new Results();
            
            int middle = results.size()/2;
            
            int i, j;
            /* Save left Results */
            for (i = 0; i < middle; i++) {
                left.addStation(results.getStation(i));
            }
            
            /* Save right Results */
            for (j = middle; j < results.size(); j++) {
                right.addStation(results.getStation(j));
            }
            
            
            /* Use the method recursively */
            left = merge_sort_restaurant(left);
            right = merge_sort_restaurant(right);
            
            
            int l = 0;
            int r = 0;
            /* Merge the left and right ArrayList to result */
            while (left.size() != l && right.size() != r) {
                if (left.getStation(l).getRestaurants().size() >= right.getStation(r).getRestaurants().size()) {
                    result.addStation(left.getStation(l));
                    l++;
                } else {
                    result.addStation(right.getStation(r));
                    r++;
                }
            }
            
            /* Save remain elements to result */
            while (left.size() != l) {
                result.addStation(left.getStation(l));
                l++;
            }
            while (right.size() != r) {
                result.addStation(right.getStation(r));
                r++;
            }
            
            /* Return result */
            return result;
        }
    }
}
