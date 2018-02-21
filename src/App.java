import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.beans.*;
import java.io.Serializable;
import javax.swing.*;

public class App implements Serializable {
    public static final Color OPEN_CELL_BGCOLOR = Color.WHITE;
    public static final Color AFTER_CHANGE1 = new Color(157, 122, 200);
    public static final Color AFTER_CHANGE12 = new Color(230, 15, 47);
    public static final Color CLOSED_CELL_BGCOLOR = Color.GRAY;

    Bean[][] sudokuCells;
    int typ;
    boolean[][] masks;
    boolean[][] czyWstawione;
    int[][] areas;
    int[][] sudoku;
    JFrame frame;

    public App(int type, JFrame frame) {
        this.typ = type;
        this.frame = frame;
        if (type == 0) {
            masks = Results.masks;
            czyWstawione = Results.czyWstawione;
            areas = Results.areas;
            sudoku = Results.sudoku;
            sudokuCells = new Bean[9][9];
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    final int ii = i;
                    final int jj = j;
                    sudokuCells[i][j] = new Bean(type, i, j);
                    sudokuCells[i][j].bean.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            listeners(ii, jj, 9);
                        }
                    });

                    sudokuCells[i][j].bean.addFocusListener(new FocusListener() {
                        @Override
                        public void focusGained(FocusEvent fe){
                            String ins = sudokuCells[ii][jj].bean.getText();
                        }
                        @Override
                        public void focusLost(FocusEvent fe){
                            listeners(ii, jj, 9);
                        }
                    });

                    sudokuCells[i][j].addVetoableChangeListener(new InsertControl());
                    sudokuCells[i][j].addPropertyChangeListener(new ChangeGUI());
                }
            }
        }
        else if(type==1){
            masks = Results.masks2;
            czyWstawione = Results.czyWstawione2;
            areas = Results.areas2;
            sudoku = Results.sudoku2;
            sudokuCells = new Bean[6][6];
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 6; j++) {
                    final int ii = i;
                    final int jj = j;
                    sudokuCells[i][j] = new Bean(type, i, j);

                    sudokuCells[i][j].bean.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            listeners(ii, jj, 6);
                        }
                    });

                    sudokuCells[i][j].bean.addFocusListener(new FocusListener() {
                        @Override
                        public void focusGained(FocusEvent fe){
                            String ins = sudokuCells[ii][jj].bean.getText();
                        }
                        @Override
                        public void focusLost(FocusEvent fe){
                            listeners(ii, jj, 6);
                        }
                    });
                    sudokuCells[i][j].addVetoableChangeListener(new InsertControl());
                    sudokuCells[i][j].addPropertyChangeListener(new ChangeGUI());
                }
            }
        }
        else{
            masks = Results.masks3;
            czyWstawione = Results.czyWstawione3;
            areas = Results.areas3;
            sudoku = Results.sudoku3;
            sudokuCells = new Bean[16][16];
            for (int i = 0; i < 16; i++) {
                for (int j = 0; j < 16; j++) {
                    final int ii = i;
                    final int jj = j;
                    sudokuCells[i][j] = new Bean(type, i, j);
                    sudokuCells[i][j].bean.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            listeners(ii, jj, 16);
                        }
                    });
                    sudokuCells[i][j].bean.addFocusListener(new FocusListener() {
                        @Override
                        public void focusGained(FocusEvent fe){
                            String ins = sudokuCells[ii][jj].bean.getText();
                        }
                        @Override
                        public void focusLost(FocusEvent fe){
                            listeners(ii, jj, 16);
                        }
                    });
                    sudokuCells[i][j].addVetoableChangeListener(new InsertControl());
                    sudokuCells[i][j].addPropertyChangeListener(new ChangeGUI());
                }
            }
        }
    }

    public void clearConf(int x, int y, int size) {
        Color sour = sudokuCells[x][y].bean.getBackground();
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                if(j==y && i==x) continue;
                Color test = sudokuCells[i][j].bean.getBackground();
                if(test.equals(sour) && sour.equals(AFTER_CHANGE12)){
                    String one = sudokuCells[i][j].bean.getText();
                    String two = sudokuCells[x][y].oldValue;
                    if((i==x || j==y || areas[i][j]==areas[x][y]) && one.equals(two)){
                        sudokuCells[i][j].conflicts--;
                        if(sudokuCells[i][j].conflicts==0){
                            Color tmp = test;
                            sudokuCells[i][j].bean.setBackground(OPEN_CELL_BGCOLOR);
                            sudokuCells[i][j].oldColor = tmp;
                        }
                    }
                }
            }
        }
        if(sour.equals(AFTER_CHANGE12)){
            sudokuCells[x][y].conflicts = 0;
            Color tmp1 = sour;
            sudokuCells[x][y].bean.setBackground(OPEN_CELL_BGCOLOR);
            sudokuCells[x][y].oldColor = tmp1;
        }
    }

    public class ChangeGUI implements PropertyChangeListener{
        @Override
        public void propertyChange(PropertyChangeEvent evt)  {
            Bean source = (Bean) evt.getSource();
            String nju = (String) evt.getNewValue();
            int y = Integer.parseInt(nju);
            int size = 0;
            if(typ==0) size = 9;
            else if(typ==1) size = 6;
            else size = 16;
            if(checkChangeColor(y, source, size)==false){
                source.bean.setBackground(AFTER_CHANGE12);
            } else{
                source.bean.setBackground(OPEN_CELL_BGCOLOR);
            }
            source.bean.setText(nju);
            int sizee;
            if(typ==0) sizee = 9;
            else if(typ==1) sizee = 6;
            else sizee = 16;

            int counter = 0;
            Color checkColor;
            String checkValue;
            for(int i=0; i<sizee; i++){
                for(int j=0; j<sizee; j++){
                    checkColor = sudokuCells[i][j].bean.getBackground();
                    checkValue = sudokuCells[i][j].bean.getText();
                    if(!checkColor.equals(AFTER_CHANGE12) && !checkValue.equals("")){
                        counter++;
                    }
                }
            }
            if(counter==(size*size)){
                String message = "Gratulacje, wygrałeś! Zagraj ponownie!";
                JOptionPane.showMessageDialog(frame, message, "Sudoku", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    public class InsertControl implements VetoableChangeListener {
        @Override
        public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
            Bean source = (Bean) evt.getSource();
            if (!evt.getPropertyName().equals("insert")) return;
            String nju = (String) evt.getNewValue();
            int y;
            try{
                y = Integer.parseInt(nju);
            } catch(NumberFormatException nfe) {
                source.bean.setText("");
                throw new PropertyVetoException("Prohibited", evt);
            }
            int size = 0;
            if(typ==0) size = 9;
            else if(typ==1) size = 6;
            else size = 16;

            if (!checkFlash(y, source, size)){
                //source.bean.setEditable(false);
                //flashMyField(source, AFTER_CHANGE1, OPEN_CELL_BGCOLOR, 1500, 500);
                //source.bean.setEditable(true);
                // bean.setText(oldVal);
                String old = (String) evt.getOldValue();
                try{
                    int oldInt = Integer.parseInt(old);
                    if(checkChangeColor(oldInt, source, size)==false){
                        source.bean.setBackground(AFTER_CHANGE12);
                    } else{
                        source.bean.setBackground(OPEN_CELL_BGCOLOR);
                    }
                }catch(NumberFormatException nfe){
                    System.out.println("powrot do pustego pola");
                }
                throw new PropertyVetoException("Prohibited", evt);
            }
        }
    }

    public boolean checkFlash(int newVal, Bean source, int size) {
        if(checkRow0(newVal, source, 1, size)==false || checkColumn0(newVal, source, 1, size)==false || checkGrid0(newVal, source, 1, size)==false){
            //flashMyField(source, AFTER_CHANGE1, OPEN_CELL_BGCOLOR, 1500, 500);
            return false;
        }
        return true;
    }

    public boolean checkChangeColor(int newVal, Bean source, int size){
        int count = 0;
        if(!checkColumn0(newVal, source, 0, size)){
            count++;
        }
        if(!checkRow0(newVal, source, 0, size)){
            count++;
        }
        if(!checkGrid0(newVal, source, 0, size)){
            count++;
        }
        if (count>0)
            return false;
        else
            return true;
    }

    public boolean checkColumn0(int newVal, Bean source, int mask, int size) { //row
        String y = String.valueOf(newVal);
        int conf = 0;
        if(mask==1){
            for (int tmp = 0; tmp < size; tmp++) {
                if(masks[source.x][tmp]==true){
                    if (czyWstawione[source.x][tmp] == true && tmp != source.y) {
                        String x = sudokuCells[source.x][tmp].bean.getText();
                        if (y.equals(x)) {
                            conf++;
                        }
                    }
                }
            }
        } else{
            for (int tmp = 0; tmp < size; tmp++) {
                if (czyWstawione[source.x][tmp] == true && tmp != source.y) {
                    String x = sudokuCells[source.x][tmp].bean.getText();
                    if (y.equals(x)) {
                        sudokuCells[source.x][tmp].bean.setBackground(AFTER_CHANGE12);
                        sudokuCells[source.x][tmp].conflicts += 1;
                        source.conflicts += 1;
                        czyWstawione[source.x][tmp] = true;
                        conf++;
                    }
                }
            }
        }
        if(conf>0) return false;
        else return true;
    }

    public boolean checkRow0(int newVal, Bean source, int mask, int size) { // column
        String y = String.valueOf(newVal);

        int conf = 0;
        if(mask==1){
            for (int tmp = 0; tmp < size; tmp++) {
                if(masks[tmp][source.y]==true) {
                    if (czyWstawione[tmp][source.y] == true && source.x != tmp) {
                        String x = sudokuCells[tmp][source.y].bean.getText();
                        if (y.equals(x)) {
                            conf++;
                        }
                    }
                }
            }
        } else{
            for (int tmp = 0; tmp < size; tmp++) {
                if (czyWstawione[tmp][source.y] == true && source.x != tmp) {
                    String x = sudokuCells[tmp][source.y].bean.getText();
                    if (y.equals(x)) {
                        sudokuCells[tmp][source.y].bean.setBackground(AFTER_CHANGE12);
                        sudokuCells[tmp][source.y].conflicts += 1;
                        source.conflicts += 1;
                        czyWstawione[tmp][source.y] = true;
                        conf++;
                    }
                }
            }
        }
        if(conf>0) return false;
        else return true;
    }

    public boolean checkGrid0(int newVal, Bean source, int mask, int size) {
        String y = String.valueOf(newVal);
        int conf = 0;
        if(mask==1){
            for(int i=0; i<size; i++){
                for(int j=0; j<size; j++){
                    if(masks[i][j]==true) {
                        if(czyWstawione[i][j]==true && source.x != i && source.y != j){
                            String x = sudokuCells[i][j].bean.getText();
                            if(x.equals(y) && areas[i][j]==areas[source.x][source.y]){
                                conf++;
                            }
                        }
                    }
                }
            }
        } else{
            for(int i=0; i<size; i++){
                for(int j=0; j<size; j++){
                    if(czyWstawione[i][j]==true && source.x != i && source.y != j){
                        String x = sudokuCells[i][j].bean.getText();
                        if(x.equals(y) && areas[i][j]==areas[source.x][source.y]){
                            sudokuCells[i][j].bean.setBackground(AFTER_CHANGE12);
                            sudokuCells[i][j].conflicts += 1;
                            source.conflicts += 1;
                            czyWstawione[i][j] = true;
                            conf++;
                        }
                    }
                }
            }
        }
        if(conf>0) return false;
        else return true;
    }

    public void flashMyField(final Bean source, Color flashColor, Color flashColor2, int totalTime, int timerDelay) {
        final int totalCount = totalTime / timerDelay;
        javax.swing.Timer timer = new javax.swing.Timer(timerDelay, new ActionListener(){
            int count = 0;

            public void actionPerformed(ActionEvent evt) {
                if (count % 2 == 0) {
                    source.bean.setBackground(flashColor);
                } else {
                    source.bean.setBackground(flashColor2);
                    if (count >= totalCount) {
                        ((javax.swing.Timer)evt.getSource()).stop();
                    }
                }
                count++;
            }
        });
        timer.start();
    }

    public void listeners(int ii, int jj, int size){
        Color test = sudokuCells[ii][jj].bean.getBackground();
        String ins = sudokuCells[ii][jj].bean.getText();
        if(!ins.equals(sudokuCells[ii][jj].oldValue) && !test.equals(CLOSED_CELL_BGCOLOR)){
            try{
                int i = Integer.parseInt(ins);
                if(i>=1 && i<=size){
                    clearConf(ii, jj, size);
                    sudokuCells[ii][jj].insert(ins, true);
                }
                else{
                    sudokuCells[ii][jj].bean.setText(sudokuCells[ii][jj].oldValue);
                }
            }
            catch (NumberFormatException nfe){
                if(ins.equals("")){
                    clearConf(ii, jj, size);
                    sudokuCells[ii][jj].insert("", true);
                } else{
                    sudokuCells[ii][jj].bean.setText(sudokuCells[ii][jj].oldValue);
                }
            }
        }
    }
}