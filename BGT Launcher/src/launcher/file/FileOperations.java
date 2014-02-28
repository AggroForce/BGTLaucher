package launcher.file;

import java.util.ArrayList;

public class FileOperations extends Thread {
	
	private static ArrayList<IFileTask> TASK_QUEUE = new ArrayList<IFileTask>();
	
	public static void addTaskToQueue(IFileTask task){
		if(!TASK_QUEUE.contains(task)){
			TASK_QUEUE.add(task);
		}
	}
	
	public void run(){
		while(this.isAlive()){
			this.executeNextInQueue();
		}
	}
	
	private void executeNextInQueue(){
		if(!TASK_QUEUE.isEmpty()&&TASK_QUEUE.get(0)!=null){
			TASK_QUEUE.get(0).executeTask();
			TASK_QUEUE.remove(0);
		}
	}
}
