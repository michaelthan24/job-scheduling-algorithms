import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class RoundRobinTwo {
	static boolean checkJobDone(ArrayList<Job> jobList) { // checks if all jobs are done, if not return false
		ArrayList<Boolean> jobDoneList = new ArrayList<>(jobList.size());
		for (int i=0;i<jobList.size();i++) { // create an array for boolean values of finished jobs
			jobDoneList.add(false);
		}
		for (int i=0;i<jobList.size();i++) {
			if (jobList.get(i).jobTime > 0) {
				jobDoneList.set(i, false);
			}
			else {
				jobDoneList.set(i, true);
			}
		}
		if(jobDoneList.contains(false)) {
			return false;
		}
		else
			return true;
	}
	static void rrTwo(ArrayList<Job> jobList) {
		int counter = 0;
		int timeSlice = 2;
		int totalTurnAround = 0;
		System.out.println("Job# | Start time | end time | Job completion");
		while(checkJobDone(jobList) == false) {
			// if the jobs are not all complete continue round robin
			for (int i=0;i<jobList.size();i++) {
				if(jobList.get(i).jobTime > 0){ // if there is still job time left over
					if(jobList.get(i).jobTime <= timeSlice) { // the time left over is less than three
						System.out.print(jobList.get(i).name + " | " + counter);
						int timeLeft = jobList.get(i).jobTime;
						jobList.get(i).jobTime -= timeLeft;
						counter += timeLeft;
						System.out.print(" | " + counter);
						jobList.get(i).turnAroundTime = counter;
						totalTurnAround += counter;
						System.out.println(" | " + jobList.get(i).name + " completed at " + counter);
					}
					else { // else there is more than 2 left in jobTime
						System.out.print(jobList.get(i).name + " | " + counter);
						jobList.get(i).jobTime -= timeSlice;
						counter += timeSlice;
						System.out.println(" | " + counter);
					}
				}
			}
			
		}
		System.out.println("Average Turn Around Time = " + totalTurnAround/(float)jobList.size()); // print out average turnaround

	}
	public static void main(String[] args) { 
		ArrayList<Job> jobList = new ArrayList<Job>();// create the three inputs from the text files
		ArrayList<Job> jobList1 = new ArrayList<Job>();
		ArrayList<Job> jobList2 = new ArrayList<Job>();
		
		try {
			File jobs = new File ("job.txt");
			Scanner jobScanner = new Scanner(jobs);
			while (jobScanner.hasNext()) {
				String jobname = jobScanner.nextLine();
				String jobtime = jobScanner.nextLine();
				int time = Integer.parseInt(jobtime);
				Job newJob = new Job(time, jobname);
				jobList.add(newJob);
				
			}
			jobScanner.close();
			File jobs1 = new File ("job1.txt");
			Scanner jobScanner1 = new Scanner(jobs1);
			while (jobScanner1.hasNext()) {
				String jobname = jobScanner1.nextLine();
				String jobtime = jobScanner1.nextLine();
				int time = Integer.parseInt(jobtime);
				Job newJob = new Job(time, jobname);
				jobList1.add(newJob);
				
			}
			jobScanner1.close();
			File jobs2 = new File ("job2.txt");
			Scanner jobScanner2 = new Scanner(jobs2);
			while (jobScanner2.hasNextLine()) {
				String jobname = jobScanner2.nextLine();
				String jobtime = jobScanner2.nextLine();
				int time = Integer.parseInt(jobtime);
				Job newJob = new Job(time, jobname);
				jobList2.add(newJob);
				
			}
			jobScanner2.close();
			
			
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
		rrTwo(jobList);
		rrTwo(jobList1);
		rrTwo(jobList2);
	
	}
}

