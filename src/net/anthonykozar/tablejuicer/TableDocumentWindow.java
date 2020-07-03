/*	TableDocumentWindow.java
	
	Displays a table document in a window with a menu bar, toolbar,
	and other controls.
	
	This file is part of Table Juicer.
	Copyright © 2020 by Anthony M. Kozar Jr.
	Licensed under the GNU General Public License, v.2.
	
 */

package net.anthonykozar.tablejuicer;

import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class TableDocumentWindow extends Stage
{
	private Scene documentScene;
	private MenuBar mainMenus;
	private ToolBar topToolBar;
	private TableView tableView;
	private TableDataModel data;
	
	private String winTitle;
	
	/** Global handler for unimplemented main menu items */
	private EventHandler<ActionEvent> unimplementedCmdHandler = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent ev) {
			String menuname = ((MenuItem)ev.getTarget()).getText();
			// TODO: respond to menu command
			
			/* TODO: This is a clumsy way of handling menu commands.  Alternatives:
			
			     (1) Create separate OnAction handlers for each command.  This will 
			         allow reuse (?) of the same handlers for toolbars or other controls
			         and allow handler code to be organized by whether it is app-wide, 
			         doc-specific, selection-specific, etc.
			     (2) Set menu item 'id' properties with unique identifiers and retrieve
			         them here in the handler.
			     (3) Use set/getUserData() methods on menu items?
			 */
			
		}
	};

	
	public TableDocumentWindow(String wintitle) {
		super();
		
		winTitle = wintitle;
		this.setTitle(winTitle);
		
		createWindowControls();
	}

	public TableDocumentWindow(String wintitle, TableDataModel tabledata) {
		super();
		
		winTitle = wintitle;
		this.setTitle(winTitle);
		data = tabledata;
		
		createWindowControls();
		TableJuicerApp.bindTableViewToModel(tableView, tabledata);
	}

	private void createWindowControls() {
		// make a scene with a vertical layout and populate it with controls
		VBox rootlayout = new VBox();
		Scene scene = new Scene(rootlayout);
		
		mainMenus = createMainMenus();
		topToolBar = new ToolBar();
		tableView = new TableView();
		rootlayout.getChildren().addAll(mainMenus, topToolBar, tableView);
		
		this.setScene(scene);
		this.sizeToScene();
		
	}
	
	private MenuBar createMainMenus() {
		MenuBar menus = new MenuBar();
		Menu filemenu = new Menu("File");
		Menu editmenu = new Menu("Edit");
		Menu helpmenu = new Menu("Help");
		
		filemenu.getItems().addAll(
				createMenuItem(TableJuicerApp.NewCmdHandler, "New Table", "N"),
				createMenuItem(TableJuicerApp.OpenCmdHandler, "Open...", "O"),
				createMenuItem(unimplementedCmdHandler, "Close", "W"),
				createMenuItem(unimplementedCmdHandler, "Save", "S", true),
				createMenuItem(unimplementedCmdHandler, "Save As...", "shift+S", true));

		editmenu.getItems().addAll(
				createMenuItem(unimplementedCmdHandler, "Undo", "Z", true),
				createMenuItem(unimplementedCmdHandler, "Redo", "shift+Z", true),
				new SeparatorMenuItem(),
				createMenuItem(unimplementedCmdHandler, "Cut", "X"),
				createMenuItem(unimplementedCmdHandler, "Copy", "C"),
				createMenuItem(unimplementedCmdHandler, "Paste", "V"),
				new SeparatorMenuItem(),
				createMenuItem(unimplementedCmdHandler, "Select All", "A"));
		
		helpmenu.getItems().addAll(createMenuItem(TableJuicerApp.AboutCmdHandler, "About..."));
		
		menus.getMenus().addAll(filemenu, editmenu, helpmenu);
		return menus;
	}
	
	private MenuItem createMenuItem(EventHandler<ActionEvent> handler, String text) {
		return createMenuItem(handler, text, null, false);
	}
	
	private MenuItem createMenuItem(EventHandler<ActionEvent> handler, String text, String shortcut) {
		return createMenuItem(handler, text, shortcut, false);
	}

	private MenuItem createMenuItem(EventHandler<ActionEvent> handler, String text, String shortcut, boolean disabled) {
		MenuItem item = new MenuItem(text);
		if (shortcut != null) {
			item.setAccelerator(KeyCombination.keyCombination("shortcut+" + shortcut));
		}
		item.setOnAction(handler);
		item.setDisable(disabled);
		
		return item;
	}
}
