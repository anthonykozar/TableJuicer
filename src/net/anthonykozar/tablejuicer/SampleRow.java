/*	SampleRow.java
	
	Wraps the data for a single table row of a sample table.
	Used for early testing.  Obsolete?
	
	This file is part of Table Juicer.
	Copyright © 2020 by Anthony M. Kozar Jr.
	Licensed under the GNU General Public License, v.2.
	
 */

package net.anthonykozar.tablejuicer;

import javafx.beans.property.*;

public class SampleRow
{
	private final StringProperty _name;
	private final StringProperty _color;
	private final StringProperty _size;
	private final IntegerProperty _count;

	public SampleRow(String name, String color, String size, int count) {
		_name = new SimpleStringProperty(name);
		_color = new SimpleStringProperty(color);
		_size = new SimpleStringProperty(size);
		_count = new SimpleIntegerProperty(count);
	}
	
	public StringProperty nameProperty() { return _name; }
	public StringProperty colorProperty() { return _color; }
	public StringProperty sizeProperty() { return _size; }
	public IntegerProperty countProperty() { return _count; }
	
}
