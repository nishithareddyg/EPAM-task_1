//Metro 
/*
A city subway line has become huge and it is hard to take the shortest path through them. You have to find the shortest path in subway lines. In the second , you are in the station  and you want to go to the station 

.

The city has 
 stations. The subway has 

 lines. Each subway line goes to some stations.

The
subway goes to stations (in order) and this train takes seconds to travel from  to (for  and 

).

Trains are ready for the passengers to get in, but the last train goes in the second
(and you are allowed to board it in between the path).*/
 import java.io.OutputStream;
    import java.io.IOException;
    import java.io.InputStream;
    import java.io.PrintWriter;
    import java.util.Arrays;
    import java.io.BufferedInputStream;
    import java.util.PriorityQueue;
    import java.util.ArrayList;
    import java.util.AbstractCollection;
    import java.io.FilterInputStream;
    import java.util.Comparator;
    import java.io.InputStream;
     
    public class Metro{
        public static void main(String[] args) {
            InputStream inputStream = System.in;
            OutputStream outputStream = System.out;
            ScanReader in = new ScanReader(inputStream);
            PrintWriter out = new PrintWriter(outputStream);
            Test solver = new Test();
            solver.solve(1, in, out);
            out.close();
        }
     
        public static class Test {
            public void solve(int testNumber, ScanReader in, PrintWriter out) {
                int n = in.scanInt();
                int m = in.scanInt();
                ArrayList<pair> arrayList[] = new ArrayList[n + 1];
                for (int i = 0; i <= n; i++) arrayList[i] = new ArrayList();
     
     
                for (int i = 0; i < m; i++) {
                    int s = in.scanInt();
                    long t = in.scanInt();
                    int arr[] = new int[s];
                    for (int j = 0; j < s; j++) arr[j] = in.scanInt();
                    for (int j = 0; j < s - 1; j++) {
                        int we = in.scanInt();
                        arrayList[arr[j]].add(new pair(arr[j + 1], we, t));
                        t += we;
                    }
                }
     
     
                int st = in.scanInt();
                int end = in.scanInt();
     
     
                long dis[] = new long[n + 1];
                Arrays.fill(dis, Long.MAX_VALUE / 2);
     
     
                dis[st] = 0;
                PriorityQueue<pair2> pq = new PriorityQueue<>(new Comparator<pair2>() {
     
                    public int compare(pair2 o1, pair2 o2) {
                        return Long.compare(o1.dis, o2.dis);
                    }
                });
     
                pq.add(new pair2(st, dis[st]));
                boolean visited[] = new boolean[n + 1];
                visited[st] = true;
     
     
                while (!pq.isEmpty()) {
                    pair2 p = pq.poll();
                    if (p.node == end) {
                        out.println(dis[p.node]);
                        return;
                    }
     
                    for (pair pp : arrayList[p.node]) {
                        if (!visited[pp.u]) {
                            if (dis[p.node] <= pp.t && dis[pp.u] > dis[p.node] + pp.w) {
                                dis[pp.u] = dis[p.node] + pp.w;
                                visited[pp.u] = true;
                                pq.add(new pair2(pp.u, dis[pp.u]));
                            }
                        }
                    }
     
                }
                out.println(-1);
     
     
            }
     
            class pair2 {
                int node;
                long dis;
     
                public pair2(int node, long dis) {
                    this.node = node;
                    this.dis = dis;
                }
     
            }
     
            class pair {
                int u;
                long w;
                long t;
     
                public pair(int u, long w, long t) {
                    this.u = u;
                    this.w = w;
                    this.t = t;
                }
     
            }
     
        }