/*
 * Class to calculate statistical figures from supplied samples
 */
package statutils;

import java.util.Collections;
import java.util.List;

/**
 * @author Satheyaseelan Sugieethan
 */
public class statisticalFigures {
    
    // private variables
    private double  mean, variance, max, min, median, standardDeviation;
    private List<Double> exampleData;
    private double total;
    private int numberOfSamples;
    
    // constructor
    public statisticalFigures(List<Double> _exampleData){
                              exampleData = _exampleData;
    }
    
    // gets and sets
    public double getMean() {return mean;}      
    public double getVariance() {return variance;}       
    public double getMin() {return min;}       
    public double getMax() {return max;}      
    public double getMedian() {return median;}       
    public double getStandardDeviation() {return standardDeviation;}  
    public int getNumberOfSamples() {return numberOfSamples;}
    
    // methods to calcualte respective statistical values
    public void calculateMean(){
        
        for(int i = 0; i < exampleData.size(); ++i) {            
            
            total += exampleData.get(i);            
        
        }
        
        mean = (total / exampleData.size()); // finding ther average value
        
    }
    
    
    public void calculateVariance(){
        
        double squareDiff = 0;
        
        for (int i = 0; i < exampleData.size(); i++){
            
            squareDiff += (exampleData.get(i) - mean) *
                          (exampleData.get(i) - mean);
            
        }
        
        variance = ((double)squareDiff / exampleData.size());
        
    }
    
    
    public void calculateMinAndMax(){
        
        Collections.sort(exampleData);
        
        min = (exampleData.get(0));
        
        max = ((exampleData.get(exampleData.size()-1)));
        
    }
    
    
    public void calculateMedian(){
        
        Collections.sort(exampleData);

        if (exampleData.size() % 2 == 1) {
            
            median = exampleData.get((exampleData.size() + 1) / 2 - 1);
            
        } else {
        
            double lower = exampleData.get(exampleData.size() / 2 - 1);
            double upper = exampleData.get(exampleData.size() / 2);

            median = ((lower + upper) / 2.0);
            
        }
    }
    
    
    public void calculateStandardDeviation(){
        
        standardDeviation = (Math.sqrt(variance));
        
    }  
    
     
    public void calcualteNumberOfSamples(){
        
        numberOfSamples = (exampleData.size());
        
    }
}
