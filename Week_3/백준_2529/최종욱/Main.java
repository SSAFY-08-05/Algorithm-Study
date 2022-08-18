import java.io.*;
import java.util.*;
 
public class BJ_2529_부등호 {
   static int K;
   static int[] pick;
   static boolean[] number = new boolean[10];
   static boolean[] input;
   static ArrayList<String> list;
   public static void main(String[] args) throws IOException {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      K = Integer.parseInt(in.readLine());
      input = new boolean[K];
      
      StringTokenizer st = new StringTokenizer(in.readLine());
      for(int i = 0; i < K; i++) {
    	  input[i] = st.nextToken().charAt(0) == '<';
      }
      
      pick = new int[K+1];
 
      list = new ArrayList<>();
                
      Permutation(0);
      
      Collections.sort(list);
      System.out.println("sort : "+list);
      
      System.out.println(list.get(list.size()-1)+"\n"+list.get(0));
   }
   private static void Permutation(int cnt){
      if(cnt == K+1){
         StringBuilder sb = new StringBuilder();
         for(int i=0;i<=K;i++) {
        	 sb.append(pick[i]);
         }
         list.add(sb.toString());
         return;
      }
 
      for(int i = 0; i < 10; i++){
         if(number[i] || (cnt > 0 && pick[cnt-1] < i != input[cnt-1])) {
        	 continue;
         }
         number[i] = true;
         pick[cnt] = i;
         Permutation(cnt+1);
         number[i] = false;
      }
   }
}
