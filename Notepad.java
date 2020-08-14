package demo;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.StyledEditorKit;
import javax.swing.undo.UndoManager;

public class Notepad extends JFrame{
	static FileDialog openDia, saveDia;
	private static File file;
	static JFrame frame;
	static String a="";
	static int h=500;
	static int w=500;
	private static UndoManager um;
	static JComboBox com;
	
	private static JFrame frame1 = new JFrame("文字展示器");
	// 创建一个标签居中显示文字
	private static JLabel label = new JLabel("字体颜色", JLabel.CENTER);
	// 选择颜色后不会改变颜色，只有应用后才会
	private static JButton choose = new JButton("选择颜色");
	private static JButton apply = new JButton("应用颜色");
	// 创建一键换色按钮
	private static JButton change = new JButton("一键变色");
	private static Color color = Color.BLACK;

	public static void open(JTextArea area){
		openDia.setVisible(true);
//		saveDia.setVisible(true);
		String dirPath = saveDia.getDirectory();
		String fileName = saveDia.getFile();
		file = new File(dirPath,fileName);
			try
			{
				BufferedWriter bufw = new BufferedWriter(new FileWriter(file+".txt"));
			
				String text = area.getText();

				bufw.write(text);
			
				bufw.close();
			}
			catch (IOException ex)
			{
				throw new RuntimeException("文件保存失败！");
			}
			
	}
	
	public static String dakai(JTextArea area) {
		openDia.setVisible(true);
		String dirPath = openDia.getDirectory();
		String fileName = openDia.getFile();
		file = new File(dirPath,fileName);
		System.out.println(file);
		file = new File(dirPath,fileName);
		
		try
		{
				BufferedReader bufr = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
				String line = null;

				while( (line = bufr.readLine())!= null)
				{
					area.append(line +"\r\n");
					
				}
				bufr.close();
		}
		catch (IOException ex)
		{
			throw new RuntimeException("文件读取失败！");
		}
		return file+"";
	}
	
	public static void main(String[] args) {
		frame=new JFrame();
		
		int j=1;
		JTextArea area=new JTextArea("");
		frame.setLayout(new BorderLayout());
		//菜单栏
		JMenuBar menbar=new JMenuBar();
		 um=new UndoManager();
		 area.getDocument().addUndoableEditListener(um);
		 area.setFont(new Font("楷体", 1, 30));
		JMenu menu01=new JMenu("文件");
		JMenu menu02=new JMenu("编辑");
		JMenu menu03=new JMenu("查看");
		JMenu menu04=new JMenu("帮助");
		//文件
		JMenuItem item01=new JMenuItem("新建");
		JMenuItem item02=new JMenuItem("打开");
		JMenuItem item03=new JMenuItem("保存");
		JMenuItem item04=new JMenuItem("另存");
		JMenuItem item05=new JMenuItem("页面设置");
		JMenuItem item06=new JMenuItem("退出");
		//编辑
		JMenuItem item11=new JMenuItem("复制");
		JMenuItem item12=new JMenuItem("粘贴");
		JMenuItem item13=new JMenuItem("剪切");
		JMenuItem item14=new JMenuItem("全选");
		JMenuItem item15=new JMenuItem("时间");
		JMenuItem item16=new JMenuItem("删除");

		//查看
		JMenuItem item21=new JMenuItem("查找");
		JMenuItem item22=new JMenuItem("替换");
		JMenuItem item23=new JMenuItem("字体设置");
		JMenuItem item24=new JMenuItem("撤销");
		JMenuItem item25=new JMenuItem("清空");
		//帮助
		JMenuItem item31=new JMenuItem("网站");
		JMenuItem item32=new JMenuItem("说明");
		//将选项添加文件到菜单
		menu01.add(item01);
		menu01.add(item02);
		menu01.add(item03);
		menu01.add(item04);
		menu01.add(item05);
		menu01.add(item06);
		//将选项添加到编辑菜单
		menu02.add(item11);
		menu02.add(item12);
		menu02.add(item13);
		menu02.add(item14);
		menu02.add(item15);
		menu02.add(item16);
		//将选项添加到查看菜单
		menu03.add(item21);
		menu03.add(item22);
		menu03.add(item23);
		menu03.add(item24);
		menu03.add(item25);
		//将选项添加到帮助菜单
		menu04.add(item31);
		menu04.add(item32);
		
		//将菜单添加到栏目上
		menbar.add(menu01);
		menbar.add(menu02);
		menbar.add(menu03);
		menbar.add(menu04);

		frame.add(area,BorderLayout.CENTER);
		frame.add(menbar,BorderLayout.NORTH);
		openDia = new FileDialog(frame,"我的打开",FileDialog.LOAD);
		saveDia = new FileDialog(frame,"我的保存",FileDialog.SAVE);
		//事件监控
		//新建
		item01.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String areatext=area.getText();
				System.out.println(areatext+"--");
				if(areatext.equals("")) {
					
				}else {
					int aa=JOptionPane.showConfirmDialog(null, "当前内容不为空，是否保存", "提示", JOptionPane.YES_NO_OPTION); 

					if(aa==0) {
						open(area);
					}else {
						area.setText("");
					}
				}
			}
		});
		//打开
		item02.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String areatext=area.getText();
				if(areatext.equals("")) {
					a=dakai(area);
					
				}else {
					int aa=JOptionPane.showConfirmDialog(null, "当前内容不为空，是否保存", "提示", JOptionPane.YES_NO_OPTION); 
					if(aa==0) {
						open(area);
					}else {
						a=dakai(area);
					}
				}
					
			}
		});
		//保存
		item03.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String areatext=area.getText();
				if(a.equals("")) {
					System.out.println("dfs");
					open(area);
				}else {
					BufferedWriter bufw;
					try {
						bufw = new BufferedWriter(new FileWriter(a));
						String text = area.getText();
						bufw.write(text);
						bufw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					
				}
				
			}
		});
		//另存
		item04.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				open(area);
			}
		});
		frame.setSize(h,w);

		//页面设置
		item05.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String inputValue = JOptionPane.showInputDialog("请输入高度","500"); 
				String inputValue01 = JOptionPane.showInputDialog("请输入宽度","500"); 
				h=Integer.parseInt(inputValue);
				w=Integer.parseInt(inputValue01);
				frame.setSize(h,w);

			}
		});
		item06.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		//复制
		 item11.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	area.copy();
			    }
		 });
		//粘贴
		 item12.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			         area.paste();
			            }
			      });
		 //剪切
		 item13.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	area.cut();
			    }
		 });
		 //全选
		 item14.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	area.selectAll();
			            }
			      });
		 //时间
		 item15.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    	 String text = area.getText();
			    	 text+=df.format(new Date());
			    	 area.setText(text);
			            }
			      });
		 
		//删除
		 item16.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	 area.replaceSelection(null);
			            }
			      });
		 //查找
		 item21.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
					String inputValue = JOptionPane.showInputDialog("请输入查找内容"); 
//	            	int s=JOptionPane.showConfirmDialog(null, "当前内容不为空，是否保存", "提示", JOptionPane.YES_NO_OPTION); 
					int s=0;
					int c=0;
	            	String aa = area.getText();
	            	int l=aa.length();
	            	while(l > c) {
	            	if(s==0) {
		            	int aa1=aa.indexOf(inputValue);
		            	int bb=aa.length();
		            	aa = aa.substring(aa1+inputValue.length(),aa.length());
		            	c+=(bb-aa.length());
		            	area.setSelectionStart(c-inputValue.length());
		            	area.setSelectionEnd(c);

	            	}
	            	s=JOptionPane.showConfirmDialog(null, "查找下一个，是否查找", "提示", JOptionPane.YES_NO_OPTION); 

	            	l--;
					}
	            		

	               
	            }
	        });
		 //替换
		 item22.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
					String inputValue = JOptionPane.showInputDialog("请输入需要替换内容"); 
					String inputValue01 = JOptionPane.showInputDialog("请输入替换后内容"); 
	            	String ateatext=area.getText();
	            	
	            	if(ateatext.indexOf(inputValue)==-1) {
	                    JOptionPane.showMessageDialog(null,"替换内容不存在","警告",JOptionPane.WARNING_MESSAGE);

	            	}else {
	            		String aa1=ateatext.replace(inputValue,inputValue01);
	            		System.out.println(aa1);
	            		area.setText(aa1);
	  
					}
		 
	            }
	        });
		 //字体设置
		 item23.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	            	com=new JComboBox();
	            	com.addItem("楷体");
	            	com.addItem("宋体");
	            	com.addItem("黑体");
	            	com.addItem("微软雅黑");
	            	com.addItem("幼圆");
	            	com.addItem("幼圆");
	            	com.addItem("隶书");
	            	com.addItem("大篆");
	            	com.addItem("方正姚体");
	            	com.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							String item=(String)com.getSelectedItem();
							area.setFont(new Font(item, 1, 30));
							label.setFont(new Font(item, 1, 50));
						}
					});
	            	
	            	
	            	
	            	label.setFont(new Font("华文行楷", 1, 50));

	        		frame1.add(label);
	         
	        		JPanel panel = new JPanel();
	        		// 只有选择颜色后应用按钮才可用
	        		apply.setEnabled(false);
	        		// 添加监听器
	        		choose.addActionListener(e -> {
	        			try {
	        				color = JColorChooser.showDialog(frame, "选择字体颜色", color);
	        				System.out.println(color);
	        			} catch (Exception ex) {
	        				ex.printStackTrace();
	        			}
	        			// 应用按钮可用
	        			apply.setEnabled(true);
	        		});
	         
	        		apply.addActionListener(e -> {
	        			label.setForeground(color);
	        			area.setForeground(color);
	        		});
	         
	        		change.addActionListener(e -> {
	        			JColorChooser colorChooser = new JColorChooser();
	        			JDialog dialog = JColorChooser.createDialog(frame, "请选择颜色", false, colorChooser,
	        					e1 -> label.setForeground(colorChooser.getColor()), null);
	        			dialog.setVisible(true);
	        		});
	        		panel.add(choose);
	        		panel.add(apply);
	        		panel.add(change);
	        		panel.add(com);

	        		frame1.setBounds(400, 400, 200, 200);
	        		frame1.add(panel, BorderLayout.SOUTH);
	         
	        		frame1.pack();
	        		frame1.setVisible(true);

	            }
	        });
		 //撤销
		 item24.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	                if(um.canUndo()) {
	                    um.undo();
	                } else {
	                    JOptionPane.showMessageDialog(null,"无法撤销","警告",JOptionPane.WARNING_MESSAGE);
	                }
	            }
	        });
		//清空
		 item25.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	            	area.setText("");
	            }
	        });
		 //网站
		 item31.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	            	   try {
						Runtime.getRuntime().exec("cmd   /c   start   https://me.csdn.net/qq_45626867?ref=miniprofile");
					} catch (IOException e) {
						e.printStackTrace();
					}
	            }
	        });
		 //说明
		 item32.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {

	            	JOptionPane.showMessageDialog(null, "由zgt开发,有问题联系网站");
	            }
	        });
     	

		frame.setTitle("记事本");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		

	}

}
