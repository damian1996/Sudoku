import javax.swing.*;
import java.awt.*;

public class GUI {
    boolean[][] masks;
    boolean[][] czyWstawione;
    int[][] areas;
    JButton button1, button2, button3, reset;
    JFrame frame;

    public GUI(int type, Bean[][] sudokuCells, int size, JFrame frame) {
        this.frame = frame;
        this.frame.setResizable(true);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if(type==0){
            frame.setMinimumSize(new Dimension(650, 650));
            frame.setMaximumSize(new Dimension(650, 650));
            masks = Results.masks;
            czyWstawione = Results.czyWstawione;
            areas = Results.areas;
        }
        else if(type==1){
            frame.setMinimumSize(new Dimension(600, 600));
            frame.setMaximumSize(new Dimension(600, 600));
            masks = Results.masks2;
            czyWstawione = Results.czyWstawione2;
            areas = Results.areas2;
        }
        else{
            masks = Results.masks3;
            czyWstawione = Results.czyWstawione3;
            areas = Results.areas3;
            frame.setMinimumSize(new Dimension(670, 670));
            frame.setMaximumSize(new Dimension(670, 670));
        }
        this.frame.setResizable(false);

        JPanel chooseBoards = new JPanel (new FlowLayout (FlowLayout.CENTER));
        button1 = new JButton("9x9");
        button1.setPreferredSize(new Dimension(80, 40));
        button2 = new JButton("6x6");
        button2.setPreferredSize(new Dimension(80, 40));
        button3 = new JButton("16x16");
        button3.setPreferredSize(new Dimension(80, 40));
        ButtonGroup grp = new ButtonGroup();
        grp.add(button1);
        grp.add(button2);
        grp.add(button3);
        chooseBoards.add(button1);
        chooseBoards.add(button2);
        chooseBoards.add(button3);
        JPanel board = new JPanel(new GridLayout (size, size));

        board.setLayout(new GridLayout(size, size));
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                board.add(sudokuCells[i][j].getBean());
                if(type==0)
                    borderFrame0(sudokuCells[i][j].bean, i, j);
                else if(type==1)
                    borderFrame1(sudokuCells[i][j].bean, i, j);
                else
                    borderFrame2(sudokuCells[i][j].bean, i, j);
            }
        }

        JPanel clear = new JPanel();
        clear.add (new JLabel ("Try again:"));
        reset = new JButton("Clear Board");
        reset.setPreferredSize(new Dimension(120, 40));
        clear.add(reset);

        frame.getContentPane().add(chooseBoards, BorderLayout.NORTH);
        frame.getContentPane().add(board, BorderLayout.CENTER);
        frame.getContentPane().add(clear, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
    }

    private void borderFrame2(JTextField bean, int i, int j){
        if((j<15 && areas[i][j]!=areas[i][j+1]) && (i<15 && areas[i][j]!=areas[i+1][j])){
            bean.setBorder(BorderFactory.createMatteBorder(1, 1, 7, 7, Color.black));
        }
        else if(j<15 && areas[i][j]!=areas[i][j+1]){
            bean.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 7, Color.black));
        }
        else if(i<15 && areas[i][j]!=areas[i+1][j]){
            bean.setBorder(BorderFactory.createMatteBorder(1, 1, 7, 1, Color.black));
        }
        else{
            bean.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
        }
    }

    private void borderFrame0(JTextField bean, int i, int j){
        if((j<8 && areas[i][j]!=areas[i][j+1]) && (i<8 && areas[i][j]!=areas[i+1][j])){
            bean.setBorder(BorderFactory.createMatteBorder(1, 1, 10, 10, Color.black));
        }
        else if(j<8 && areas[i][j]!=areas[i][j+1]){
            bean.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 10, Color.black));
        }
        else if(i<8 && areas[i][j]!=areas[i+1][j]){
            bean.setBorder(BorderFactory.createMatteBorder(1, 1, 10, 1, Color.black));
        }
        else{
            bean.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
        }
    }

    private static void borderFrame1(JTextField bean, int i, int j) {
        if ((j < 5 && Results.areas2[i][j] != Results.areas2[i][j + 1]) && (i < 5 && Results.areas2[i][j] != Results.areas2[i + 1][j])) {
            bean.setBorder(BorderFactory.createMatteBorder(1, 1, 10, 10, Color.black));
        } else if (j < 5 && Results.areas2[i][j] != Results.areas2[i][j + 1]) {
            bean.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 10, Color.black));
        } else if (i < 5 && Results.areas2[i][j] != Results.areas2[i + 1][j]) {
            bean.setBorder(BorderFactory.createMatteBorder(1, 1, 10, 1, Color.black));
        } else {
            bean.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
        }
    }
}



/*


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;

public class Linki {
    static GUI view;
    static JFrame frame;
    static int size;
    static Change list = new Change();
    static ClickLists selectInList = new ClickLists();
    static java.util.List<String> curr = new ArrayList<String>();
    static JList listHistory;
    static int current = 0;
    static Connection c;
    static Statement stmt;
    static JScrollPane scrollHist;


    public static void main(String[] args) {
        c = null;
        stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            String sql = "CREATE TABLE HISTORY " +
                    "(DATE TEXT PRIMARY KEY     NOT NULL," +
                    " HTTP           TEXT     NOT NULL)";
            stmt.executeUpdate(sql);

            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    frame = new JFrame("LINKI");
                    view = new GUI(frame);
                    view.button1.addActionListener(list);
                    view.button2.addActionListener(list);
                    view.button3.addActionListener(list);
                    view.button4.addActionListener(list);
                    view.list.addMouseListener(selectInList);
                }
            });
            System.out.println("hahaa");
            stmt.close();
            c.close();
        }
         catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    static class ClickLists implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() >= 1) { // ==
                String selectedItem = (String) view.list.getSelectedValue();
                curr.add(selectedItem);
                current++;
                view.area.setText(selectedItem);
                connect y = new connect(selectedItem, view, curr, current);
                String sql = "INSERT INTO HISTORY (DATE, HTTP) " +
                        "VALUES (" + selectedItem + ", datetime('now'));";
                try{
                    stmt.executeUpdate(sql);
                } catch (Exception f){
                    System.err.println( f.getClass().getName() + ": " + f.getMessage() );
                    System.exit(0);
                }
                view.listmodel.clear();
                view.text1.setText(String.valueOf(y.counter));
                view.text2.setText(String.valueOf(y.size));
                DefaultListModel model = new DefaultListModel();
                for(String xx : y.https){
                    System.out.println(xx);
                    model.addElement(xx);
                }
                view.list.setModel(model);
            }
        }
        @Override
        public void mouseEntered(MouseEvent e) {
        }
        @Override
        public void mouseExited(MouseEvent e) {
        }
        @Override
        public void mousePressed(MouseEvent e) {
        }
        @Override
        public void mouseReleased(MouseEvent e) {
        }
    }

    static class Change implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton butt = (JButton) e.getSource();
            String text = butt.getText();
            if(text.equals("<-")){
                System.out.println("BACK");
                if(current>1){
                    curr.remove(current-1);
                    current--;
                    String temp = curr.get(current-1);
                    view.area.setText(temp);
                    String sql = "INSERT INTO HISTORY (DATE, HTTP) " +
                            "VALUES (" + temp + ", datetime('now'));";
                    try{
                        stmt.executeUpdate(sql);
                    } catch (Exception f){
                        System.err.println( f.getClass().getName() + ": " + f.getMessage() );
                        System.exit(0);
                    }
                    connect y = new connect(temp, view, curr, current);
                    view.listmodel.clear();
                    view.text1.setText(String.valueOf(y.counter));
                    view.text2.setText(String.valueOf(y.size));
                    DefaultListModel model = new DefaultListModel();
                    for(String xx : y.https){
                        System.out.println(xx);
                        model.addElement(xx);
                    }
                    view.list.setModel(model);
                }
            }
            else if(text.equals("NEXT")){
                System.out.println("NEXT");
            }
            else if(text.equals("START")){
                String x = view.area.getText();
                curr.add(x);
                current++;
                connect y = new connect(x, view, curr, current);
                String sql = "INSERT INTO HISTORY (DATE, HTTP) " +
                        "VALUES (" + x + ", datetime('now'));";
                try{
                    stmt.executeUpdate(sql);
                } catch (Exception f){
                    System.err.println( f.getClass().getName() + ": " + f.getMessage() );
                    System.exit(0);
                }
                view.listmodel.clear();
                view.text1.setText(String.valueOf(y.counter));
                view.text2.setText(String.valueOf(y.size));
                DefaultListModel model = new DefaultListModel();
                for(String xx : y.https){
                    System.out.println(xx);
                    model.addElement(xx);
                }
                view.list.setModel(model);
            }
            else { // HISTORY
                System.out.println("HISTORY");
                try{
                    DefaultListModel modelTest = new DefaultListModel();
                    ResultSet resultSet = stmt.executeQuery("SELECT * FROM HISTORY;"); //run your query
                    while (resultSet.next()) //go through each row that your query returns
                    {
                        String itemCode = resultSet.getString("item_code"); //get the element in column "item_code"
                        modelTest.addElement(itemCode); //add each item to the model
                    }
                    listHistory.setModel(modelTest);
                    resultSet.close();
                } catch (Exception g){
                    System.err.println( g.getClass().getName() + ": " + g.getMessage() );
                    System.exit(0);
                }

                JPanel Hist = new JPanel();
                listHistory.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
                //listScroller = new JScrollPane(list);
                scrollHist = new JScrollPane();
                scrollHist.setViewportView(listHistory);
                Hist.add(scrollHist);
                JOptionPane.showMessageDialog(frame, Hist);
                // JOptionPane.showMessageDialog(frame, message, "Sudoku", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }
}


*/