package server_Catch;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;
 
public class ServerBackground {
	
	public static String[] Plyaer_Name=new String[2];
	public static String[] Plyaer_Image=new String[2];
	public static int[] Plyaer_Goals=new int[]{0,0};
	public static String[] Game_Q=new String[]{"자동차","황소","자바","메타몽","기린","칸쵸","계란과자","이어폰","핸드폰","키보드","사랑","삼각김밥","강아지","사이다","대학생","모니터","에어컨","세탁기","원룸","자동차"};
	public static int[] Game_start=new int[2];
	public static String[] Player_Score=new String[]{"0","0"};
	public int number=0;
    private ServerSocket serverSocket; // 서버 소켓
    private Socket socket; // 받아올 소켓 저장
    private ServerGUI gui;
    private String msg;
    /** XXX 03. 사용자들의 정보를 저장하는 맵 */ 
    private String Current_Goal;
    private Map<String, DataOutputStream> clientMap = new HashMap<String, DataOutputStream>();
    public int turn=0;
    int i=0;
    public void setGui(ServerGUI gui) {
        this.gui = gui;
    }
 
    public static void main(String[] args) {
    	Game_start[0]=0;
    	Game_start[1]=0;
        ServerBackground serverBackground = new ServerBackground();
        serverBackground.setting();
    }
    //서버가 생성될때 셋팅해주는 함수 
    public void setting() {
        
        try {
            
            Collections.synchronizedMap(clientMap); //교통정리를 해준다.( clientMap을 네트워크 처리해주는것 ) 
            serverSocket = new ServerSocket(8000);
 
            while (true) {
                /** XXX 01.서버가 할일 : 방문자를 계속 받아서, 쓰레드 리시버를 계속 생성 */
                
                System.out.println("대기중.....");
                socket = serverSocket.accept(); // 여기서 클라이언트 받음
                System.out.println(socket.getInetAddress() + "에서 접속했습니다.");
                
                //여기서 새로운 사용자 스레드 클래스를 생성해서 소켓 정보를 넣어줘야한다.
                Receiver receiver = new Receiver(socket);
                receiver.start();
            }
 
        } catch (IOException e) {
            e.printStackTrace(); 
        }
    }
    
    //맵의내용(클라이언트) 저장과 삭제 
    public void addClient(String nick, DataOutputStream out) throws IOException{
    	StringTokenizer tokens = new StringTokenizer(nick);
    	String msg1=tokens.nextToken("=>");
    	String msg2=tokens.nextToken("=>");
    	
    	Plyaer_Name[number]=msg1;
    	Plyaer_Image[number]=msg2;
        clientMap.put(Plyaer_Name[number], out);
        number++;
        if(number==1){
        	String message = "플레이어1은"+"=>"+Plyaer_Name[0]+"=>"+Plyaer_Image[0];
        	gui.appendMsg(message);
        	sendMessage(message);
        }
        else if(number==2){
        	String message = "플레이어1은"+"=>"+Plyaer_Name[0]+"=>"+Plyaer_Image[0];
        	gui.appendMsg(message);
        	sendMessage(message);
        	String message1 = "플레이어2은"+"=>"+Plyaer_Name[1]+"=>"+Plyaer_Image[1];
        	gui.appendMsg(message1);
        	sendMessage(message1);
        }
        
    }
    
    public void removeClient(String nick){
        String message=nick + "님이 나가셨습니다. \n";
        sendMessage(message);
        gui.appendMsg(message);
        clientMap.remove(nick);
    }
    
    //메세지 내용 전파 
    public void sendMessage (String msg){
        Iterator<String> iterator = clientMap.keySet().iterator(); //key셋으로 반복자지정
        String key = "";
  
        
        while(iterator.hasNext()){
        	
            key = iterator.next();// 반복자에서 하나하나 키를 빼온다.
            try{
                clientMap.get(key).writeUTF(msg);
            	}
             catch(IOException e){
                e.printStackTrace();
            }
        
        }
    }
    // ------------------리시버---------------------------
    class Receiver extends Thread {
        /** XXX 리시버가 할일 : 네트워크 소켓을 받아서 계속듣고 보내는 일. */
        private DataInputStream in; // 데이터 입력 스트림
        private DataOutputStream out; // 데이터 아웃풋 스트림
        private String nick;
        Random random;
 
        public Receiver(Socket socket) {
            try {
                out = new DataOutputStream(socket.getOutputStream());
                in = new DataInputStream(socket.getInputStream());
                nick = in.readUTF();
                addClient(nick,out);
            } catch (IOException e) {
                e.printStackTrace();
            }
            random = new Random();
        }
 
        @Override
        public void run() {
        	
            try {
                while (in != null) {
                    msg = in.readUTF();// UTF로 읽어들인다.
                    StringTokenizer tokens = new StringTokenizer(msg);
                    String msg1=tokens.nextToken("=>");
	            	String msg2=tokens.nextToken("=>");
	            	if(msg2.equals("@플레이어 준비완료!"))
	            	{
	            		if(msg1.equals(Plyaer_Name[0])){
	            			Game_start[0]=1;
	            			if(Game_start[0]==1&&Game_start[1]==1){
	            				int data = random.nextInt(20);
	            				Current_Goal=Game_Q[data];
	            				turn++;
	            				sendMessage("@게임시작!!=>"+Game_Q[i++]);
	            			}
	            		}
	            		else if(msg1.equals(Plyaer_Name[1]))
	            		{
	            			Game_start[1]=1;
	            			if(Game_start[0]==1&&Game_start[1]==1){
	            				int data = random.nextInt(20);
	            				Current_Goal=Game_Q[data];
	                    		turn++;
	            				sendMessage("@게임시작!!=>"+Game_Q[i++]);
	            			}
	            		}
	            	}
	            	else if(msg2.equals("@플레이어 준비완료해체!")){
	            		if(msg1.equals(Plyaer_Name[0])){
	            			Game_start[0]=0;
	            		}
	            		else if(msg1.equals(Plyaer_Name[1]))
	            		{
	            			Game_start[1]=0;
	            		}
	            	}
	            	else if(msg2.equals("@@다음라운드로!")){
	            		int data = random.nextInt(20);
	            		if(turn%2==0){
	                		Current_Goal=Game_Q[data];
	                		turn++;
	                		sendMessage("너의순번은@@=>"+Plyaer_Name[0]+"=>"+Plyaer_Name[0]+" 의차례입니다."+"=>"+Current_Goal+"=>"+Player_Score[0]+"=>"+Player_Score[1]);
	                	}
	                	else if(turn%2==1){
	                		Current_Goal=Game_Q[data];
	                		turn++;
	                		sendMessage("너의순번은@@=>"+Plyaer_Name[1]+"=>"+Plyaer_Name[1]+" 의차례입니다."+"=>"+Current_Goal+"=>"+Player_Score[0]+"=>"+Player_Score[1]);
	                	}
	            	}
	            	else if(msg1.equals("정답!!!!123")){
	            		  StringTokenizer tokens2 = new StringTokenizer(msg);
	                      String msg3=tokens.nextToken("=>");
	            		if(msg3.equals(Plyaer_Name[0])){
	            			int score= Integer.parseInt(Player_Score[0]);
	            			score++;
	            			if(score==3){
	            				sendMessage(Plyaer_Name[0]+"=>의 승리입니다.");
	            			}
	            			String score2=Integer.toString(score);
	            			Player_Score[0]=score2;
	            			sendMessage(msg);
	            		}
	            		else if(msg3.equals(Plyaer_Name[1])){
	            			int score= Integer.parseInt(Player_Score[1]);
	            			score++;
	            			if(score==3){
	            				sendMessage(Plyaer_Name[1]+"=>의 승리입니다.");
	            			}
	            			String score2=Integer.toString(score);
	            			Player_Score[1]=score2;
	            			sendMessage(msg);
	            		}
	            	}
	            	else{
                    sendMessage(msg);
	            	}
                    gui.appendMsg(msg);
                }
            } catch (Exception e) {
                //사용접속종료시 여기서 에러발생. 
                removeClient(nick);
            }
        }
        
        
        
    }
 
}