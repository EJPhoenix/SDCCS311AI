import java.sql.SQLOutput;
import java.util.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class Main {
    public static void main(String[] args) throws IOException {
        // Initialize 2D array - 6 questions with 4 answers each
        String[][] surveyQuestions = {
                {"1: What are you feeling regarding 2nd Amendment Rights in light of recent mass-shootings?\n",
                        "A. Recent mass-shooting give clear evidence that stricter gun control measures are needed\n",
                        "B. Responsible gun ownership education is necessary, but stricter gun control measures are not necessarily the answer\n",
                        "C. While tragic, recent mass shootings should not compromise the fundamental 2nd Amendment Rights of law-abiding citizens\n",
                        "D. While tragic, recent mass shootings should be addressed though non-coercive means\n"},
                {"2: Does the government have a Constitutional or moral responsibility to financially support its citizens?\n",
                        "A. The government has a constitutional responsibility to protect individual freedoms and limited involvement in citizens' financial matters\n",
                        "B. Yes, the government has both a constitutional and moral responsibility to provide financial support to its citizens\n",
                        "C. The government's role should be limited, focusing on protecting individual rights rather than providing financial support\n",
                        "D. Yes, the government has a moral responsibility to provide financial support to its citizens\n"},
                {"3: How should the government handle immigration issues?\n",
                        "A. The government should prioritize border security, enforce existing immigration laws, and promote legal pathways for immigration\n",
                        "B. The government should address immigration issues through comprehensive reform that includes a pathway to citizenship, border security, and humane treatment of migrants\n",
                        "C. The government should prioritize compassionate and comprehensive immigration policies, including a pathway to citizenship and fair treatment for migrants\n",
                        "D. Government intervention in immigration should be minimized, emphasizing voluntary interactions, and exploring market-based solutions\n"},
                {"4: Does the United States possess a duty to assist other nations, whether that be humanitarian or militaristic aid?\n",
                        "A. The United States has a duty to provide humanitarian aid to other nations and should prioritize diplomatic solutions over militaristic interventions\n",
                        "B. Yes, the United States has a duty to provide assistance to other nations, both in humanitarian aid and, when appropriate, through diplomatic and international cooperation\n",
                        "C. The United States should engage in diplomatic efforts but prioritize its national interests in deciding whether to provide aid to other nations\n",
                        "D. The U.S. should prioritize non-interventionism, refraining from compulsory involvement in other nations' affairs, and favoring voluntary aid\n"},
                {"5: Abortion. Yes or No?\n",
                        "A. Yes, American women should have the right to choose, but that does not mean I necessarily support abortion\n",
                        "B. Yes, American women should have the right to choose\n",
                        "C. No, I am \"Pro-Life\" so abortion should not be legalized by either the federal or State government\n",
                        "D. Yes, American women should have the right to choose and the government should never be involved in personal freedoms such as healthcare decisions\n"},
                // Last question to ask which political party user affiliates with
                {"6: What is your political party affiliation?\n",
                        "A. Republican Party\n",
                        "B. Democratic Party\n",
                        "C. Libertarian Party\n",
                        "D. Green Party\n",
                }
        };
        // Assign each question a point value
        int[] questionValues = {5, 2, 5, 3, 4, 8};
        // Create variables to help represent each of the 4 parties in the survey, using int for scorekeeping
        int republicanSCORE = 0;
        int democraticScore = 0;
        int libertarianScore = 0;
        int greenScore = 0;
        // Prompt user with a question, initialize input scanner, declare string for user response
        boolean surveyComplete = false;
        Scanner userScan = new Scanner(System.in);
        String userResponse = "";
        // LinkedHashMap for the Q&A storage
        Map<String, String> questionAndChoice = new LinkedHashMap<>();
        // Welcome message
        System.out.println("Welcome to PartyPulse!!!\n---Pinpointing Your Political Position---\n*INSTRUCTIONS*\nSelect a response from the options below, and we'll predict which political party aligns best with your views! \n");
        // Begin loop for survey, questions will come as the user inputs an answer to make it easier to read and understand the questions
        while(!surveyComplete) {
            for(int i = 0; i < surveyQuestions.length; i++) {
                String question = surveyQuestions[i][0];
                System.out.print("Question " + question);
                for(int j = 1; j < surveyQuestions[i].length; j++) {
                    String answer = surveyQuestions[i][j];
                    System.out.print(answer);
                }
                // Accept user input, regardless of upper/lower case
                userResponse = userScan.nextLine().trim().toUpperCase();
                if (userResponse.matches("[A-Da-d](\\.)?")) {
                    // Response validations
                    if (userResponse.equalsIgnoreCase("A") || userResponse.equalsIgnoreCase("A.")) {
                        republicanSCORE += questionValues[i];
                        userResponse = surveyQuestions[i][1];
                        System.out.println("You selected: " + surveyQuestions[i][1]);
                    } else if (userResponse.equalsIgnoreCase("B") || userResponse.equalsIgnoreCase("B.")) {
                        democraticScore += questionValues[i];
                        userResponse = surveyQuestions[i][2];
                        System.out.println("You selected: " + surveyQuestions[i][2]);
                    } else if (userResponse.equalsIgnoreCase("C") || userResponse.equalsIgnoreCase("C.")) {
                        libertarianScore += questionValues[i];
                        userResponse = surveyQuestions[i][3];
                        System.out.println("You selected: " + surveyQuestions[i][3]);
                    } else if (userResponse.equalsIgnoreCase("D") || userResponse.equalsIgnoreCase("D.")) {
                        greenScore += questionValues[i];
                        userResponse = surveyQuestions[i][4];
                        System.out.println("You selected: " + surveyQuestions[i][4]);
                    }
                    // Storing Q&A from LinkedHashMap
                    int selectedQuestionIndex = i;
                    String selectedQuestion = surveyQuestions[selectedQuestionIndex][0];
                    String selectedChoice = surveyQuestions[selectedQuestionIndex][userResponse.charAt(0) - 'A' + 1];
                    questionAndChoice.put(selectedQuestion, selectedChoice);
                    surveyComplete = true;
                    // Catch provided to re-prompt user with question in case of entry error/etc
                } else {
                    System.out.println("Please select from one of the valid responses\n");
                    i--;
                }
            }
            // Create HashMap to help calculate and print out winner
            Map<Integer, String> partyResult = new HashMap<>();
            partyResult.put(republicanSCORE, "Republican Party");
            partyResult.put(democraticScore, "Democratic Party");
            partyResult.put(libertarianScore, "Libertarian Party");
            partyResult.put(greenScore, "Green Party");
            // INT calculates winner using Math.max and then assigns score to string that will be displayed to the player
            int partyWinner = Math.max(Math.max(republicanSCORE,democraticScore),Math.max(libertarianScore,greenScore));
            String winningPartyName = partyResult.get(partyWinner);
            // Party affiliation winner revealed in a string
            System.out.println("Upon review, your views align most closely with the " + winningPartyName + "!\n");
            // Print out score end totals for extra information
            System.out.println("Republican Party Score: " + republicanSCORE);
            System.out.println("Democrat Party Score: " + democraticScore);
            System.out.println("Libertarian Party Score: " + libertarianScore);
            System.out.println("Green Party Score " + greenScore);
            // Winning parties score/answers saved in text file. Upon moving to IntelliJ, cant get this to work anymore...
            File outputFile = new File(winningPartyName + ".txt");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                // Write responses to the winner's text file. Catch if there is an error
                for(Map.Entry<String, String> entry : questionAndChoice.entrySet()){
                    String selectedQuestion = entry.getKey();
                    String selectedChoice = entry.getValue();

                    writer.write(selectedQuestion);
                    writer.write(selectedChoice);
                    writer.newLine();;
                }
                writer.flush();
                System.out.println("Filed created successfully");
            } catch (IOException e) {
                System.out.println("Error writing to the file: " + e.getMessage());
            }
        }
    }
}