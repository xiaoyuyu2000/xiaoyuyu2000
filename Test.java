public class Test {
    public static void main(String[] args) {
        Job j1 = new Job("A", 0, 4, 5);
        Job j2 = new Job("B", 1, 3, 20);
        Job j3 = new Job("C", 2, 4, 2);
        Job j4 = new Job("D", 3, 2, 8);
        Job j5 = new Job("E", 4, 4, 10);
        Job[] jobs = new Job[]{j1, j2, j3, j4, j5};

//        fcfs(jobs);
//        for (int i = 0; i < jobs.length; i++) {
//            System.out.println(jobs[i].getName() + "\t" + jobs[i].getArriveTime() + "\t"
//                    + jobs[i].getTimeNeeded() + "\t" + jobs[i].getLeaveTime() + "\t"
//                    + jobs[i].getAvgTime() + "\t" + jobs[i].getAvgTimeWeight());
//        }




    }

    //复位操作
    public static void rennew(Job[] jobs) {
        for (int i = 0; i < jobs.length; i++) {
            jobs[i].setLeaveTime(0);
            jobs[i].setAvgTime(0);
            jobs[i].setAvgTimeWeight(0);
        }
    }

    //1.先来先服务算法（FCFS）
    public static void fcfs(Job[] jobs) {
        for (int i = 0; i < jobs.length; i++) {
            for (int j = 0; j < jobs.length - i - 1; j++) {
                if (jobs[j].getArriveTime() > jobs[j + 1].getArriveTime()) {
                    Job temp = jobs[j];
                    jobs[j] = jobs[j + 1];
                    jobs[j + 1] = temp;
                }
            }
        }
        int timeNow = jobs[0].getArriveTime();
        for (int i = 0; i < jobs.length; i++) {
            jobs[i].setLeaveTime(timeNow + jobs[i].getTimeNeeded());
            jobs[i].setAvgTime(jobs[i].getLeaveTime() - jobs[i].getArriveTime());
            jobs[i].setAvgTimeWeight(jobs[i].getAvgTime() / jobs[i].getTimeNeeded());
            timeNow = jobs[i].getLeaveTime();
        }
    }

    //2.短作业优先算法（SJF）
    public static void sjf(Job[] jobs) {
        for (int i = 1; i < jobs.length; i++) {
            for (int j = 1; j < jobs.length - i - 1; j++) {
                if (jobs[j].getTimeNeeded() > jobs[j + 1].getTimeNeeded()) {
                    Job temp = jobs[j];
                    jobs[j] = jobs[j + 1];
                    jobs[j + 1] = temp;
                }
            }
        }
        int timeNow = jobs[0].getArriveTime();
        for (int i = 0; i < jobs.length; i++) {
            jobs[i].setLeaveTime(timeNow + jobs[i].getTimeNeeded());
            jobs[i].setAvgTime(jobs[i].getLeaveTime() - jobs[i].getArriveTime());
            jobs[i].setAvgTimeWeight(jobs[i].getAvgTime() / jobs[i].getTimeNeeded());
            timeNow = jobs[i].getLeaveTime();
        }
    }

    //3.高响应比算法（HRRN）
    public static void hrrn(Job[] jobs) {
        int timeNow = jobs[0].getArriveTime();
        timeNow += jobs[0].getTimeNeeded();
        jobs[0].setLeaveTime(timeNow);
        jobs[0].setAvgTime(timeNow);
        jobs[0].setAvgTimeWeight(jobs[0].getAvgTime() / jobs[0].getTimeNeeded());
        for (int i = 1; i < jobs.length; i++) {
            jobs[i].setWaitTime(timeNow - jobs[i].getArriveTime());
            jobs[i].setRp(1 + jobs[i].getWaitTime() / jobs[i].getTimeNeeded());
            for (int j = i; j < jobs.length; j++) {
                for (int k = j; k < jobs.length - i - j ; k++) {
                    if (jobs[k].getRp() < jobs[k + 1].getRp()) {
                        Job temp = jobs[k];
                        jobs[k] = jobs[k + 1];
                        jobs[k + 1] = temp;
                    }
                }

            }
            jobs[i].setLeaveTime(timeNow + jobs[i].getTimeNeeded());
            jobs[i].setAvgTime(jobs[i].getLeaveTime() - jobs[i].getArriveTime());
            jobs[i].setAvgTimeWeight(jobs[i].getAvgTime() / jobs[i].getTimeNeeded());
            timeNow = jobs[i].getLeaveTime();

        }
    }

    //4.优先级调度算法（非抢占式）
    public static void yxjdd(Job[] jobs) {
        for (int i = 1; i < jobs.length; i++) {
            for (int j = 1; j < jobs.length - i; j++) {
                if (jobs[j].getPriority() < jobs[j + 1].getPriority()) {
                    Job temp = jobs[j];
                    jobs[j] = jobs[j + 1];
                    jobs[j + 1] = temp;
                }
            }
        }
        int timeNow = jobs[0].getArriveTime();
        for (int i = 0; i < jobs.length; i++) {
            jobs[i].setLeaveTime(timeNow + jobs[i].getTimeNeeded());
            jobs[i].setAvgTime(jobs[i].getLeaveTime() - jobs[i].getArriveTime());
            jobs[i].setAvgTimeWeight(jobs[i].getAvgTime() / jobs[i].getTimeNeeded());
            timeNow = jobs[i].getLeaveTime();
        }
    }

    //5.时间片轮转算法
    public static void rr(Job[] jobs) {

    }

    public static double getAverageTime(Job[] jobs) {
        double averageTime = 0;
        double sum = 0;
        for (int i = 0; i < jobs.length; i++) {
            sum += jobs[i].getAvgTime();
        }
        averageTime = sum / jobs.length;
        return averageTime;
    }

    public static double getAverageTimeWeight(Job[] jobs) {
        double averageTimeWeight = 0;
        double sum = 0;
        for (int i = 0; i < jobs.length; i++) {
            sum += jobs[i].getAvgTimeWeight();
        }
        averageTimeWeight = sum / jobs.length;
        return averageTimeWeight;
    }

    public double getMax(double[] arr) {
        double max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max)
                max = arr[i];
        }
        return max;
    }

    public static void BubbleSort(int[] arr, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
