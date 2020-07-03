/*	RowValueFactory.java
	
	A cell value factory for the TableView class that returns
	the property in a specified column of a RowData object.
	
	This file is part of Table Juicer.
	Copyright © 2020 by Anthony M. Kozar Jr.
	Licensed under the GNU General Public License, v.2.
	
 */

package net.anthonykozar.tablejuicer;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.beans.value.ObservableValue;
import javafx.util.Callback;

public class RowValueFactory 
	implements Callback<TableColumn.CellDataFeatures<RowData, String>, ObservableValue<String>>
{
	private int column;
	
	/**  Constructs a cell value factory that will return the property in 
	 *   a specified column of a RowData object.
	 *   
	 * @param columnidx  The column index of the property to return.
	 */
	public RowValueFactory(int columnidx) {
		column = columnidx;
	}
	
	@Override
	public ObservableValue<String> call(CellDataFeatures<RowData, String> param) {
		return param.getValue().getIndex(column);
	}

}
