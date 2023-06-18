package Unit_test_random;

/*
 Client code to test the binmethod package
*/

import java.util.*;
// the bin rule formulae must be implemented in binmethod package
import binmethod.*;
import inputTextFile.textFileReader;
import java.io.*;


/**
 *
 * @author sugie
 */
public class Unit_test_textFileInput {
    public static void main(String[] args) throws FileNotFoundException {
        
        // Create array of data
        textFileReader textFileReaderInstace = new textFileReader("C:\\Users\\sugie\\OneDrive - The University of Nottingham\\2022-2023\\EEEE3084 Scalable Cross-Platform Software Design\\Coursework 3 – JAVA and GUI Topic\\histogramProject\\test\\measurementData.txt");
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
        System.out.printf("By Rice Rule Formula: %d \n", RiceRuleInstance.getNumberOfBins());
    
    }
}
