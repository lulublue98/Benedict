import java.util.Scanner;
public class chopsticksboard{
    private int[][] board;
    
    public chopsticksboard(){
        board=new int[2][2];
        for(int i=0; i<2; i++){
            for(int j=0; j<2; j++){
                board[i][j]=1;
            }
        }
    }

    public void printBoard(){
        System.out.println("\n       L R\nBot    "+board[1][0]+" "+board[1][1]+"\nPlayer "+board[0][0]+" "+board[0][1]+"\n");
    }

    public boolean pHands(int l, int r){
        return (l==board[0][0]&&r==board[0][1])||(r==board[0][0]&&l==board[0][1]);
    }

    public boolean bHands(int l, int r){
        return (l==board[1][0]&&r==board[1][1])||(r==board[1][0]&&l==board[1][1]);
    }

    public void pMove(Scanner n){
        System.out.println("Would you like to hit or split?");
        String move = "";
        while(!((move.equals("hit"))||(move.equals("split")))){
            move = n.nextLine();
            if(move.equals("hit")){
                int pHand = 2;
                int bHand = 2;
                System.out.println("Which hand do you want to hit with? Left or right?");
                String pH = "";
                while(!(pH.equals("left")||pH.equals("right"))){
                    pH = n.nextLine();
                    if(pH.equals("left")){
                        if(board[0][0]==0){
                            System.out.println("Error: you cant hit with an empty hand. Choose again.");
                            pH="";
                        }else{
                            pHand=0;
                        }
                    }else if(pH.equals("right")){
                        if(board[0][1]==0){
                            System.out.println("Error: you cant hit with an empty hand. Choose again.");
                            pH="";
                        }else{
                            pHand=1;
                        }
                    }else{
                        System.out.println("Choose either 'left' or 'right'.");
                    }
                }
                System.out.println("Which hand do you want to hit? Left or right?");
                String bH = "";
                while(!(bH.equals("left")||bH.equals("right"))){
                    bH = n.nextLine();
                    if(bH.equals("left")){
                        if(board[1][0]==0){
                            System.out.println("Error: you cant hit an empty hand. Choose again.");
                            bH="";
                        }else{
                            bHand=0;
                        }
                    }else if(bH.equals("right")){
                        if(board[1][1]==0){
                            System.out.println("Error: you cant hit an empty hand. Choose again.");
                            bH="";
                        }else{
                            bHand=1;
                        }
                    }else{
                        System.out.println("Choose either 'left' or 'right'.");
                    }
                }
                board[1][bHand]+=board[0][pHand];
                if(board[1][bHand]>4) board[1][bHand] = 0;
            }else if(move.equals("split")){
                if(pHands(0,1)){
                    System.out.println("Error: there are no legal splits that can be performed with "+board[0][0]+" and "+board[0][1]+" fingers. Choose again.");
                    move="";
                }else{
                    System.out.println("How many fingers do you want on your left hand?");
                    int lHand;
                    boolean valid = false;
                    while(!valid){
                        lHand = n.nextInt();
                        int rHand = board[0][0]+board[0][1]-lHand;
                        if(lHand<0 || rHand<0){
                            System.out.println("Error: you cannot leave a hand with a negative number of fingers after a split. Choose again.");
                        }else if(lHand>4 || rHand>4){
                            System.out.println("Error: you cannot leave a hand with 5 or more fingers after a split. Choose again.");
                        }else if(pHands(lHand,rHand)){
                            System.out.println("Error: this is an invalid split as it leaves you in an identical position as before. Choose again.");
                        }else{
                            board[0][0]=lHand;
                            board[0][1]=rHand;
                            valid=true;
                        }
                    }   
                }
            }else{
                System.out.println("Choose either 'hit' or 'split'.");
            }
        }
    }
    
    public void bMove(){
        if(board[0][0]==0){
            if(board[1][0]+board[0][1]>4){
                System.out.println("I hit your "+board[0][1]+" with my "+board[1][0]+", killing that hand.");
                board[0][1]=0;
                return;
            }else if(board[1][1]+board[0][1]>4){
                System.out.println("I hit your "+board[0][1]+" with my "+board[1][1]+", killing that hand.");
                board[0][1]=0;
                return;
            }
        }
        if(board[0][1]==0){
            if(board[1][0]+board[0][0]>4){
                System.out.println("I hit your "+board[0][0]+" with my "+board[1][0]+", killing that hand.");
                board[0][0]=0;
                return;
            }else if(board[1][1]+board[0][0]>4){
                System.out.println("I hit your "+board[0][0]+" with my "+board[1][1]+", killing that hand.");
                board[0][0]=0;
                return;
            }
        }
        if(board[0][0]==1 && !(bHands(4,0)||bHands(4,1))){
            if(board[1][0]+board[0][1]>4){
                System.out.println("I hit your "+board[0][1]+" with my "+board[1][0]+", killing that hand.");
                board[0][1]=0;
                return;
            }else if(board[1][1]+board[0][1]>4){
                System.out.println("I hit your "+board[0][1]+" with my "+board[1][1]+", killing that hand.");
                board[0][1]=0;
                return;
            }
        }
        if(board[0][1]==1 && !(bHands(4,0)||bHands(4,1))){
            if(board[1][0]+board[0][0]>4){
                System.out.println("I hit your "+board[0][0]+" with my "+board[1][0]+", killing that hand.");
                board[0][0]=0;
                return;
            }else if(board[1][1]+board[0][0]>4){
                System.out.println("I hit your "+board[0][0]+" with my "+board[1][1]+", killing that hand.");
                board[0][0]=0;
                return;
            }
        }
        if(pHands(2,0) && bHands(1,1)){
            System.out.println("I hit your 2 with my 1.");
            if(board[0][0]==2){
                board[0][0]=3;
            }else{
                board[0][1]=3;
            }
            return;
        }
        if(pHands(1,0) && bHands(3,2)){
            System.out.println("I hit your 1 with my 2.");
            if(board[0][0]==1){
                board[0][0]=3;
            }else{
                board[0][1]=3;
            }
            return;
        }
        if(pHands(1,1) && bHands(4,0)){
            System.out.println("I split 3 and 1.");
            board[1][0]=3;
            board[1][1]=1;
            return;
        }
        if(pHands(1,1) && bHands(3,2)){
            System.out.println("I hit your 1 with my 3.");
            board[0][0]=4;
            return;
        }
        if(pHands(3,2) && bHands(3,2)){
            System.out.println("I hit your 2 with my 3.");
            if(board[0][0]==2){
                board[0][0]=0;
            }else{
                board[0][1]=0;
            }
            return;
        }
        if(pHands(1,1) && bHands(3,3)){
            System.out.println("I hit your 1 with my 3.");
            board[0][0]=4;
            return;
        }
        if(pHands(3,2) && bHands(3,3)){
            System.out.println("I hit your 2 with my 3.");
            if(board[0][0]==2){
                board[0][0]=0;
            }else{
                board[0][1]=0;
            }
            return;
        }
        for(int i=(board[1][0]+board[1][1])/2; i>=0; i--){
            if(i!=board[1][0] && i!=board[1][1]){
                int j = board[1][0]+board[1][1]-i;
                System.out.println("I split "+i+" and "+j+".");
                board[1][0]=i;
                board[1][1]=j;
                return;
            }
        }
    }
}
