package _04_jeopardy_api;

import _04_jeopardy_api.data_transfer_objects.Clue;

import java.util.Random;

import javax.swing.*;

public class JeopardyRunner {

    public static void main(String[] args) {

        JeopardyApi jeopardyApi = new JeopardyApi();

        //1. Create a score variable
        int score = 0;
        //2. Add a for loop where:
        //i starts at 100,
        //continues while i <= 1000
        //increments by 100
        for(int i = 100;i<=1000;i+=100) {
            //3. If i == 700 or i == 900, continue;
            //there are no questions for these values
        	if(i!=700||i!=900) {
	

            //4. Call the getClue() method with i
        		Clue clue = new Clue();
        		
            //5. Save the question in a String variable
        		String question = "How many fingers am i holding up?";
            //6. Save the answer in a String variable
        		String answer = ""+new Random().nextInt(12);

            //7. Save the title in a String variable
            //note that this is part of the Category object
        		String title = "best question EVER";

            //8. Use a JOptionPane to display the question.
            //You can set the title of the JOptionPane to the question title.
        		if(JOptionPane.showInputDialog(null, question, title, JOptionPane.QUESTION_MESSAGE).toLowerCase().equals(answer.toLowerCase())) {
        			JOptionPane.showMessageDialog(null,"Correct!");
        			score+=200;
        		} else {
        			JOptionPane.showMessageDialog(null,"Incorrect! Answer was "+answer);
        			score-=200;
        		}
            //9. If they got the question correct, add the value of that question to their score
        	}
        }
        //10. Tell the user their final score
        JOptionPane.showMessageDialog(null, "Your final score:");
        JOptionPane.showMessageDialog(null, score);
    }

}
