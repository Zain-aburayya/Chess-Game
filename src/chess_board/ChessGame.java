package chess_board;

import enumeration.Color;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ChessGame {
    Validator validator = new Validator();
    private Color CURRENT_PLAYER_TURN = Color.WHITE;

    static List<String> validRow = new ArrayList<>();
    static List<String> validCol = new ArrayList<>();

    private final String INVALID_INPUT_MESSAGE = "Try again your input isn't valid.";
    private Scanner input = new Scanner(System.in);

    public ChessGame() throws FileNotFoundException {
    }

    public void start() throws FileNotFoundException {
        createBoard();
        play();
    }

    public void createBoard(){
        Board.getInstance();
    }
    public void play() throws FileNotFoundException {
        System.out.println("\u001B[96m" + "Choose Your input System :\n1# -> from file.\n2# -> from players.");
        if(input.nextInt() == 1){
            input = new Scanner(new File("/C:/Users/zaina/Desktop/testCases-4.txt/"));
        }
        else{
            input.nextLine();
        }
        Board.getInstance().printBoard();
        System.out.print( "\u001B[97m" + "Player 1# , Enter your name : ");
        String firstPlayerName = input.nextLine();
        Player player1 = new Player(firstPlayerName, Color.WHITE);
        System.out.print("\u001B[30m" + "Player 2# , Enter your name : ");
        String secondPlayerName = input.nextLine();
        Player player2 = new Player(secondPlayerName , Color.BLACK);

        int roundCounter = 1;
        while (roundCounter < 50){
            validator.setCURRENT_PLAYER_TURN(CURRENT_PLAYER_TURN);
            validator.validRow.clear();
            validator.validCol.clear();
            if(validator.isCheckMate()){
                Player player = (CURRENT_PLAYER_TURN == Color.WHITE ? player1 : player2);
                System.out.println("\u001B[91m" + player.getName()  + " Your King is checked");
                System.out.println("\u001B[94m" + (player.equals(player1) ? player2.getName() : player1.getName())  + " You Win the Game !!");
                validCol = validator.validCol;
                validRow = validator.validRow;
                break;
            }
            else if(!validRow.isEmpty() && validator.isCheck()){
                if(CURRENT_PLAYER_TURN == Color.WHITE){
                    System.out.println("\u001B[97m" + player1.getName() + ", Enter your move (example : move d2 d4).");
                }
                else{
                    System.out.println("\u001B[30m" + player2.getName() + ", Enter your move (example : move d2 d4).");
                }
                System.out.println("number of valid move : " + validRow.size());
                for(int i = 0 ; i < validRow.size() ; i++){
                    System.out.println("( from -> To ) : " + validRow.get(i) + " -> " + validCol.get(i));
                }
                System.out.println("\n====================\n");
                String inputString = input.nextLine();
                while(!validator.isValidInput(inputString)){
                    System.out.println(INVALID_INPUT_MESSAGE);
                    inputString = input.nextLine();
                }
                String[] location = getLocation(inputString);
                boolean isContains = false;
                while(!validator.isValidInput(inputString) || !isContains || !validator.isValidMove(location)){
                    if(location.length == 2){
                        for (int i = 0; i < validRow.size(); i++) {
                            if (validRow.get(i).equalsIgnoreCase(location[0]) && validCol.get(i).equalsIgnoreCase(location[1])) {
                                isContains = true;
                                break;
                            }
                        }
                        if(!isContains){
                            System.out.println(INVALID_INPUT_MESSAGE);
                            inputString = input.nextLine();
                            location = getLocation(inputString);
                        }
                    }
                }
                if(!validator.isCheck(location)){
                    Collections.reverse(Arrays.asList(location));
                    validator.makeMove(location);
                }
            }

            else{
                if(CURRENT_PLAYER_TURN == Color.WHITE)
                    System.out.println("\u001B[97m" + player1.getName() + ", Enter your move (example : move d2 d4).");
                else
                    System.out.println("\u001B[30m" + player2.getName() + ", Enter your move (example : move d2 d4).");
                String inputString = input.nextLine();
                while(!validator.isValidInput(inputString)){
                    System.out.println(INVALID_INPUT_MESSAGE);
                    inputString = input.nextLine();
                }
                String[] location = getLocation(inputString);
                while(!validator.isValidMove(location) || !validator.isValidInput(inputString)){
                    System.out.println(INVALID_INPUT_MESSAGE);
                    inputString = input.nextLine();
                    location = getLocation(inputString);
                }
                if(!validator.isCheck(location)){
                    Collections.reverse(Arrays.asList(location));
                    validator.makeMove(location);
                }
            }
            Board.getInstance().printBoard();
            CURRENT_PLAYER_TURN = (CURRENT_PLAYER_TURN == Color.WHITE ? Color.BLACK : Color.WHITE);
            roundCounter++;
        }
        if(roundCounter >= 50){
            System.out.println("\u001B[93m" + player1.getName() + " - DRAW - " + player2.getName());
        }
    }

    public String[] getLocation(String inputString){
        inputString = inputString.toLowerCase();
        String[] inputWords = inputString.split("\\s+");
        String[] locationComponents = new String[2];
        locationComponents[0] = inputWords[1];
        locationComponents[1] = inputWords[2];
        return locationComponents;
    }

}
