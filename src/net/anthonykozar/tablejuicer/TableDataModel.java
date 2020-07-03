/*	TableDataModel.java
	
	Provides a model for all of the data in a table and its
	associated metadata.
	
	This file is part of Table Juicer.
	Copyright © 2020 by Anthony M. Kozar Jr.
	Licensed under the GNU General Public License, v.2.
	
 */

package net.anthonykozar.tablejuicer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.*;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TableDataModel
{
	final ObservableList<RowData> rows;
	final int numcols;
	final ColumnModel[] columns;
	
	/** Construct an "empty" table with the specified # of columns and a
	 *  single blank row.
	 * @param numColumns
	 */
	public TableDataModel(int numColumns) {
		numcols = numColumns;
		columns = new ColumnModel[numColumns];

		String[] blankrow = new String[numColumns];
		for (int i = 0; i < numColumns; i++) {
			columns[i] = new ColumnModel(i, "Column " + Integer.toString(i+1));
			blankrow[i] = "";
		}
		
		rows = FXCollections.observableArrayList();
		rows.add(new RowData(blankrow));
	}
	
	/** Construct a table with the given column details and no row data.
	 * @param columnModels
	 */	
	public TableDataModel(ColumnModel ... columnModels) {
		numcols = columnModels.length;
		columns = columnModels;
		rows = FXCollections.observableArrayList();
	}

	/** Creates and returns a new TableDataModel from a tab-delimited text file.
	 *  Assumes that the first line of the file is a header row and that every line
	 *  after that is a data row with the same number of columns as the header.
	 * @param filename
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static TableDataModel readTabDelimitedFile(String filename) throws FileNotFoundException, IOException {
		try (FileReader fin = new FileReader(filename)) {
			BufferedReader finbuffer = new BufferedReader(fin);
			String   line;
			String[] fields;
			
			// parse the first line for headers
			line = finbuffer.readLine();
			fields = line.split("\t");
			
			// create the table & column models
			TableDataModel data= new TableDataModel(fields.length);
			for (int i = 0; i < fields.length; i++) {
				data.columns[i] = new ColumnModel(i, fields[i]);
			}
			
			// parse each remaining line of the file into row data
			line = finbuffer.readLine();
			while (line != null) {
				fields = line.split("\t");
				data.rows.add(new RowData(fields));
				line = finbuffer.readLine();
			}
			
			return data;
		}
	}
	
	public static TableDataModel generateSampleData() {
		TableDataModel data= new TableDataModel(
				new ColumnModel(0, "Collection"), 
				new ColumnModel(1, "Color"),
				new ColumnModel(2, "Size"),
				new ColumnModel(3, "Count"));
		
		String[] collections = {"Pyramid Arcade", "Nomids", "Rainbow stash"};
		String[] colors = {"red", "green", "blue", "purple", "silver"};
		String[] sizes = {"small", "medium", "large"};
		
		for (int i = 0; i < 20; i++) {
			int a = (int)(3 * Math.random());		// choose a random collection
			int c = (int)(5 * Math.random());		// choose a random color
			int s = (int)(3 * Math.random());		// choose a random size
			int count = (int)(10 * Math.random());	// choose a random count
			
			RowData entry = new RowData(collections[a], colors[c], sizes[s], Integer.toString(count));
			data.rows.add(entry);
		}
		
		return data;
	}
}
