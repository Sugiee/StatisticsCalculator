/*
 * Class to count the frequency in each bin and used later in 'histogramNormalisation'
 */
package statutils;

import java.util.List;

/**
 * @author Satheyaseelan Sugieethan
 */
public class samplesPerBin {
    
    // private variables
    private int numberOfSamples,numberOfBins;
    private double[] samplesInEachBin;
    private double width, min, max;
    private List<Double> exampleData;
    
    // constructor
    public samplesPerBin(int _numberOfBins, double _min, double _max, List<Double> _exampleData, int _numberOfSamples){  
                         numberOfBins = _numberOfBins;
                         min = _min;
                         max = _max;
                         exampleData = _exampleData;
                         numberOfSamples = _numberOfSamples;
    }
    
    // getters
    public double[] getSamplesInEachBin() {return samplesInEachBin;}
    public double getWidth() {return width;}
    
    //method to calculate frequency per bin
    public void calculateSamplesInEachBin() {
        
        samplesInEachBin = new double[numberOfBins];
        
        width = ((max - min) / numberOfBins);
            // algorithm to sort through the list and count the samples for respective bins
        for(int i = 0; i < numberOfBins; ++i){ 
            
            for(int j = 0; j < numberOfSamples; ++j){
                    // checks if sample fits between each iteration of upper/lower bound
                if ((min + (width * i)) <= exampleData.get(j) && exampleData.get(j) < ((min + (width * (i+1))))) {
                    
                    samplesInEachBin[i] += 1;
                    
                }   // last value doesn't fit ranges as they not inclusive of upper bound so this ensures last value not ignored
                else if (exampleData.get(j) == max && (i == (numberOfBins-1))) { 
                        
                    samplesInEachBin[i] += 1;   //frequency stored in array
                    
                }
            }
        }
    }
}
