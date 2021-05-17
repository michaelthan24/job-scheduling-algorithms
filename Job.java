public class Job{ // job object with a name, job time, start time, and turn around time attributes
	String name;
	int startTime;
	int jobTime;
	int turnAroundTime;
	public Job (int jobTime, String name) {
		this.jobTime = jobTime;
		this.name = name;
	}
	public Job(int jobTime ) {
		this.jobTime = jobTime;
	}
	public Job copyJob() {
		Job newjob = new Job(this.jobTime);
		return newjob;
	}
}
