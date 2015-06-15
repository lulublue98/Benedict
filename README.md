# Benedict
INSTRUCTIONS FOR USING BENEDICT

Conversation:

1. There are a number of built in responses in Benedict's system. For instance, Benedict will always answer "hello" with "Hello". Also, if you enter something that he cannot interpret, he will answer "I don't understand".

2. To start a conversation, enter "let's have a conversation" at the start of the program.

3. When Benedict asks "Okay, what do you want to talk about?", give him a noun, verb, or adjective as a topic of conversation. 

4. To exit talking mode, enter "done". Then, you can start a game by entering "game", or start a new conversation.

5. You can end the program at any time during conversation. To do so, enter "goodbye". Only in game mode is the kill command necessary.

6. Benedict's interpretation system is not case sensitive, so as long as you follow the syntax rules, Benedict will understand you.

Gameplay:
To start to play a game, type "game" in chat.
Benedict offers three different games: nim, chopsticks, and othello.

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
	Othello, a.k.a. reversi a.k.a. crappy American Go, is a game played on an 8x8 board with pieces that are black on one side and white on the other, or in our case X's and O's.  The board starts with the central 4 squares occupied by X's and O's.