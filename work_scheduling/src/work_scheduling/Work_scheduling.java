package work_scheduling;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.font.TextAttribute;
import java.util.Vector;
import java.text.AttributedString;

public class Work_scheduling extends JFrame {

    private static DefaultListModel<String> nameListModel;
    private static DefaultListModel<String> totalTimeListModel;
    private static DefaultListModel<String> jobListModel;
    private static DefaultListModel<String> timeListModel;
    private static DefaultListModel<String> weekListModel;
    private static JList<String> namelist;
    private static JList<String> totalTimelist;
    private static JList<String> joblist;
    private static JList<String> timelist;
    private static JList<String> weeklist;
    private static JPanel deletePnl;
    private static Vector<String> timevector = new Vector<String>();
    private static Vector<JTextField> timetextfieldvector = new Vector<JTextField>();
    private static Vector<JCheckBox> timecheckvector = new Vector<JCheckBox>(); // D L E d l e
    private static Vector<JCheckBox> weekcheckvector = new Vector<JCheckBox>();
    private static String infoString = "";
    private static JPanel pnl1;
    private static JPanel p2;
    private static CardLayout p2Layout;
    private static Vector<WorkTable> schedule;
    //private static Vector<Schedule> schedule;

    public Work_scheduling() {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1050, 700);

        Container cp = this.getContentPane();
        GridBagLayout gBag = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        cp.setLayout(gBag);
        gbc.fill = GridBagConstraints.BOTH;

        nameListModel = new DefaultListModel<>();
        totalTimeListModel = new DefaultListModel<>();
        jobListModel = new DefaultListModel<>();
        timeListModel = new DefaultListModel<>();
        weekListModel = new DefaultListModel<>();

        namelist = new JList<>(nameListModel);
        totalTimelist = new JList<>(totalTimeListModel);
        joblist = new JList<>(jobListModel);
        timelist = new JList<>(timeListModel);
        weeklist = new JList<>(weekListModel);
        
        pnl1 = new JPanel(); // 근무자 패널
        pnl1.setPreferredSize(new Dimension(525, 700));
        pnl1.setBorder(new TitledBorder(new LineBorder(Color.black, 5), "근무자 정보"));
        pnl1.setLayout(new BorderLayout());
        
        JPanel p0 = createWorkerCondition();
        p0.setPreferredSize(new Dimension(525, 250));
        p0.setBorder(new LineBorder(Color.black, 3));
        pnl1.add(p0, BorderLayout.NORTH);
        
        JPanel p1 = createWorkerList();
        p1.setPreferredSize(new Dimension(85, 450));
        p1.setBorder(new LineBorder(Color.black, 3));
        pnl1.add(p1, BorderLayout.WEST);
        
        p2 = new JPanel();
        p2Layout = new CardLayout();
        p2.setLayout(p2Layout);
        p2.setPreferredSize(new Dimension(450, 450));
        p2.setBorder(new LineBorder(Color.black, 3));
        pnl1.add(p2, BorderLayout.CENTER);
        
        JPanel pnl2 = new JPanel(); // 근무지 패널
        pnl2.setPreferredSize(new Dimension(525, 230));
        pnl2.setBorder(new TitledBorder(new LineBorder(Color.black, 5), "근무지 정보"));
        pnl2.setLayout(new BorderLayout());
        
        JPanel p4 = createTimeCondition();
        p4.setPreferredSize(new Dimension(525, 200));
        pnl2.add(p4, BorderLayout.NORTH);
        
        JPanel pnl3 = new JPanel(new BorderLayout());
        pnl3.setPreferredSize(new Dimension(525, 470));
        JPanel tmp1 = new JPanel();
        tmp1.setPreferredSize(new Dimension(200,200));
        JPanel tmp2 = new JPanel();
        tmp2.setPreferredSize(new Dimension(200,200));
        JPanel tmp3 = new JPanel();
        tmp3.setPreferredSize(new Dimension(200,200));
        JPanel tmp4 = new JPanel();
        tmp4.setPreferredSize(new Dimension(200,200));
        JPanel tmp5 = createGenerateButton();
        tmp5.setPreferredSize(new Dimension(200,200));
        pnl3.add(tmp1, BorderLayout.EAST);
        pnl3.add(tmp2, BorderLayout.WEST);
        pnl3.add(tmp3, BorderLayout.SOUTH);
        pnl3.add(tmp4, BorderLayout.NORTH);
        pnl3.add(tmp5, BorderLayout.CENTER);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        cp.add(pnl1, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        cp.add(pnl2, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        cp.add(pnl3, gbc);

        this.pack();
    }

    private static JPanel createTimeCondition() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        addLabelForTime(panel, gbc, "근무파트", 0, 0, 1, 1, true);
        addLabelForTime(panel, gbc, "시작시간", 1, 0, 1, 1, true);
        addLabelForTime(panel, gbc, "근무시간", 2, 0, 1, 1, true);
        addLabelForTime(panel, gbc, "Day", 0, 1, 1, 1, false);
        addLabelForTime(panel, gbc, "Lunch", 0, 2, 1, 1, false);
        addLabelForTime(panel, gbc, "Evening", 0, 3, 1, 1, false);
        addLabelForTime(panel, gbc, "day Like", 0, 4, 1, 1, false);
        addLabelForTime(panel, gbc, "lunch Like", 0, 5, 1, 1, false);
        addLabelForTime(panel, gbc, "evening Like", 0, 6, 1, 1, false);
        addLabelForTime(panel, gbc, "Part time", 0, 7, 1, 1, false);
        
        JPanel tmppanel = new JPanel();
        tmppanel.setPreferredSize(new Dimension(45, 25));
        JButton setTime = new JButton("set");
        gbc.gridx = 9;
        gbc.gridy = 0;
        //gbc.gridwidth = 3;
        //gbc.gridheight = 1;
        tmppanel.add(setTime);
        panel.add(tmppanel, gbc);

        for(int i = 1; i < 8; i++) {
        	for(int j = 1; j < 3;j++)
        		addTextField(panel, gbc, j, i, 1, 1);
        }
        setTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(timevector.isEmpty()) {
            		for(int i = 0;i < timetextfieldvector.size();i++)
            			timevector.add(timetextfieldvector.get(i).getText());
            	}
            	else {
            		for(int i = 0;i < timetextfieldvector.size();i++)
            			timevector.set(i, timetextfieldvector.get(i).getText());
            	}
            }
        });
    	return panel;
    }
    private static void addLabelForTime(JPanel panel, GridBagConstraints gbc, String name, int x, int y, int width, int height, boolean center) {
    	JLabel label = new JLabel(name);
    	label.setBorder(new LineBorder(Color.BLACK));
    	if(name.charAt(0) =='D' || name.charAt(0) =='L' || name.charAt(0) =='E')
    		underlineFirstLetter(label);
    	if(name.charAt(0) =='d' || name.charAt(0) =='l' || name.charAt(0) =='e' || name.charAt(0) =='P')
    		underlineFirstLetter(label);
    	label.setPreferredSize(new Dimension(160 * width, 25 * height));
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.weightx = 1.0;
        //gbc.gridwidth = width;
        //gbc.gridheight = height;
        if (center)
        	label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label, gbc);
    }
    private static JPanel createWorkerCondition() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setPreferredSize(new Dimension(525, 250));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        
        JPanel topPanel = new JPanel(new GridBagLayout());
        addLabelForWorker(topPanel, gbc, "이름", 0, 0, 3, 1, true);
        addLabelForWorker(topPanel, gbc, "직급", 1, 0, 3, 1, true);
        addLabelForWorker(topPanel, gbc, "주간 총 근무 시간", 2, 0, 8, 1, true);
        
        JPanel nameTFPanel = new JPanel(new GridBagLayout());
        nameTFPanel.setBorder(new LineBorder(Color.BLACK));
        nameTFPanel.setPreferredSize(new Dimension(105, 25));
        JTextField nameTf = new JTextField();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        nameTFPanel.add(nameTf, gbc);
        topPanel.add(nameTFPanel, gbc);
        
        JPanel cbPanel = new JPanel(new GridBagLayout());
        cbPanel.setBorder(new LineBorder(Color.BLACK));
        cbPanel.setPreferredSize(new Dimension(105, 25));
        String[] items = {"전문가", "숙련자", "초보자"};
        JComboBox cb = new JComboBox(items);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        cbPanel.add(cb, gbc);
        topPanel.add(cbPanel, gbc);
        
        JPanel totalTimeTFPanel = new JPanel(new GridBagLayout());
        totalTimeTFPanel.setBorder(new LineBorder(Color.BLACK));
        totalTimeTFPanel.setPreferredSize(new Dimension(280, 25));
        JTextField totalTimeTf = new JTextField();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        totalTimeTFPanel.add(totalTimeTf, gbc);
        topPanel.add(totalTimeTFPanel, gbc);
        
        JPanel midPanel = new JPanel(new GridBagLayout());
        midPanel.setBorder(new LineBorder(Color.BLACK));
        addLabelForWorker(midPanel, gbc, "D", 0, 1, 2, 1, true);
        addLabelForWorker(midPanel, gbc, "L", 2, 1, 2, 1, true);
        addLabelForWorker(midPanel, gbc, "E", 4, 1, 2, 1, true);
        addLabelForWorker(midPanel, gbc, "d", 6, 1, 2, 1, true);
        addLabelForWorker(midPanel, gbc, "l", 8, 1, 2, 1, true);
        addLabelForWorker(midPanel, gbc, "e", 10, 1, 2, 1, true);
        addLabelForWorker(midPanel, gbc, "P", 12, 1, 2, 1, true);
        for(int i = 0;i < 13;i += 2)
        	addTimeCheckBox(midPanel, gbc, i, 2, 2, 1);
        
        JPanel bottomPanel = new JPanel(new GridBagLayout());
        bottomPanel.setBorder(new LineBorder(Color.BLACK));
        addLabelForWorker(bottomPanel, gbc, "MON", 0, 1, 2, 1, true);
        addLabelForWorker(bottomPanel, gbc, "TUE", 2, 1, 2, 1, true);
        addLabelForWorker(bottomPanel, gbc, "WED", 4, 1, 2, 1, true);
        addLabelForWorker(bottomPanel, gbc, "THU", 6, 1, 2, 1, true);
        addLabelForWorker(bottomPanel, gbc, "FRI", 8, 1, 2, 1, true);
        addLabelForWorker(bottomPanel, gbc, "SAT", 10, 1, 2, 1, true);
        addLabelForWorker(bottomPanel, gbc, "SUN", 12, 1, 2, 1, true);
        for(int i = 0;i < 13;i += 2)
        	addWeekCheckBox(bottomPanel, gbc, i, 2, 2, 1);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 490;
        gbc.gridheight = 50;
        gbc.weightx = 1.0;
        panel.add(topPanel, gbc);
        
        JPanel tmppnl1 = new JPanel();
        tmppnl1.setBorder(new LineBorder(Color.BLACK));
        addLabelForWorker(tmppnl1, gbc, "근무 가능 시간", 0, 0, 14, 1, true);
        gbc.gridx = 0;
        gbc.gridy = 50;
        gbc.gridwidth = 490;
        gbc.gridheight = 25;
        panel.add(tmppnl1, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 75;
        gbc.gridwidth = 490;
        gbc.gridheight = 50;
        panel.add(midPanel, gbc);

        JPanel tmppnl2 = new JPanel();
        tmppnl2.setBorder(new LineBorder(Color.BLACK));
        addLabelForWorker(tmppnl2, gbc, "근무 가능 요일", 0, 0, 14, 1, true);
        gbc.gridx = 0;
        gbc.gridy = 125;
        gbc.gridwidth = 490;
        gbc.gridheight = 25;
        panel.add(tmppnl2, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 150;
        gbc.gridwidth = 490;
        gbc.gridheight = 50;
        panel.add(bottomPanel, gbc);
        
        JButton addWorker = new JButton("add");
        gbc.gridx = 490;
        gbc.gridy = 0;
        gbc.gridwidth = 35;
        gbc.gridheight = 25;
        panel.add(addWorker, gbc);
        
        addWorker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameTf.getText();
                String totalTime = totalTimeTf.getText();
                String jobLevel = (String) cb.getSelectedItem();
                String time = "";
                String week = "";
                if (timecheckvector.get(0).isSelected())
                    time += "D";
                if (timecheckvector.get(1).isSelected())
                    time += "L";
                if (timecheckvector.get(2).isSelected())
                    time += "E";
                if (timecheckvector.get(3).isSelected())
                    time += "d";
                if (timecheckvector.get(4).isSelected())
                    time += "l";
                if (timecheckvector.get(5).isSelected())
                    time += "e";
                if (timecheckvector.get(6).isSelected())
                    time += "P";

                if (weekcheckvector.get(0).isSelected())
                	week += "0";
                if (weekcheckvector.get(1).isSelected())
                	week += "1";
                if (weekcheckvector.get(2).isSelected())
                	week += "2";
                if (weekcheckvector.get(3).isSelected())
                	week += "3";
                if (weekcheckvector.get(4).isSelected())
                	week += "4";
                if (weekcheckvector.get(5).isSelected())
                	week += "5";
                if (weekcheckvector.get(6).isSelected())
                	week += "6";

                nameListModel.addElement(name);
                totalTimeListModel.addElement(totalTime);
                jobListModel.addElement(jobLevel);
                timeListModel.addElement(time);
                weekListModel.addElement(week);

                nameTf.setText("");
                totalTimeTf.setText("");
                cb.setSelectedIndex(0);
                for(JCheckBox checkbox : timecheckvector)
                	checkbox.setSelected(false);
                for(JCheckBox checkbox : weekcheckvector)
                	checkbox.setSelected(false);
                
                JPanel tmpPanel = new JPanel(new GridBagLayout());
                tmpPanel.setBorder(new LineBorder(Color.BLACK));
                addLabelForShowWorker(tmpPanel, gbc, "이름", 0, 0, 1, 1);
                addLabelForShowWorker(tmpPanel, gbc, "직급", 0, 1, 1, 1);
                addLabelForShowWorker(tmpPanel, gbc, "총 근무 가능 시간", 0, 2, 1, 1);
                addLabelForShowWorker(tmpPanel, gbc, "가능 시간", 0, 3, 1, 1);
                addLabelForShowWorker(tmpPanel, gbc, "가능 요일", 0, 4, 1, 1);
                addLabelForShowWorker(tmpPanel, gbc, name, 1, 0, 1, 1);
                addLabelForShowWorker(tmpPanel, gbc, jobLevel, 1, 1, 1, 1);
                addLabelForShowWorker(tmpPanel, gbc, totalTime, 1, 2, 1, 1);
                addLabelForShowWorker(tmpPanel, gbc, time, 1, 3, 1, 1);
                
                String str = "";
                if(week.contains("0")) {
                	str += "MON";
                	if(week.charAt(week.length() - 1) != '0')
                		str += ", ";
                }
                if(week.contains("1")) {
                	str += "TUE";
                	if(week.charAt(week.length() - 1) != '1')
                		str += ", ";
                }
                if(week.contains("2")) {
                	str += "WED";
                	if(week.charAt(week.length() - 1) != '2')
                		str += ", ";
                }
                if(week.contains("3")) {
                	str += "THU";
                	if(week.charAt(week.length() - 1) != '3')
                		str += ", ";
                }
                if(week.contains("4")) {
                	str += "FRI";
                	if(week.charAt(week.length() - 1) != '4')
                		str += ", ";
                }
                if(week.contains("5")) {
                	str += "SAT";
                	if(week.charAt(week.length() - 1) != '5')
                		str += ", ";
                }
                if(week.contains("6")) {
                	str += "SUN";
                	if(week.charAt(week.length() - 1) != '6')
                		str += ", ";
                }
                addLabelForShowWorker(tmpPanel, gbc, str, 1, 4, 1, 1);
                p2.add(tmpPanel, name);
            }
        });
        
        JButton selectAllTime = new JButton("All");
        gbc.gridx = 490;
        gbc.gridy = 75;
        gbc.gridwidth = 35;
        gbc.gridheight = 25;
        panel.add(selectAllTime, gbc);
        selectAllTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(JCheckBox checkbox : timecheckvector)
                	checkbox.setSelected(true);
            }
        });

        JButton selectAllWeek = new JButton("All");
        gbc.gridx = 490;
        gbc.gridy = 150;
        gbc.gridwidth = 35;
        gbc.gridheight = 25;
        panel.add(selectAllWeek, gbc);
        selectAllWeek.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(JCheckBox checkbox : weekcheckvector)
                	checkbox.setSelected(true);
            }
        });
        return panel;
    }
    private static void addLabelForShowWorker(JPanel panel, GridBagConstraints gbc, String name, int x, int y, int width, int height) {
    	JLabel label = new JLabel(name);
    	label.setBorder(new LineBorder(Color.BLACK));
    	gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.gridheight = height;
        panel.add(label, gbc);
    }
    private static void addLabelForWorker(JPanel panel, GridBagConstraints gbc, String name, int x, int y, int width, int height, boolean center) {
    	JLabel label = new JLabel(name);
    	label.setBorder(new LineBorder(Color.BLACK));
    	if(name.charAt(0) =='D' || name.charAt(0) =='L' || name.charAt(0) =='E')
    		underlineFirstLetter(label);
    	if(name.charAt(0) =='d' || name.charAt(0) =='l' || name.charAt(0) =='e' || name.charAt(0) =='P')
    		underlineFirstLetter(label);
    	label.setPreferredSize(new Dimension(35 * width, 25 * height));
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.weightx = 1.0;
        if(width == 14)
        	gbc.weightx = 0.0;
        //gbc.gridwidth = width;
        //gbc.gridheight = height;
        if (center)
        	label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label, gbc);
    }
    private static void addTextField(JPanel panel, GridBagConstraints gbc, int x, int y, int width, int height) {
    	JPanel tmpPanel = new JPanel(new BorderLayout());
    	tmpPanel.setPreferredSize(new Dimension(160, 25));
    	JTextField textfield = new JTextField(4);
    	textfield.setBorder(new LineBorder(Color.BLACK));
        gbc.gridx = x;
        gbc.gridy = y;
        tmpPanel.add(textfield);
        panel.add(tmpPanel, gbc);
    	timetextfieldvector.add(textfield);
    }
    private static void addTimeCheckBox(JPanel panel, GridBagConstraints gbc, int x, int y, int width, int height) {
    	JPanel tmpPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    	tmpPanel.setPreferredSize(new Dimension(35 * width, 25 * height));
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        tmpPanel.setBorder(border);
    	JCheckBox checkbox = new JCheckBox();
    	timecheckvector.add(checkbox);
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.gridheight = height;
        tmpPanel.add(checkbox);
        panel.add(tmpPanel, gbc);
    }
    private static void addWeekCheckBox(JPanel panel, GridBagConstraints gbc, int x, int y, int width, int height) {
    	JPanel tmpPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    	tmpPanel.setPreferredSize(new Dimension(35 * width, 25 * height));
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        tmpPanel.setBorder(border);
    	JCheckBox checkbox = new JCheckBox();
        weekcheckvector.add(checkbox);
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.gridheight = height;
        tmpPanel.add(checkbox);
        panel.add(tmpPanel, gbc);
    }
    private static JPanel createWorkerList() {
    	JPanel panel = new JPanel(new BorderLayout());
    	panel.add(namelist, BorderLayout.CENTER);
    	namelist.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                	String str = namelist.getSelectedValue();
                    JPanel panel = showWorker(str);
                    panel.setPreferredSize(new Dimension(450, 450));
                    panel.setBorder(new LineBorder(Color.black, 3));
                    pnl1.add(panel, BorderLayout.CENTER);
                }
            }
        });
    	JButton delete = new JButton("delete");
    	panel.add(delete, BorderLayout.SOUTH);
    	return panel;
    }
    private static JPanel showWorker(String str) {
    	JPanel panel = new JPanel();
    	p2Layout.show(p2, str);
    	return panel;
    }
    private static void underlineFirstLetter(JLabel label) {
        String text = label.getText();

        if (text != null && !text.isEmpty()) {
            String htmlText = "<html><u>" + text.charAt(0) + "</u>" + text.substring(1) + "</html>";
            label.setText(htmlText);
        }
    }
    private static int findIndex(String name, String jobLevel, String time) {
        for (int i = 0; i < nameListModel.size(); i++) {
            if (nameListModel.getElementAt(i).equals(name) &&
                jobListModel.getElementAt(i).equals(jobLevel) &&
                timeListModel.getElementAt(i).equals(time)) {
                return i;
            }
        }
        return -1;
    }

    
    private static JPanel createGenerateButton() {
    	JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(200,200));
        JButton generateButton = new JButton("Generate");
        generateButton.setPreferredSize(new Dimension(100, 50));
        panel.add(generateButton);
        generateButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
                schedule = makeSchedule();
                Vector<JPanel> inputJPanelVector = new Vector<JPanel>();
                for(WorkTable wt : schedule) {
                	inputJPanelVector.add(wt.showTable());
                }
                generateTable(inputJPanelVector);
            }
        });
    	return panel;
    }
    private static JPanel createTables() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        for (int i = 0; i < 40; i++) {
            //JPanel innerPanel = generateTable(schedule.elementAt(i));
        	JPanel innerPanel = new JPanel();
            innerPanel.setBorder(new LineBorder(Color.black, 3));

            JLabel label = new JLabel("Table " + (i + 1));
            innerPanel.add(label);

            panel.add(innerPanel);
            
        }
    	return panel;
    }
    
    private static Vector<WorkTable> makeSchedule(){
    	Vector<WorkTable> result = new Vector<WorkTable>();
    	Schedule scd = new Schedule(timevector, nameListModel,
    			totalTimeListModel,jobListModel, 
    			timeListModel, weekListModel);
    	result = scd.makeTable();
    	return result;
    }
    private static void updateTablesPanel(JPanel tablesPanel) {
        JLabel label = (JLabel) tablesPanel.getComponent(0);
        label.setText("<html>"+infoString+"</html>");
    }
    
    private static void generateTable(Vector<JPanel> panelvector) {
    	JFrame frame = new JFrame("WorkTables");
    	JPanel panel = new JPanel();
    	panel.setLayout(new GridLayout(panelvector.size(), 1));
    	for(JPanel pnl : panelvector)
    		panel.add(pnl);
    	JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        frame.add(scrollPane);
    }
    
    public static void main(String[] args) {
    	Work_scheduling t = new Work_scheduling();
    }
}
