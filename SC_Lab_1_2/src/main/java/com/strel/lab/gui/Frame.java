package com.strel.lab.gui;

import com.strel.lab.logic.FileProcessor;
import com.strel.lab.logic.TextProcessor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by DemonSlayer on 01.10.2015.
 */
public class Frame extends JFrame {

    final   JFileChooser fc = new JFileChooser();

    private JMenuBar    menuBar;
    private JMenu       mFile;
    private JMenuItem   miOpen;
    private JMenuItem   miSave;
    private JMenuItem   miSaveAs;
    private JMenuItem   miExit;

    private JPanel  contentPane;
    private JPanel  pnlBeforeEdit;
    private JPanel  pnlAfterEdit;
    private JPanel  pnlActions;

    private JButton btnEdit;
    private JButton btnClear;

    private JTextArea txtBefore;
    private JTextArea txtAfter;

    private JScrollPane scrollBefore;
    private JScrollPane scrollAfter;


    public Frame(String caption) {
        setTitle(caption);

        initializeElements();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(contentPane);
        setJMenuBar(menuBar);

        pack();
        setSize(1200, 400);
    }

    private void initializeElements() {
        // === Main panel
        contentPane = new JPanel(new GridLayout(0, 3));
        // ===============

        // === Menu section
        menuBar = new JMenuBar();
        mFile = new JMenu("File");
        miOpen = new JMenuItem("Open");
        miSave = new JMenuItem("Save");
        miSaveAs = new JMenuItem("Save as");
        miExit = new JMenuItem("Exit");

        miOpen.addActionListener(new OpenListener());
        miSave.addActionListener(new SaveListener());
        miSaveAs.addActionListener(new SaveListener());
        miExit.addActionListener(new ExitListener());

        menuBar.add(mFile);
        mFile.add(miOpen);
        mFile.add(miSave);
        mFile.add(miSaveAs);
        mFile.addSeparator();
        mFile.add(miExit);
        // ===============

        // === From file panel section
        pnlBeforeEdit = new JPanel(new BorderLayout());
        pnlBeforeEdit.setBorder(BorderFactory.createTitledBorder("Text from a file"));

        txtBefore = new JTextArea();
        txtBefore.setLineWrap(true);
        scrollBefore = new JScrollPane(txtBefore);
        scrollBefore.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        pnlBeforeEdit.add(scrollBefore, BorderLayout.CENTER);
        contentPane.add(pnlBeforeEdit);
        // ===============

        // === Action panel section
        pnlActions = new JPanel(new FlowLayout());
        pnlActions.setBorder(BorderFactory.createTitledBorder("Choose action"));

        btnEdit = new JButton("Edit");
        btnEdit.addActionListener(new EditListener());

        btnClear = new JButton("Clear");
        btnClear.addActionListener(new ClearListener());

        pnlActions.add(btnEdit);
        pnlActions.add(btnClear);

        contentPane.add(pnlActions);
        // ===============

        // === After edit panel section
        pnlAfterEdit = new JPanel(new BorderLayout());
        pnlAfterEdit.setBorder(BorderFactory.createTitledBorder("Text after changing"));

        txtAfter = new JTextArea();
        txtAfter.setLineWrap(true);
        scrollAfter = new JScrollPane(txtAfter);
        scrollAfter.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        pnlAfterEdit.add(scrollAfter, BorderLayout.CENTER);
        contentPane.add(pnlAfterEdit);
        // ===============
    }

    public void visualize() {
        setVisible(true);
    }


    private class EditListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String input = txtBefore.getText();
            if (!"".equals(input)) {
                ArrayList<String> words = TextProcessor.process(input);
                txtAfter.setText("Words, that has only 2 repetitions: \n");
                words.forEach(word -> txtAfter.append(word + "\n"));
            } else {
                JOptionPane.showMessageDialog(null, "Please input some text first", "Input warning", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private class ClearListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            txtBefore.setText("");
            txtAfter.setText("");
        }
    }

    private class OpenListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int ret = fc.showOpenDialog(Frame.this);
            if (ret == JFileChooser.APPROVE_OPTION) {
                try {
                    File chosen = fc.getSelectedFile();
                    txtBefore.setText(FileProcessor.readText(chosen));
                }
                catch (IOException ex) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Such file not found or does not exists!",
                            "File error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        }
    }

    private class SaveListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int ret = fc.showSaveDialog(Frame.this);
            if (ret == JFileChooser.APPROVE_OPTION) {
                String filename = fc.getSelectedFile().getName();
                String path = fc.getCurrentDirectory().toString();
                String content = txtAfter.getText();

                if (!"".equals(filename) && !"".equals(path))
                    FileProcessor.saveToFile(filename, path, content);
                else
                    JOptionPane.showMessageDialog(
                            null,
                            "Please give name to your file!",
                            "File error",
                            JOptionPane.ERROR_MESSAGE
                    );
            }
        }
    }

    private class ExitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
