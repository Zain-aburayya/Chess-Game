package chess_board;

import enumeration.Color;
import enumeration.PieceType;
import piece.PieceFactory;

public class Board {
    private static final int BOARD_SIZE = 9;
    private static final String WHITE_TEXT = "\u001B[97m";
    private final Cell[][] cells;
    private static Board instance;
    private Board() {
        cells = new Cell[BOARD_SIZE][BOARD_SIZE];
        createCells();
    }
    public static Board getInstance(){
        if(instance == null){
            instance = new Board();
        }
        return instance;
    }

    public void colorCells(){
        for(int i = 1 ; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                if ((i + j) % 2 == 1)
                    cells[i][j] = new Cell(null, Color.WHITE);
                else
                    cells[i][j] = new Cell(null, Color.BLACK);
            }
        }
    }
    public void createCells(){
        colorCells();

        // White piece's
        PieceFactory pieceFactory = new PieceFactory();
        cells[1][1] = new Cell(pieceFactory.createPiece(PieceType.ROOK , Color.WHITE) ,cells[1][1].getColor());
        cells[1][2] = new Cell(pieceFactory.createPiece(PieceType.KNIGHT , Color.WHITE) ,cells[1][2].getColor());
        cells[1][3] = new Cell(pieceFactory.createPiece(PieceType.BISHOP , Color.WHITE) ,cells[1][3].getColor());
        cells[1][4] = new Cell(pieceFactory.createPiece(PieceType.QUEEN , Color.WHITE) ,cells[1][4].getColor());
        cells[1][5] = new Cell(pieceFactory.createPiece(PieceType.KING , Color.WHITE) ,cells[1][5].getColor());
        cells[1][6] = new Cell(pieceFactory.createPiece(PieceType.BISHOP , Color.WHITE) ,cells[1][6].getColor());
        cells[1][7] = new Cell(pieceFactory.createPiece(PieceType.KNIGHT , Color.WHITE) ,cells[1][7].getColor());
        cells[1][8] = new Cell(pieceFactory.createPiece(PieceType.ROOK , Color.WHITE) ,cells[1][8].getColor());
        for(int i = 1 ; i <= 8 ; i++){
            cells[2][i] = new Cell(pieceFactory.createPiece(PieceType.PAWN , Color.WHITE) ,cells[2][i].getColor());
        }

        // Black piece's
        cells[8][1] = new Cell(pieceFactory.createPiece(PieceType.ROOK , Color.BLACK) ,cells[8][1].getColor());
        cells[8][2] = new Cell(pieceFactory.createPiece(PieceType.KNIGHT , Color.BLACK) ,cells[8][2].getColor());
        cells[8][3] = new Cell(pieceFactory.createPiece(PieceType.BISHOP , Color.BLACK) ,cells[8][3].getColor());
        cells[8][4] = new Cell(pieceFactory.createPiece(PieceType.QUEEN , Color.BLACK) ,cells[8][4].getColor());
        cells[8][5] = new Cell(pieceFactory.createPiece(PieceType.KING , Color.BLACK) ,cells[8][5].getColor());
        cells[8][6] = new Cell(pieceFactory.createPiece(PieceType.BISHOP , Color.BLACK) ,cells[8][6].getColor());
        cells[8][7] = new Cell(pieceFactory.createPiece(PieceType.KNIGHT , Color.BLACK) ,cells[8][7].getColor());
        cells[8][8] = new Cell(pieceFactory.createPiece(PieceType.ROOK , Color.BLACK) ,cells[8][8].getColor());
        for(int i = 1 ; i <= 8 ; i++){
            cells[7][i] = new Cell(pieceFactory.createPiece(PieceType.PAWN , Color.BLACK) ,cells[7][i].getColor());
        }
    }


    public void printBoard(){
        for(int i = 1 ; i <= 8; i++){
            System.out.println(WHITE_TEXT +"\t------------------------------------------------------------------------------------------------");
            for(int j = 1 ; j <= 8 ; j++) {
                if(j == 1) System.out.print( i + "\t|\t");
                System.out.print(cells[i][j].toString() + WHITE_TEXT + "\t|\t");
            }
            System.out.println();
        }
        System.out.println(WHITE_TEXT +"\t------------------------------------------------------------------------------------------------");
        System.out.println(WHITE_TEXT + "\t\ta\t\t\tb\t\t\tc\t\t\td\t\t\te\t\t\tf\t\t\tg\t\t\th");
    }
    public Cell[][] getCells() {
        return cells;
    }
}
