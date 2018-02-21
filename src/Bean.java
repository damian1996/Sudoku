import javax.swing.*;
import java.awt.*;
import java.beans.*;
import java.io.Serializable;


public class Bean implements Serializable {
    public static final Font FONT_NUMBERS = new Font("Monospaced", Font.BOLD, 34);
    public static final Font FONT_NUMBERS1 = new Font("Monospaced", Font.BOLD, 40);
    public static final Font FONT_NUMBERS2 = new Font("Monospaced", Font.BOLD, 22);
    public static final Color OPEN_CELL_BGCOLOR = Color.WHITE;
    public static final Color CLOSED_CELL_BGCOLOR = Color.GRAY;
    public static final Color AFTER_CHANGE12 = new Color(230, 15, 47);
    public static final Color CLOSED_CELL_TEXT = Color.BLACK;

    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private VetoableChangeSupport vcs = new VetoableChangeSupport(this);

    public synchronized void addPropertyChangeListener (PropertyChangeListener lst) {
        pcs.addPropertyChangeListener(lst);
    }
    public synchronized void removePropertyChangeListener (PropertyChangeListener lst) {
        pcs.removePropertyChangeListener(lst);
    }

    public synchronized void addVetoableChangeListener (VetoableChangeListener lst) {
        vcs.addVetoableChangeListener(lst);
    }
    public synchronized void removeVetoableChangeListener (VetoableChangeListener lst) {
        vcs.removeVetoableChangeListener(lst);
    }

    JTextField bean;
    int x, y, conflicts=0, typ;
    String oldValue;
    Color oldColor;
    boolean[][] masks;
    boolean[][] czyWstawione;
    int[][] areas;
    int[][] sudoku;

    public Bean(int type, int i, int j) {
        this.typ = type;
        if(type==0){
            masks = Results.masks;
            czyWstawione = Results.czyWstawione;
            areas = Results.areas;
            sudoku = Results.sudoku;
        }
        else if(type==1){
            masks = Results.masks2;
            czyWstawione = Results.czyWstawione2;
            areas = Results.areas2;
            sudoku = Results.sudoku2;
        }
        else{
            masks = Results.masks3;
            czyWstawione = Results.czyWstawione3;
            areas = Results.areas3;
            sudoku = Results.sudoku3;
        }
        this.x = i;
        this.y = j;
        bean = new JTextField();
        if(type==0) bean.setFont(FONT_NUMBERS);
        else if(type==1) bean.setFont(FONT_NUMBERS1);
        else bean.setFont(FONT_NUMBERS2);

        bean.setHorizontalAlignment(JTextField.CENTER);
        this.oldValue = bean.getText();
        if (!masks[i][j]) {
            bean.setText("");
            bean.setEditable(true);
            bean.setBackground(OPEN_CELL_BGCOLOR);
            this.oldColor = bean.getBackground();
        }
        else {
            bean.setText(sudoku[i][j] + "");
            bean.setEditable(false);
            bean.setBackground(CLOSED_CELL_BGCOLOR);
            bean.setForeground(CLOSED_CELL_TEXT);
            this.oldColor = bean.getBackground();
        }
    }

    public JTextField getBean() {
        return bean;
    }

    public void insert(String ins, boolean xx) { // nie return
        /*if(ins.equals(oldValue)){
            bean.setText(oldValue);
            setInsert(oldValue, oldValue);
           // return;
        }*/
        if(xx==true){
            setInsert(ins, oldValue);
        }
        /*else{
            bean.setText(oldValue);
            setInsert(oldValue, oldValue);
            // return;
        }*/
    }

    public void setInsert(String ins, String oldVal){
        try {
            vcs.fireVetoableChange("insert", oldVal, ins);
            pcs.firePropertyChange("editable", oldVal, ins);
            oldColor = bean.getBackground();
            bean.setForeground(CLOSED_CELL_TEXT);
            czyWstawione[x][y] = true;
            oldValue = ins;
        }
        catch (PropertyVetoException e) {
            //oldValue = ;
            if(bean.getText().equals("")){
                oldValue = "";
            }else{
                bean.setText(oldVal);
            }
            czyWstawione[x][y] = false;
        }
    }
}