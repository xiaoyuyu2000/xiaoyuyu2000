import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Work extends Application {
    @Override
    public void start(Stage primaryStage) {
        Job j1 = new Job("A", 0, 4, 5);
        Job j2 = new Job("B", 1, 3, 20);
        Job j3 = new Job("C", 2, 5, 2);
        Job j4 = new Job("D", 3, 2, 8);
        Job j5 = new Job("E", 4, 10, 10);
        Job[] jobs = new Job[]{j1, j2, j3, j4, j5};

        //五个按钮--五种算法的选择--按下按钮则显示对应结果
        VBox vbox = new VBox();
        HBox buttons = new HBox();
        Button b1 = new Button("先来先服务算法");
        Button b2 = new Button("短作业优先算法");
        Button b3 = new Button("高响应比算法");
        Button b4 = new Button("优先级调度算法");
//        Button b5 = new Button("时间片轮转算法");
        buttons.getChildren().addAll(b1, b2, b3, b4);
        buttons.setSpacing(15);
        buttons.setAlignment(Pos.CENTER);

        //菜单栏
        BorderPane rootBP = new BorderPane();//BorderPane：top-菜单.center-VBox
        MenuBar menuBar = new MenuBar();
        rootBP.setTop(menuBar);
        rootBP.setCenter(vbox);
        Menu startTest = new Menu("Options");
        MenuItem renew = new MenuItem("复位");
//        MenuItem code = new MenuItem("显示代码");
        Menu exitTest = new Menu("Exit");
        MenuItem exit = new MenuItem("退出程序");
        startTest.getItems().addAll(renew);
        exitTest.getItems().add(exit);
        menuBar.getMenus().addAll(startTest, exitTest);


        //表格部分
        TableView<Job> tblJobs = new TableView<>();
        TableColumn<Job, String> colName = new TableColumn<>("进程名");
        TableColumn<Job, Integer> colArriveTime = new TableColumn<>("到达时间");
        TableColumn<Job, Integer> colTimeNeeded = new TableColumn<>("需要时间");
        TableColumn<Job, Integer> colPriority = new TableColumn<>("优先级");
        TableColumn<Job, Integer> colLeaveTime = new TableColumn<>("离开时间");
        TableColumn<Job, Double> colAvgTime = new TableColumn<>("周转时间");
        TableColumn<Job, Double> colAvgTimeWeight = new TableColumn<>("带权周转时间");
        tblJobs.getColumns().addAll(
            colName, colArriveTime, colTimeNeeded, colPriority, colLeaveTime, colAvgTime, colAvgTimeWeight
        );
        tblJobs.getItems().addAll(j1, j2, j3, j4, j5);
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colArriveTime.setCellValueFactory(new PropertyValueFactory<>("arriveTime"));
        colTimeNeeded.setCellValueFactory(new PropertyValueFactory<>("timeNeeded"));
        colPriority.setCellValueFactory(new PropertyValueFactory<>("priority"));
        colLeaveTime.setCellValueFactory(new PropertyValueFactory<>("leaveTime"));
        colAvgTime.setCellValueFactory(new PropertyValueFactory<>("avgTime"));
        colAvgTimeWeight.setCellValueFactory(new PropertyValueFactory<>("avgTimeWeight"));


        Label avg = new Label("平均周转时间：" + "\n" + "带权平均周转时间：");
        vbox.getChildren().addAll(buttons, tblJobs, avg);
        vbox.setSpacing(20);

        //菜单的功能实现
        renew.setOnAction(e -> {
            Test.rennew(jobs);
            tblJobs.getItems().removeAll(j1, j2, j3, j4, j5);
            tblJobs.getItems().addAll(j1, j2, j3, j4, j5);
            avg.setText("平均周转时间：" + "\n" + "带权平均周转时间：");
        });
        exit.setOnAction(e -> primaryStage.close());


        //创建五个按钮的单级响应时间
        //1.先来先服务算法
        b1.setOnAction(e -> {
            Test.fcfs(jobs);
            tblJobs.getItems().removeAll(j1, j2, j3, j4, j5);
            tblJobs.getItems().addAll(j1, j2, j3, j4, j5);
            avg.setText("平均周转时间：" + Test.getAverageTime(jobs) + "\n"
                    + "带权平均周转时间：" + Test.getAverageTimeWeight(jobs));
        });

        //2.短作业优先算法
        b2.setOnAction(e -> {
            Test.sjf(jobs);
            tblJobs.getItems().removeAll(j1, j2, j3, j4, j5);
            tblJobs.getItems().addAll(j1, j2, j3, j4, j5);
            avg.setText("平均周转时间：" + Test.getAverageTime(jobs) + "\n"
                    + "带权平均周转时间：" + Test.getAverageTimeWeight(jobs));
        });

        //3.高响应比算法
        b3.setOnAction(e -> {
            Test.hrrn(jobs);
            tblJobs.getItems().removeAll(j1, j2, j3, j4, j5);
            tblJobs.getItems().addAll(j1, j2, j3, j4, j5);
            avg.setText("平均周转时间：" + Test.getAverageTime(jobs) + "\n"
                    + "带权平均周转时间：" + Test.getAverageTimeWeight(jobs));
        });

        //4.优先级调度算法
        b4.setOnAction(e -> {
            Test.yxjdd(jobs);
            tblJobs.getItems().removeAll(j1, j2, j3, j4, j5);
            tblJobs.getItems().addAll(j1, j2, j3, j4, j5);
            avg.setText("平均周转时间：" + Test.getAverageTime(jobs) + "\n"
                    + "带权平均周转时间：" + Test.getAverageTimeWeight(jobs));
        });

        //5.时间片轮转算法


        Scene scene = new Scene(rootBP,800,600);
        primaryStage.setTitle("不同算法的模拟");
        primaryStage.setScene(scene);
        primaryStage.show();



    }

    public static void main(String[] args) {
//        Job j1 = new Job("A", 0, 4, 5);
//        Job j2 = new Job("B", 1, 3, 10);
//        Job j3 = new Job("C", 2, 4, 2);
//        Job j4 = new Job("D", 3, 2, 8);
//        Job j5 = new Job("E", 4, 4, 10);
//        Job[] jobs = new Job[]{j1, j2, j3, j4, j5};





        launch(args);

    }
}