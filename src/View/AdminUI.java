package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class AdminUI extends JFrame implements ActionListener {
    // Các nút trong thanh điều hướng
    JMenu users, newQuiz, updateQuiz, allQuiz, stuRes, logOut;

    public AdminUI() {
        // Thiết lập JFrame
        setTitle("ADMIN");
        setSize(900, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // icon frame
        ImageIcon iconAdmin = new ImageIcon("D:\\image\\admin.png");
        setIconImage(iconAdmin.getImage());

        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);

        //Users
        users = new JMenu("Users");
        ImageIcon icUsers = new ImageIcon("D:\\image\\user.png");
        users.setIcon(icUsers);
        menubar.add(users);
        
        JMenuItem createUser = new JMenuItem("Create");
        createUser.addActionListener(this);
        ImageIcon icCreateUser = new ImageIcon("D:\\image\\create.png");
        createUser.setIcon(icCreateUser);
        users.add(createUser);
        
        JMenuItem removeUser = new JMenuItem("Remove");
        removeUser.addActionListener(this);
        ImageIcon icRemoveUser = new ImageIcon("D:\\image\\remove.png");
        removeUser.setIcon(icRemoveUser);
        users.add(removeUser);
        
        JMenuItem viewUser = new JMenuItem("View");
        viewUser.addActionListener(this);
        ImageIcon icViewUser = new ImageIcon("D:\\image\\view.png");
        viewUser.setIcon(icViewUser);
        users.add(viewUser);
        
        // New Quiz
        newQuiz = new JMenu("New Quiz");
        ImageIcon icNewQuiz = new ImageIcon("D:\\image\\add.png");
        newQuiz.setIcon(icNewQuiz);
        menubar.add(newQuiz);

        JMenuItem mc = new JMenuItem("Multiple Choice");
        mc.addActionListener(this);
        ImageIcon icMC = new ImageIcon("D:\\image\\option.png");
        mc.setIcon(icMC);
        newQuiz.add(mc);

        JMenuItem answerFilling = new JMenuItem("Answer Filling");
        answerFilling.addActionListener(this);
        ImageIcon icAnswerFilling = new ImageIcon("D:\\image\\answer.png");
        answerFilling.setIcon(icAnswerFilling);
        newQuiz.add(answerFilling);
   
        // Update Quiz
        updateQuiz = new JMenu("Update Quiz");
        ImageIcon iconUpdateQuiz = new ImageIcon("D:\\image\\follow-up.png");
        updateQuiz.setIcon(iconUpdateQuiz);
        menubar.add(updateQuiz);

        allQuiz = new JMenu("All Quiz");
        ImageIcon iconAllQuiz = new ImageIcon("D:\\image\\grid.png");
        allQuiz.setIcon(iconAllQuiz);
        menubar.add(allQuiz);

        stuRes = new JMenu("Student Result");
        ImageIcon iconStuRes = new ImageIcon("D:\\image\\medical-result.png");
        stuRes.setIcon(iconStuRes);
        menubar.add(stuRes);

        logOut = new JMenu("Log Out");
        ImageIcon iconLogOut = new ImageIcon("D:\\image\\logout.png");
        logOut.setIcon(iconLogOut);
        menubar.add(logOut);
        
        add(menubar, BorderLayout.NORTH);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);


    }
    public static void main(String[] args) {
        new AdminUI();
    }
    @Override
    public void actionPerformed (ActionEvent e) {
        String text = e.getActionCommand(); 

        if(text.equals("Create")) {
            new CreateUserUI();
        } else if (text.equals("Remove")) {
            new RemoveUserUI();
        } else if(text.equals("View")) {
            new ViewUserUI();
        } else if(text.equals("Multiple Choice")) {
            new createQuizUI(text);
        } else if (text.equals("Answer Filling")) {
            new createQuizUI(text);
        } else if (text.equals("All Student Result")) {
           // new StudentResultUI();
        }
    }
}





