
/**
 * Title:        PathEdit - A Classpath Editor written in Java<p>
 * Description:  This is a tiny program that tries to make it easy for the developer
 * to take a path specification and reorganize it.<p>
 * Copyright:    Copyright (c) David Martinez<p>
 * Company:      <p>
 * @author David Martinez
 * @version 1.
 */
package com.hackerdude.devtools.pathedit.ui;
import com.hackerdude.devtools.pathedit.*;

import javax.swing.table.*;
import javax.swing.event.*;
import javax.swing.*;

public class PathListTableModel extends AbstractTableModel {

  PathEditList paths;
  ImageIcon noFile = new ImageIcon(PathEditFrame.class.getResource("Stop.gif"));
  ImageIcon yesFile = new ImageIcon(PathEditFrame.class.getResource("Check.gif"));

  public static final String[] coltitles = { "Item", "Found" };

  public PathListTableModel() {
    this(new PathEditList(System.getProperty("java.class.path")));
  }

  public PathListTableModel(PathEditList paths) {
    this.paths = paths;
  }

  public void moveUp(int row) {
    PathEditItem item = paths.get(row);
    paths.moveUp(item);
    fireTableDataChanged();
  }

  public void moveDown(int row) {
    PathEditItem item = paths.get(row);
    paths.moveDown(item);
    fireTableDataChanged();
  }

  public void remove(int row) {
    paths.remove(row);
    fireTableDataChanged();
  }

  public int getColumnCount() {
    return coltitles.length;
  }

  public boolean isCellEditable(int row, int column) {
    return (column == 0);

  }

  public void setValueAt(Object obj, int row, int column ) {
    PathEditItem item = paths.get(row);
    if ( item == null ) return;
    if ( column == 0 ) {
      PathEditItem newitem = new PathEditItem((String)obj);
      paths.replace(item, newitem);
    }

  }

  public void addPathItem(PathEditItem item) {
     paths.add(item);
     fireTableDataChanged();
  }

  public PathEditItem getItem(int row) {
    return paths.get(row);
  }

  public int getRowCount() {
    return paths.size();
  }

  public int findColumn(String columnName) {
    int n = -1;
    for ( int i =0; i<coltitles.length; i++ ) {
      if ( coltitles[i].equals(columnName) ) n = i;
    }
    return n;
  }

  public String getColumnName(int column) {
    return coltitles[column];
  }

  public Class getColumnClass(int column) {
    if ( column == 1 || column == 2 ) { return yesFile.getClass(); }
    return java.lang.String.class;
  }

  public void setData(PathEditList list ) {
    this.paths = list;
    fireTableChanged(new TableModelEvent(this));
  }

  public Object getValueAt(int row, int col) {
    PathEditItem item = paths.get(row);
    Object result = null;
    if ( col == 1 ) { if ( item.exists() ) { return yesFile; } else { return noFile; } }
    if ( col == 2 ) { result = new Boolean(item.exists()); } // TODO: Fix
    if ( result == null ) { result = item.toString(); }
    return result;

/*        TableModel dataModel = new AbstractTableModel() {
            public int getColumnCount() { return names.length; }
            public int getRowCount() { return data.length;}
            public Object getValueAt(int row, int col) {return data[row][col];}
            public String getColumnName(int column) {return names[column];}
            public Class getColumnClass(int c) {return getValueAt(0, c).getClass();}
	    public boolean isCellEditable(int row, int col) {return col != 5;}
            public void setValueAt(Object aValue, int row, int column) { data[row][column] = aValue; }
         };*/

  }



}