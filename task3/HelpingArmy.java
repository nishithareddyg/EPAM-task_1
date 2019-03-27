//Helping the Indian Army
/* 
Protection of the Indian border and safe transport of items from one point to another along the border are the paramount jobs for the Indian army. However they need some information about the protection status along the length of the border. The border can be viewed as the real x-axis. Along the axis, Indian army has N checkpoints for lookout.

We know that each checkpoint is located at an integer location xi. Each checkpoint must have a fleet of armed men which are responsible for guarding the neighboring areas of the checkpoint and provide military assistance of all kinds. The size of the fleet is based on the location of the checkpoint and how active the region is for terrorist activities.

Given the number of armed men assigned at the ith checkpoint, as pi, this information is available for all checkpoints.
With the skills of the armed men, it is known that if for the ith checkpoint, the length on the x axis that they can defend is a closed interval [xi-pi, xi+pi].

Now, your task is to transport some military items from position S to the end position E on the x-axis


*/
    import java.util.Arrays;
    import java.util.HashMap;
    import java.util.Map;
    import java.util.Scanner;
     
    class HelpingArmy{
     
        public static void main(String args[]) throws Exception {
            final Scanner scanner = new Scanner(System.in);
            String[] ranges = scanner.nextLine().split(" ");
            int n = Integer.parseInt(ranges[0]);
            int s = Integer.parseInt(ranges[1]);
            int e = Integer.parseInt(ranges[2]);
            int[] startPoints = new int[n];
            Map<Integer, Integer> endPoints = new HashMap<>();
            for (int i = 0; i < n; i++) {
                String[] points = scanner.nextLine().split(" ");
                startPoints[i] = Integer.parseInt(points[0])-Integer.parseInt(points[1])<s ? s:Integer.parseInt(points[0])-Integer.parseInt(points[1]);
                endPoints.put(startPoints[i], Integer.parseInt(points[0])+Integer.parseInt(points[1])>e ? e:Integer.parseInt(points[0])+Integer.parseInt(points[1]));
            }
     
            int lastStartPoint = startPoints[0];
            int lastEndPoint = s;
            int nonProtectedArea = 0;
            int nextEndPoint = endPoints.get(lastStartPoint);
     
            Arrays.sort(startPoints);
     
            for(int i=1; i<n; i++){
                if(nextEndPoint < startPoints[i]){
                    nonProtectedArea += lastStartPoint - lastEndPoint;
                    lastEndPoint = endPoints.get(lastStartPoint);
                    lastStartPoint = startPoints[i];
                    nextEndPoint = endPoints.get(startPoints[i]);
                }else if(nextEndPoint < endPoints.get(startPoints[i]))
                    {
                    nextEndPoint = endPoints.get(startPoints[i]);
     
                }
            }
            nonProtectedArea+=lastStartPoint - lastEndPoint;
            scanner.close();
            System.out.println(nonProtectedArea);
        }
     
    }