/*	ColumnModel.java
	
	The metadata for a single table column.
	
	This file is part of Table Juicer.
	Copyright © 2020 by Anthony M. Kozar Jr.
	Licensed under the GNU General Public License, v.2.
	
 */

package net.anthonykozar.tablejuicer;

public class ColumnModel
{
	public static final int UNNUMBERED = -1;
	
	private String	headerText;			// text to display in the column header
	private String	propertyName;		// the data model property for this column
	private int		columnnum;			// the index of this column in the row data
	
	public ColumnModel(String property, String colHeader) {
		columnnum = UNNUMBERED;
		propertyName = property;
		headerText = colHeader;
	}

	public ColumnModel(int colidx, String colHeader) {
		columnnum = colidx;
		propertyName = "";
		headerText = colHeader;
	}

	public String getPropertyName() {
		return propertyName;
	}
	
	public void setPropertyName(String property) {
		propertyName = property;
	}

	public String getHeaderText() {
		return headerText;
	}
	
	public void setHeaderText(String header) {
		headerText = header;
	}
	
	public int getColumnIndex() {
		return columnnum;
	}
	
	public void setColumnIndex(int colidx) {
		columnnum = colidx;
	}
	
}
