//Rod Cutting Problem 
/*
A young mischievous boy Harsh, got into a trouble when his mechanical workshop teacher told him to cut Iron rods. The rod cutting algorithm is as follows:

Step 1. If the rod can be divided into two equal parts, cut it and choose any one of them.

Step 2. Else cut the rod into two parts having non-zero integral lengths such that the difference between the lengths of the two pieces is minimized, and then choose the piece having smaller length.

Step 3. Repeat the above algorithm with the currently chosen piece. If the length of the currently chosen piece becomes 1 , stop the algorithm.

There can be special rods which require Step 2 in every step of its cutting. Harsh want to find out the number of such special rods. Help Harsh to find out the answer. */
import java.util.*;


// Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail

class RodCutting {
    public static int rod(int cut){
        if(cut%2==0)
        return 0;
        else if(cut==1)
        return 1;
        else
        return rod(cut/2);
        
    }
    public static void main(String args[] ) throws Exception {
        Scanner sc=new Scanner(System.in);
        int cases=sc.nextInt();
        
        for(int i=1;i<=cases;i++)
        {
            int sum=0;
            int n=sc.nextInt();
            for(int j=1;j<=n;j++)
            {
                int x=rod(j);
                sum+=x;
            }
            System.out.println(sum-1);
        }
        }
        }
        