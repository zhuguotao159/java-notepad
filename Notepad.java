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
	
	private static JFrame frame1 = new JFrame("����չʾ��");
	// ����һ����ǩ������ʾ����
	private static JLabel label = new JLabel("������ɫ", JLabel.CENTER);
	// ѡ����ɫ�󲻻�ı���ɫ��ֻ��Ӧ�ú�Ż�
	private static JButton choose = new JButton("ѡ����ɫ");
	private static JButton apply = new JButton("Ӧ����ɫ");
	// ����һ����ɫ��ť
	private static JButton change = new JButton("һ����ɫ");
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
				throw new RuntimeException("�ļ�����ʧ�ܣ�");
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
			throw new RuntimeException("�ļ���ȡʧ�ܣ�");
		}
		return file+"";
	}
	
	public static void main(String[] args) {
		frame=new JFrame();
		
		int j=1;
		JTextArea area=new JTextArea("");
		frame.setLayout(new BorderLayout());
		//�˵���
		JMenuBar menbar=new JMenuBar();
		 um=new UndoManager();
		 area.getDocument().addUndoableEditListener(um);
		 area.setFont(new Font("����", 1, 30));
		JMenu menu01=new JMenu("�ļ�");
		JMenu menu02=new JMenu("�༭");
		JMenu menu03=new JMenu("�鿴");
		JMenu menu04=new JMenu("����");
		//�ļ�
		JMenuItem item01=new JMenuItem("�½�");
		JMenuItem item02=new JMenuItem("��");
		JMenuItem item03=new JMenuItem("����");
		JMenuItem item04=new JMenuItem("���");
		JMenuItem item05=new JMenuItem("ҳ������");
		JMenuItem item06=new JMenuItem("�˳�");
		//�༭
		JMenuItem item11=new JMenuItem("����");
		JMenuItem item12=new JMenuItem("ճ��");
		JMenuItem item13=new JMenuItem("����");
		JMenuItem item14=new JMenuItem("ȫѡ");
		JMenuItem item15=new JMenuItem("ʱ��");
		JMenuItem item16=new JMenuItem("ɾ��");

		//�鿴
		JMenuItem item21=new JMenuItem("����");
		JMenuItem item22=new JMenuItem("�滻");
		JMenuItem item23=new JMenuItem("��������");
		JMenuItem item24=new JMenuItem("����");
		JMenuItem item25=new JMenuItem("���");
		//����
		JMenuItem item31=new JMenuItem("��վ");
		JMenuItem item32=new JMenuItem("˵��");
		//��ѡ������ļ����˵�
		menu01.add(item01);
		menu01.add(item02);
		menu01.add(item03);
		menu01.add(item04);
		menu01.add(item05);
		menu01.add(item06);
		//��ѡ����ӵ��༭�˵�
		menu02.add(item11);
		menu02.add(item12);
		menu02.add(item13);
		menu02.add(item14);
		menu02.add(item15);
		menu02.add(item16);
		//��ѡ����ӵ��鿴�˵�
		menu03.add(item21);
		menu03.add(item22);
		menu03.add(item23);
		menu03.add(item24);
		menu03.add(item25);
		//��ѡ����ӵ������˵�
		menu04.add(item31);
		menu04.add(item32);
		
		//���˵���ӵ���Ŀ��
		menbar.add(menu01);
		menbar.add(menu02);
		menbar.add(menu03);
		menbar.add(menu04);

		frame.add(area,BorderLayout.CENTER);
		frame.add(menbar,BorderLayout.NORTH);
		openDia = new FileDialog(frame,"�ҵĴ�",FileDialog.LOAD);
		saveDia = new FileDialog(frame,"�ҵı���",FileDialog.SAVE);
		//�¼����
		//�½�
		item01.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String areatext=area.getText();
				System.out.println(areatext+"--");
				if(areatext.equals("")) {
					
				}else {
					int aa=JOptionPane.showConfirmDialog(null, "��ǰ���ݲ�Ϊ�գ��Ƿ񱣴�", "��ʾ", JOptionPane.YES_NO_OPTION); 

					if(aa==0) {
						open(area);
					}else {
						area.setText("");
					}
				}
			}
		});
		//��
		item02.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String areatext=area.getText();
				if(areatext.equals("")) {
					a=dakai(area);
					
				}else {
					int aa=JOptionPane.showConfirmDialog(null, "��ǰ���ݲ�Ϊ�գ��Ƿ񱣴�", "��ʾ", JOptionPane.YES_NO_OPTION); 
					if(aa==0) {
						open(area);
					}else {
						a=dakai(area);
					}
				}
					
			}
		});
		//����
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
		//���
		item04.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				open(area);
			}
		});
		frame.setSize(h,w);

		//ҳ������
		item05.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String inputValue = JOptionPane.showInputDialog("������߶�","500"); 
				String inputValue01 = JOptionPane.showInputDialog("��������","500"); 
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
		//����
		 item11.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	area.copy();
			    }
		 });
		//ճ��
		 item12.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			         area.paste();
			            }
			      });
		 //����
		 item13.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	area.cut();
			    }
		 });
		 //ȫѡ
		 item14.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	area.selectAll();
			            }
			      });
		 //ʱ��
		 item15.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    	 String text = area.getText();
			    	 text+=df.format(new Date());
			    	 area.setText(text);
			            }
			      });
		 
		//ɾ��
		 item16.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	 area.replaceSelection(null);
			            }
			      });
		 //����
		 item21.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
					String inputValue = JOptionPane.showInputDialog("�������������"); 
//	            	int s=JOptionPane.showConfirmDialog(null, "��ǰ���ݲ�Ϊ�գ��Ƿ񱣴�", "��ʾ", JOptionPane.YES_NO_OPTION); 
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
	            	s=JOptionPane.showConfirmDialog(null, "������һ�����Ƿ����", "��ʾ", JOptionPane.YES_NO_OPTION); 

	            	l--;
					}
	            		

	               
	            }
	        });
		 //�滻
		 item22.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
					String inputValue = JOptionPane.showInputDialog("��������Ҫ�滻����"); 
					String inputValue01 = JOptionPane.showInputDialog("�������滻������"); 
	            	String ateatext=area.getText();
	            	
	            	if(ateatext.indexOf(inputValue)==-1) {
	                    JOptionPane.showMessageDialog(null,"�滻���ݲ�����","����",JOptionPane.WARNING_MESSAGE);

	            	}else {
	            		String aa1=ateatext.replace(inputValue,inputValue01);
	            		System.out.println(aa1);
	            		area.setText(aa1);
	  
					}
		 
	            }
	        });
		 //��������
		 item23.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	            	com=new JComboBox();
	            	com.addItem("����");
	            	com.addItem("����");
	            	com.addItem("����");
	            	com.addItem("΢���ź�");
	            	com.addItem("��Բ");
	            	com.addItem("��Բ");
	            	com.addItem("����");
	            	com.addItem("��׭");
	            	com.addItem("����Ҧ��");
	            	com.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							String item=(String)com.getSelectedItem();
							area.setFont(new Font(item, 1, 30));
							label.setFont(new Font(item, 1, 50));
						}
					});
	            	
	            	
	            	
	            	label.setFont(new Font("�����п�", 1, 50));

	        		frame1.add(label);
	         
	        		JPanel panel = new JPanel();
	        		// ֻ��ѡ����ɫ��Ӧ�ð�ť�ſ���
	        		apply.setEnabled(false);
	        		// ��Ӽ�����
	        		choose.addActionListener(e -> {
	        			try {
	        				color = JColorChooser.showDialog(frame, "ѡ��������ɫ", color);
	        				System.out.println(color);
	        			} catch (Exception ex) {
	        				ex.printStackTrace();
	        			}
	        			// Ӧ�ð�ť����
	        			apply.setEnabled(true);
	        		});
	         
	        		apply.addActionListener(e -> {
	        			label.setForeground(color);
	        			area.setForeground(color);
	        		});
	         
	        		change.addActionListener(e -> {
	        			JColorChooser colorChooser = new JColorChooser();
	        			JDialog dialog = JColorChooser.createDialog(frame, "��ѡ����ɫ", false, colorChooser,
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
		 //����
		 item24.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	                if(um.canUndo()) {
	                    um.undo();
	                } else {
	                    JOptionPane.showMessageDialog(null,"�޷�����","����",JOptionPane.WARNING_MESSAGE);
	                }
	            }
	        });
		//���
		 item25.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	            	area.setText("");
	            }
	        });
		 //��վ
		 item31.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	            	   try {
						Runtime.getRuntime().exec("cmd   /c   start   https://me.csdn.net/qq_45626867?ref=miniprofile");
					} catch (IOException e) {
						e.printStackTrace();
					}
	            }
	        });
		 //˵��
		 item32.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {

	            	JOptionPane.showMessageDialog(null, "��zgt����,��������ϵ��վ");
	            }
	        });
     	

		frame.setTitle("���±�");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		

	}

}
