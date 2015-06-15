# Benedict
INSTRUCTIONS FOR USING BENEDICT

Conversation:

1. There are a number of built in responses in Benedict's system. For instance, Benedict will always answer "hello" with "Hello". Also, if you enter something that he cannot interpret, he will answer "I don't understand".

2. To start a conversation, enter "let's have a conversation" at the start of the program.

3. When Benedict asks "Okay, what do you want to talk about?", give him a noun, verb, or adjective as a topic of conversation. 

4. To exit talking mode, enter "done". Then, you can start a game by entering "game", or start a new conversation.

5. You can end the program at any time during conversation. To do so, enter "goodbye". Only in game mode is the kill command necessary.

6. Benedict's interpretation system is not case sensitive, so as long as you follow the syntax rules, Benedict will understand you.

Note: The conversation part of the code is unfinished.

Gameplay:
To start to play a game, type "game" in chat.
Benedict offers three different games: nim, chopsticks, and othello.
Note for all games, Benedict will stop you from making invalid moves, and the code will not crash.

NIM:
Instructions:
	In the game of nim, 2 players start with several piles of coins and take turns removing coins from the piles.  On each turn, a player can take any number of coins (at least 1), as long as they are all from the same pile.  The winner is the player who takes the final coin.  At the start of the game, Benedict allows you to choose the number of piles you want to play with, and the number of coins you want in each pile.  Then, Benedict will choose whether he wants to go first or not.

Backstory:
	Classically, the game of nim is only played with 3 piles, containing 3 4 and 5 coins.  Our version is an extension of this classical version as you have limitless freedom in the number of piles and how many each can contain.  While the game seems relatively simple to someone who has never played it before, it actually is very intricate.
	
Code:
	Unfortunately for the player, we have written Benedict's strategy in a way that the player will never win.  We determined a perfect heuristic, meaning that Benedict can explicitly compute whether or not a certain position will lead to a loss or a victory without needing to think ahead.  Benedict looks at the number of coins in each pile in base 2, and counts the number of times each power of 2 is a 1.  A losing position is one where all powers of 2 are a 1 an even number of times.  We see that these are losing positions since they meet the criteria in the two-player game theory definition:  1. From any losing position, the only legal moves take you to a winning position; 2. From any winning position, there is a way to send your opponent to a losing position.  The proofs of these statements can be seen algorithmically in our code.  The point is Benedict will always force you back to a place where each power of 2 occurs an even number of times, and you will not be able to send him to one.  Since the number of total coins continuously decreases, Benedict must eventually send you to the position where there are no coins in any pile, since all 0's meets the criterion, which means Benedict took the last coin on the last move, and won.  Important to note: you can show mathematically that this strategy is a winning strategy, but you can also show mathematically that it is the only winning strategy to the game.  If at any point in the game you chose a move different from this strategy, your opponent would be able to start using this strategy and put you in a losing position.  This means that while we personally derived the algorithm for our own code, it must already exist elsewhere seeing as nim is a solved game, but we assure you that our strategy was not taken from somewhere else.

CHOPSTICKS:
Instructions:
	Chopsticks is played in real life between two people using their fingers and hands.  Both players start with 1 finger on each hand and the goal of the game is to kill both of your opponent's hands.  On each turn, a player is given the option to either hit or split.  On a hit, the player taps one of his opponent's hands with one of his own hands, and the new number of fingers on the opponent's hand becomes the sum of the fingers on the two hands.  For example, if a player hit an opponent's hand that had 1 finger using a hand with two fingers, the opponent would change the hand from 1 finger to 3.  If the number of fingers on a hand is greater than or equal to 5 after a hit, the hand dies and goes back to 0 fingers.  You cannot hit or hit with a dead hand.  On a split, a player can move fingers between his two hands, keeping the total number constant.  For example, if a player had 3 fingers on his left hand and 1 on his right hand, the player could split to 2 and 2, or even 4 and 0 (killing one of his own hands).  You are also able to split a hand back to life.  For example, with 3 and 0, you can split to 2 and 1.  What you cannot do is make a split that does not change your position.  For example, if you have 2 and 1, you cannot split to 1 and 2 as you have effectively done nothing during your turn.
	
Code:
	Similarly to him, Benedict will never lose at chopsticks, but in a completely different way.  For nim, we found a variable that Benedict can always use to bring you back to a losing position.  The strategy it uses for chopsticks comes from a complete analysis of the decision tree of the game.  I learned this game back in middle school, and had been thinking about the strategy of the game for a long time, eventually exhausting all possible outcomes of the game (which aren't actually that many) to determine a winning strategy.  All paths the winning strategy take eventually get the opponent to a point with 0 fingers on one hand and 1 on the other.  Since there are no legal non-redundant splits from this position, your opponent is forced to hit every turn, until they get to a point where they must hit one of your hands to a 4, enabling you to win the game.  As it happens, the majority of the moves along the winning strategy involve splitting as evenly as possible, and the rare exception cases where Benedict needs to hit are hard coded into the strategy.
	
OTHELLO:
Instructions:
	Othello, a.k.a. reversi a.k.a. crappy American Go, IS THE MAIN ATTRACTION.  It is a game played on an 8x8 board with pieces that are black on one side and white on the other, or in our case X's and O's.  The board starts with the central 4 squares occupied by X's and O's in a cross.  On each move, a player places his piece on one of the unoccupied squares, and captures pieces by forming sandwiches.  A sandwich is formed when there is a string of your opponents pieces emanating from where you placed your piece, that terminates with one of your own pieces.  The sandwich can go in any of the 8 directions on the grid (vertically, horizontally, or diagonally).  If a sandwich is formed, all of the pieces inside the sandwich get captured by you.  For example: if a row looked like _OOOXO, and player X went in the leftmost position, XOOOXO, the three O's would be captured --> XXXXXO.  Additionally, more than one sandwich can be made with a single move, example:
X	O	O	X

O	O	O	O

O	O	O	O

_	O	X	O

If X were to go in the bottom left corner of this diagram, three sandwiches would be formed:
X	O	O	X

X	O	X	O

X	X	O	O

X	X	X	O

In fact, a move is ONLY legal if a sandwich is formed and at least one piece is captured.  All legal moves are represented in game by a *, and all other blank spaces are represented by a -.  If a player has no legal moves on their turn, their turn is skipped.  When neither player has any legal moves remaining (which usually occurs only when the game board is filled), the game is over, and the winner is the player with more pieces.

Code and backstory:
	Fortunately for the player of this game, Othello is not solved, so Benedict does not have a guaranteed win, but he has a pretty strong heuristic that enables him to win most games.  I played a lot of Othello as a kid, so I had background knowledge into the strategy that I used to help build a heuristic.  Basically, the key to winning is the capturing of corners, as these pieces can never be recaptured as they cannot be sandwiched.  After that, the edge pieces become the most critical since they cannot be captured unless a sandwich is formed running along the edge.  Once control of the perimeter has been established, it becomes easy to make large sandwiches sweeping through the middle of the board to win in the end.  In the beginning of the game, however, players start in the center of the board.  The majority of the strategy in the early game, before any corner control has been established, is to limit the number of moves your opponent has, which inevitably forces your opponent to go in a position that allows you to take a corner.  These positions where you want to force your opponent to go are the three squares surrounding each corner, especially the square diagonally tangent to the corner.  For you to capture a corner through one of the non-diagonal squares, it must be done along the side, which is sometimes difficult to set up.  For example: _OX_, if this represented the left part of the top row, and it is O's turn O could just go to the right of the X: _OOO, and shut down X's chance at the corner.  If the situation was instead _OX_X, X would force O to give up the corner.  However, if the opponent takes the square diagonal to the corner, it is very easy to take one of the central squares along the diagonal and then the corner on the next move, without setting up an elaborate trap.  Once corner control has been established, the goal becomes to control as much of the perimeter as possible, which further limits your opponents moves while giving you access to large sandwiches.

	The heuristic I used for the value of a position is based on these two principles.

50	-3	10	5	5	10	-3	50

-3	-10	0	0	0	0	-10	-3

10	0	0	0	0	0	0	10

5	0	0	0	0	0	0	5

5	0	0	0	0	0	0	5

10	0	0	0	0	0	0	10

-3	-10	0	0	0	0	-10	-3

50	-3	10	5	5	10	-3	50

------------------------------------------------------

10	0	5	3	3	5	0	10

0	0	1	1	1	1	0	0

5	1	1	1	1	1	1	5

3	1	1	1	1	1	1	3

3	1	1	1	1	1	1	3

5	1	1	1	1	1	1	5

0	0	1	1	1	1	0	0

10	0	5	3	3	5	0	10

	First, Benedict takes the first board of numbers, adds all of the squares he occupies to the evaluating number, then subtracts all of the squares that his opponent occupies.  The super valuable corners are given a very large 50, while the toxic squares surrounding the corners are given negative values (especially the diagonal one).  The squares in the center are given a value of 0, as they are essentially valueless until the absolute end of the game, since the game is decided by edge control anyways.  For the remaining edges, as edge control is valuable, these squares are given positive values.  The reason the edge squares 2 away from the corners are valued slightly higher than those 3 away comes from experience playing the game.  Corner pinning setups along the edges often come from getting stuck with the squares 2 away.  Meanwhile, if you control the 2 squares 2 away from the corners on a given edge, its almost impossible to lose control of that edge without the opponent having a corner.
	
	The final change Benedict makes to the evaluating number in a position where it is his opponent's turn, is he considers all of the legal moves his opponents has, and subtracts the values of those moves using the second table.  Since the center of the values table is all zeroes (which helps Benedict in the mid game), he needs some assistance in the early game to develop a sound opening.  As stated before, the second-most important thing in the game, next to corner control, is limiting the number of moves your opponent has.  Most squares in the center of the second table will only subtract 1 from the total.  Also importantly, the squares surrounding the corners in this table are 0s instead of negative.  This is because, even though you want your opponent to be forced to move to one of these squares, your opponent having the option to move to the square doesn't make it a good position unless it is his ONLY option.  If a move by Benedict enables the opponent to go in one of the toxic squares, but also gives him access to some more normal squares, he will obviously just go in one of the normal squares, rendering a negative value pointless.
	
	The most important part of the code, though, is that, armed with this heuristic, Benedict thinks 3 moves ahead, instead of 1.  Instead of just considering the outcomes of all of his moves, and choosing the best one by the heuristic, he uses min max to think three moves ahead.  He takes all outcomes for the next 3 moves, meaning all possible ways he takes a move, then you, then him again.  Call the current position B1, the position after Benedict moves P1, the position after the player moves B2, and the position after Benedict moves again P2.  For the P2's, he uses the heuristic to see how powerful the position is.  For the B2's, he looks at all possible values for the P2's, and chooses the maximum of these, assuming he will take the best move when it is his turn.  For the P1's, he looks at all possible values for the B2's and chooses the minimum of these maximums, assuming you will take the move on your turn that gives him the worst options.  Finally back at B1, Benedict takes the max of the P1's as his best move, and does it.
	
	These were the general ideas for the code.  To determine the specific values for the numbers used in the grids for the heuristic, I set up 20 Benedicts with identical code and slightly different grid values.  While the numbers in the 20 Benedicts were different, they all followed the general trends of valuable corners, invaluable toxic squares etc.  Then, I had the 20 Benedicts play in a round robin tournament, and the numbers used in the final code were those of the Benedict with the most wins.