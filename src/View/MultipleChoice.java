package View;

import DAO.ConnectionDatabase;

import DAO.QuestionQuery;
import Model.Multiple_Choice_Question;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

public class MultipleChoice extends JFrame implements ActionListener {
    private static String input;
    JButton addButton, clear;
    JTextField quizIDField, idField, questionField, aField, bField, cField, dField, keyField;
    DefaultTableModel model;
    JFrame frame;
    public MultipleChoice(String input) {
        this.input = input;
        frame = new JFrame("Multiple Choice") ;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setLayout(null);

        String[] columnNames = {"Quiz ID", "Question ID", "Question", "A", "B", "C", "D", "Key"};
        Object[][] data = {

        };
        model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 20, 950, 200);
        frame.add(scrollPane);

        // Panel nhập dữ liệu
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(null);
        inputPanel.setBounds(20, 300, 950, 300);
        //JTextField nhập dữ liệu
        JLabel id = new JLabel("Question ID");
        id.setBounds(0, 0  , 150, 20);
        inputPanel.add(id);
        idField = new JTextField(10);
        idField.setBounds(200, 0, 100, 25);
        inputPanel.add(idField);

        JLabel question = new JLabel("Question");
        question.setBounds(0, 30, 150, 20);
        inputPanel.add(question);
        questionField = new JTextField(30);
        questionField.setBounds(200, 30, 300, 25);
        inputPanel.add(questionField);

        JLabel a = new JLabel("Option A");
        a.setBounds(0, 60, 150, 20);
        inputPanel.add(a);
        aField = new JTextField(10);
        aField.setBounds(200, 60, 300, 25);
        inputPanel.add(aField);

        JLabel b = new JLabel("Option B");
        b.setBounds(0, 90, 150, 20);
        inputPanel.add(b);
        bField = new JTextField(10);
        bField.setBounds(200, 90, 300, 25);
        inputPanel.add(bField);

        JLabel c = new JLabel("Option C");
        c.setBounds(0, 120, 150, 20);
        inputPanel.add(c);
        cField = new JTextField(10);
        cField.setBounds(200, 120, 300, 25);
        inputPanel.add(cField);

        JLabel d = new JLabel("Option D");
        d.setBounds(0, 150, 150, 20);
        inputPanel.add(d);
        dField = new JTextField(10);
        dField.setBounds(200, 150, 300, 25);
        inputPanel.add(dField);

        JLabel key = new JLabel("Your Answer");
        key.setBounds(0, 180, 150, 20);
        inputPanel.add(key);
        keyField = new JTextField(10);
        keyField.setBounds(200, 180, 300, 25);
        inputPanel.add(keyField);

        //JButton
        addButton = new JButton("Add question");
        addButton.setBounds(600, 60, 150, 20);
        addButton.addActionListener(this);
        inputPanel.add(addButton);
        clear = new JButton("Clear");
        clear.setBounds(600, 100, 150, 20);
        clear.addActionListener(this);
        inputPanel.add(clear);
        // thêm các component vào inputPanel

        //them inputPanel vào cửa sổ
        frame.add(inputPanel);
        frame.setVisible(true);

    }
    public static void main(String[] args){
        new MultipleChoice(input);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String quizid = input;
        String questionid = idField.getText();
        String question = questionField.getText();
        String a = aField.getText();
        String b = bField.getText();
        String c = cField.getText();
        String d = dField.getText();
        String key = keyField.getText();
        Multiple_Choice_Question mc = new Multiple_Choice_Question(input, questionid, question, a, b, c, d, key);
        boolean isSuccess = QuestionQuery.insertMC(mc);
        if(e.getSource() == addButton) {
            if (questionid.isEmpty() || question.isEmpty() | a.isEmpty() || b.isEmpty() || c.isEmpty() || d.isEmpty() || key.isEmpty()){
                JOptionPane.showMessageDialog(
                        this,
                        "Please fill the blank",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE
                );
            } else {
                if(isSuccess) {
                    model.addRow(new Object[]{input,questionid, question, a, b, c, d, key});
                    JOptionPane.showMessageDialog(
                            this,
                            "Added question successfully",
                            "Notification",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
            }
        }
    }
}
