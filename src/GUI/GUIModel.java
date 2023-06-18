/*
 * General model class so that controller only communicates to model
 * Model then communicates to the appropriate clases
 */
package GUI;

import binmethod.*;
import statutils.*;
import mathutils.dataFitting;
import java.util.ArrayList;

/**
 * @author Satheyaseelan Sugieethan
 */
public class GUIModel {
    
    // private variables
    ArrayList<Double> exampleData;
    private int [] numberOfBins = new int[3];
    private int numberOfSamples;
    private double min, max, mean, variance, standardDeviation;
    private double [] width = new double[3];    
    private double [] SturgesDatafittingParameters,
                      SquareRootChoiceDatafittingParameters,
                      RiceRuleDatafittingParameters,
                      SturgesNormalisedSamplesInEachBin,
                      SquareRootChoiceNormalisedSamplesInEachBin,
                      RiceRuleNormalisedSamplesInEachBin,
                      SturgesPDF,
                      SquareRootChoicePDF,
                      RiceRulePDF;
    
    // constructor
    public GUIModel(ArrayList<Double> _exampleData){exampleData = _exampleData;}
    
    // getters
    public int [] getNumberOfbins() {return numberOfBins;}
    public double getNumberOfData() {return numberOfSamples;}
    public double getMin() {return min;}
    public double getMax() {return max;}
    public double getMean() {return mean;}
    public double getVariance() {return variance;}
    public double getStandardDeviation() {return standardDeviation;}
    public double [] getWidth() {return width;}
    public double [] getSturgesDatafittingParameters() {return SturgesDatafittingParameters ;}
    public double [] getSquareRootChoiceDatafittingParameters() {return SquareRootChoiceDatafittingParameters ;}
    public double [] getRiceRuleDatafittingParameters() {return RiceRuleDatafittingParameters ;}  
    public double [] getSturgesNormalisedSamplesInEachBin() {return SturgesNormalisedSamplesInEachBin;}
    public double [] getSquareRootChoiceNormalisedSamplesInEachBin() {return SquareRootChoiceNormalisedSamplesInEachBin;}
    public double [] getRiceRuleNormalisedSamplesInEachBin() {return RiceRuleNormalisedSamplesInEachBin;}
    public double [] getSturgesPDF() {return SturgesPDF;}
    public double [] getSquareRootChoicePDF() {return SquareRootChoicePDF;}
    public double [] getRiceRulePDF() {return RiceRulePDF;}
    
    // method to create instance and calculate all values when controller desires
    // Instanties respecitve objects, passing compulsary variables to consturctor
    // After instantiation the calculate functions are used
    // Getters used to allow manual checking of values in the terminal window
    public void calculateAll(){
        
        // binmethod                          
        SturgesFormula SturgesInstance = new SturgesFormula(exampleData);
        SquareRootChoice SquareRootChoiceInstance = new SquareRootChoice(exampleData);
        RiceRule RiceRuleInstance = new RiceRule(exampleData);
        
        SturgesInstance.calculateNumberOfBins();
        SquareRootChoiceInstance.calculateNumberOfBins();               
        RiceRuleInstance.calculateNumberOfBins();
        
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////        
///////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
        
        // statisticalFigures
        statisticalFigures statisticalInstance = new statisticalFigures(exampleData);
        
        statisticalInstance.calculateMean();
        statisticalInstance.calculateMedian();
        statisticalInstance.calculateMinAndMax();
        statisticalInstance.calculateVariance();
        statisticalInstance.calculateStandardDeviation();
        statisticalInstance.calcualteNumberOfSamples();
        
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////        
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////   
        
        // samplesPerBin
        samplesPerBin SturgesSamplesPerBinInstance = new samplesPerBin(SturgesInstance.getNumberOfBins(), statisticalInstance.getMin(), statisticalInstance.getMax(), exampleData, statisticalInstance.getNumberOfSamples());
        samplesPerBin SquareRootChoiceSamplesPerBinInstance = new samplesPerBin(SquareRootChoiceInstance.getNumberOfBins(), statisticalInstance.getMin(), statisticalInstance.getMax(), exampleData, statisticalInstance.getNumberOfSamples());
        samplesPerBin RiceRuleSamplesPerBinInstance = new samplesPerBin(RiceRuleInstance.getNumberOfBins(), statisticalInstance.getMin(), statisticalInstance.getMax(), exampleData, statisticalInstance.getNumberOfSamples());      
       
        SturgesSamplesPerBinInstance.calculateSamplesInEachBin();
        SquareRootChoiceSamplesPerBinInstance.calculateSamplesInEachBin();
        RiceRuleSamplesPerBinInstance.calculateSamplesInEachBin();
        
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////        
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////   
        
        // histogramNormalisation
        histogramNormalisation SturgesHistogramNormalisationInstance = new histogramNormalisation(SturgesSamplesPerBinInstance.getWidth(), 
                                                                                                  SturgesInstance.getNumberOfBins(),
                                                                                                  SturgesSamplesPerBinInstance.getSamplesInEachBin(), 
                                                                                                  statisticalInstance.getNumberOfSamples());
        histogramNormalisation SquareRootChoiceHistogramNormalisationInstance = new histogramNormalisation(SquareRootChoiceSamplesPerBinInstance.getWidth(), 
                                                                                                           SquareRootChoiceInstance.getNumberOfBins(),
                                                                                                           SquareRootChoiceSamplesPerBinInstance.getSamplesInEachBin(), 
                                                                                                           statisticalInstance.getNumberOfSamples());
        histogramNormalisation RiceRuleHistogramNormalisationInstance = new histogramNormalisation(RiceRuleSamplesPerBinInstance.getWidth(), 
                                                                                                   RiceRuleInstance.getNumberOfBins(),
                                                                                                   RiceRuleSamplesPerBinInstance.getSamplesInEachBin(), 
                                                                                                   statisticalInstance.getNumberOfSamples());
        
        SturgesHistogramNormalisationInstance.calculateNormalisedFrequency();
        SquareRootChoiceHistogramNormalisationInstance.calculateNormalisedFrequency();
        RiceRuleHistogramNormalisationInstance.calculateNormalisedFrequency();
        
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////        
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        //data fitting
        dataFitting SturgesDataFittingInstance = new dataFitting(SturgesInstance.getNumberOfBins(),
                                                                    SturgesHistogramNormalisationInstance.getNormalisedSamplesInEachBin(),
                                                                    SturgesSamplesPerBinInstance.getWidth(),
                                                                    statisticalInstance.getMin());
        SturgesDataFittingInstance.calculateFitting();
        SturgesPDF = SturgesDataFittingInstance.getPDF();
        SturgesDatafittingParameters = SturgesDataFittingInstance.getDataFittingParameters();
        
        dataFitting SquareRootChoiceDataFittingInstance = new dataFitting(SquareRootChoiceInstance.getNumberOfBins(),
                                                                    SquareRootChoiceHistogramNormalisationInstance.getNormalisedSamplesInEachBin(),
                                                                    SquareRootChoiceSamplesPerBinInstance.getWidth(),
                                                                    statisticalInstance.getMin());
        SquareRootChoiceDataFittingInstance.calculateFitting();
        SquareRootChoicePDF = SquareRootChoiceDataFittingInstance.getPDF();
        SquareRootChoiceDatafittingParameters = SquareRootChoiceDataFittingInstance.getDataFittingParameters();
        
        dataFitting RiceRuleDataFittingInstance = new dataFitting(RiceRuleInstance.getNumberOfBins(),
                                                                    RiceRuleHistogramNormalisationInstance.getNormalisedSamplesInEachBin(),
                                                                    RiceRuleSamplesPerBinInstance.getWidth(),
                                                                    statisticalInstance.getMin());
        RiceRuleDataFittingInstance.calculateFitting();
        RiceRulePDF = RiceRuleDataFittingInstance.getPDF();
        RiceRuleDatafittingParameters = RiceRuleDataFittingInstance.getDataFittingParameters();
        
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////        
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        // store values and pass to controller, specifally needed for 'plotAction'
        numberOfBins[0] = SturgesInstance.getNumberOfBins();
        numberOfBins[1] = SquareRootChoiceInstance.getNumberOfBins();
        numberOfBins[2] = RiceRuleInstance.getNumberOfBins();
        
        numberOfSamples = statisticalInstance.getNumberOfSamples();
        
        min = statisticalInstance.getMin();
        max = statisticalInstance.getMax();
        mean = statisticalInstance.getMean();
        variance = statisticalInstance.getVariance();
        standardDeviation = statisticalInstance.getStandardDeviation();
        
        width[0] = SturgesSamplesPerBinInstance.getWidth();
        width[1] = SquareRootChoiceSamplesPerBinInstance.getWidth();
        width[2] = RiceRuleSamplesPerBinInstance.getWidth();   
                
        SturgesNormalisedSamplesInEachBin = SturgesHistogramNormalisationInstance.getNormalisedSamplesInEachBin();
        SquareRootChoiceNormalisedSamplesInEachBin = SquareRootChoiceHistogramNormalisationInstance.getNormalisedSamplesInEachBin();
        RiceRuleNormalisedSamplesInEachBin = RiceRuleHistogramNormalisationInstance.getNormalisedSamplesInEachBin();
                
    }
    
}
