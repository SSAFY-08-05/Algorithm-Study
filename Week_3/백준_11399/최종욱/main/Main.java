import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int size = sc.nextInt();
		int[] arr = new int[size];
		for(int i = 0; i < size; i++) {
			arr[i] = sc.nextInt();
		}		
		Arrays.sort(arr);
		int[] arr2 = new int[size];
		arr2[0] = arr[0];
		int sum = arr2[0];
		for(int i = 1; i < size; i++) {
			arr2[i] = arr[i]+ arr2[i-1];
			sum += arr2[i];
		}
		
		System.out.println(sum);
				

	}

}
