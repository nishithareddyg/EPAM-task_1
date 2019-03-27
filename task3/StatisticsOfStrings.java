//Statistics of strings 
/*
 Data science is very popular now. A lot of universities have courses about it. A lot of companies have open data science positions. So it's quite important to know how to work with statistics. And this task can help you to make first step into statistics.

Lets define S as all strings of the length n consisting of letters from the some alphabet of the size a. For each string s lets define
- maximum among all z-function values of the string s. Your task is calculate modulo

. 

You can read more about z-function on https://e-maxx-eng.appspot.com/string/z-function.html. Also in this problem we define
. 
*/


    import java.io.*;
    import java.util.*;
     
    public class StatisticsOfStrings {
    	static int power(int a, int b, int mod) {
    		if (b == 0)
    			return 1;
    		int p = power(a, b / 2, mod);
    		p = (int) ((long) p * p % mod);
    		if (b % 2 == 1)
    			p = (int) ((long) p * a % mod);
    		return p;
    	}
    	static int[] aa;
    	static int solve_(int k, int v, int n, int a, int mod) {
    		Arrays.fill(aa, 0);
    		int bcnt = 0, min = k;
    		int i_ = -1;
    		for (int i = 1; i < n; i++)
    			if ((v & 1 << i) > 0) {
    				aa[i]++; aa[i + k]--;
    				bcnt++;
    				if (i_ != -1)
    					min = Math.min(min, i - i_);
    				i_ = i;
    			}
    		for (int i = 1; i < n; i++)
    			aa[i] += aa[i - 1];
    		for (int i = min; i < k; i++)
    			aa[i] = 1;
    		int cnt = 0;
    		for (int i = 1; i < n; i++)
    			if (aa[i] > 0)
    				cnt++;
    		int sum = power(a, n - cnt, mod);
    		return bcnt % 2 == 1 ? sum : (mod - sum) % mod;
    	}
    	static String binary(int v, int n) {
    		return n == 0 ? Integer.toString(v) : binary(v / 2, n - 1) + Integer.toString(v % 2);
    	}
    	static int solve(int n, int a, int mod) {
    		aa = new int[n + 1];
    		int sum = 0;
    		for (int k = 1; k < n; k++)
    			for (int v = 2; v < 1 << n - k + 1; v += 2)
    				sum = (sum + solve_(k, v, n, a, mod)) % mod;
    		return sum;
    	}
    	static int bit(int v, int i) {
    		return (v & 1 << i) == 0 ? 0 : 1;
    	}
    	public static void main(String[] args) throws IOException {
    		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    		StringTokenizer st = new StringTokenizer(br.readLine());
    		int n = Integer.parseInt(st.nextToken());
    		int a = Integer.parseInt(st.nextToken());
    		int mod = Integer.parseInt(st.nextToken());
    		System.out.println(solve(n, a, mod));
    	}
    }


