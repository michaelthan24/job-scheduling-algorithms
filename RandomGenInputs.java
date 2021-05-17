import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class RandomGenInputs {
	static float schedule(ArrayList<Job> jobList) { //First come first serve algorithm
		int counter = 0;
		int totalTurnAround = 0;
		for (int i=0;i<jobList.size();i++) {
			jobList.get(i).startTime = counter;
			counter += jobList.get(i).jobTime;
			totalTurnAround += counter; // add up turn around time to calculate average
			jobList.get(i).turnAroundTime = counter; // end time, also turn around time
		}
		return totalTurnAround/(float)jobList.size(); // return average turnaround
		
	}
	static float SJF (ArrayList<Job> jobList) { // shortest job first algorithm
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
		for (int i=0;i<jobList.size();i++) {
			jobList.get(i).startTime = counter;
			counter += jobList.get(i).jobTime;
			totalTurnAround += counter; // add up turn around time to calculate average
			jobList.get(i).turnAroundTime = counter; // end time, also turn around time
		}
		return totalTurnAround/(float)jobList.size(); // return average turnaround
	}
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
	static float rrTwo(ArrayList<Job> jobList) { // round robin with time slice of two
		int counter = 0;
		int timeSlice = 2;
		int totalTurnAround = 0;
		while(checkJobDone(jobList) == false) {
			// if the jobs are not all complete continue round robin
			for (int i=0;i<jobList.size();i++) {
				if(jobList.get(i).jobTime > 0){ // if there is still job time left over
					if(jobList.get(i).jobTime <= timeSlice) { // the time left over is less than three
						int timeLeft = jobList.get(i).jobTime;
						jobList.get(i).jobTime -= timeLeft;
						counter += timeLeft;
						jobList.get(i).turnAroundTime = counter;
						totalTurnAround += counter;
					}
					else { // else there is more than 2 left in jobTime
						jobList.get(i).jobTime -= timeSlice;
						counter += timeSlice;
					}
				}
			}
			
		}
		return totalTurnAround/(float)jobList.size(); // return average turnaround

	}
	static float rrFive(ArrayList<Job> jobList) { // round robin with time slice of two
		int counter = 0;
		int timeSlice = 5;
		int totalTurnAround = 0;
		while(checkJobDone(jobList) == false) {
			// if the jobs are not all complete continue round robin
			for (int i=0;i<jobList.size();i++) {
				if(jobList.get(i).jobTime > 0){ // if there is still job time left over
					if(jobList.get(i).jobTime <= timeSlice) { // the time left over is less than three
						int timeLeft = jobList.get(i).jobTime;
						jobList.get(i).jobTime -= timeLeft;
						counter += timeLeft;
						jobList.get(i).turnAroundTime = counter;
						totalTurnAround += counter;
					}
					else { // else there is more than 2 left in jobTime
						jobList.get(i).jobTime -= timeSlice;
						counter += timeSlice;
					}
				}
			}
			
		}
		return totalTurnAround/(float)jobList.size(); // return average turnaround

	}
	
	public static void main(String[] args) {
		int min = 1;
		int max = 20;
		float turnAroundFCFSfive = 0;
		float turnAroundSJFfive = 0;
		float turnAroundRRtwofive = 0;
		float turnAroundRRfivefive = 0;
		float turnAroundFCFSten = 0;
		float turnAroundSJFten = 0;
		float turnAroundRRtwoten = 0;
		float turnAroundRRfiveten = 0;
		float turnAroundFCFSfifteen = 0;
		float turnAroundSJFfifteen = 0;
		float turnAroundRRtwofifteen = 0;
		float turnAroundRRfivefifteen = 0;
		for (int i=0;i<20;i++) { // test each generated list 20 times
			ArrayList<Job> jobList = new ArrayList<>();
			ArrayList<Job> jobList1 = new ArrayList<>();
			ArrayList<Job> jobList2 = new ArrayList<>();
			for (int j=0;j<5;j++) { // generate random lists for each size of jobs
				int ranTime = (int)Math.floor(Math.random()*(max-min+1)+min);
				Job newJob = new Job(ranTime);
				jobList.add(newJob);
			}
			for (int j=0;j<10;j++) { // generate random lists for each size of jobs
				int ranTime = (int)Math.floor(Math.random()*(max-min+1)+min);
				Job newJob = new Job(ranTime);
				jobList1.add(newJob);
			}
			for (int j=0;j<15;j++) { // generate random lists for each size of jobs
				int ranTime = (int)Math.floor(Math.random()*(max-min+1)+min);
				Job newJob = new Job(ranTime);
				jobList2.add(newJob);
			}
			
			turnAroundFCFSfive+=schedule(jobList); // record the average turn around time for each single trial for the
			turnAroundSJFfive+=SJF(jobList);		// job list with 5 jobs
			ArrayList<Job> jobListCopy = new ArrayList<>();// duplicate list for the next round robin test
			for(int l=0;l<jobList.size();l++) {
				Job newtest = jobList.get(l).copyJob();
				jobListCopy.add(newtest);
			}
			turnAroundRRtwofive+=rrTwo(jobList);
			turnAroundRRfivefive+=rrFive(jobListCopy);			
			turnAroundFCFSten+=schedule(jobList1); // record the average turnaround time for each single trials for 10 jobs
			turnAroundSJFten+=SJF(jobList1);
			ArrayList<Job> jobListCopy1 = new ArrayList<>();
			for(int l=0;l<jobList1.size();l++) {
				Job newtest = jobList1.get(l).copyJob();
				jobListCopy1.add(newtest);
			}
			turnAroundRRtwoten+=rrTwo(jobList1);
			turnAroundRRfiveten+=rrFive(jobListCopy1);
			
			turnAroundFCFSfifteen+=schedule(jobList2); // record the average turnaround time for each single trial for 15 jobs
			turnAroundSJFfifteen+=SJF(jobList2);
			ArrayList<Job> jobListCopy2 = new ArrayList<>();
			for(int l=0;l<jobList2.size();l++) {
				Job newtest = jobList2.get(l).copyJob();
				jobListCopy2.add(newtest);
			}
			turnAroundRRtwofifteen+=rrTwo(jobList2);
			turnAroundRRfivefifteen+=rrFive(jobListCopy2);
		}
		System.out.println("With 20 trials ran for each algorithm:");
		System.out.println("Average for 5 jobs with FCFS = " + turnAroundFCFSfive/20);
		System.out.println("Average for 5 jobs with SJF = " + turnAroundSJFfive/20);
		System.out.println("Average for 5 jobs with Round Robin w/ slice of 2 = " + turnAroundRRtwofive/20);
		System.out.println("Average for 5 jobs with Round Robin w/ slice of 5 = " + turnAroundRRfivefive/20);
		System.out.println("Average for 10 jobs with FCFS = " + turnAroundFCFSten/20);
		System.out.println("Average for 10 jobs with SJF = " + turnAroundSJFten/20);
		System.out.println("Average for 10 jobs with Round Robin w/ slice of 2 = " + turnAroundRRtwoten/20);
		System.out.println("Average for 10 jobs with Round Robin w/ slice of 5 = " + turnAroundRRfiveten/20);
		System.out.println("Average for 15 jobs with FCFS = " + turnAroundFCFSfifteen/20);
		System.out.println("Average for 15 jobs with SJF = " + turnAroundSJFfifteen/20);
		System.out.println("Average for 15 jobs with Round Robin w/ slice of 2 = " + turnAroundRRtwofifteen/20);
		System.out.println("Average for 15 jobs with Round Robin w/ slice of 5 = " + turnAroundRRfivefifteen/20);
	}
}
