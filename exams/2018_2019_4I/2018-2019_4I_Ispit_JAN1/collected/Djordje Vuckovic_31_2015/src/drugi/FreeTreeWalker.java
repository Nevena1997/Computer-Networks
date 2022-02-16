package drugi;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.BlockingQueue;

public class FreeTreeWalker implements Runnable {

	public static final Path DUMMY= Paths.get("");

	private BlockingQueue<Path> queue;
	private Path startingDir;

	public FreeTreeWalker (Path path, BlockingQueue<Path> queue){
		this.queue = queue;
		this.startingDir = path;
	}

	public void run(){
		try {
			walk(this.startingDir);
			this.queue.put(DUMMY);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


private void walk(Path startingDir) throws InterruptedException{
	try (DirectoryStream<Path> ds = Files.newDirectoryStream(startingDir)){
		for (Path p : ds)
			walk(p);
	} catch (IOException e) {
		System.err.println("IO");
		// TODO: handle exception
	}
}
}