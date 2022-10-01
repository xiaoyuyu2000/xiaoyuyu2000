public class Job {
    private String name;
    private int arriveTime;
    private int leaveTime;
    private int timeNeeded;
    private int priority;
    private double waitTime = 0;
    private double avgTime;
    private double avgTimeWeight;
    private double rp;

    public void setName(String name) {
        this.name = name;
    }

    public void setArriveTime(int arriveTime) {
        this.arriveTime = arriveTime;
    }

    public void setLeaveTime(int leaveTime) {
        this.leaveTime = leaveTime;
    }

    public void setTimeNeeded(int timeNeeded) {
        this.timeNeeded = timeNeeded;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setAvgTime(double avgTime) {
        this.avgTime = avgTime;
    }

    public void setAvgTimeWeight(double avgTimeWeight) {
        this.avgTimeWeight = avgTimeWeight;
    }

    public void setWaitTime(double waitTime) {
        this.waitTime = waitTime;
    }

    public void setRp(double rp) {
        this.rp = rp;
    }

    public String getName() {
        return name;
    }

    public double getRp() {
        return rp;
    }

    public int getArriveTime() {
        return arriveTime;
    }

    public int getLeaveTime() {
        return leaveTime;
    }

    public int getTimeNeeded() {
        return timeNeeded;
    }

    public double getWaitTime() {
        return waitTime;
    }

    public int getPriority() {
        return priority;
    }

    public double getAvgTime() {
        return avgTime;
    }

    public double getAvgTimeWeight() {
        return avgTimeWeight;
    }

    public Job(String name, int arriveTime, int timeNeeded, int priority) {
        setName(name);
        setArriveTime(arriveTime);
        setTimeNeeded(timeNeeded);
        setPriority((priority));
    }
}