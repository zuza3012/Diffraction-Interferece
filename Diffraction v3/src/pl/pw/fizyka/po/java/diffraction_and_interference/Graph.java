package pl.pw.fizyka.po.java.diffraction_and_interference;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Graph extends JPanel
{
	
	Graph() throws BiffException, IOException
	{
		ClassLoader classLoader = getClass().getClassLoader();
		file = new File(classLoader.getResource("zeszyt.xls").getFile());
		

		FileInputStream f = new FileInputStream(file);
		Workbook w = Workbook.getWorkbook(f);
		Sheet s = w.getSheet("Arkusz2");
		
	    XYDataset dataset = createDataset(s);
	    JFreeChart chart = ChartFactory.createXYLineChart("title here","x", "y",dataset,PlotOrientation.VERTICAL,true, true, false);
	    ChartPanel panel = new ChartPanel(chart);

	    this.add(panel); 
	    this.setLayout(new GridLayout(1,1));
		this.setBackground(Color.RED);
	}
	
	

	private XYDataset createDataset(Sheet s) 
	  {
	    XYSeriesCollection dataset = new XYSeriesCollection();

	    XYSeries series = new XYSeries("y = x^2");
	    for(int i=1; i<39; i++)
	    {
		    series.add(Double.parseDouble(s.getCell(0,i).getContents()),Double.parseDouble(s.getCell(1,i).getContents()));
	    }
	    dataset.addSeries(series);
	    
	    return dataset;
	  }
	
	public void setFile(File file) throws BiffException, IOException
	{
		this.file = file;
		FileInputStream f = new FileInputStream(file);
		Workbook w = Workbook.getWorkbook(f);
		Sheet s = w.getSheet("Arkusz2");
		
	    XYDataset dataset = createDataset(s);
	    JFreeChart chart = ChartFactory.createXYLineChart("title here","x", "y",dataset,PlotOrientation.VERTICAL,true, true, false);
	    ChartPanel panel = new ChartPanel(chart);

	    this.removeAll();
	    this.add(panel); 
	    this.revalidate();
	    this.repaint();
	    this.setLayout(new GridLayout(1,1));
		this.setBackground(Color.RED);
	}
	File file;
}
//w tej klasie jest rysowany wykres natezenia, dane sa pobierne z Excela 2003 (.xls)

