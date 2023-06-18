/*
 * Method to take an absoulte file path to extract data
 * Only used due to ease of use in test files and not in the final controller
 * as for controller user selects the file location. 
 */
package inputTextFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * @author Satheyaseelan Sugieethan
 */
public class textFileReader {
    // member variable
    String textFileName;
    
    // constructor taking in data
    public textFileReader(String _textFileName){textFileName = _textFileName;};
    
    // if file found the data in text file copied to array and returned via function 
    public ArrayList<Double> FileReader() throws FileNotFoundException {
        
        Scanner s = new Scanner(new File(textFileName));
        ArrayList<Double> list = new ArrayList<Double>();
        
        while (s.hasNextDouble()){ // loop till no more lines left to copy
            list.add(s.nextDouble());
        }
    
    System.out.println(list);
    
    return(list);
    }
}
