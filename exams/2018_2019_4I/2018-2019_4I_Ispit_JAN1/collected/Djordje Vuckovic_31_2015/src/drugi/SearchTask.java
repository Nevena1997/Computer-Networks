package drugi;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

public class SearchTask implements Runnable {

	private BlockingQueue<Path> queue;
	private String key;

	public SearchTask(BlockingQueue<Path> queue, String key){
		this.key = key;
		this.queue = queue;
	}
	public void run(){
		try {
			boolean done=false;
			while(!done){
				Path p = this.queue.take();
				if(p == FreeTreeWalker.DUMMY) {
					done = false;
				}
				else{
					search(p);
				}

			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
public void search(Path f){
	try (Scanner sc = new Scanner(f)){
		for (int ln=1; sc.hasNextLine(); ln++){
			String line = sc.nextLine();
			if(line.contains(this.key))
				System.out.printf("nit_%d : %s : %d : %d\n",Thread.currentThread().getId(),f.getFileName() , ln ,line.indexOf(key));
		}

	} catch (IOException e) {
		System.err.println("IO");
	}
}

}
