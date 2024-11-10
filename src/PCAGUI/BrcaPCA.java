package PCAGUI;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.PrincipalComponents;
import weka.gui.visualize.PlotData2D;
import weka.gui.visualize.VisualizePanel;

import javax.swing.JFrame;
import java.awt.BorderLayout;
public class BrcaPCA
{
	public static void main(String[] args) throws Exception 
	{
		try {
            // Load the dataset
            DataSource source = new DataSource("path/to/your/data.csv");
            Instances data = source.getDataSet();

            // Set the class index to the last attribute (used for color coding)
            data.setClassIndex(data.numAttributes() - 1);

            // Configure and apply the PCA filter
            PrincipalComponents pca = new PrincipalComponents();
            pca.setCenterData(true);   // Center (normalize) the data
            pca.setMaximumAttributes(2);  // Limit to 2 principal components for visualization

            // Set the input format and transform the data
            pca.setInputFormat(data);
            Instances transformedData = Filter.useFilter(data, pca);

            // Create a PlotData2D object for visualization
            PlotData2D plotData = new PlotData2D(transformedData);
            plotData.setPlotName("PCA Plot");
            plotData.addInstanceNumberAttribute(); // Adds instance numbers
            
            // Specify the class index for color coding (already set in transformedData)

            // Set up the VisualizePanel and add the PlotData2D
            VisualizePanel vp = new VisualizePanel();
            vp.setName("PCA Visualization");
            vp.addPlot(plotData);

            // Display the visualization in a JFrame
            JFrame frame = new JFrame("PCA Scatter Plot");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLayout(new BorderLayout());
            frame.add(vp, BorderLayout.CENTER);
            frame.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
	}

}
