package Unit_test_mathutils;

/*
 Client code to test the statutils package
*/
import binmethod.RiceRule;
import binmethod.SquareRootChoice;
import binmethod.SturgesFormula;
import java.util.List;
import java.util.Arrays;
import mathutils.dataFitting;
// the bin rule formulae must be implemented in binmethod package
import statutils.*;
/**
 * @author Satheyaseealan Sugieethan
 */
public class Unit_test_dataFitting {
    public static void main(String[] args) {
        
        // Create array of data
        List<Double> exampleData = Arrays.asList(1., 2., 3., 4., 5., 6., 7., 8., 9., 10., 11.);
        // test SturgesFormula class
        SturgesFormula SturgesInstance = new SturgesFormula(exampleData);
        SturgesInstance.calculateNumberOfBins();
        System.out.printf("By Sturges Formula: %d \n", SturgesInstance.getNumberOfBins());
        
        // test SquareRootChoice class
        SquareRootChoice SquareRootChoiceInstance = new SquareRootChoice(exampleData);
        SquareRootChoiceInstance.calculateNumberOfBins();
        System.out.printf("By Square Root Formula: %d \n", SquareRootChoiceInstance.getNumberOfBins());
        
        //test RiceRule class
        RiceRule RiceRuleInstance = new RiceRule(exampleData);
        RiceRuleInstance.calculateNumberOfBins();
        System.out.printf("By Rice Rule Formula: %d \n\n", RiceRuleInstance.getNumberOfBins());
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////        
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // test statisticalFigures class
        statisticalFigures statisticalInstance = new statisticalFigures(exampleData);
        
        statisticalInstance.calculateMean();
        System.out.printf("Mean: %f \n", statisticalInstance.getMean());
        
        statisticalInstance.calculateMedian();
        System.out.printf("Median: %f \n", statisticalInstance.getMedian());
        
        statisticalInstance.calculateMinAndMax();
        System.out.println("Min: " + statisticalInstance.getMin() + "\nMax: " + statisticalInstance.getMax());
        
        statisticalInstance.calculateVariance();
        System.out.printf("Variance: %f \n", statisticalInstance.getVariance());
        
        statisticalInstance.calculateStandardDeviation();
        System.out.printf("Standard Deviation: %f \n\n", statisticalInstance.getStandardDeviation());
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////        
///////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
        statisticalInstance.calcualteNumberOfSamples();
        System.out.printf("Number of Samples: %d \n\n", statisticalInstance.getNumberOfSamples());
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////        
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////          
        // test statisticalFigures class
        samplesPerBin SturgesSamplesPerBinInstance = new samplesPerBin(SturgesInstance.getNumberOfBins(), statisticalInstance.getMin(), statisticalInstance.getMax(), exampleData, statisticalInstance.getNumberOfSamples());
        SturgesSamplesPerBinInstance.calculateSamplesInEachBin();
        System.out.println("Sturges Unnormalised frequencies: " + Arrays.toString(SturgesSamplesPerBinInstance.getSamplesInEachBin()));
        
        samplesPerBin SquareRootChoiceSamplesPerBinInstance = new samplesPerBin(SquareRootChoiceInstance.getNumberOfBins(), statisticalInstance.getMin(), statisticalInstance.getMax(), exampleData, statisticalInstance.getNumberOfSamples());
        SquareRootChoiceSamplesPerBinInstance.calculateSamplesInEachBin();
        System.out.println("SquareRootChocie Unnormalised frequencies: " + Arrays.toString(SquareRootChoiceSamplesPerBinInstance.getSamplesInEachBin()));
        
        samplesPerBin RiceRuleSamplesPerBinInstance = new samplesPerBin(RiceRuleInstance.getNumberOfBins(), statisticalInstance.getMin(), statisticalInstance.getMax(), exampleData, statisticalInstance.getNumberOfSamples());
        RiceRuleSamplesPerBinInstance.calculateSamplesInEachBin();
        System.out.println("RiceRule Unnormalised frequencies: " + Arrays.toString(RiceRuleSamplesPerBinInstance.getSamplesInEachBin()) + "\n");
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////        
///////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
        //Histogram normalisation
        histogramNormalisation SturgesHistogramNormalisationInstance = new histogramNormalisation(SturgesSamplesPerBinInstance.getWidth(), 
                                                                                                    SturgesInstance.getNumberOfBins(),
                                                                                                    SturgesSamplesPerBinInstance.getSamplesInEachBin(), 
                                                                                                    statisticalInstance.getNumberOfSamples());
        SturgesHistogramNormalisationInstance.calculateNormalisedFrequency();
        System.out.println("Sturges Normalised Frequency: " + Arrays.toString(SturgesHistogramNormalisationInstance.getNormalisedSamplesInEachBin()));
        
        
        histogramNormalisation SquareRootChoiceHistogramNormalisationInstance = new histogramNormalisation(SquareRootChoiceSamplesPerBinInstance.getWidth(), 
                                                                                                    SquareRootChoiceInstance.getNumberOfBins(),
                                                                                                    SquareRootChoiceSamplesPerBinInstance.getSamplesInEachBin(), 
                                                                                                    statisticalInstance.getNumberOfSamples());
        SquareRootChoiceHistogramNormalisationInstance.calculateNormalisedFrequency();
        System.out.println("SquareRootChoice Normalised Frequency: " + Arrays.toString(SquareRootChoiceHistogramNormalisationInstance.getNormalisedSamplesInEachBin()));
        
        
        histogramNormalisation RiceRuleHistogramNormalisationInstance = new histogramNormalisation(RiceRuleSamplesPerBinInstance.getWidth(), 
                                                                                                    RiceRuleInstance.getNumberOfBins(),
                                                                                                    RiceRuleSamplesPerBinInstance.getSamplesInEachBin(), 
                                                                                                    statisticalInstance.getNumberOfSamples());
        RiceRuleHistogramNormalisationInstance.calculateNormalisedFrequency();
        System.out.println("RiceRule Normalised Frequency: " + Arrays.toString(RiceRuleHistogramNormalisationInstance.getNormalisedSamplesInEachBin()) + "\n");
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////        
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
       //data fitting
        dataFitting SturgesDataFittingInstance = new dataFitting(SturgesInstance.getNumberOfBins(),
                                                                    SturgesHistogramNormalisationInstance.getNormalisedSamplesInEachBin(),
                                                                    SturgesSamplesPerBinInstance.getWidth(),
                                                                    statisticalInstance.getMin());
        SturgesDataFittingInstance.calculateFitting();
        System.out.println(Arrays.toString(SturgesDataFittingInstance.getPDF()) + "\n");
        
        dataFitting SquareRootChoiceDataFittingInstance = new dataFitting(SquareRootChoiceInstance.getNumberOfBins(),
                                                                    SquareRootChoiceHistogramNormalisationInstance.getNormalisedSamplesInEachBin(),
                                                                    SquareRootChoiceSamplesPerBinInstance.getWidth(),
                                                                    statisticalInstance.getMin());
        SquareRootChoiceDataFittingInstance.calculateFitting();
        System.out.println(Arrays.toString(SquareRootChoiceDataFittingInstance.getPDF()) + "\n");
        
        dataFitting RiceRuleDataFittingInstance = new dataFitting(RiceRuleInstance.getNumberOfBins(),
                                                                    RiceRuleHistogramNormalisationInstance.getNormalisedSamplesInEachBin(),
                                                                    RiceRuleSamplesPerBinInstance.getWidth(),
                                                                    statisticalInstance.getMin());
        RiceRuleDataFittingInstance.calculateFitting();
        System.out.println(Arrays.toString(RiceRuleDataFittingInstance.getPDF()) + "\n");
    }
}
