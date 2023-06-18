/*
 * Class to normalise and store the frequency array calcualted in 'samplesPerBin'
 */
package statutils;
/**
 * @author Satheyaseelan Sugieethan
 */
public class histogramNormalisation {
    
    // private variables
    private double width;
    private double[] normalisedSamplesInEachBin;
    private int numberOfBins;
    private double[] samplesInEachBin;
    private int sumOfAllFreq;
    
    //constructor 
    public histogramNormalisation(double _width, int _numberOfBins, double [] _samplesInEachBin, int _sumOfAllFreq){
                                    width = _width;
                                    numberOfBins = _numberOfBins;
                                    samplesInEachBin = _samplesInEachBin;
                                    sumOfAllFreq = _sumOfAllFreq;
    };
    
    //getters
    public double [] getNormalisedSamplesInEachBin() {return normalisedSamplesInEachBin;}
    
    // method to normalised the counted frequency
    public void calculateNormalisedFrequency() {  
        
        normalisedSamplesInEachBin = new double[numberOfBins];
        
        for (int i = 0; i < numberOfBins; ++i){
            // iterate through number of bins and normalise repsective frequecy and store in new array
        normalisedSamplesInEachBin[i] = (samplesInEachBin[i] / (sumOfAllFreq*width));
        
        }
  
    }
    
}
