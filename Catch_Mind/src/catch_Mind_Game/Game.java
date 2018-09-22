package catch_Mind_Game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.StringTokenizer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;


public class Game extends JFrame {
	private static Game win=new Game();
	static int a=0;
	public Game() {
	}
	
	
	public void change() throws IOException {
			getContentPane().removeAll();
			MyPanel2 jpanel02=new MyPanel2(win);
			getContentPane().add(jpanel02);
			revalidate();
			repaint();
			a++;
	}
	
	public void change2() throws IOException{
		getContentPane().removeAll();
		SwingUtilities.updateComponentTreeUI(this);
		MyPanel2 jpanel02=new MyPanel2(win);
		getContentPane().add(jpanel02);
		revalidate();
		repaint();
		a++;
		
	}

	

	public static void main(String[] args) {
		// TODO 자동 생성된 메소드 스텁
		JFrame.setDefaultLookAndFeelDecorated(true);
		try {
			UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
		} catch (Exception e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		}
		win = new Game();
		win.setTitle("캐치마인드");
		MyPanel1 jpanel01=new MyPanel1(win);
		win.getContentPane().add(jpanel01);
		win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		win.setSize(1200,700);
		win.setVisible(true);
	}
}


class MyPanel1 extends JPanel{
		private JTextField textField;
		private JTextField txtAsd;
		private JTextArea textArea_1;
		private JButton btnNewButton_1;
		private JButton btnNewButton_2;
		private JButton btnNewButton_3;
		private JButton btnNewButton_4;
		JLabel My_Chacter_input;
	 private Font f1, f2, f3;
	 private Game win;
	 static String My_NickName="";
	 Thread abc;
	 static String MyImage;

	 static  String Image1_Path="사진경로";
	 static  String Image2_Path="사진경로";
	 static  String Image3_Path="사진경로";
	 static String Image4_Path="사진경로";
	 
	 static String MyImage_Path=" ";
	 
	 static String[] PlayerImage_Path=new String[]{"",""};
	 
	
	 
	 MyPanel1(Game win){
		 this.setLayout(null);
		 this.win=win;
		 
		 abc= new Sound1("소리파일있는 경로", true);
		 
		 Image img = new ImageIcon("이미지 파일").getImage();
		 JButton btnNewButton = new JButton("Game Start");
			btnNewButton.setBounds(596, 600, 258, 40);
			win.add(btnNewButton);
			
			
			
			
			btnNewButton_1 = new JButton("");
			btnNewButton_1.setBackground(Color.WHITE);
			btnNewButton_1.setIcon(new ImageIcon(Image1_Path));
			btnNewButton_1.setBounds(280, 564, 80, 72);
			win.add(btnNewButton_1);
			
			btnNewButton_2 = new JButton("");
			btnNewButton_2.setIcon(new ImageIcon(Image2_Path));
			btnNewButton_2.setBounds(360, 564, 80, 72);
			win.add(btnNewButton_2);
			
			btnNewButton_3 = new JButton("");
			btnNewButton_3.setIcon(new ImageIcon(Image3_Path));
			btnNewButton_3.setBounds(440, 564, 80, 72);
			win.add(btnNewButton_3);
			
			btnNewButton_4 = new JButton("");
			btnNewButton_4.setIcon(new ImageIcon(Image4_Path));
			btnNewButton_4.setBounds(520, 564, 80, 72);
			win.add(btnNewButton_4);
			
			My_Chacter_input = new JLabel("캐릭터를 선택하세요!");
			My_Chacter_input.setBackground(Color.WHITE);
			My_Chacter_input.setHorizontalAlignment(SwingConstants.CENTER);
			My_Chacter_input.setBounds(280, 500, 240, 72);
			My_Chacter_input.setFont(new Font("Serif", Font.BOLD, 20));
			win.add(My_Chacter_input);
			
			My_Chacter_input = new JLabel("(닉네임 미입력, 캐릭터 미선택..이용불가..)");
			My_Chacter_input.setBackground(Color.WHITE);
			My_Chacter_input.setHorizontalAlignment(SwingConstants.CENTER);
			My_Chacter_input.setBounds(570, 500, 350, 72);
			My_Chacter_input.setFont(new Font("Serif", Font.BOLD, 15));
			win.add(My_Chacter_input);
			
			JLabel My_Chacter = new JLabel("내 캐릭터");
			My_Chacter.setBackground(Color.WHITE);
			My_Chacter.setIcon(new ImageIcon());
			My_Chacter.setOpaque(true);
			My_Chacter.setBounds(851, 564, 80, 72);
			win.add(My_Chacter);
			
			txtAsd = new JTextField(" ",5);
			txtAsd.setHorizontalAlignment(SwingConstants.CENTER);
			txtAsd.setForeground(Color.BLACK);
			txtAsd.setText("");
			txtAsd.setBounds(597, 560, 257, 47);
			win.add(txtAsd);
			textArea_1 = new JTextArea(){
	            { setOpaque( false ) ; }
	            public void paintComponent(Graphics g){
	              g.drawImage(img,0,0,this);       //이미지 그리기
	                super.paintComponent(g);
	            }
	        };
	        textArea_1.setEditable (false); 
			textArea_1.setColumns(10);
	        textArea_1.setBounds(0, 0, 1200, 700);
	        win.add(textArea_1);
			/*
			textField = new JTextField("asdf ",0);
			textField.setBackground(Color.WHITE);
			textField.setBounds(275, 560, 650, 80);
			win.add(textField);
			textField.setColumns(10);
	*/
			btnNewButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					My_NickName=txtAsd.getText();
					if(MyImage_Path.equals(" ")||My_NickName.equals("")){
						JOptionPane.showMessageDialog(null, "닉네임을 입력 및 캐릭터 선택하시오.");
					}
					else if(My_NickName.length()>4){
						JOptionPane.showMessageDialog(null, "닉네임은 1~4자로 정해주세요.");
					}
					else{
					try {
						((Sound1) abc).Stop_Sound();
						win.change();
					} catch (IOException e1) {
						// TODO 자동 생성된 catch 블록
						e1.printStackTrace();
					}
					}
				}
			});
			btnNewButton_1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					MyImage_Path=Image1_Path;
					My_Chacter.setIcon(new ImageIcon(MyImage_Path));
					Sound("프로젝트 경로/메타몽.wav", false);
					MyImage="1";
				}
			});
			btnNewButton_2.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					MyImage_Path=Image2_Path;
					My_Chacter.setIcon(new ImageIcon(MyImage_Path));
					Sound("프로젝트 경로/이상해씨.wav", false);
					MyImage="2";
				}
			});
			btnNewButton_3.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					MyImage_Path=Image3_Path;
					My_Chacter.setIcon(new ImageIcon(MyImage_Path));
					Sound("프로젝트 경로/피카츄.wav", false);
					MyImage="3";
				}
			});
			btnNewButton_4.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					MyImage_Path=Image4_Path;
					My_Chacter.setIcon(new ImageIcon(MyImage_Path));
					Sound("프로젝트 경로/근육몬.wav", false);
					MyImage="4";
				}
			});
	 }
	 
	 public static void Sound(String file, boolean Loop){
			//사운드재생용메소드
			//메인 클래스에 추가로 메소드를 하나 더 만들었습니다.
			//사운드파일을받아들여해당사운드를재생시킨다.
			Clip clip;
			try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(file)));
			clip = AudioSystem.getClip();
			clip.open(ais);
			clip.start();
			if ( Loop) clip.loop(-1);
			//Loop 값이true면 사운드재생을무한반복시킵니다.
			//false면 한번만재생시킵니다.
			} catch (Exception e) {
			e.printStackTrace();
			}
		}
	 
	 class Sound1 extends Thread{
		//사운드재생용메소드
		//메인 클래스에 추가로 메소드를 하나 더 만들었습니다.
		//사운드파일을받아들여해당사운드를재생시킨다.
		 public boolean Stop=true;
		 Clip clip;
		 Sound1(String file, boolean Loop){
		boolean Stop=true;
		this.start();
		
		try {
		AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(file)));
		clip = AudioSystem.getClip();
		clip.open(ais);
		clip.start();
		if ( Loop) clip.loop(-1);
		//Loop 값이true면 사운드재생을무한반복시킵니다.
		//false면 한번만재생시킵니다.
		} catch (Exception e) {
		e.printStackTrace();
		}
		}
		 
		void Stop_Sound(){
			clip.stop();
		 }
		@Override
		public void run(){
		
		}
	 }
}

class MyPanel2 extends JPanel{
	public static String Winner_name;
	private Font f1;
	 private Game win;
	String name=MyPanel1.My_NickName;
	public static String[] Plyaer_Name={"  ","  "};
	public static String[] Plyaer_Image_Path={"",""};
	static JButton Game_Start_Button = new JButton("게임 시작");
	static JTextField Talk_String= new JTextField("");
	static JLabel Game_Loading_Stauts = new JLabel("대기중...");
	static JLabel Current_Goal_Stauts = new JLabel("...");
	static JLabel Player_Name_String1 = new JLabel(" ");
	static JLabel Player_Name_String2 = new JLabel(" ");
	static JLabel Player1_Image = new JLabel("");
	static JLabel Player2_Image = new JLabel("");
	static JLabel Player1_Score = new JLabel("0문제");
	static JLabel Player2_Score = new JLabel("0문제");
	static JTextArea	Player1_Talk = new JTextArea(" ");
	static JTextArea	Player2_Talk = new JTextArea(" ");
	static JButton button = new JButton("전송!");
	static JButton Black_Button = new JButton("검은색");
	static JButton Green_Button = new JButton("초록색");
	static JButton Red_Button = new JButton("빨간색");
	static JButton White_Button = new JButton("지우개");
	static JButton Clear_Button = new JButton("화면지우기");
	private JTextArea textArea_1;
	private JTextArea textArea_1_1;
	static JLabel Current_Turn;
	Image img = new ImageIcon("프로젝트 경로/Backg_r.png").getImage();
	
	Image img_2 = new ImageIcon("프로젝트 경로/sky_r.jpg").getImage();
	

	 EtchedBorder eborder=new EtchedBorder(EtchedBorder.RAISED);//평면에 끌로 판듯이 외곽선 효과를 내는 것이고 양각의 효과를 준다.
	 
	static JLabel Timer_count= new JLabel("Timer");
	
	static int My_turn=0;
	static int color=0;
	private Game win2;
	static String Current_Goal;
	
	static Canvas can; 
	
	static Thread t;
	
	int numInt2;
	int numInt3;
	int numInt4;
	
	private ClientBackground client;
	
	MyPanel2(Game win) throws IOException{
		 this.setLayout(null);
		 this.win=win;
		 if(Game.a==1)
		 {
			 
			 Current_Turn = new JLabel("...");
			 Current_Turn.setBounds(800, 10, 250, 50);
			 Current_Turn.setFont(new Font("Serif", Font.BOLD, 18));
			 Current_Turn.setHorizontalAlignment(SwingConstants.CENTER);
			 Gaming_Loading();
			 win.add(Current_Turn);
		 }
		 
		 button.setBounds(712, 610, 155, 35);
		 win.add(button);
		 
		 Timer_count.setBounds(525, 12, 150, 35);
		 Timer_count.setFont(new Font("궁서체", Font.BOLD, 28));
		 Timer_count.setHorizontalAlignment(SwingConstants.CENTER);
		 win.add(Timer_count);
		 
		 Current_Goal_Stauts.setBounds(10,22, 179, 42);
		 Current_Goal_Stauts.setFont(new Font("Serif", Font.BOLD, 28));
		 Current_Goal_Stauts.setHorizontalAlignment(SwingConstants.CENTER);
		 win.add(Current_Goal_Stauts);
		 
		 Talk_String.setBounds(340, 610, 360, 35);
		 win.add(Talk_String);
		 Talk_String.setColumns(10);
			
		 Player1_Talk.setBounds(12, 275, 176, 60);
		 Player1_Talk.setEditable (false); 
		 Player1_Talk.setLineWrap(true);
		 win.add(Player1_Talk);
		 
		 Player1_Talk.setColumns(10);
		 
		 Player1_Score.setBounds(88, 204, 100, 52);
		 Player1_Score.setHorizontalAlignment(SwingConstants.CENTER);
		 Player1_Score.setBorder(eborder);
		 Player1_Score.setOpaque(true);
		 Player1_Score.setBackground(Color.WHITE);
		 win.add(Player1_Score);
		 
		 Player2_Score.setBounds(1092, 204, 100, 52);
		 Player2_Score.setHorizontalAlignment(SwingConstants.CENTER);
		 Player2_Score.setBorder(eborder);
		 Player2_Score.setOpaque(true);
		 Player2_Score.setBackground(Color.WHITE);
		 win.add(Player2_Score);
		
		 
		 Player1_Image.setBounds(7, 183, 80, 72);
		 Player1_Image.setHorizontalAlignment(SwingConstants.CENTER);
		 Player1_Image.setOpaque(true);
		 if(Plyaer_Image_Path[0].equals("1")){
			 Player1_Image.setIcon(new ImageIcon(MyPanel1.Image1_Path));
		 }
		 else if(Plyaer_Image_Path[0].equals("2")){
			 Player1_Image.setIcon(new ImageIcon(MyPanel1.Image2_Path));
		 }
		 else if(Plyaer_Image_Path[0].equals("3")){
			 Player1_Image.setIcon(new ImageIcon(MyPanel1.Image3_Path));
		 }
		 else if(Plyaer_Image_Path[0].equals("4")){
			 Player1_Image.setIcon(new ImageIcon(MyPanel1.Image4_Path));
		 }
		 win.add(Player1_Image);
			
		 Player2_Image.setBounds(1012, 183, 80, 72);
		 Player2_Image.setHorizontalAlignment(SwingConstants.CENTER);
		 Player2_Image.setOpaque(true);
		 if(Plyaer_Image_Path[1].equals("1")){
			 Player2_Image.setIcon(new ImageIcon(MyPanel1.Image1_Path));
		 }
		 else if(Plyaer_Image_Path[1].equals("2")){
			 Player2_Image.setIcon(new ImageIcon(MyPanel1.Image2_Path));
		 }
		 else if(Plyaer_Image_Path[1].equals("3")){
			 Player2_Image.setIcon(new ImageIcon(MyPanel1.Image3_Path));
		 }
		 else if(Plyaer_Image_Path[1].equals("4")){
			 Player2_Image.setIcon(new ImageIcon(MyPanel1.Image4_Path));
		 }
		 win.add(Player2_Image);
			
		 Player2_Talk.setColumns(10);
		 Player2_Talk.setEditable (false);
		 Player2_Talk.setBounds(1012, 275, 180, 60);
		 Player1_Talk.setLineWrap(true);
		 win.add(Player2_Talk);
		
		 
		 can=new MyCanvas();
		 win.add(can);
		 can.setBounds(200, 100, 800, 500);
		 can.setBackground(Color.white);
		 
		 MyHandler my=new MyHandler();
		 can.addMouseMotionListener(my); // 캔버스 객체에 마우스모션리스너를 부착한다.
	
		if(Game.a==0){	
		 Game_Start_Button.setBounds(1026, 10, 150, 37);
		 Game_Start_Button.setHorizontalAlignment(SwingConstants.CENTER);
		 win.add(Game_Start_Button);
			
		 Game_Loading_Stauts.setBounds(870, 22, 100, 15);
		 Game_Loading_Stauts.setHorizontalAlignment(SwingConstants.CENTER);
		 win.add(Game_Loading_Stauts);
		 MyPanel1.Sound("C:\\Users\\lunarsjy\\Desktop\\Java_Project\\Catch_Mind\\lib\\ditto_w.wav", true);
		}
		 
		 Player_Name_String1.setBounds(88, 183, 100, 21);
		 Player_Name_String1.setHorizontalAlignment(SwingConstants.CENTER);
		 Player_Name_String1.setOpaque(true);
		 Player_Name_String1.setBorder(eborder);
		 Player_Name_String1.setBackground(Color.WHITE);
		 win.add(Player_Name_String1);
			
		 Player_Name_String2.setBounds(1092, 183, 100, 21);
		 Player_Name_String2.setHorizontalAlignment(SwingConstants.CENTER);
		 Player_Name_String2.setOpaque(true);
		 Player_Name_String2.setBorder(eborder);
		 Player_Name_String2.setBackground(Color.WHITE);
		 win.add(Player_Name_String2);
		 
		 Black_Button.setBounds(210, 67, 75, 23);
		 Black_Button.setHorizontalAlignment(SwingConstants.CENTER);
		 win.add(Black_Button);
		 
		 Clear_Button.setBounds(880, 67, 110, 23);
		 Clear_Button.setHorizontalAlignment(SwingConstants.CENTER);
		 win.add(Clear_Button);
		 Green_Button.setBounds(380, 67, 75, 23);
		 Green_Button.setHorizontalAlignment(SwingConstants.CENTER);
		win.add(Green_Button);
			
		Red_Button.setBounds(295, 67, 75, 23);
		Red_Button.setHorizontalAlignment(SwingConstants.CENTER);
		win.add(Red_Button);
			
		White_Button.setBounds(465, 67, 75, 23);
		White_Button.setHorizontalAlignment(SwingConstants.CENTER);
		win.add(White_Button);
			
		JTextArea textArea = new JTextArea();
		 
		 Black_Button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					((MyCanvas)can).color123=0;
				}
			});
			
		 Green_Button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					((MyCanvas)can).color123=1;
				}
			});


		 Red_Button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					((MyCanvas)can).color123=2;
				}
			});

		 White_Button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					((MyCanvas)can).color123=3;
				}
			});
		 
		 Clear_Button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					((MyCanvas)can).color123=4;
					if(My_turn==1){
						MyPanel2.Sender.Sender_Draw(0,0,4);
						MyHandler.clear_canvas();
					}
					else if(My_turn!=2){
					MyHandler.clear_canvas();
					}
				}
			});
		 
		 textArea_1_1 = new JTextArea(){
	            { setOpaque( false ) ; }
	            public void paintComponent(Graphics g){
	              g.drawImage(img_2,0,0,null);       //이미지 그리기
	                super.paintComponent(g);
	            }
	        };
	        
			textArea_1_1.setBounds(198, 57, 804, 545);
			textArea_1_1.setColumns(10);
			textArea_1_1.setEditable (false); 
			win.add(textArea_1_1);
			
			textArea = new JTextArea(){
	            { setOpaque( false ) ; }
	            public void paintComponent(Graphics g){
	              g.drawImage(img,0,0,null);       //이미지 그리기
	                super.paintComponent(g);
	            }
	        };
	        textArea.setEditable (false); 
			textArea.setBounds(0, 0, 1200, 700);
			textArea.setColumns(10);
			win.add(textArea);
			
			JTextArea textArea_1 = new JTextArea();
			
		 if(Game.a==0){
		 client= new ClientBackground();
		 }
	}
	


	
	public class ClientBackground{
	    private Socket socket;

	    JButton bt1;
	    
	    ClientBackground() {connect(); }
	    
	    //클라이언트 접속이 완료되면 
	    public void connect(){
	        try {
	            socket = new Socket("127.0.0.1", 8000);
	            System.out.println("서버에 연결됨");
	            
	            Thread sender = new Sender(socket, name,MyPanel1.MyImage);            
	            //서버에서 보내는 메시지를 사용자의 콘솔에 출력하는 쓰레드.
	            Thread receiver = new Receiver(socket);      
	            
	            //접속하자마자 닉네임 전송하면, 서버가 닉네임으로 인식 
	            System.out.println("클라이언트 : 닉네임 전송완료 ");
	            
	            sender.start(); //스레드 시동
	            receiver.start(); //스레드 시동
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	  
	  
	}
	
	public static class Sender extends Thread {
	    Socket socket;
	    static DataOutputStream out;
	    String name;
	    String MyImage;
	   
	    int Game_Start_number=0;
	    
	   
	    //생성자 ( 매개변수로 소켓과 사용자 이름 받습니다. )
	    public Sender(Socket socket, String name,String MyImage){ //소켓과 사용자 이름을 받는다.
	        this.socket = socket;      
	        try{
	            out = new DataOutputStream(this.socket.getOutputStream());
	            this.MyImage=MyImage;
	            this.name = name; //받아온 사용자이름을 전역변수에 저장, 다른 메서드인 run()에서 사용하기위함.
	        }catch(Exception e){
	            System.out.println("예외:"+e);
	        }
	    }
	   
	    @Override
	    public void run(){ //run()메소드 재정의
	    	try {
				out.writeUTF(name+"=>"+MyImage);
			} catch (IOException e1) {
				// TODO 자동 생성된 catch 블록
				e1.printStackTrace();
			}
	    	
	        //키보드로부터 입력을 받기위한 스캐너 객체 생성
	    	button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String msg=null;
					msg =Talk_String.getText();
					Talk_String.setText("");
					if(msg.equals(Current_Goal)&&My_turn==2){
						try {
							out.writeUTF("정답!!!!123=>"+name+"님 정답입니다.=>"+name);
						} catch (IOException e1) {
							// TODO 자동 생성된 catch 블록
							e1.printStackTrace();
						}
					}
				
	        //출력스트림이 null이 아니면..반복
	      //while문 안에 try-catch문을 사용한 이유는 while문 내부에서 예외가 발생하더라도
	                  //계속 반복할수있게 하기위해서이다.     
					else{
	             try {
					out.writeUTF(name+"=>"+msg);
					out.writeUTF(name+"=>"+"채팅지우기@@@@@");
				} catch (IOException e1) {
					// TODO 자동 생성된 catch 블록
					e1.printStackTrace();
				} //키보드로부터 입력받은 문자열을 서버로 보낸다.
					}
	 //run()------
	}//class Sender-------
		});
	    	Game_Start_Button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Game_Start_number++;
					if(Game_Start_number%2==1){
						try {
							out.writeUTF(name+"=>"+"@플레이어 준비완료!");
							Game_Loading_Stauts.setText("준비완료!");
					
						} catch (IOException e) {
							// TODO 자동 생성된 catch 블록
							e.printStackTrace();
						}
					}
					else if(Game_Start_number%2==0){
						try {
							out.writeUTF(name+"=>"+"@플레이어 준비완료해체!");
							Game_Loading_Stauts.setText("준비해주세요!");
						
						} catch (IOException e) {
							// TODO 자동 생성된 catch 블록
							e.printStackTrace();
						}
					}
				}
			});
	    	
	    }
	    
	    public static void Sender_Draw(int x, int y, int color){
	    	try {
	    		String x1=String.valueOf(x);
	    		String y1=String.valueOf(y);
	    		String color1=String.valueOf(color);
	    		String str="@@그릴좌표는=>"+x1+"=>"+y1+"=>"+color1;
				out.writeUTF(str);
			} catch (IOException e1) {
				// TODO 자동 생성된 catch 블록
				e1.printStackTrace();
			}
	    }
	    public static void Turn_Around_Ready(){
	    	String str="asdfasf=>@@다음라운드로!";
	    	try {
				out.writeUTF(str);
			} catch (IOException e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
	    }
	   
	}
	public class Receiver extends Thread{
		   
	    Socket socket;
	    DataInputStream in;
	   
	    //Socket을 매개변수로 받는 생성자.
	    public Receiver(Socket socket){
	        this.socket = socket;
	       
	        try{
	            in = new DataInputStream(this.socket.getInputStream());
	        }catch(Exception e){
	            System.out.println("예외:"+e);
	        }
	    }//생성자 --------------------
	   
	    @Override
	    public void run(){ //run()메소드 재정의
	    
	        while(in!=null){ //입력스트림이 null이 아니면..반복
	            try{
	            	String msg = in.readUTF();
	            	StringTokenizer tokens1 = new StringTokenizer(msg);
	            	String msg5=tokens1.nextToken("=>");
	            	String msg6=tokens1.nextToken("=>");
	            	if(msg5.equals("@게임시작!!")){
	            		Current_Goal=msg6;
	            		win.change2();
	            	}
	            	else if(msg6.equals("채팅지우기@@@@@")){
	            		sleep(2000);
	            		if(msg5.equals(Plyaer_Name[0])){
	            			 Player1_Talk.setText("   ");
	            		}
	            		else if(msg5.equals(Plyaer_Name[1])){
	            			Player2_Talk.setText("   ");
	            		}
	            	}
	            	else if(msg6.equals("의 승리입니다.")){
	            		Winner_name=msg5;
	            		JOptionPane.showMessageDialog(null, msg5+"님의 승리입니다.!!");
	            		System.exit(0);
	            	}
	            	else if(msg5.equals("@@그릴좌표는")){
	            		StringTokenizer tokens = new StringTokenizer(msg);
		            	String msg1=tokens.nextToken("=>");
		            	String msg2=tokens.nextToken("=>");
		            	String msg3=tokens.nextToken("=>");
		            	String msg4=tokens.nextToken("=>");
		            	numInt2 = Integer.parseInt(msg2);
		            	numInt3 = Integer.parseInt(msg3);
		            	numInt4 = Integer.parseInt(msg4);
		            	((MyCanvas)can).x=numInt2;
		            	((MyCanvas)can).y=numInt3;
		            	((MyCanvas)can).color123=numInt4;
		            	if(numInt4==4&numInt2==0&&numInt3==0){
		            		MyHandler.clear_canvas();
		            	}
		            	else{
		            	MyHandler.Drawing();
		            	}
		         
	            	}
	            	
	            	else if(msg5.equals("너의순번은@@")){
	            		StringTokenizer tokens = new StringTokenizer(msg);
		            	String msg1=tokens1.nextToken("=>");
		            	String msg2=tokens1.nextToken("=>");
		            	String msg3=tokens1.nextToken("=>");
		            	String msg4=tokens1.nextToken("=>");
		            	Current_Turn.setText(msg1);
		            	sleep(1000);
		            	((Timer) t).soted_Timer();
	            		Start_Game_Loading(msg6,msg2,msg3,msg4);

	            
	            	}
	            	else if(msg5.equals("정답!!!!123")){
	            		StringTokenizer tokens = new StringTokenizer(msg);
	            		Current_Turn.setText(msg6);
		            	sleep(1000);
		            	if(My_turn==1){
		            	MyPanel2.Sender.Turn_Around_Ready();
		            	}
		            	
	            	}
	            	else{
	            	StringTokenizer tokens = new StringTokenizer(msg);
	            	String msg1=tokens.nextToken("=>");
	            	String msg2=tokens.nextToken("=>");
	            	if(msg1.equals("플레이어1은")){
	            		StringTokenizer tokens2 = new StringTokenizer(msg);
		            	String msg3=tokens.nextToken("=>");
	            		Plyaer_Name[0]=msg2;
	            		Player_Name_String1.setText(Plyaer_Name[0]);
	            		Plyaer_Image_Path[0]=msg3;
	           
	            		if(Plyaer_Image_Path[0].equals("1")){
	            			Player1_Image.setIcon(new ImageIcon(MyPanel1.Image1_Path));
	            		}
	            		else if(Plyaer_Image_Path[0].equals("2")){
	            			Player1_Image.setIcon(new ImageIcon(MyPanel1.Image2_Path));
	            		}
	            		else if(Plyaer_Image_Path[0].equals("3")){
	            			Player1_Image.setIcon(new ImageIcon(MyPanel1.Image3_Path));
	            		}
	            		else if(Plyaer_Image_Path[0].equals("4")){
	            			Player1_Image.setIcon(new ImageIcon(MyPanel1.Image4_Path));
	            		}
	            		
	            		//Player1_image
	            		//SwingUtilities.updateComponentTreeUI(win);
	            	}
	            	else if(msg1.equals("플레이어2은")){
	            		StringTokenizer tokens2 = new StringTokenizer(msg);
		            	String msg3=tokens.nextToken("=>");
	            		Plyaer_Name[1]=msg2;
	            		Player_Name_String2.setText(Plyaer_Name[1]);
	            		Plyaer_Image_Path[1]=msg3;
	       
	            		if(Plyaer_Image_Path[1].equals("1")){
	            			Player2_Image.setIcon(new ImageIcon(MyPanel1.Image1_Path));
	            		}
	            		else if(Plyaer_Image_Path[1].equals("2")){
	            			Player2_Image.setIcon(new ImageIcon(MyPanel1.Image2_Path));
	            		}
	            		else if(Plyaer_Image_Path[1].equals("3")){
	            			Player2_Image.setIcon(new ImageIcon(MyPanel1.Image3_Path));
	            		}
	            		else if(Plyaer_Image_Path[1].equals("4")){
	            			Player2_Image.setIcon(new ImageIcon(MyPanel1.Image4_Path));
	            		}
	            		Game_Loading_Stauts.setText("준비해주세요!");
	            		//SwingUtilities.updateComponentTreeUI(win);
	            	}
	            	else if(msg1.equals(Plyaer_Name[0])){
	            		Player1_Talk.setText(msg2);
	            		//SwingUtilities.updateComponentTreeUI(win);
	            		
	            	}
	            	else if(msg1.equals(Plyaer_Name[1])){
	            		Player2_Talk.setText(msg2);
	            		//SwingUtilities.updateComponentTreeUI(win);
	         
	            	}
	            	
	                //서버로부터 읽어온 데이터를 콘솔에 출력
	            	}
	            }catch(Exception e){
	                System.out.println("예외:"+e);
	            }
	        }//while----
	    }//run()------
	    
	}//class Receiver ------
	
	void  Gaming_Loading(){
		SwingUtilities.updateComponentTreeUI(this);
		
		JLabel Game_Count = new JLabel("Game~ Start~");
		Game_Count.setVerticalAlignment(SwingConstants.CENTER);
		Game_Count.setHorizontalAlignment(SwingConstants.CENTER);
		Game_Count.setFont(new Font("돋움", Font.BOLD, 100));
		Game_Count.setForeground(Color.BLUE);
		win.add(Game_Count);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		}
		win.remove(Game_Count);
		SwingUtilities.updateComponentTreeUI(this);
		
		
		
		
		if(MyPanel2.Plyaer_Name[0].equals(MyPanel1.My_NickName)){
			MyPanel2.My_turn=1;
			Current_Goal_Stauts.setText(Current_Goal);
			
		}
		else if(MyPanel2.Plyaer_Name[1].equals(MyPanel1.My_NickName)){
			MyPanel2.My_turn=2;
			Current_Goal_Stauts.setText("...");
		}
		Current_Turn.setText(Plyaer_Name[0]+" 의차례입니다.");
	
		t = new Timer();
	}
	void Start_Game_Loading(String NickName,String GOALs,String Player1_score,String Player2_score ){
		if(name.equals(NickName)){
			MyPanel2.My_turn=1;
			((MyCanvas)can).color123=4;
			MyHandler.clear_canvas();
			Player1_Score.setText(Player1_score+"문제");
			Player2_Score.setText(Player2_score+"문제");
			Current_Goal_Stauts.setText(GOALs);
			Current_Goal=GOALs;
	}
		else{
			MyPanel2.My_turn=2;
			((MyCanvas)can).color123=4;
			MyHandler.clear_canvas();
			Player1_Score.setText(Player1_score+"문제");
			Player2_Score.setText(Player2_score+"문제");
			 Current_Goal_Stauts.setText("...");
			 Current_Goal=GOALs;
		}
		t = new Timer();
	}
	
	
	
	static class MyHandler implements MouseMotionListener{
		  
		  public void mouseDragged(MouseEvent e){
		   //마우스를 드래그한 지점의 x좌표,y좌표를 얻어와서 can의 x,y 좌표값에 전달한다.
		   int xx=e.getX(); int yy=e.getY();
		   ((MyCanvas)can).x=xx; ((MyCanvas)can).y=yy;
		   int color=((MyCanvas)can).color123;
		   //paint()는 JVM이 호출해주는 메소드으로 변경x, repaint을 써서 재사용하자
		   if(MyPanel2.My_turn==0)
		   can.repaint();
		   else if(MyPanel2.My_turn==1){
			   MyPanel2.Sender.Sender_Draw(xx, yy,color);
		   		can.repaint();
		   }
		  }

		  public void mouseMoved(MouseEvent e){
		  }
		  
		  static void Drawing(){
			  can.repaint();  
		  }
		  
		  static void clear_canvas(){
			  color=4;
			  can.repaint();  
		  }
		  
		  
		   
	}
		 
	public class MyCanvas extends Canvas {
			 
			 //처음에 까만색 점 안찍히게 하기 위해서 x,y -값 지정
			 int x=-50; 
			 int y=-50;
			 int w=7; 
			 int h=7;
			 int color123=0;
			 
			 @Override
			 public void paint(Graphics g){
				if(color123==0){
			  g.setColor(Color.black);
			  g.fillOval(x, y, w, h); // x, y 지점에 70,70 크기의 원 그리기
				}
				else if(color123==1){
					  g.setColor(Color.GREEN);
					  g.fillOval(x, y, w, h); // x, y 지점에 70,70 크기의 원 그리기
				}
				else if(color123==2){
					  g.setColor(Color.red);
					  g.fillOval(x, y, w, h); // x, y 지점에 70,70 크기의 원 그리기
				}
				else if(color123==3){
					  g.setColor(Color.WHITE);
					  g.fillOval(x, y, w, h); // x, y 지점에 70,70 크기의 원 그리기
				}
				else if(color123==4){
					g.setColor(Color.WHITE);
					g.fillRect(0,0,892, 595);
					color123=0;
					}
			 }
			 
			 @Override
			 public void update(Graphics g){
			  paint(g);
			 }
			 
	}
	
	class Timer extends Thread{
		int Count=81;
		private boolean stopped=false;
		
		Timer(){
			stopped=false;
			this.start();
		}
		
		@Override
		public void run(){
			  while(!stopped){
				  try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO 자동 생성된 catch 블록
					e.printStackTrace();
				}
				  Count--;
				  String numStr2 = String.valueOf(Count);
				  Timer_count.setText(numStr2);
				  if(numStr2.equals("0")){
					  stopped = true;
					  Sender.Turn_Around_Ready();
				  }
			  }
		 }
		
		void soted_Timer(){
			stopped = true;
		}
		/*
		void start_Timer(){
			Count=80;
			stopped=false;
		}
		*/
	}
	
}



	
	
	

