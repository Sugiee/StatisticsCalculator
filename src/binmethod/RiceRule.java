/*
 * Rice Rule method to calculating number of bins needed
 */
package binmethod;

import java.util.List;
/**
 * @author Satheyaseelan Sugieethan
 */
public class RiceRule extends BinFormulae {
    // member variable
    private final int sizeOfExampleData;
    
    // constructor taking in data
    public RiceRule( List<Double> _exampleData){
                        sizeOfExampleData = _exampleData.size();
    }
    
    // method to calcaulte number of bins and store using set
    @Override
    public void calculateNumberOfBins(){
        setNumberOfBins((int)(Math.ceil(2*(Math.cbrt(sizeOfExampleData)))));
    }
}

