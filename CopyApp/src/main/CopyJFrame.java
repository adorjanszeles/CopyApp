package main;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.nio.file.StandardCopyOption;

public class CopyJFrame {
	private static final String jFrameText = "Mentés alkalmazás";
	private static final String saveButtonText = "Mentés!";
	private static final String exitButtonText = "Kilépés!";
	private static final String programStateText = "Mentés előkészítve!";
	private static final String saveSuccessText = "Mentés sikerült!";
	private static final String errorText = "Hiba történt, lépjen kapcsolatba a rendszer tervezőjével!";
	private JFrame mainFrame;
	private JPanel panel;
	private JButton save;
	private JButton exit;
	private JLabel label;
	private JProgressBar progressBar;
	
	public void createElements() {
		mainFrame = new JFrame(jFrameText);
		panel = new JPanel(new BorderLayout());
		save = new JButton(saveButtonText);
		exit = new JButton(exitButtonText);
		label = new JLabel(programStateText);
		label.setHorizontalAlignment(JLabel.CENTER);
		progressBar = new JProgressBar(0, 100);
	}
	
	public void setTogether() {
		panel.add(label, BorderLayout.CENTER);
		panel.add(save, BorderLayout.LINE_START);
		panel.add(exit, BorderLayout.LINE_END);
		panel.add(progressBar, BorderLayout.PAGE_END);
		mainFrame.add(panel);
	}
	
	public void showWindow() {
		mainFrame.setSize(300,100);
		mainFrame.setVisible(true);
	}
	
	public void setActionListeners() {
		save.addActionListener(e -> doCopy());
		exit.addActionListener(e -> System.exit(0));
	}

    private void doCopy() {
        Runnable runnable = () -> {
            try {
                PathParser parser = new PathParser();
                parser.parseXML();
                File sourceFile = new File(parser.getSource());
                File destinationFile = new File(parser.getDestination());
                Copy copy = new Copy();
                copy.copyFileOrFolder(sourceFile, destinationFile, StandardCopyOption.REPLACE_EXISTING);
                label.setText("Mentés kész!");
                progressBar.setIndeterminate(false);
                JOptionPane.showMessageDialog(mainFrame, saveSuccessText);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(mainFrame, errorText);
            }
        };
        Thread thread = new Thread(runnable);
        progressBar.setIndeterminate(true);
        label.setText("Mentés folyamatban...");
        thread.start();
    }
}
