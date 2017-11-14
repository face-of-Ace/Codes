import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.lang.*;
import static java.lang.System.*;
import static java.lang.Math.*;
/*created by aditya76*/
public class adi {
        static int maxx=Integer.MAX_VALUE;
        static int mod=1000000007;
        public static void main(String[] args) throws IOException {
        InputReader sc=new InputReader(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n * 2];
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 2 * n; i++)
            arr[i] = sc.nextInt();
        Arrays.sort(arr);
        for (int i = 0; i < 2 * n; i++) {
            for (int j = i + 1; j < 2 * n; j++) {
                Vector<Integer> vec = new Vector<Integer>();
                for (int k = 0; k < 2 * n; k++) {
                    if (k != i && k != j) {
                        vec.add(arr[k]);
                    }
                }
                int value = compute(vec);
                if (value < min)
                    min = value;
            }
        }
        out.println (min);
    }

//lcs
public static int lcs(String s1, String s2){
    char[] X=s1.toCharArray();
    char[] Y=s2.toCharArray();
    int m=X.length;
    int n=Y.length;
    int L[][] = new int[m+1][n+1];
    for (int i=0; i<=m; i++){
      for (int j=0; j<=n; j++){
        if (i == 0 || j == 0)
            L[i][j] = 0;
        else if (X[i-1] == Y[j-1])
            L[i][j] = L[i-1][j-1] + 1;
        else
            L[i][j] = unity(L[i-1][j], L[i][j-1]);
      }
    }
  return L[m][n];
  }
//unity function
  public static int unity(int a, int b){
    return (a > b)? a : b;
}

public static int compute (Vector<Integer> vec) {
        Integer[] arr = new Integer[vec.size()];
        int diff = 0;
        vec.toArray(arr);
        for (int i = 0; i < vec.size() - 1; i += 2)
            diff += (arr[i + 1] - arr[i]);
        return diff;
    }
//longest increasing subsequence (n log n)
public static int CeilIndex(int A[],int l,int r,int key){
        while(r-l>1){
            int m=l+(r-l)/2;
            if (A[m]>=key)
                r=m;
            else
                l=m;
      }
      return r;
    }

 public static int lis(int A[]){
        int size=A.length;
        int[] tailTable   = new int[size];
        int len;
        tailTable[0] = A[0];
        len = 1;
        for (int i=1;i<size;i++){
            if (A[i]<tailTable[0])
                tailTable[0]=A[i];
            else if(A[i]>tailTable[len-1])
                tailTable[len++]=A[i];
            else
                tailTable[CeilIndex(tailTable, -1, len-1, A[i])] = A[i];
        }
        return len;
    }

//binary modular
public static long bxp(long a,long b){
        long res=1;
        while(b>0){
          if(b%2!=0)
            res=res*a;
          a=(a*a);
          b>>=1;
      }
  return res;
}
//euclid gcd
public static int gcd(int p, int q) {
        if (q == 0)
          return p;
        return gcd(q, p % q);
      }
//prime
public static boolean isprime(long number){
        for(int i=2;i<=sqrt(number); i++){
          if(number % i == 0){
            return false;
          }
      }
        return true;
}

//large fibbonacci
public static int ffb(int n) {
		BigInteger a = BigInteger.ZERO;
		BigInteger b = BigInteger.ONE;
		int m = 0;
		for (int i = 31 - Integer.numberOfLeadingZeros(n); i >= 0; i--) {
			assert a.equals(sfb(m));
			assert b.equals(sfb(m+1));
			BigInteger d = multiply(a, b.shiftLeft(1).subtract(a));
			BigInteger e = multiply(a, a).add(multiply(b, b));
			a = d;
			b = e;
			m *= 2;
			assert a.equals(sfb(m));
			assert b.equals(sfb(m+1));
			if (((n >>> i) & 1) != 0) {
				BigInteger c = a.add(b);
				a = b;
				b = c;
				m++;
				assert a.equals(sfb(m));
				assert b.equals(sfb(m+1));
			}
		}
		return a.intValue();
	}

public static BigInteger sfb(int n) {
  		BigInteger a = BigInteger.ZERO;
  		BigInteger b = BigInteger.ONE;
  		for (int i = 0; i < n; i++) {
  			BigInteger c = a.add(b);
  			a = b;
  			b = c;
  		}
  		return a;
  	}

public static BigInteger multiply(BigInteger x, BigInteger y) {
    		return x.multiply(y);
  }
//reverse string
public static String rev(String s){
    StringBuilder sb=new StringBuilder(s);
	  sb.reverse();
	  return sb.toString();
}
//least common multiple
public static int lcm(int a, int b){
    return a * (b / gcd(a, b));
}

//depth first search
public static void dfs(ArrayList<ArrayList<Integer>> adjLists,boolean[] neha,int v){
      neha[v]=true;
    //System.out.print(v+" ");
      for(int w:adjLists.get(v)){
        if(!neha[w]){
          dfs(adjLists,neha,w);
      }
    }
}

//sieve prime
public static ArrayList<Integer> sieve(int n,int m){
      boolean[] isPrime = new boolean[n+1];
      ArrayList<Integer> al=new ArrayList<Integer>();
        for (int i = 2; i <= n; i++)
            isPrime[i] = true;
        for (int factor = 2; factor*factor <=n; factor++) {
            if (isPrime[factor]) {
                for (int j = factor; factor*j <= n; j++)
                    isPrime[factor*j] = false;
            }
        }
        int primes = 0;
        for (int i = m; i <= n; i++) {
            if (isPrime[i])
              al.add(i);
            }
        return al;
        }

//input reader
static class InputReader {
      private final InputStream stream;
      private final byte[] buf = new byte[8192];
      private int curChar, snumChars;
      public InputReader(InputStream st) {
            this.stream = st;
        }
      public int read() {
          if (snumChars == -1)
              throw new InputMismatchException();
          if (curChar >= snumChars) {
              curChar = 0;
              try {
                  snumChars = stream.read(buf);
                } catch (IOException e) {
                  throw new InputMismatchException();
              }
              if (snumChars <= 0)
                  return -1;
            }
              return buf[curChar++];
          }
      public int nextInt() {
          int c = read();
          while (isSpaceChar(c)) {
              c = read();
            }
          int sgn = 1;
            if (c == '-') {
              sgn = -1;
              c = read();
            }
          int res = 0;
          do{
            res *= 10;
            res += c - '0';
            c = read();
          } while (!isSpaceChar(c));
            return res * sgn;
          }
      public long nextLong() {
          int c = read();
          while (isSpaceChar(c)) {
              c = read();
            }
          int sgn = 1;
          if (c == '-') {
              sgn = -1;
              c = read();
            }
          long res = 0;
          do {
            res *= 10;
            res += c - '0';
            c = read();
          } while (!isSpaceChar(c));
              return res * sgn;
          }
      public int[] nextIntArray(int n) {
              int a[] = new int[n];
              for (int i = 0; i < n; i++) {
                a[i] = nextInt();
              }
              return a;
            }

      public String readString() {
              int c = read();
              while (isSpaceChar(c)) {
                c = read();
              }
              StringBuilder res = new StringBuilder();
              do {
                res.appendCodePoint(c);
                c = read();
              } while (!isSpaceChar(c));
              return res.toString();
            }

      public String readLine() {
              int c = read();
              while (isSpaceChar(c))
                c = read();
              StringBuilder res = new StringBuilder();
              do {
                res.appendCodePoint(c);
                c = read();
              } while (!isEndOfLine(c));
              return res.toString();
            }

            public boolean isSpaceChar(int c) {
              return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
            }

            private boolean isEndOfLine(int c) {
              return c == '\n' || c == '\r' || c == -1;
            }
      }
}

