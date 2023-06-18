package Unit_test_random;

/*
 Client code to test the statutils package
*/
import binmethod.RiceRule;
import binmethod.SquareRootChoice;
import binmethod.SturgesFormula;
import inputTextFile.textFileReader;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import mathutils.dataFitting;
import statutils.*;
/**
 * @author Satheyaseealan Sugieethan
 */
public class Unit_test_final {
    public static void main(String[] args) throws FileNotFoundException {
        
        // Create array of data
        textFileReader textFileReaderInstace = new textFileReader("C:\\Users\\sugie\\OneDrive - The University of Nottingham\\2022-2023\\EEEE3084 Scalable Cross-Platform Software Design\\Coursework 3 – JAVA and GUI Topic\\histogramProject\\test\\Unit_test_random\\measurementData.txt");
        ArrayList<Double> exampleData = textFileReaderInstace.FileReader();
        
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
        System.out.println("Mean: " + statisticalInstance.getMean());
        
        statisticalInstance.calculateMedian();
        System.out.println("Median: " + statisticalInstance.getMedian());
        
        statisticalInstance.calculateMinAndMax();
        System.out.println("Min: " + statisticalInstance.getMin() + "\nMax: " + statisticalInstance.getMax());
        
        statisticalInstance.calculateVariance();
        System.out.println("Variance: " + statisticalInstance.getVariance());
        
        statisticalInstance.calculateStandardDeviation();
        System.out.println("Standard Deviation: " + statisticalInstance.getStandardDeviation() + "\n");

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
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////        
///////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
    }
}

        