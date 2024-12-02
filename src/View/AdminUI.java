package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class AdminUI extends JFrame implements ActionListener {
    // Các nút trong thanh điều hướng
    JMenu users, newQuiz, updateQuiz, allQuiz, stuRes, logOut;
    JTextField searchQuiz;
    JButton search;
    public AdminUI() {
        // Thiết lập JFrame
        setTitle("ADMIN");
        setSize(900, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        
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


        allQuiz = new JMenu("All Quiz");
        ImageIcon iconAllQuiz = new ImageIcon("D:\\image\\grid.png");
        allQuiz.setIcon(iconAllQuiz);
        menubar.add(allQuiz);
        JMenuItem allquiz = new JMenuItem("All Quiz Created");
        allquiz.setIcon(iconAllQuiz);
        allquiz.addActionListener(this);
        allQuiz.add(allquiz);

        stuRes = new JMenu("Student Result");
        ImageIcon iconStuRes = new ImageIcon("D:\\image\\medical-result.png");
        stuRes.setIcon(iconStuRes);
        menubar.add(stuRes);
        JMenuItem stuResASItem = new JMenuItem("All Results");
        stuRes.setIcon(iconStuRes);
        stuResASItem.addActionListener(this);
        stuRes.add(stuResASItem);

        logOut = new JMenu("Log Out");
        ImageIcon iconLogOut = new ImageIcon("D:\\image\\logout.png");
        logOut.setIcon(iconLogOut);
        menubar.add(logOut);
        JMenuItem logOutItem = new JMenuItem("Log Out");
        logOutItem.setIcon(iconLogOut);
        logOutItem.addActionListener(this);
        logOut.add(logOutItem);

        JLabel s = new JLabel("SEARCH QUIZ");
        s.setBounds(200, 150, 200, 20);
        add(s);
        searchQuiz = new JTextField();
        searchQuiz.setBounds(350, 150, 200, 20);
        ImageIcon icSearch = new ImageIcon("search-results_5358562.png");
        s.setIcon(icSearch);
        add(searchQuiz);
        search = new JButton("Search");
        search.setBounds(600, 150, 100, 20);
        search.setBackground(new Color(0, 51, 102));
        search.setForeground(Color.WHITE);
        search.setFont(new Font("Arial", Font.BOLD, 11)); // Chọn font Arial, kiểu chữ thường, cỡ 16
        search.addActionListener(this);
        add(search);

        add(menubar, BorderLayout.NORTH);
        setVisible(true);


    }
    public static void main(String[] args) {
        new AdminUI();
    }
    @Override
    public void actionPerformed (ActionEvent e) {
        String text = e.getActionCommand(); 

        if(text.equals("Create")) {
            new CreateUserUI_Admin();
        } else if (text.equals("Remove")) {
            new RemoveUserUI_Admin();
        } else if(text.equals("View")) {
            new ViewUserUI_Admin();
        } else if(text.equals("Multiple Choice")) {
            new createQuizUI(text);
        } else if (text.equals("Answer Filling")) {
            new createQuizUI(text);
        } else if (text.equals("All Results")) {
            new ResultQuizofAS();
        }else if (text.equals("All Quiz Created")) {
            //new TeacherUI(teacher).setVisible(true);
            new AllQuiz();
        }else if(e.getSource() == search) {
            String searchContent = searchQuiz.getText();
            new UpdateQuiz(searchContent);
        }  else if (text.equals("Log Out")) {
            setVisible(false);
            new Login();
        }

    }
}





