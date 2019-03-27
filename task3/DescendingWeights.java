//Descending Weights 
/*You have been given an array A of size N and an integer K. This array consists of N integers ranging from 1 to . Each element in this array is said to have a Special Weight. The special weight of an element is

.

You now need to sort this array in Non-Increasing order of the weight of each element, i.e the element with the highest weight should appear first, then the element with the second highest weight and so on. In case two elements have the same weight, the one with the lower value should appear in the output first.

Input Format:

The first line consists of two space separated integers N and K. The next line consists of N space separated integers denoting the elements of array A.

Output Format:

Print N space separated integers denoting the elements of the array in the order in which they are required.*/
import java.util.*;
// Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail


class DescendingWeights {
    static class Weights {
        int num, wt;
        public Weights(int num, int wt) {
            this.num = num;
            this.wt = wt;
        }
    }
    
    
    
    
    
       public static void main(String args[] ) throws Exception {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), k = in.nextInt();
        ArrayList<Weights> nums = new ArrayList<>();
        for (int i=0;i<n;i++) {
            int num = in.nextInt();
            nums.add(new Weights(num, num%k));
        }
        Collections.sort(nums, new Comparator<Weights>() {
        public int compare(Weights a, Weights b) {
                if (a.wt == b.wt) {
                    return a.num - b.num;
                }
                return b.wt - a.wt;
            } 
        });
        for (int i=0;i<n;i++) {
            System.out.print(nums.get(i).num+" ");
        }
    }
}