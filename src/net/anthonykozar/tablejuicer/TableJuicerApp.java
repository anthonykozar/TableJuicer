/*	TableJuicerApp.java
	
	Main application class for Table Juicer, a JavaFX application
	to help you "squeeze the data you want out of your tables."
	
	Project started June 28, 2020.
	
	Copyright © 2020 by Anthony M. Kozar Jr.
	Licensed under the GNU General Public License, v.2.
	
 */

package net.anthonykozar.tablejuicer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;


public class TableJuicerApp extends Application
{
	final static String AppName = "Table Juicer";
	final static String testfile = "/Volumes/iMacSystem/Users/anthony/Projects/TableJuicer/sample-data/songlist1.tab";
	private static String fileToOpen = null;
	private static Stage appStage = null;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		appStage = primaryStage;	// TODO: this is a hack so that we have a stage for FileChooser later...

		/* Because tables now use TableDocumentWindow, I don't really have another 
		   use for primaryStage at this point.  (Maybe a splashscreen?)

			TableView tableView = createSampleTable();
			
			// set the scene and display window
			Scene scene = new Scene(tableView);
			primaryStage.setTitle(AppName);
			primaryStage.setScene(scene);
			primaryStage.show();
		 */
		
		if (fileToOpen != null) {
			// open file if one was given on the command-line
			OpenDocumentFile(fileToOpen);
		}
		else {
			// otherwise, create a blank table window
			NewCmdHandler.handle(null);
		}
	}

	public static void main(String[] args) {
		// look for a filename argument
		if (args.length > 0) {
			fileToOpen = args[0];
		}
		
		launch(args);
	}

	private TableView createSampleTable() {
		// create a set of randomized data
		TableDataModel data = TableDataModel.generateSampleData();
		return tableViewFromModel(data);
	}
	
	private TableView createTableFromFile(String filename) throws FileNotFoundException, IOException {
		TableDataModel data = TableDataModel.readTabDelimitedFile(filename);
		return tableViewFromModel(data);
	}
	
	static TableView tableViewFromModel(TableDataModel data) {
		// create a table 
		TableView tableView = new TableView();
		bindTableViewToModel(tableView, data);
		
		return tableView;
	}
	
	static void bindTableViewToModel(TableView tableView, TableDataModel data) {
		// set the row data
		tableView.setItems(data.rows);
		tableView.setEditable(true);
		
		// create the needed number of columns
		for (ColumnModel colmodel : data.columns) {
			TableColumn col = new TableColumn<>(colmodel.getHeaderText());
			col.setCellValueFactory(new RowValueFactory(colmodel.getColumnIndex()));
			col.setCellFactory(TextFieldTableCell.forTableColumn());  // Note: could pass a StringConverter to handle different cell data types
			tableView.getColumns().add(col);
		}
	}

	public static void OpenDocumentFile(String filepath) {
		TableDataModel data;
		try {
			// display the file in a new window
			data = TableDataModel.readTabDelimitedFile(filepath);
			TableDocumentWindow newwindow = new TableDocumentWindow(AppName + " — " + filepath, data);
			newwindow.show();
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/* Action/Command Handlers */
	
	/** Handler for the "New Table" menu command */
	public final static EventHandler<ActionEvent> NewCmdHandler = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent ev) {
			TableDataModel data = new TableDataModel(2);
			TableDocumentWindow newwindow = new TableDocumentWindow(AppName + " — untitled", data);
			newwindow.show();			
		}
	};
	
	/** Handler for the "Open..." menu command */
	public final static EventHandler<ActionEvent> OpenCmdHandler = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent ev) {
				// let the user choose a file
				File chosenfile;
				FileChooser fchooser = new FileChooser();
				fchooser.setTitle("Open File");
				chosenfile = fchooser.showOpenDialog(appStage);
				
				if (chosenfile != null) {
					OpenDocumentFile(chosenfile.getPath());
				}
		}
	};
	
	/** Handler for the "About..." menu command */
	public final static EventHandler<ActionEvent> AboutCmdHandler = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent ev) {
			
		}
	};
	
	
}
