package cpsc2150.extendedConnects;

import cpsc2150.extendedConnectX.models.BoardPosition;
import cpsc2150.extendedConnectX.models.GameBoard;
import cpsc2150.extendedConnectX.models.IGameBoard;

import java.util.Scanner;

/*GROUP MEMBER NAMES AND GITHUB USERNAMES SHOULD GO HERE

    Prahalad Gururajan (PravClem)
    Connor Burkart (crburka)
    Sean Farrell (sfarrellClemosn)
    Parthiv Patel (Parthivp2205)

 */

public class GameScreen {

    public static void main(String[] args)
    {

        // Creates a game-board object with  rows 7 col. 5 pieces of the sample player in a row is needed to win
        IGameBoard board = new GameBoard();

        Boolean stopPlaying = false; // status if player is playing the game
        Character player = 'X';      // Player X
        Character playAgain;         // Used for input if the play does or does not want to play again
        Boolean switcher = true;     // Boolean used to switch to different players turns

        // scanner for user Input
        Scanner myObj = new Scanner(System.in);

        int move; // Int used for players selected column

        // Executes the rest of main util the player is done playing.

        while (stopPlaying == false)
        {
                // Prints out gameboard to the console
                System.out.println(board);

                // Asks a player where they would like to placer their piece in.
                System.out.println("Player " + player + ", what column do you want to place your marker in?");
                move = myObj.nextInt();

                // Validates that the player is selecting a valid column in the game board
                while(move > 6 || move < 0 || board.checkIfFree(move) == false)
                {
                    while(move < 0 ||move > 6 )
                    {
                        if(move < 0) {
                            System.out.println("Column cannot be less than 0");
                            System.out.println("Player " + player + ", what column do you want to place your marker in?");
                            move = myObj.nextInt();
                        }
                        if(move > 6)
                        {
                            System.out.println("Column cannot be greater than 6");
                            System.out.println("Player " + player + ", what column do you want to place your marker in?");
                            move = myObj.nextInt();
                        }
                    }
                    // Prompts the user to place a piece in another column if chosen column is full of pieces
                    while (board.checkIfFree(move) == false)
                    {
                        System.out.println("Column is full");
                        System.out.println("Player " + player + ", what column do you want to place your marker in?");
                        move = myObj.nextInt();

                        while(move < 0 ||move > 6 )
                        {
                            if(move < 0) {
                                System.out.println("Column cannot be less than 0");
                                System.out.println("Player " + player + ", what column do you want to place your marker in?");
                                move = myObj.nextInt();
                            }
                            if(move > 6)
                            {
                                System.out.println("Column cannot be greater than 6");
                                System.out.println("Player " + player + ", what column do you want to place your marker in?");
                                move = myObj.nextInt();
                            }
                        }
                    }
                }
                // Inserts a player's piece in the board if the input is valid
                board.dropToken(player,move);

                // Displays the gameboard object with the newly inserted piece.
                System.out.println(board);

                // If the player wins the game it asks if the player wants to play another game or exit the program
                if(board.checkForWin(move))
                {
                    System.out.println("Player " + player +" Won!");
                    System.out.println("Would you like to play again? Y/N");
                    playAgain = myObj.next().charAt(0);

                    if(playAgain == 'n' || playAgain == 'N')
                    {
                        stopPlaying = true;

                    }
                    else
                    {
                        board = new GameBoard();

                    }

                }
            // If the player ties in the game it asks if the player wants to play another game or exit the program
            if(board.checkTie())
                {
                    System.out.println("The Game is Tied!");
                    System.out.println("Would you like to play again? Y/N");
                    playAgain = myObj.next().charAt(0);


                    while(playAgain == 'n' || playAgain == 'N' || playAgain == 'y' || playAgain == 'Y') {

                        if (playAgain == 'n' || playAgain == 'N') {
                            stopPlaying = true;

                        } else if (playAgain == 'y' || playAgain == 'Y') {
                            board = new GameBoard();

                        } else {
                            System.out.println("Please Answer with Y/N!");
                            System.out.println("Would you like to play again? Y/N");
                            playAgain = myObj.next().charAt(0);

                        }
                    }

                }
            // Switchers the turn to the different player
            else
                {
                    if(switcher == true)
                    {
                        player = 'O';
                        switcher = false;
                    }
                    else
                    {
                        player = 'X';
                        switcher = true;
                    }

                }

        }




    }

}
