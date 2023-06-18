/*
 * Class is used to peform fitting of data using external maths library
 */
package mathutils;

// uses the maths 'apache' library to peform fit
import org.apache.commons.math3.fitting.GaussianCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoints;

/**
 * @author Satheyaseelan Sugieethan
 */
public class dataFitting {
    
    // private variable
    private static int numberOfBins;
    private static double[] normalisedSamplesInEachBin;
    private static double width;
    private static double min;
    private static double [] PDF;
    private static double [] dataFittingParameters;
    
    // constructor
    public dataFitting(int _numberOfBins, double [] _normalisedSamplesInEachBin, double _width, double _min){  
                            numberOfBins = _numberOfBins;
                            normalisedSamplesInEachBin = _normalisedSamplesInEachBin;
                            width = _width;
                            min = _min;
    }
    
    // getters
    public double [] getNormalisedSamplesInEachBin() {return normalisedSamplesInEachBin;}
    public double [] getDataFittingParameters() {return dataFittingParameters;}
    public double [] getPDF() {return PDF;}
    
    // method to fit the data to allow a line graph plot
    public static void calculateFitting(){
        
        /* 
        *   Fits points to a Gaussian function.
        *   The initial guess values must be passed in the following order:
        *   Normalisation
        *   Mean
        *   Sigma
        *   The optimal values will be returned in the same order. 
        */
        
        WeightedObservedPoints obs = new WeightedObservedPoints();
            // iteration to add the midpoint for each range and respective normalised frequency to 'obs'
        for(int i = 0; i < numberOfBins; ++i){
            
            obs.add((min + ((width * i))) ,normalisedSamplesInEachBin[i]);
        
        }
            // passed into the fitter class to calculte desired values
        double[] parameters = GaussianCurveFitter.create().fit(obs.toList());
        
        // Print out result on screen
        System.out.printf("Normalization factor = %f\n",parameters[0]);
        System.out.println("Mean = " + parameters[1]);
        System.out.println("Sigma = " + parameters[2]);
        
        dataFittingParameters = parameters;
        
        PDF = new double[numberOfBins];
        
        for (int i = 0; i < numberOfBins; ++i) { // using the values calculated from 'guassainFitter' to calcualte PDF
            
            PDF[i] = parameters[0]*(Math.exp(-0.5*(Math.pow(((((((min + (width * i))+(min + (width*(i+1)))) / 2))-parameters[1])/parameters[2]),2))));
            
        }
        
    }

}
