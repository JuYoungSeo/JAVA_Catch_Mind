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
	public static String[] Game_Q=new String[]{"�ڵ���","Ȳ��","�ڹ�","��Ÿ��","�⸰","ĭ��","�������","�̾���","�ڵ���","Ű����","���","�ﰢ���","������","���̴�","���л�","�����","������","��Ź��","����","�ڵ���"};
	public static int[] Game_start=new int[2];
	public static String[] Player_Score=new String[]{"0","0"};
	public int number=0;
    private ServerSocket serverSocket; // ���� ����
    private Socket socket; // �޾ƿ� ���� ����
    private ServerGUI gui;
    private String msg;
    /** XXX 03. ����ڵ��� ������ �����ϴ� �� */ 
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
    //������ �����ɶ� �������ִ� �Լ� 
    public void setting() {
        
        try {
            
            Collections.synchronizedMap(clientMap); //���������� ���ش�.( clientMap�� ��Ʈ��ũ ó�����ִ°� ) 
            serverSocket = new ServerSocket(8000);
 
            while (true) {
                /** XXX 01.������ ���� : �湮�ڸ� ��� �޾Ƽ�, ������ ���ù��� ��� ���� */
                
                System.out.println("�����.....");
                socket = serverSocket.accept(); // ���⼭ Ŭ���̾�Ʈ ����
                System.out.println(socket.getInetAddress() + "���� �����߽��ϴ�.");
                
                //���⼭ ���ο� ����� ������ Ŭ������ �����ؼ� ���� ������ �־�����Ѵ�.
                Receiver receiver = new Receiver(socket);
                receiver.start();
            }
 
        } catch (IOException e) {
            e.printStackTrace(); 
        }
    }
    
    //���ǳ���(Ŭ���̾�Ʈ) ����� ���� 
    public void addClient(String nick, DataOutputStream out) throws IOException{
    	StringTokenizer tokens = new StringTokenizer(nick);
    	String msg1=tokens.nextToken("=>");
    	String msg2=tokens.nextToken("=>");
    	
    	Plyaer_Name[number]=msg1;
    	Plyaer_Image[number]=msg2;
        clientMap.put(Plyaer_Name[number], out);
        number++;
        if(number==1){
        	String message = "�÷��̾�1��"+"=>"+Plyaer_Name[0]+"=>"+Plyaer_Image[0];
        	gui.appendMsg(message);
        	sendMessage(message);
        }
        else if(number==2){
        	String message = "�÷��̾�1��"+"=>"+Plyaer_Name[0]+"=>"+Plyaer_Image[0];
        	gui.appendMsg(message);
        	sendMessage(message);
        	String message1 = "�÷��̾�2��"+"=>"+Plyaer_Name[1]+"=>"+Plyaer_Image[1];
        	gui.appendMsg(message1);
        	sendMessage(message1);
        }
        
    }
    
    public void removeClient(String nick){
        String message=nick + "���� �����̽��ϴ�. \n";
        sendMessage(message);
        gui.appendMsg(message);
        clientMap.remove(nick);
    }
    
    //�޼��� ���� ���� 
    public void sendMessage (String msg){
        Iterator<String> iterator = clientMap.keySet().iterator(); //key������ �ݺ�������
        String key = "";
  
        
        while(iterator.hasNext()){
        	
            key = iterator.next();// �ݺ��ڿ��� �ϳ��ϳ� Ű�� ���´�.
            try{
                clientMap.get(key).writeUTF(msg);
            	}
             catch(IOException e){
                e.printStackTrace();
            }
        
        }
    }
    // ------------------���ù�---------------------------
    class Receiver extends Thread {
        /** XXX ���ù��� ���� : ��Ʈ��ũ ������ �޾Ƽ� ��ӵ�� ������ ��. */
        private DataInputStream in; // ������ �Է� ��Ʈ��
        private DataOutputStream out; // ������ �ƿ�ǲ ��Ʈ��
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
                    msg = in.readUTF();// UTF�� �о���δ�.
                    StringTokenizer tokens = new StringTokenizer(msg);
                    String msg1=tokens.nextToken("=>");
	            	String msg2=tokens.nextToken("=>");
	            	if(msg2.equals("@�÷��̾� �غ�Ϸ�!"))
	            	{
	            		if(msg1.equals(Plyaer_Name[0])){
	            			Game_start[0]=1;
	            			if(Game_start[0]==1&&Game_start[1]==1){
	            				int data = random.nextInt(20);
	            				Current_Goal=Game_Q[data];
	            				turn++;
	            				sendMessage("@���ӽ���!!=>"+Game_Q[i++]);
	            			}
	            		}
	            		else if(msg1.equals(Plyaer_Name[1]))
	            		{
	            			Game_start[1]=1;
	            			if(Game_start[0]==1&&Game_start[1]==1){
	            				int data = random.nextInt(20);
	            				Current_Goal=Game_Q[data];
	                    		turn++;
	            				sendMessage("@���ӽ���!!=>"+Game_Q[i++]);
	            			}
	            		}
	            	}
	            	else if(msg2.equals("@�÷��̾� �غ�Ϸ���ü!")){
	            		if(msg1.equals(Plyaer_Name[0])){
	            			Game_start[0]=0;
	            		}
	            		else if(msg1.equals(Plyaer_Name[1]))
	            		{
	            			Game_start[1]=0;
	            		}
	            	}
	            	else if(msg2.equals("@@���������!")){
	            		int data = random.nextInt(20);
	            		if(turn%2==0){
	                		Current_Goal=Game_Q[data];
	                		turn++;
	                		sendMessage("���Ǽ�����@@=>"+Plyaer_Name[0]+"=>"+Plyaer_Name[0]+" �������Դϴ�."+"=>"+Current_Goal+"=>"+Player_Score[0]+"=>"+Player_Score[1]);
	                	}
	                	else if(turn%2==1){
	                		Current_Goal=Game_Q[data];
	                		turn++;
	                		sendMessage("���Ǽ�����@@=>"+Plyaer_Name[1]+"=>"+Plyaer_Name[1]+" �������Դϴ�."+"=>"+Current_Goal+"=>"+Player_Score[0]+"=>"+Player_Score[1]);
	                	}
	            	}
	            	else if(msg1.equals("����!!!!123")){
	            		  StringTokenizer tokens2 = new StringTokenizer(msg);
	                      String msg3=tokens.nextToken("=>");
	            		if(msg3.equals(Plyaer_Name[0])){
	            			int score= Integer.parseInt(Player_Score[0]);
	            			score++;
	            			if(score==3){
	            				sendMessage(Plyaer_Name[0]+"=>�� �¸��Դϴ�.");
	            			}
	            			String score2=Integer.toString(score);
	            			Player_Score[0]=score2;
	            			sendMessage(msg);
	            		}
	            		else if(msg3.equals(Plyaer_Name[1])){
	            			int score= Integer.parseInt(Player_Score[1]);
	            			score++;
	            			if(score==3){
	            				sendMessage(Plyaer_Name[1]+"=>�� �¸��Դϴ�.");
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
                //������������ ���⼭ �����߻�. 
                removeClient(nick);
            }
        }
        
        
        
    }
 
}