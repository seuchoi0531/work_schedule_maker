package work_scheduling;

import java.util.Vector;
import java.util.Random;
import javax.swing.DefaultListModel;

public class Schedule {
    private static Time time;
    private Vector<Worker> workervector = new Vector<Worker>();
    private Vector<WorkTable> worktablevector = new Vector<WorkTable>();
    private int open;
    private int close;
    private static int Dnum = 1;
    private static int Lnum = 2;
    private static int Enum = 1;
    private static String[] tmp= new String[7];
	Schedule(Vector<String> timevector, 
			DefaultListModel<String> nameListModel, 
			DefaultListModel<String> totalTimeListModel, 
			DefaultListModel<String> jobListModel, 
			DefaultListModel<String> timeListModel,
			DefaultListModel<String> weekListModel){
		this.time = new Time(timevector);
		this.open = Integer.parseInt(this.time.dstart);
		if (this.open > Integer.parseInt(this.time.Dstart))
			this.open = Integer.parseInt(this.time.Dstart);
		this.close = Integer.parseInt(this.time.Estart)
				+ Integer.parseInt(this.time.Eworking);
		if (this.close < Integer.parseInt(this.time.estart)
				+ Integer.parseInt(this.time.eworking))
			this.close = Integer.parseInt(this.time.estart)
			+ Integer.parseInt(this.time.eworking);
		for(int i = 0;i<nameListModel.size();i++)
			this.workervector.add(new Worker(nameListModel.elementAt(i), 
					totalTimeListModel.elementAt(i),
					jobListModel.elementAt(i), 
					timeListModel.elementAt(i),
					weekListModel.elementAt(i)));
		this.workervector = workerVectorSort(this.workervector);
	}
	private Vector<Worker> workerVectorSort(Vector<Worker> workervector) {
		Vector<Worker> result = workervector;
		for(int i = 0;i < result.size() - 1;i++) {
			for(int j = i + 1;j < result.size();j++) {
				Worker w1 = result.get(i);
				Worker w2 = result.get(j);
				String s1 = w1.job;
				String s2 = w2.job;
				if (s1.equals("숙련자")){
					if(s2.equals("전문가")) {
						result.set(i,  w2);
						result.set(j,  w1);
					}
				}else if (s1.equals("초보자")){
					if(s2.equals("전문가") || s2.equals("숙련자")) {
						result.set(i,  w2);
						result.set(j,  w1);
					}
				}
			}
		}
		
		return result;
	}

	public Vector<WorkTable> makeTable(){
		int n = 5;
		while(n-- > 0) {
			String[][] createtable = cc();
			Vector<String[]> tmp = new Vector<String[]>();
			for(String[] line : createtable) {
				tmp.add(line);
			}
			worktablevector.add(new WorkTable(tmp));
		}
		return worktablevector;
	}
	private String[][] cc() {
		int[] sum = new int[workervector.size()];
		int[][] weektimenum = new int[3][7];
		int height = workervector.size(); // 근무자 수
		int width = 7;
		String[][] tmp = new String[height][width];
		for (int i = 0; i < height; i++) {
		    for (int j = 0; j < width; j++) {
		    	tmp[i][j] = "";
		    }
		}
		Random rand = new Random();
		int k = 0;
		while(true) {
			int row = rand.nextInt(height);
			int col = rand.nextInt(width);
			if(tmp[row][col] == null && workervector.elementAt(row).week.contains(Integer.toString(col))) {
				int inputtime = rand.nextInt(workervector.elementAt(row).time.length());
				//inputtime = 2, DLe로 예를 들면 e를 가리키는것
				int tmpint = sum[row];
				if(workervector.elementAt(row).time.charAt(inputtime) == 'D')
					sum[row] += Integer.parseInt(time.Dworking);
				if(workervector.elementAt(row).time.charAt(inputtime) == 'L')
					sum[row] += Integer.parseInt(time.Lworking);
				if(workervector.elementAt(row).time.charAt(inputtime) == 'E')
					sum[row] += Integer.parseInt(time.Eworking);
				if(workervector.elementAt(row).time.charAt(inputtime) == 'd')
					sum[row] += Integer.parseInt(time.dworking);
				if(workervector.elementAt(row).time.charAt(inputtime) == 'l')
					sum[row] += Integer.parseInt(time.lworking);
				if(workervector.elementAt(row).time.charAt(inputtime) == 'e')
					sum[row] += Integer.parseInt(time.eworking);
				if(workervector.elementAt(row).time.charAt(inputtime) == 'P')
					sum[row] += Integer.parseInt(time.Pworking);
				if(sum[row]>Integer.parseInt(workervector.elementAt(row).totalTime)) {
					sum[row] = tmpint;
				}else {
					char inputchar = workervector.elementAt(row).time.charAt(inputtime);
					System.out.println(k+"=====================");
					System.out.println("inputchar " + inputchar);
					System.out.println("inputtime " + inputtime);
					System.out.println("week " + workervector.elementAt(row).week);
					if(inputchar == 'D' || inputchar == 'd')
						weektimenum[0][col] +=1;
					if(inputchar == 'L' || inputchar == 'l')
						weektimenum[1][col] +=1;
					if(inputchar == 'E' || inputchar == 'e')
						weektimenum[2][col] +=1;
					tmp[row][col] = Character.toString(inputchar);
					System.out.println(k+"===================");
					for(int[] j : weektimenum) {
						for(int i : j)
							System.out.print(i+" ");
						System.out.println();
					}
					for(String[] j : tmp) {
						for(String i : j)
							System.out.print(i+" ");
						System.out.println();
					}
					System.out.println("===================");
				}
				if(flag(weektimenum))
					break;
				if(ok(tmp))
					break;
				k++;
			}
		}
		return tmp;
	}
	private static boolean flag(int[][] array) {
		boolean result = true;
		for(int i = 0;i<7;i++) {
			if(array[i][0]<Dnum || array[i][1]<Lnum || array[i][2]<Enum) {
				result = false;
				break;
			}
		}
		return result;
	}
	private static boolean ok(String[][] array) {
		boolean result = true;
		for(String[] j : array) {
			for(String i : j) {
				if(i.equals("")) {
					result = false;
					break;
				}
			}
		}
		return result;
	}
}
