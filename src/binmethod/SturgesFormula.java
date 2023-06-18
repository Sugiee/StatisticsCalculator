/*
 * Sturges Formula method to calculating number of bins needed
 */
package binmethod;

import java.util.List;
/**
 * @author Satheyaseelan Sugieethan
 */
public class SturgesFormula extends BinFormulae {
    // member variable
    private final int sizeOfExampleData;
    
    // constructor
    public SturgesFormula(List<Double> _exampleData){
                            sizeOfExampleData = _exampleData.size();          
    }
    
    // method to calcaulte number of bins and store using set
    @Override
    public void calculateNumberOfBins(){
        setNumberOfBins((int)(Math.ceil(3.3*(Math.log10(sizeOfExampleData))+1)));
    }
}   