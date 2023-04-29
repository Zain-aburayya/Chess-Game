package chess_board;

import enumeration.Color;
import enumeration.PieceType;
import piece.Piece;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    public void setCURRENT_PLAYER_TURN(Color CURRENT_PLAYER_TURN) {
        this.CURRENT_PLAYER_TURN = CURRENT_PLAYER_TURN;
    }

    Color CURRENT_PLAYER_TURN;
    List<String> validRow = new ArrayList<>();
    List<String> validCol = new ArrayList<>();
    public Validator() throws FileNotFoundException {
    }

    public boolean isValidInput(String input){
        String inputFormatRegex = "^move\\s+[a-h][1-8]\\s+[a-h][1-8]";
        Pattern pattern = Pattern.compile(inputFormatRegex , Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
    public boolean isValidMove(String[] locationComponents){

        int fromCol = locationComponents[0].charAt(0) - 'a' + 1;
        int fromRow = locationComponents[0].charAt(1) - '0';
        int toCol = locationComponents[1].charAt(0) - 'a' + 1;
        int toRow = locationComponents[1].charAt(1) - '0';

        Cell[][] cells = Board.getInstance().getCells();
        Piece fromPiece = cells[fromRow][fromCol].getPiece();
        Piece toPiece = cells[toRow][toCol].getPiece();

        if(fromPiece == null) {
            return false;
        }

        if(fromPiece.getPieceColor() != CURRENT_PLAYER_TURN) {
            return false;
        }

        if(toPiece != null && toPiece.getPieceColor() == CURRENT_PLAYER_TURN) {
            return false;
        }

        return fromPiece.isValidMove(locationComponents);
    }

    public void makeMove(String[] location){

        int fromCol = location[0].charAt(0) - 'a' + 1;
        int fromRow = location[0].charAt(1) - '0';
        int toCol = location[1].charAt(0) - 'a' + 1;
        int toRow = location[1].charAt(1) - '0';

        Cell[][] cells = Board.getInstance().getCells();

        cells[toRow][toCol].setPiece(cells[fromRow][fromCol].getPiece());
        cells[fromRow][fromCol].setPiece(null);
    }

    public String getKingLocation(){

        Cell[][] cells = Board.getInstance().getCells();

        int kingRow = -1;
        int kingCol = -1;

        for(int i = 1 ; i <= 8 ; i++) {
            for (int j = 1; j <= 8; j++) {
                if (cells[i][j].getPiece() != null && cells[i][j].getPiece().getPieceColor() == CURRENT_PLAYER_TURN
                        && cells[i][j].getPiece().getPieceType() == PieceType.KING) {
                    kingRow = i;
                    kingCol = j;
                }
            }
        }

        return "" + ((char)(kingCol + 'a' - 1)) + kingRow;
    }
    public boolean isCheck(){
        return isCheck(null);
    }
    public boolean isCheck(String[] moveLocation){
        Cell[][] cells = Board.getInstance().getCells();
        if(moveLocation != null){
            makeMove(moveLocation);
            Collections.reverse(Arrays.asList(moveLocation));
        }
        String[] location = new String[2];
        location[1] = getKingLocation();
        String enemyLocation;
        Piece piece = null;
        for(int i = 1 ; i <= 8 ; i++) {
            for (int j = 1; j <= 8; j++) {
                Piece currentPiece = cells[i][j].getPiece();
                if (currentPiece != null && currentPiece.getPieceColor() != CURRENT_PLAYER_TURN) {
                    enemyLocation = "" + ((char)(j + 'a' - 1)) + i;
                    location[0] = enemyLocation;
                    if(currentPiece.isValidMove(location)){
                        if(moveLocation != null)
                            makeMove(moveLocation);
                        return true;
                    }
                }
            }
        }
        if(moveLocation != null) {
            makeMove(moveLocation);
        }
        return false;
    }
    public boolean isCheckMate(){
        if(!isCheck()){
            return false;
        }
        Cell[][] cells = Board.getInstance().getCells();
        for(int i = 1 ; i <= 8 ; i++){
            for(int j = 1 ; j <= 8 ; j++){
                Piece piece = cells[i][j].getPiece();
                if(piece != null && piece.getPieceColor() == CURRENT_PLAYER_TURN){
                    for(int m = 1 ; m <= 8 ; m++){
                        for(int n = 1 ; n <= 8 ; n++){
                            String[] location = convertLocation(i , j , m , n);
                            if(isValidMove(location)){
                                Piece toPiece = cells[m][n].getPiece();
                                cells[m][n].setPiece(piece);
                                cells[i][j].setPiece(null);
                                if(!isCheck()){
                                    validRow.add(location[0]);
                                    validCol.add(location[1]);
                                }
                                cells[i][j].setPiece(piece);
                                cells[m][n].setPiece(toPiece);
                            }
                        }
                    }
                }
            }
        }
        ChessGame.validRow = validRow;
        ChessGame.validCol = validCol;
        return validRow.isEmpty();
    }

    public String[] convertLocation(int fromRow , int fromCol , int toRow , int toCol){
        String[] location = new String[2];
        location[0] = ("" + ((char)(fromCol + 'a' - 1)) + fromRow);
        location[1] = ("" + ((char)(toCol + 'a' - 1)) + toRow);
        return location;
    }

}
