import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizApplication extends JFrame implements ActionListener 
{
    String[] questions = 
    {
            "Who is Father of Nation?",
            "What is 2+3?",
            "What is the capital of Telangana?"
    };
    String[][] options = 
    {
            {"Nehru", "BhagatSingh", "Gandhi", "VallabaiPatel"},
            {"4", "5", "6", "7"},
            {"Hyderabad", "Chennai", "Delhi", "Mumbai"}
    };
    int[] answers = {2, 1, 0};  
    JLabel questionLabel;
    JRadioButton[] radioButtons;
    ButtonGroup buttonGroup;
    JButton submitButton;
    JLabel timerLabel;
    Timer timer;
    int currentQuestionIndex = 0;
    int score = 0;
    int timeLimit = 10;
    int timeRemaining = timeLimit;
    public QuizApplication() 
    {
        setTitle("Quiz Application with Timer");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        questionLabel = new JLabel(questions[currentQuestionIndex], SwingConstants.CENTER);
        add(questionLabel, BorderLayout.NORTH);
        radioButtons = new JRadioButton[4];
        buttonGroup = new ButtonGroup();
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(4, 1));
        for (int i = 0; i < 4; i++) 
	{
            radioButtons[i] = new JRadioButton(options[currentQuestionIndex][i]);
            buttonGroup.add(radioButtons[i]);
            optionsPanel.add(radioButtons[i]);
        }
        add(optionsPanel, BorderLayout.CENTER);
        timerLabel = new JLabel("Time left: " + timeRemaining + " seconds", SwingConstants.CENTER);
        add(timerLabel, BorderLayout.SOUTH);
        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        add(submitButton, BorderLayout.EAST);
        timer = new Timer(1000, new ActionListener() 
	{
            @Override
            public void actionPerformed(ActionEvent e) 
	    {
                timeRemaining--;
                timerLabel.setText("Time left: " + timeRemaining + " seconds");
                if (timeRemaining <= 0) {
                    timer.stop();
                    evaluateAnswer();
                    nextQuestion();
                }
            }
        });
        timer.start();
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        timer.stop();
        evaluateAnswer();
        nextQuestion();
    }
    private void evaluateAnswer() 
    {
        int selectedOption = -1;
        for (int i = 0; i < radioButtons.length; i++) 
	{
            if (radioButtons[i].isSelected()) 
	    {
                selectedOption = i;
                break;
            }
        }
        if (selectedOption == answers[currentQuestionIndex]) 
	{
            score++;
        }
    }

    private void nextQuestion() 
    {
        currentQuestionIndex++;
        if (currentQuestionIndex < questions.length) 
	{
            questionLabel.setText(questions[currentQuestionIndex]);
            buttonGroup.clearSelection();
            for (int i = 0; i < radioButtons.length; i++) {
                radioButtons[i].setText(options[currentQuestionIndex][i]);
        }
            timeRemaining = timeLimit;
            timerLabel.setText("Time left: " + timeRemaining + " seconds");
            timer.start();
        } 
	else 
	{
            showResults();
        }
    }
    private void showResults() 
    {
        JOptionPane.showMessageDialog(this, "Quiz Over! Conragats... Your score: " + score + "/" + questions.length);
        System.exit(0);
    }
    public static void main(String[] args) 
    {
        new QuizApplication();
    }
}
