/**
 * Title:        PathEdit - A Classpath Editor written in Java<p>
 * Description:  This is a tiny program that tries to make it easy for the developer
 * to take a path specification and reorganize it.<p>
 * Copyright:    Copyright (c) David Martinez<p>
 * Company:      <p>
 * @author David Martinez
 * @version 1.0
 */
package com.hackerdude.devtools.pathedit.ui;

import javax.swing.table.*;
import java.io.*;
import com.hackerdude.devtools.pathedit.*;


public class VariableListTableModel extends AbstractTableModel {

  protected PathEditFile scriptFile;

  public VariableListTableModel() {
    this(new PathEditFile());
  }

  public VariableListTableModel(PathEditFile file) {
    this.scriptFile = file;

  }

  public int getColumnCount() {
    return 1;
  }

  public Class getColumnClass(int column) {
    return String.class;
  }

  public String getColumnName(int column) {
    return "Variables";
  }

  public Object getValueAt(int row, int col) {
    return scriptFile.get(row).getVarName();
  }

  public boolean isCellEditable(int row, int col) { return true; }

  public PathEditLine getPathEditLineAt( int row ) {
    return scriptFile.get(row);
  }

  public void setValueAt(Object obj, int row, int col) {
    PathEditLine currentItem = scriptFile.get(row);
    if ( currentItem != null ) {
      currentItem.setVarName(obj.toString());
    }
  };

  public int getRowCount() {
    return scriptFile.size();
  }

  public synchronized void setFile( String fileName ) throws IOException {
      this.scriptFile = new PathEditFile(fileName);
      fireTableDataChanged();
  }

}