import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
//Constructor that assembles the components into a launchable frame//
public class RockPaperScissorsFrame extends JFrame {
    public RockPaperScissorsFrame(String title){
        super(title);
        setLayout(new FlowLayout());
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize=kit.getScreenSize();
        FlowLayout mainLineup =new FlowLayout();
        Random comMoveMaker = new Random();

        /**
         * STAT COUNTERS
         * These call the StatCounter class
         * StatCounter has one attribute, an int called Count
         *This provides a count of interactions that can be updated from anywhere
         * */
        StatCounter winCounter = new StatCounter(0);
        StatCounter loseCounter = new StatCounter(0);
        StatCounter tieCounter = new StatCounter(0);

        /**
         * MAIN CONTAINER
         * This is a FlowLayout as per the default, arranging items in a line
         */

        JPanel mainPanel= new JPanel(mainLineup);
        this.add(mainPanel); //Sets up a panel to hold everything else//
        this.setSize(screenSize.width,screenSize.height);

        /**
         * OUTPUT WINDOW
         * This doesn't scroll, although I don't know why
         * */

        JTextArea gameLog = new JTextArea();
        gameLog.setPreferredSize(new Dimension(400,screenSize.height));
        JScrollPane gameLogScroller = new JScrollPane(gameLog);
        gameLogScroller.setSize(new Dimension(400, screenSize.height));
        gameLogScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        mainPanel.add(gameLogScroller);

        /**STAT LABELS
         * These are supposed  to be updated by the JButtons, but they remain static
         * With my knowledge I don't know how to resolve this*/
        GridLayout statCluster = new GridLayout();
        JPanel statDisplay = new JPanel();
        //WINS//
        int timesWon=0;
        JLabel winLabel =new JLabel("Times you've won: "+winCounter.getCount());
        winLabel.setSize(new Dimension(30,30));
        statDisplay.add(winLabel);
        //LOSSES//
        int timesLost=0;
        JLabel lossLabel = new JLabel("Times you've lost: "+loseCounter.getCount());
        lossLabel.setSize(new Dimension(30,30));
        statDisplay.add(lossLabel);
        //TIES//
        int timesTied=0;
        JLabel tieLabel = new JLabel("Times you've tied: "+tieCounter.getCount());
        tieLabel.setSize(new Dimension(30,30));
        statDisplay.add(tieLabel);

        /**
         * GAMEPLAY BUTTONS
         * All of these contain an ActionListener that actually plays the game of Rock Paper Scissors and appends the result to the JTextArea
         * They also update the StatCounters
         * */
        GridLayout buttonGrid=new GridLayout(2,2);//Defines the grid for the button cluster//
        JPanel buttonCluster= new JPanel(buttonGrid);//Holds the buttons for playing and quitting the game//
        mainPanel.add(buttonCluster);//Attaches the buttons to the main panel//

        //ROCK//
        ImageIcon rockIcon= new ImageIcon("src\\Rock.png");
        JButton rockButton =new JButton();
        rockButton.setIcon(rockIcon);
        rockButton.setSize(30,30);
        rockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int commMove=comMoveMaker.nextInt(3);
                if (commMove==0){gameLog.append("\nRock and Rock- tie");
                tieCounter.setCount(tieCounter.getCount()+1);}
                else if (commMove==1) {gameLog.append("\nRock and Paper- COM wins");
                loseCounter.setCount(loseCounter.getCount()+1);}
                else if (commMove==2){gameLog.append("\nRock and Scissors- You win");
                winCounter.setCount(winCounter.getCount()+1);}

            }
        });
        buttonCluster.add(rockButton);

        //PAPER//
        ImageIcon paperIcon= new ImageIcon("src\\Paper.jpg");
        JButton paperButton =new JButton();
        paperButton.setIcon(paperIcon);
        paperButton.setSize(30,30);
        paperButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int commMove=comMoveMaker.nextInt(3);
                if (commMove==0){gameLog.append("\nPaper and Rock- You win");
                winCounter.setCount(winCounter.getCount()+1);}
                else if (commMove==1) {gameLog.append("\nPaper and Paper- Tie");
                tieCounter.setCount(tieCounter.getCount()+1);}
                else if (commMove==2){gameLog.append("\nPaper and Scissors- COM wins");}

            }
        });
        buttonCluster.add(paperButton);

        //SCISSORS//
        ImageIcon scissorIcon= new ImageIcon("src\\Scissors.png");
        JButton scissorButton =new JButton();
        scissorButton.setIcon(scissorIcon);
        scissorButton.setSize(30,30);
        scissorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int commMove=comMoveMaker.nextInt(3);
                if (commMove==0){gameLog.append("\nScissors and Rock- COM wins");
               loseCounter.setCount(loseCounter.getCount()+1); }
                else if (commMove==1) {gameLog.append("\nScissors and Paper- You win");
                winCounter.setCount(winCounter.getCount()+1);}
                else if (commMove==2){gameLog.append("\nScissors and Scissors- tie");
                tieCounter.setCount(tieCounter.getCount());}

            }
        });
        buttonCluster.add(scissorButton);

        //QUIT//
        JButton quitButton =new JButton("Quit");
        quitButton.setSize(30,30);
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        buttonCluster.add(quitButton);

        mainPanel.add(statDisplay);

    }

}
