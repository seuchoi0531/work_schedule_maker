package work_scheduling;

import java.util.Vector;
import javax.swing.DefaultListModel;

public class Worker {
    private DefaultListModel<String> nameListModel;
    private DefaultListModel<String> totalTimeListModel;
    private DefaultListModel<String> jobListModel;
    private DefaultListModel<String> timeListModel;
    private DefaultListModel<String> weekListModel;
    private Vector<Worker> workervector;
    String name;
    String totalTime;
	String job;
	String time;
	String week;
    Worker(String name, String totalTime, String job, String time, String week){
    	this.name = name;
    	this.totalTime = totalTime;
    	this.job = job;
    	this.time = time;
    	this.week = week;
	}
}
