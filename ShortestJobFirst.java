import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ShortestJobFirst {
	static void SJF (ArrayList<Job> jobList) { 
		for (int i=0;i<jobList.size()-1;i++) { //sort the arraylist by the shortest job first
			for (int j=0;j<jobList.size()-1;j++) {
				if(jobList.get(j).jobTime > jobList.get(j+1).jobTime) {
					Job temp = jobList.get(j);
					jobList.set(j, jobList.get(j+1));
					jobList.set(j+1, temp);
				}
			}
		}
		int counter = 0;
		int totalTurnAround = 0;
		System.out.println("Job# | Starttime | endtime | Job completion");
		for (int i=0;i<jobList.size();i++) {
			System.out.print(jobList.get(i).name + " | ");
			System.out.print(counter+ " | "); // start time
			jobList.get(i).startTime = counter;
			counter += jobList.get(i).jobTime;
			System.out.print(counter+ " | "); 
			totalTurnAround += counter; // add up turn around time to calculate average
			jobList.get(i).turnAroundTime = counter; // end time, also turn around time
			System.out.print(jobList.get(i).name + "finished at " + counter + " | "); // time job finished
			System.out.println();
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
		SJF(jobList); // send the three inputs into the shortest job first algorithm
		SJF(jobList1);
		SJF(jobList2);
	}
}
