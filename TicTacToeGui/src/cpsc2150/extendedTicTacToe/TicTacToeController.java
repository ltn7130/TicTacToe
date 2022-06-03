package cpsc2150.extendedTicTacToe;

/**
 * The TicTacToe controller class will handle communication between our TicTacToeView and our Model (IGameBoard and BoardPosition)
 * <p>
 * This is where you will write code
 * <p>
 * You will need to include your BoardPosition class, the IGameBoard interface
 * and the implementations from previous homeworks
 * If your code was correct you will not need to make any changes to your IGameBoard classes
 */
public class TicTacToeController {

    //our current game that is being played
    private IGameBoard curGame;
    //The screen that provides our view
    private TicTacToeView screen;
    private int numPlayers;
    private Character player[];
    public static final int MAX_PLAYERS = 10;
    //use this to determine whose turn
    private int turn;
    //use it to reset the game
    private String playAgain;
    /**
     * @param model the board implementation
     * @param view  the screen that is shown
     * @param np    The number of players for the game
     *
     * @post the controller will respond to actions on the view using the model.
     */
    public TicTacToeController(IGameBoard model, TicTacToeView view, int np) {
        //initialize private variable
        this.curGame = model;
        this.screen = view;
        numPlayers = np;
        //declare marks of players
        player = new Character[np];
        player[0] = 'X';
        //choose the marks for players
        for(int i = 1; i < np; ++i){
            if(player[i-1] == 'Z') {
                player[i] = 'A';
                continue;
            }
            player[i] = (char)(player[i-1] + 1);
            //change the confused characters;
            if(player[i] == 'G') player[i] = 'L';
            if(player[i] == 'F') player[i] = 'S';
        }
        turn = 0;
        playAgain = "";
    }

    /**
     * @param row the row of the activated button
     * @param col the column of the activated button
     *
     * @pre row and col are in the bounds of the game represented by the view
     * @post The button pressed will show the right token and check if a player has won.
     */
    public void processButtonClick(int row, int col) {
        //calculate the turn of players
        int i = turn%numPlayers;
        //error message
        String error = "";
        //if playAgain is not empty, which means a player has won or the game draw
        // then create a new game
        if(!playAgain.equals("")){
            this.newGame();
        }
        // use it the display whose turn
        String playerTurn = "";
        playerTurn += "It is " + player[(i + 1)%numPlayers] + "\'s " + "turn. ";
        //display turn of players
        screen.setMessage(playerTurn);
        //check space
        BoardPosition pos = new BoardPosition(row,col);
        //if valid, place the marker
        if(curGame.checkSpace(pos)){
            curGame.placeMarker(pos,player[i]);
            screen.setMarker(row,col,player[i]);
        }
        //if invalid space, display error and choose grid again
        else {
            error += "Invalid space";
            screen.setMessage(error);
            return;
        }
        //check for winner
        if(curGame.checkForWinner(pos)){
            playAgain += "Player " + player[i] + " won. ";
        }
        //check for draw
        else if (curGame.checkForDraw()){
            playAgain += "Game draw. ";
        }
        //if playAgain is not empty, display message either a player won, or game draw
        if(!playAgain.equals("")){
            playAgain += "Click any grid to play again";
            screen.setMessage(playAgain);
        }
        //next player's turn
        ++turn;
    }

    private void newGame() {
        // You do not need to make any changes to this code.
        screen.dispose();
        GameSetupScreen screen = new GameSetupScreen();
        GameSetupController controller = new GameSetupController(screen);
        screen.registerObserver(controller);
    }
}