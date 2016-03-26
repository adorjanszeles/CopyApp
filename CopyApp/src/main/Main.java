package main;

public class Main {
    public static void main(String[] args){
        CopyJFrame frame = new CopyJFrame();
        frame.createElements();
        frame.setTogether();
        frame.setActionListeners();
        frame.showWindow();
    }
}