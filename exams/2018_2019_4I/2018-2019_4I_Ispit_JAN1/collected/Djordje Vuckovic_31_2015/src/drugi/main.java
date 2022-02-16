package drugi;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class main {
	private static final int FILE = 10;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String dir = sc.nextLine();
		String key = sc.next();
		int n = sc.nextInt();
		sc.close();

		BlockingQueue<Path> fileQueue = new ArrayBlockingQueue<>(FILE);
		FreeTreeWalker ftw = new FreeTreeWalker(Paths.get(dir), fileQueue);
		new Thread(ftw).start();
		for (int i=0; i<n ; i++)
			new Thread(new SearchTask(fileQueue,key )).start();
	}

}
