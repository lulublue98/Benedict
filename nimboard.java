import java.util.Scanner;

public class nimboard{
    private int[] board;
    private int[] max;
    public nimboard(Scanner n){
        System.out.println("How many rows do you want to play with?");
        board = new int[n.nextInt()];
        for(int i=0; i<board.length; i++){
            System.out.println("How many coins should be in row " + (i+1));
            board[i]=n.nextInt();
        }
        max = new int[2];
        max[0] = 0;
        max[1] = board[0];
        for(int i=1; i<board.length; i++){
            if(board[i]>max[1]){
                max[0]=i;
                max[1]=board[i];
            }
        }
    }

    public void printBoard(){
        for(int i=0; i<board.length; i++){
            System.out.println("Row " + (i+1) + ": " + board[i]);
        }
    }

    public void pause(int ms){
        try {
            Thread.sleep(ms);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public boolean[] nimSum(){
        boolean[] out = new boolean[32 - Integer.numberOfLeadingZeros(max[1])];
        for(int i: board){
            for(int j=out.length-1; j>=0; j--){
                if(i%2==1) out[j] = !out[j];
                i/=2;
            }
        }
        return out;
    }

    public boolean losing(){
        boolean[] x = nimSum();
        for(boolean i: x){
            if(i) return false;
        }
        return true;
    }
    
    public void pMove(Scanner nums){
        int r=0;
        int n=0;
        while(true){
            System.out.println("Which row would you like to take from?");
            r = nums.nextInt();
            System.out.println("How many coins do you want to remove?");
            n = nums.nextInt();
            if(n<1){
                System.out.println("Invalid choice: you must take at least 1 coin");
            }else if(board[r-1]<n){
                System.out.println("Invalid choice: too many coins");
            }else{
                break;
            }
        }
        board[r-1]-=n;
        if(r-1==max[0]){
            max[0] = 0;
            max[1] = board[0];
            for(int i=1; i<board.length; i++){
                if(board[i]>max[1]){
                    max[0]=i;
                    max[1]=board[i];
                }
            }
        }
        System.out.println("\nYou took "+n+" coins from row "+r);
        pause(1000);
        printBoard();
    }

    public void bMove(){
        boolean[] nS = nimSum();
        boolean found = false;
        int x=1;
        int r=0;
        int n=0;
        for(int i=0; i<nS.length; i++){
            if(found){
                x*=2;
            }else{
                if(nS[i]) found = true;
            }
        }
        for(int i=0; i<board.length; i++){
            if(board[i]/x%2==1){
                r=i;
                break;
            }
        }
        int c=1;
        for(int i=(nS.length-1); i>=0; i--){
            if(nS[i]){
                if(board[r]/c%2==1){
                    board[r]-=c;
                    n+=c;
                }else{
                    board[r]+=c;
                    n-=c;
                }
            }
            c*=2;
        }
        pause(1000);
        System.out.println("\nI took "+n+" coins from row "+(r+1));
        pause(1000);
        printBoard();
    }

    public boolean done(){
        for(int i: board){
            if(i!=0) return false;
        }
        return true;
    }
}
