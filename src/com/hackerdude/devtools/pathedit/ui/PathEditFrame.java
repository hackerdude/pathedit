package com.hackerdude.devtools.pathedit.ui;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class PathEditFrame extends JFrame implements Observer {
  JPanel contentPane;
  PathEditPanel patheditPanel = new PathEditPanel();
  JMenuBar menuBar1 = new JMenuBar();
  JMenu menuFile = new JMenu();
  JMenuItem menuFileExit = new JMenuItem();
  JMenu menuHelp = new JMenu();
  JMenuItem menuHelpAbout = new JMenuItem();
  JMenuItem menuFileOpen = new JMenuItem();
  JMenuItem menuSave = new JMenuItem();
  JMenu jMenu1 = new JMenu();
  JMenuItem menuMoveUp = new JMenuItem();
  JMenuItem menuMoveDown = new JMenuItem();
  JMenuItem menuRemove = new JMenuItem();
  JMenuItem menuInsert = new JMenuItem();
  JMenuItem menuProperties = new JMenuItem();

  public PathEditFrame() {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }



  //Component initialization
  private void jbInit() throws Exception  {
    contentPane = (JPanel) this.getContentPane();
    contentPane.setLayout(new BorderLayout());
    jMenu1.setMnemonic('E');
    jMenu1.setText("Edit");
    menuFile.setMnemonic('F');
    menuHelp.setMnemonic('H');
    menuMoveUp.setText("Move up");
    menuMoveUp.setAccelerator(javax.swing.KeyStroke.getKeyStroke(33, java.awt.event.KeyEvent.ALT_MASK, false));
    menuMoveUp.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        menuMoveUp_actionPerformed(e);
      }
    });
    menuMoveDown.setText("Move Down");
    menuMoveDown.setAccelerator(javax.swing.KeyStroke.getKeyStroke(34, java.awt.event.KeyEvent.ALT_MASK, false));
    menuMoveDown.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        menuMoveDown_actionPerformed(e);
      }
    });
    menuRemove.setMnemonic('R');
    menuRemove.setText("Remove Item");
    menuRemove.setAccelerator(javax.swing.KeyStroke.getKeyStroke(127, java.awt.event.KeyEvent.SHIFT_MASK, false));
    menuRemove.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        menuRemove_actionPerformed(e);
      }
    });
    menuInsert.setMnemonic('I');
    menuInsert.setText("Insert Item");
    menuInsert.setAccelerator(javax.swing.KeyStroke.getKeyStroke(155, java.awt.event.KeyEvent.SHIFT_MASK, false));
    menuInsert.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        menuInsert_actionPerformed(e);
      }
    });
    menuProperties.setMnemonic('P');
    menuProperties.setText("Properties...");
    menuProperties.setAccelerator(javax.swing.KeyStroke.getKeyStroke(10, java.awt.event.KeyEvent.ALT_MASK, false));
    menuProperties.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        menuProperties_actionPerformed(e);
      }
    });
    contentPane.add(patheditPanel, BorderLayout.CENTER);
    this.setSize(new Dimension(500, 400));
    this.setTitle("Path Editor");
    menuFileOpen.setIcon(new ImageIcon(PathEditPanel.class.getResource("Open16.gif")));
    menuFileOpen.setMnemonic('O');
    menuFileOpen.setText("Open");
    menuFileOpen.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        menuFileOpen_actionPerformed(e);
      }
    });
    menuSave.setIcon(new ImageIcon(PathEditPanel.class.getResource("Save16.gif")));
    menuSave.setMnemonic('S');
    menuSave.setText("Save");
    menuSave.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        menuSave_actionPerformed(e);
      }
    });
    menuHelpAbout.setIcon(new ImageIcon(PathEditPanel.class.getResource("About16.gif")));
    menuFileExit.setIcon(new ImageIcon(PathEditPanel.class.getResource("Stop16.gif")));
    menuFile.setText("File");
    menuFileExit.setText("Exit");
    menuFileExit.addActionListener(new ActionListener()  {

      public void actionPerformed(ActionEvent e) {
        patheditPanel.fileExit_actionPerformed(e);
      }
    });
    menuHelp.setText("Help");
    menuHelpAbout.setText("About");
    menuHelpAbout.addActionListener(new ActionListener()  {

      public void actionPerformed(ActionEvent e) {
        patheditPanel.helpAbout_actionPerformed(e);
      }
    });
    menuFile.add(menuFileOpen);
    menuFile.add(menuSave);
    menuFile.addSeparator();
    menuFile.add(menuFileExit);
    menuHelp.add(menuHelpAbout);
    menuBar1.add(menuFile);
    menuBar1.add(jMenu1);
    menuBar1.add(menuHelp);
    jMenu1.add(menuInsert);
    jMenu1.add(menuRemove);
    jMenu1.addSeparator();
    jMenu1.add(menuMoveUp);
    jMenu1.add(menuMoveDown);
    jMenu1.addSeparator();
    jMenu1.add(menuProperties);
    this.setJMenuBar(menuBar1);

  }

  void menuFileOpen_actionPerformed(ActionEvent e) {
    patheditPanel.btnOpen_actionPerformed(e);
  }

  void menuSave_actionPerformed(ActionEvent e) {
    patheditPanel.btnSave_actionPerformed(e);
  }

  //Overridden so we can exit when window is closed
  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      patheditPanel.fileExit_actionPerformed(null);
    }
  }

  void menuInsert_actionPerformed(ActionEvent e) {
    patheditPanel.btnAdd_actionPerformed(e);
  }

  void menuRemove_actionPerformed(ActionEvent e) {
    patheditPanel.btnRemove_actionPerformed(e);
  }

  void menuMoveUp_actionPerformed(ActionEvent e) {
    patheditPanel.btnUp_actionPerformed(e);
  }

  void menuMoveDown_actionPerformed(ActionEvent e) {
    patheditPanel.btnDown_actionPerformed(e);
  }

  void menuProperties_actionPerformed(ActionEvent e) {
	patheditPanel.btnEdit_actionPerformed(e);
  }

  public void update(Observable obs, Object obj) {
    menuMoveUp.setEnabled(patheditPanel.btnUp.isEnabled());
    menuMoveDown.setEnabled(patheditPanel.btnDown.isEnabled());
    menuInsert.setEnabled(patheditPanel.btnAdd.isEnabled());
    menuRemove.setEnabled(patheditPanel.btnRemove.isEnabled());
  }

}