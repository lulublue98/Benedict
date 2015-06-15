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
	Unfortunately for the player, we have written Benedict's strategy in a way that the player will never win.  We determined a perfect heuristic, meaning that Benedict can explicitly compute whether or not a certain position will lead to a loss or a victory without needing to think ahead.  Benedict looks at the number of coins in each pile in base 2, and counts the number of times each power of 2 is a 1.  A losing position is one where all powers of 2 are a 1 an even number of times.  We see that these are losing positions since they meet the criteria in the two-player game theory definition:  1. From any losing position, the only legal moves take you to a winning position; 2. From any winning position, there is a way to send your opponent to a losing position.  The proofs of these statements can be seen algorithmically in our code.  The point is Benedict will always force you back to a place where each power of 2 occurs an even number of times, and you will not be able to send him to one.  Since the number of total coins continuously decreases, Benedict must eventually send you to the position where there are no coins in any pile, since all 0's meets the criterion, which means Benedict took the last coin on the last move, and won.

CHOPSTICKS:
Instructions:
	Chopsticks is played in real life between two people using their fingers and hands.  Both players start with 1 finger on each hand and the goal of the game is to kill both of your opponents hands.  On each turn, a player is given the option to either hit or split.  On a hit, the player taps one of his opponents hands with one of his own hands, and the new number of fingers on the opponents hand is the sum of the fingers on the hands that hit.  For example, if a player used a hand with two fingers and hit his opponents hand that had 1 finger, the opponent would change the hand from 1 finger to 3.  If the number of fingers on a hand is greater than or equal to 5 after a hit