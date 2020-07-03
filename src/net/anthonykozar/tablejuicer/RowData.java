/*	RowData.java
	
	Wraps the data for a single table row as an array of 
	StringProperties.
	
	This file is part of Table Juicer.
	Copyright © 2020 by Anthony M. Kozar Jr.
	Licensed under the GNU General Public License, v.2.
	
 */

package net.anthonykozar.tablejuicer;

import java.util.ArrayList;

import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;

public class RowData
{
	private ArrayList<StringProperty> rowvalues;
	
	public RowData(String ... values) {
		rowvalues = new ArrayList<StringProperty>();
		
		for (String val : values) {
			StringProperty s = new SimpleStringProperty(val);
			rowvalues.add(s);
		}
	}
	
	public int size() {
		return rowvalues.size();
	}
	
	public StringProperty getIndex(int index) {
		return rowvalues.get(index);
	}
	
	public String getIndexValue(int index) {
		return rowvalues.get(index).getValue();
	}
	
}
