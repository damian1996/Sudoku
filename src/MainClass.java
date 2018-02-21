import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainClass {
    static int type = 0;
    static GUI view;
    static JFrame frame;
    static ChangeBoard list = new ChangeBoard();
    static App inst;
    static int size;
    public static final Color OPEN_CELL_BGCOLOR = Color.WHITE;
    public static final Color CLOSED_CELL_BGCOLOR = Color.GRAY;
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                frame = new JFrame("Sudoku");
                type = 0;
                if (type == 0) {
                    inst = new App(type, frame);
                    view = new GUI(type, inst.sudokuCells, 9, frame);
                    view.button1.addActionListener(list);
                    view.button2.addActionListener(list);
                    view.button3.addActionListener(list);
                    view.reset.addActionListener(list);
                } else if (type == 1) {
                    inst =  new App(type, frame);
                    view = new GUI(type, inst.sudokuCells, 6, frame);
                    view.button1.addActionListener(list);
                    view.button2.addActionListener(list);
                    view.button3.addActionListener(list);
                    view.reset.addActionListener(list);
                } else {
                    inst =  new App(type, frame);
                    view = new GUI(type, inst.sudokuCells, 16, frame);
                    view.button1.addActionListener(list);
                    view.button2.addActionListener(list);
                    view.button3.addActionListener(list);
                    view.reset.addActionListener(list);
                }
            }
        });
    }

    static class ChangeBoard implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton butt = (JButton) e.getSource();
            String text = butt.getText();
            if (text.equals("9x9")) {
                type = 0;
                view.frame.getContentPane().removeAll();
                inst =  new App(type, frame);
                view = new GUI(type, inst.sudokuCells, 9, frame);
                view.button1.addActionListener(list);
                view.button2.addActionListener(list);
                view.button3.addActionListener(list);
                view.reset.addActionListener(list);
            } else if (text.equals("6x6")) {
                type = 1;
                frame.getContentPane().removeAll();
                inst =  new App(type, frame);
                view = new GUI(type, inst.sudokuCells, 6, frame);
                view.button1.addActionListener(list);
                view.button2.addActionListener(list);
                view.button3.addActionListener(list);
                view.reset.addActionListener(list);
            } else if (text.equals("16x16")){
                type = 2;
                view.frame.getContentPane().removeAll();
                inst =  new App(type, frame);
                view = new GUI(type, inst.sudokuCells, 16, frame);
                view.button1.addActionListener(list);
                view.button2.addActionListener(list);
                view.button3.addActionListener(list);
                view.reset.addActionListener(list);
            } else{
                if(type==0) size = 9;
                else if(type == 1) size = 6;
                else size = 16;

                for(int i=0; i<size; i++){
                    for(int j=0; j<size; j++){
                        if(inst.masks[i][j]){
                            inst.sudokuCells[i][j].bean.setEditable(true);
                            inst.sudokuCells[i][j].bean.setBackground(CLOSED_CELL_BGCOLOR);
                            inst.sudokuCells[i][j].bean.setText(String.valueOf(inst.sudoku[i][j]));
                            inst.sudokuCells[i][j].oldColor = CLOSED_CELL_BGCOLOR;
                            inst.sudokuCells[i][j].bean.setEditable(false);
                            inst.sudokuCells[i][j].conflicts = 0;
                            inst.sudokuCells[i][j].oldValue = "";
                        } else{
                            inst.sudokuCells[i][j].bean.setBackground(OPEN_CELL_BGCOLOR);
                            inst.sudokuCells[i][j].bean.setText("");
                            inst.sudokuCells[i][j].oldColor = OPEN_CELL_BGCOLOR;
                            inst.sudokuCells[i][j].conflicts = 0;
                            inst.sudokuCells[i][j].oldValue = "";
                            //inst.sudokuCells[i][j].oldValue = String.valueOf(inst.sudoku[i][j]);
                        }
                    }
                }
            }
        }
    }
}