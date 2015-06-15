import java.util.Scanner;
public class othelloboard{
    private char[][] board;
    private char player;
    private char bot;
    private int done = 0;

    public othelloboard(char p){
        player = p;
        bot = (player=='O') ? 'X' : 'O';
        board = new char[10][10];
        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                board[i][j]='-';
            }
        }
        board[4][4]='X';
        board[4][5]='O';
        board[5][4]='O';
        board[5][5]='X';
    }

    public othelloboard(char[][] in, char p){
        player = p;
        bot = (player=='O') ? 'X' : 'O';
        board = new char[10][10];
        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                board[i][j]=in[i][j];
            }
        }
    }

    public void pause (int ms) {
        try {
            Thread.sleep(ms);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public void printClean(){
        System.out.println("   A  B  C  D  E  F  G  H\n");
        for(int i=1; i<=8; i++){
            String s = ""+i+"  ";
            for(int j=1; j<=8; j++){
                s+=board[i][j]+"  ";
            }
            System.out.println(s+"\n");
        }
        System.out.println("\n");
    }

    public boolean[][] findMoves(char c){
        boolean[][] out = new boolean[10][10];
        char d = (c=='O') ? 'X' : 'O';
        for(int i=1; i<=8; i++){
            for(int j=1; j<=8; j++){
                if(board[i][j]=='-'){
                    boolean valid = false;
                    for(int k=-1; k<2; k++){
                        for(int l=-1; l<2; l++){
                            if(((k!=0)||(l!=0))&&(board[i+k][j+l]==d)){
                                int ni=i+(2*k);
                                int nj=j+(2*l);
                                while(board[ni][nj]==d){
                                    ni+=k;
                                    nj+=l;
                                }
                                if(board[ni][nj]==c){
                                    valid=true;
                                    break;
                                }
                            }
                        }
                        if(valid) break;
                    }
                    if(valid){
                        out[i][j]=true;
                    }
                }
            }
        }
        return out;
    }

    public void printDots(){
        boolean[][] dots = findMoves(player);
        System.out.println("   A  B  C  D  E  F  G  H\n");
        for(int i=1; i<=8; i++){
            String s = ""+i+"  ";
            for(int j=1; j<=8; j++){
                if(dots[i][j]){
                    s+='*'+"  ";
                }else{
                    s+=board[i][j]+"  ";
                }
            }
            System.out.println(s+"\n");
        }
        System.out.println("\n");
    }
    
    public int evaluate(){
        int out = 0;
        int[][] values = {{50,-3,10,5},{-3,-10,0,0},{10,0,0,0},{5,0,0,0}};
        for(int i=1; i<=8; i++){
            int[] nums = values[Math.min(i,9-i)-1];
            for(int j=1; j<=8; j++){
                int num = nums[Math.min(j,9-j)-1];
                if(board[i][j]==bot){
                    out+=num;
                }else if(board[i][j]==player){
                    out-=num;
                }
            }
        }
        int[][] moveValues = {{10,0,5,3},{0,0,1,1},{5,1,1,1},{3,1,1,1}};
        boolean[][] moves = findMoves(player);
        for(int i=1; i<=8; i++){
            int[] nums = moveValues[Math.min(i,9-i)-1];
            for(int j=1; j<=8; j++){
                int num = nums[Math.min(j,9-j)-1];
                if(moves[i][j]){
                    out-=num;
                }
            }
        }
        return out;
    }

    public void doMove(int r, int c, char p){
        board[r][c]=p;
        char q = (p=='O') ? 'X' : 'O';
        for(int k=-1; k<2; k++){
            for(int l=-1; l<2; l++){
                if(((k!=0)||(l!=0))&&(board[r+k][c+l]==q)){
                    int nr=r+(2*k);
                    int nc=c+(2*l);
                    while(board[nr][nc]==q){
                        nr+=k;
                        nc+=l;
                    }
                    if(board[nr][nc]==p){
                        nr-=k;
                        nc-=l;
                        while(board[nr][nc]==q){
                            board[nr][nc]=p;
                            nr-=k;
                            nc-=l;
                        }
                    }
                }
            }
        }
    }

    public void pMove(Scanner coors){
        int r=0;
        int c=0;
        boolean[][] moves = findMoves(player);
        boolean check = false;
        for(int i=1; i<=8; i++){
            for(int j=1; j<=8; j++){
                if(moves[i][j]){
                    check = true;
                    break;
                }
            }
            if(check) break;
        }
        if(!check){
            System.out.println("You have no moves! Turn skipped");
            done++;
            return;
        }else{
            done=0;
        }
        while(true){
            System.out.println("Give the coordinates of your move: i.e. A1");
            String s = coors.nextLine();
            r = (int)s.charAt(1)-48;
            c = (int)s.charAt(0)-64;
            if(r<1||r>8){
                System.out.println("Invalid row number");
            }else if(c<1||c>8){
                System.out.println("Invalid column letter. Use caps");
            }else if(!moves[r][c]){
                System.out.println("You cannot go there as that move will not capture any of my pieces");
            }else{
                break;
            }
        }
        doMove(r,c,player);
    }

    public void bMove(){
        int[] max = {0,0,Integer.MIN_VALUE};
        boolean[][] moves = findMoves(bot);
        boolean check = false;
        for(int i=1; i<=8; i++){
            for(int j=1; j<=8; j++){
                if(moves[i][j]){
                    check = true;
                    othelloboard board2 = new othelloboard(board,player);
                    board2.doMove(i,j,bot);
                    int in = board2.autoPMove();
                    if(in>=max[2]){
                        max[0]=i;
                        max[1]=j;
                        max[2]=in;
                    }
                }
            }
        }
        if(!check){
            System.out.println("I have no moves! Turn skipped");
            done++;
            return;
        }else{
            done=0;
            System.out.println("I am taking square "+(char)(max[1]+64)+max[0]+".\n");
            pause(1000);
            doMove(max[0],max[1],bot);
        }
    }

    public int autoPMove(){
        int min = Integer.MAX_VALUE;
        boolean[][] moves = findMoves(player);
        boolean check = false;
        for(int i=1; i<=8; i++){
            for(int j=1; j<=8; j++){
                if(moves[i][j]){
                    check = true;
                    othelloboard board2 = new othelloboard(board,player);
                    board2.doMove(i,j,player);
                    int in = board2.autoBMove();
                    if(in<=min) min=in;
                }
            }
        }
        if(!check){
            othelloboard board2 = new othelloboard(board,player);
            int in = board2.autoBMove();
            min=in;
        }
        return min;
    }

    public int autoBMove(){
        int max = Integer.MIN_VALUE;
        boolean[][] moves = findMoves(bot);
        boolean check = false;
        for(int i=1; i<=8; i++){
            for(int j=1; j<=8; j++){
                if(moves[i][j]){
                    check = true;
                    othelloboard board2 = new othelloboard(board,player);
                    board2.doMove(i,j,bot);
                    int in = board2.evaluate();
                    if(in>=max) max=in;
                }
            }
        }
        if(!check){
            max=evaluate();
        }
        return max;
    }

    public boolean gameOver(){
        if(done>1){
            return true;
        }else{
            return false;
        }
    }

    public int endGameTally(){
        int p=0;
        int b=0;
        for(int i=1; i<=8; i++){
            for(int j=1; j<=8; j++){
                if(board[i][j]==player) p++;
                if(board[i][j]==bot) b++;
            }
        }
        System.out.println("We are both out of moves, so the game is over!");
        System.out.println("In the end, there are "+p+" "+player+"'s and "+b+" "+bot+"'s.");
        if(b>p){
            return 1;
        }else if(b==p){
            return 2;
        }else{
            return 3;
        }
    }       
}
