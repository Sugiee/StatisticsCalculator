/*
 * Controller class used to communicate betweeen the 'view' (aka the GUI) and to the model
 */
package GUI;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 * @author Satheyaseelan Sugieethan
 */
public class GUIFXMLController implements Initializable {
    
    // GUI variables from scene builder with fx:id injected by FXMLLoader
    @FXML
    private LineChart<?, ?> lineChart;
    @FXML 
    private BarChart<?, ?> barChart;
    @FXML
    private CategoryAxis x; 
    @FXML
    private NumberAxis y; 
    @FXML
    private StackPane stackpane;      
    @FXML
    private Button chooseFile;    
    @FXML
    private Button normaliseAndPlot;    
    @FXML
    private Button Save;    
    @FXML
    private ChoiceBox<String> choiceBox;
    @FXML
    private Text numberOfDataText;
    @FXML
    private Text minimumValueText;
    @FXML
    private Text maximumValueText;
    @FXML
    private Text meanText;
    @FXML
    private Text varianceText;
    @FXML
    private Text standardDeviationText;
    @FXML
    private Text normalisationFactorText;
    @FXML
    private Text guassianMeanText;
    @FXML
    private Text sigmaText;
    
    // private variables
    private String[] BinMethods = {"Sturges Formula method",
                                   "Square Root Choice method",
                                   "Rice Rule method"};
    ArrayList<Double> dataTextFile;
    
    // constructor
    public GUIFXMLController(){}
    
    // setters and getters
    public ArrayList<Double> getExampleData() {return dataTextFile;}
    public void setExampleData(ArrayList<Double> _dataTextFile){dataTextFile = _dataTextFile;}

    @FXML   // allows user to choose input text file
    void chooseFileAction(ActionEvent event) throws FileNotFoundException {
        
        String directory; // users file path
        
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Upload File Path");
                                                    // filter so only text file can be selected
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TEXT", "*.txt"));
            
        File Directory = fileChooser.showOpenDialog(null);

            if (Directory != null) { // if file isn't empty selected file is copied into an array 
                
                directory = Directory.getAbsolutePath();

                Scanner s = new Scanner(new File(directory)); 
                
                dataTextFile = new ArrayList<Double>(); 

                while (s.hasNextDouble()){ // loop till no more lines of data to copy 
                    
                    dataTextFile.add(s.nextDouble());
                    
                }

                System.out.println(dataTextFile);
                
                setExampleData(dataTextFile);
                
            } else  { // if file invalid 'error' is printed
                
                System.out.println("error"); // or something else
                
            }
            
        }
        
    @FXML // method uses model to calcualte and output to gui required values 
    void plotAction(ActionEvent event) {

        GUIModel GUIModelInstance = new GUIModel(dataTextFile); // instantiate object from model class

        GUIModelInstance.calculateAll(); // function to calculate all values in model
        
        // if the user selects 'Sturges' as bin method choice respective values used
        if ((choiceBox.getValue()) == "Sturges Formula method") {
                // getting data from model
            ArrayList<Double> xAxisValues = new ArrayList<Double>();
            int [] numberOfBins = GUIModelInstance.getNumberOfbins();
            double min = GUIModelInstance.getMin();    
            double max = GUIModelInstance.getMax();
            double mean = GUIModelInstance.getMean();
            double variance= GUIModelInstance.getVariance();
            double standardDeviation = GUIModelInstance.getStandardDeviation();
            double [] SturgesDataFittingParameters = GUIModelInstance.getSturgesDatafittingParameters();
            double [] width = GUIModelInstance.getWidth();
            double [] SturgesNormalisedSamplesInEachBin = GUIModelInstance.getSturgesNormalisedSamplesInEachBin();
            double [] SturgesPDF = GUIModelInstance.getSturgesPDF();
            double numberOfData = GUIModelInstance.getNumberOfData();
            String temp1, temp2;
                                            // setting the text feild values on gui
            temp1 = "Number of data: ";     // 'concat' used so that text feild can be updated and dont need two seperate text feilds
            temp2 = temp1.concat(String.valueOf(numberOfData));
            numberOfDataText.setText(temp2);
            
            temp1 = "Minimum value (um): ";
            temp2 = temp1.concat(String.valueOf(min*1000000));
            minimumValueText.setText(temp2);
            
            temp1 = "Maximum value (um): ";
            temp2 = temp1.concat(String.valueOf(max*1000000));
            maximumValueText.setText(temp2);
            
            temp1 = "Mean (um): ";
            temp2 = temp1.concat(String.valueOf(mean*1000000));
            meanText.setText(temp2);
            
            temp1 = "Variance (um^2): ";
            temp2 = temp1.concat(String.valueOf((variance*1000000)*1000000));
            varianceText.setText(temp2);
            
            temp1 = "Standard Deviation (um): ";
            temp2 = temp1.concat(String.valueOf(standardDeviation*1000000));
            standardDeviationText.setText(temp2); 
                
            temp1 = "Normalisation Factor: ";
            temp2 = temp1.concat(String.valueOf(SturgesDataFittingParameters[0]));
            normalisationFactorText.setText(temp2);
            
            temp1 = "Mean (um): ";
            temp2 = temp1.concat(String.valueOf(SturgesDataFittingParameters[1]*1000000));
            guassianMeanText.setText(temp2);
            
            temp1 = "Sigma (um): ";
            temp2 = temp1.concat(String.valueOf(SturgesDataFittingParameters[2]*1000000));
            sigmaText.setText(temp2);
            
            barChart.getData().clear(); // delete plots on graph from previous bin method
            lineChart.getData().clear();

            XYChart.Series series1 = new XYChart.Series(); // creating blank bar chart
            XYChart.Series series2 = new XYChart.Series(); // creating blank line chart 

            barChart.setLegendVisible(false); // legend turned off as not more than 1 type of data being plotted
            lineChart.setLegendVisible(false);
                      
            for(int i = 0; i < numberOfBins[0] ; ++i) { // populating bar/line chart with x and y values

                xAxisValues.add(round(((((min + (width[0] * i))+(min + (width[0]*(i+1)))) / 2)*100000), 2));
                    
                series1.getData().add(new XYChart.Data(String.valueOf(xAxisValues.get(i)), SturgesNormalisedSamplesInEachBin[i]));
                series2.getData().add(new XYChart.Data(String.valueOf(xAxisValues.get(i)), SturgesPDF[i]));
                
                System.out.println(SturgesPDF[i]);
                
            }

            barChart.getData().addAll(series1); // sending stored values in object to bar chart
            lineChart.getData().addAll(series2); // sending stored values in object to line chart
            
        }
        // if the user selects 'Square Root' as bin method choice respective values used
        else if ((choiceBox.getValue()) == "Square Root Choice method") {
                // getting data from model
            ArrayList<Double> xAxisValues = new ArrayList<Double>();
            int [] numberOfBins = GUIModelInstance.getNumberOfbins();
            double min = GUIModelInstance.getMin();           
            double max = GUIModelInstance.getMax();
            double mean = GUIModelInstance.getMean();
            double variance= GUIModelInstance.getVariance();
            double standardDeviation = GUIModelInstance.getStandardDeviation();
            double [] SquareRootChoiceDataFittingParameters = GUIModelInstance.getSquareRootChoiceDatafittingParameters();
            double[] width = GUIModelInstance.getWidth();
            double[] SquareRootChoiceNormalisedSamplesInEachBin = GUIModelInstance.getSquareRootChoiceNormalisedSamplesInEachBin();
            double[] SquareRootChoicePDF = GUIModelInstance.getSquareRootChoicePDF();
            double numberOfData = GUIModelInstance.getNumberOfData();
            String temp1, temp2;
                                            //setting the text feild values on gui
            temp1 = "Number of data: ";     // 'concat' used so that text feild can be updated and dont need two seperate text feilds
            temp2 = temp1.concat(String.valueOf(numberOfData));
            numberOfDataText.setText(temp2);
            
            temp1 = "Minimum value (um): ";
            temp2 = temp1.concat(String.valueOf(min*1000000));
            minimumValueText.setText(temp2);
            
            temp1 = "Maximum value (um): ";
            temp2 = temp1.concat(String.valueOf(max*1000000));
            maximumValueText.setText(temp2);
            
            temp1 = "Mean (um): ";
            temp2 = temp1.concat(String.valueOf(mean*1000000));
            meanText.setText(temp2);
            
            temp1 = "Variance (um^2): ";
            temp2 = temp1.concat(String.valueOf((variance*1000000)*1000000));
            varianceText.setText(temp2);
            
            temp1 = "Standard Deviation (um): ";
            temp2 = temp1.concat(String.valueOf(standardDeviation*1000000));
            standardDeviationText.setText(temp2); 
            
            temp1 = "Normalisation Factor: ";
            temp2 = temp1.concat(String.valueOf(SquareRootChoiceDataFittingParameters[0]));
            normalisationFactorText.setText(temp2);
            
            temp1 = "Mean (um): ";
            temp2 = temp1.concat(String.valueOf(SquareRootChoiceDataFittingParameters[1]*1000000));
            guassianMeanText.setText(temp2);
            
            temp1 = "Sigma (um): ";
            temp2 = temp1.concat(String.valueOf(SquareRootChoiceDataFittingParameters[2]*1000000));
            sigmaText.setText(temp2);
            
            barChart.getData().clear(); // delete plots on graph from previous bin method
            lineChart.getData().clear();

            XYChart.Series series1 = new XYChart.Series();  // creating blank bar chart
            XYChart.Series series2 = new XYChart.Series();  // creating blank line chart
            
            barChart.setLegendVisible(false);   // legend turned off as not more than 1 type of data being plotted
            lineChart.setLegendVisible(false);
      
            for(int i = 0; i < numberOfBins[1] ; ++i) { // populating bar/line chart with x and y values

                xAxisValues.add(round(((((min + (width[1] * i))+(min + (width[0]*(i+1)))) / 2)*100000), 2));

                series1.getData().add(new XYChart.Data(String.valueOf(xAxisValues.get(i)), SquareRootChoiceNormalisedSamplesInEachBin[i]));
                series2.getData().add(new XYChart.Data(String.valueOf(xAxisValues.get(i)), SquareRootChoicePDF[i]));
                
                System.out.println(SquareRootChoicePDF[i]);
                
            }

            barChart.getData().addAll(series1); // sending stored values in object to bar chart
            lineChart.getData().addAll(series2); // sending stored values in object to line chart

        }
        // if the user selects 'Rice Rule' as bin method choice respective values used
        else if ((choiceBox.getValue()) == "Rice Rule method") {
                // getting data from model
            ArrayList<Double> xAxisValues = new ArrayList<Double>();
            int [] numberOfBins = GUIModelInstance.getNumberOfbins();
            double min = GUIModelInstance.getMin();     
            double max = GUIModelInstance.getMax();
            double mean = GUIModelInstance.getMean();
            double variance= GUIModelInstance.getVariance();
            double standardDeviation = GUIModelInstance.getStandardDeviation();
            double [] RiceRuleDataFittingParameters = GUIModelInstance.getRiceRuleDatafittingParameters();
            double[] width = GUIModelInstance.getWidth();
            double[] RiceRuleNormalisedSamplesInEachBin = GUIModelInstance.getRiceRuleNormalisedSamplesInEachBin();
            double[] RiceRulePDF = GUIModelInstance.getRiceRulePDF();
            double numberOfData = GUIModelInstance.getNumberOfData();
            String temp1, temp2;
                                            //setting the text feild values on gui
            temp1 = "Number of data: ";     // 'concat' used so that text feild can be updated and dont need two seperate text feilds
            temp2 = temp1.concat(String.valueOf(numberOfData));
            numberOfDataText.setText(temp2);
            
            temp1 = "Minimum value (um): ";
            temp2 = temp1.concat(String.valueOf(min*1000000));
            minimumValueText.setText(temp2);
            
            temp1 = "Maximum value (um): ";
            temp2 = temp1.concat(String.valueOf(max*1000000));
            maximumValueText.setText(temp2);
            
            temp1 = "Mean (um): ";
            temp2 = temp1.concat(String.valueOf(mean*1000000));
            meanText.setText(temp2);
            
            temp1 = "Variance (um^2): ";
            temp2 = temp1.concat(String.valueOf((variance*1000000)*1000000));
            varianceText.setText(temp2);
            
            temp1 = "Standard Deviation (um): ";
            temp2 = temp1.concat(String.valueOf(standardDeviation*1000000));
            standardDeviationText.setText(temp2); 
            
            temp1 = "Normalisation Factor: ";
            temp2 = temp1.concat(String.valueOf(RiceRuleDataFittingParameters[0]));
            normalisationFactorText.setText(temp2);
            
            temp1 = "Mean (um): ";
            temp2 = temp1.concat(String.valueOf(RiceRuleDataFittingParameters[1]*1000000));
            guassianMeanText.setText(temp2);
            
            temp1 = "Sigma (um): ";
            temp2 = temp1.concat(String.valueOf(RiceRuleDataFittingParameters[2]*1000000));
            sigmaText.setText(temp2);
            
            barChart.getData().clear(); // delete plots on graph from previous bin method
            lineChart.getData().clear();
                        
            XYChart.Series series1 = new XYChart.Series();  // creating blank bar chart
            XYChart.Series series2 = new XYChart.Series();  // creating blank line chart

            barChart.setLegendVisible(false);   // legend turned off as not more than 1 type of data being plotted
            lineChart.setLegendVisible(false);
            
            for(int i = 0; i < numberOfBins[2] ; ++i) { // populating bar/line chart with x and y values

                xAxisValues.add(round(((((min + (width[2] * i))+(min + (width[0]*(i+1)))) / 2)*100000), 2));

                series1.getData().add(new XYChart.Data(String.valueOf(xAxisValues.get(i)), RiceRuleNormalisedSamplesInEachBin[i]));
                
                series2.getData().add(new XYChart.Data(String.valueOf(xAxisValues.get(i)), RiceRulePDF[i]));
                
                System.out.println(RiceRulePDF[i]);
                
            }

            barChart.getData().addAll(series1); // sending stored values in object to bar chart
            lineChart.getData().addAll(series2); // sending stored values in object to line chart

        }
    }
    
    @FXML // method to save data on graph as a png file
    void saveAction(MouseEvent event) throws IOException {
        
        FileChooser fileChooser = new FileChooser(); // prompt for user to select location to save file
       
        File file = fileChooser.showSaveDialog(null); 
        
        String saveDirectory = file.getAbsolutePath();
        
        saveDirectory = saveDirectory.concat(".png"); // ensures if user forgets to save as png file extension 
                                                      // file still gets saved as this adds the extension
        File fileWithPDFExtension = new File(saveDirectory);
        
        if(file != null){ // if the location to save is valid a snapshot of 'stackpane' is made and 
                          // rendered as a png file
            try {
                    
                WritableImage writableImage = stackpane.snapshot(null, null);
                
                RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                
                ImageIO.write(renderedImage, "png", fileWithPDFExtension);
                
                System.out.println("Image Saved");
                
            } catch (IOException ex) {ex.printStackTrace();}
        }
    }
    // method to get the bin method user selects on the gui 
    public void getBinMethod(ActionEvent event) {
        
         String myBinMethod = choiceBox.getValue(); // get bin method from choiceBox 
         
         System.out.println(myBinMethod);
         
    }
   
    @Override // main function that ruins at he beginning of each simulaltion
    public void initialize(URL url, ResourceBundle rb) {
       
        // sets the bin method as Sturges method as deafult at beginning of running code
        choiceBox.getItems().addAll(BinMethods);
        
        choiceBox.setValue("Sturges Formula method");
        
        choiceBox.setOnAction(this::getBinMethod);
        
    }
    
    // method to round values to ensure gui displays to a certain number of d.p
    public static double round(double value, int places) {
                // 'if' ensures correct value passed otherwise throws error
            if (places < 0) throw new IllegalArgumentException();
            
                BigDecimal bd = BigDecimal.valueOf(value);
            
                bd = bd.setScale(places, RoundingMode.HALF_UP);
            
            return bd.doubleValue();
            
    }
    
}