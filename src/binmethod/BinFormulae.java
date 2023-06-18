/*
 * Abstract parent class for 3 child classes with unique implementation 
 */
package binmethod;
/**
 * @author Satheyaseelan Sugieethan
 */
public abstract class BinFormulae {
    // member variable
    private int numberOfBins;
    
    // constructor 
    public BinFormulae(){};
    
    // gets and sets
    public int getNumberOfBins() {return numberOfBins;}
    public void setNumberOfBins(int _numberOfBins){numberOfBins = _numberOfBins;}
    
    // abstract method
    public abstract void calculateNumberOfBins();
}