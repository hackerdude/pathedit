
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

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionListener;

import com.hackerdude.devtools.pathedit.PathEditFile;
import com.hackerdude.devtools.pathedit.PathEditList;

public class VariableListPanel extends JPanel {
  BorderLayout borderLayout1 = new BorderLayout();
  JTable jTable1 = new JTable();
  PathEditFile scriptFile = new PathEditFile();
  public VariableListTableModel model = new VariableListTableModel(scriptFile);
  JScrollPane jScrollPane1 = new JScrollPane();
//  jTabbedPane1.add(jSplitPane1, "Current Environment");

  public VariableListPanel() {
    this(null);
  }

  public VariableListPanel(String fileName) {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }


  private void jbInit() throws Exception {
    this.setLayout(borderLayout1);
    jTable1.setModel(model);
    this.add(jScrollPane1, BorderLayout.CENTER);
    jScrollPane1.getViewport().add(jTable1, null);
  }

  public PathEditList getCurrent() {
    return model.getPathEditLineAt(jTable1.getSelectedRow()).getPath();
  }

  public void addSelectionListener(ListSelectionListener x) {
      jTable1.getSelectionModel().addListSelectionListener(x);
  }

  public void removeListSelectionListener( ListSelectionListener x ) {
      jTable1.getSelectionModel().removeListSelectionListener(x);
  }


}