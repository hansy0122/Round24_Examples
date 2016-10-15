import java.io.*;
import java.net.*;
import java.util.*;
/*
 * Created on 2005. 11. 16.
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

/**
 * @author kimsh
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
class ChatServer extends Thread {  //run �޼ҵ带 �����ϰ� start �� ������ 
	private ServerSocket ss;
	private Vector vc = new Vector();
	public ChatServer() {
		try {
			ss = new ServerSocket(3333);
			this.start();  //run �޼ҵ带 ���ο� thread �� �������� ���� �ǹ�
			System.out.println("���� ����...");
		}catch(IOException ee) {
			System.err.println("�̹� ������Դϴ�.");
			System.exit(0);
		}
	}
	
	public void run() { //���� ���������� ���ư��� method ( ���� ������ ���� ���ư��� Thread��. ) 
		while(true) {
			try {
				Socket imsi = ss.accept(); //ū ������. -- > 3333��Ʈ�� ������ ���� �༮���� Socket���� ����
				System.out.println("������ : " + imsi.toString()); // ������ �༮��Ÿ��
				UserInfo ui = new UserInfo(imsi);
				for(int i = 0; i < vc.size(); i++) {             //ChatServer�� vc�� �����س�����.
					UserInfo uiui = (UserInfo)
										vc.elementAt(i);
					uiui.getOut().println(
							"/p" + ui.getNickName());          // �̶����� ������ �༮�鿡�� ���ε��³༮�� nickname�� �Ѱ���
					uiui.getOut().flush();
				}
				vc.add(ui); //User ���� ����.
				for(int i = 0; i < vc.size(); i++) {
					UserInfo uiui = (UserInfo)
										vc.elementAt(i);
					ui.getOut().println(
							"/o" + uiui.getNickName());  // ���ο�༮���� �������� ������ �༮���� ����� �Ѱ���.
					ui.getOut().flush();
				}
			}catch(IOException ee) {}
		}
	}
	
	
	class UserInfo extends Thread {        // ������ Ŭ���̾�Ʈ�� ���ؼ� �����ϴ� Thread��
		private Socket soc;
		private String nickname;
		private PrintWriter out;
		private BufferedReader in;
		
		public UserInfo(Socket s) {// ������
			this.soc = s;              // server�� ������ �༮�� Socket�� �Ѱ��� 
			try {
				out = new PrintWriter(                          //����� ��Ʈ���ۼ�
					new BufferedWriter(
					new OutputStreamWriter(
					soc.getOutputStream())));
				in = new BufferedReader(
					new InputStreamReader(
					soc.getInputStream()));
				nickname = in.readLine();                 // ���� ���� chat���� �׸��� �װ� ������
				this.start();//���� ������ ����..          //�ؿ� run ���� 
			}catch(IOException ee) {}
		}
		public String getNickName() {
			return nickname;
		}
		public PrintWriter getOut() {
			return out;
		}
		public void run() {//���� ������.           UserInfo  //�ʰ� ����� start �ϸ� ���ư� �κ�
			while(true) { // �� method ��ü�� �������� ��Ÿ������.
				try {
					String str = in.readLine();       // ���� ��ü������ �о��. // �׻� ���α׷����� ū��� ���캼 ��.
					if(str.charAt(0) == '/') {   // /q�� ġ�� �����ϰԲ���.
						if(str.charAt(1) == 'q') {
							for(int i = 0; i < vc.size(); i++) {
								UserInfo ui = (UserInfo)vc.elementAt(i);
								if(ui.nickname.equals(nickname)) {
									vc.removeElementAt(i);
									break;
								}
							}
							for(int i = 0; i < vc.size(); i++) {
								UserInfo ui = (UserInfo)vc.elementAt(i);
								ui.out.println("/q" + nickname);
								ui.out.flush();               // �����鿡�� �׻���� �����ٰ� �˸��°�.
							}
							break; // �� ��� Thread ����...           --> ������� ���� ���ư����ϹǷ� ������ Thread�� �Ҵ��Ͽ� ������.
						}
						else if(str.charAt(1) == 'w') {            // �ӼӸ� ��� on
							String dest = str.substring(2, 
									str.indexOf("-")).trim();  // �������� �Ӽָ��ϴ��� ������.
							String msg = str.substring(             //���빰�� ������
								str.indexOf("-") + 1).trim();
							for(int i = 0; i < vc.size(); i++) {
								UserInfo ui = 
									(UserInfo)vc.elementAt(i);
								if(ui.nickname.equals(dest)) { //������ ã�Ҵ� !
			ui.out.println(nickname + "(�ӼӸ�:" + ui.nickname + ") >> " + msg);
			ui.out.flush();											// �Ӽָ� ����� �׳����� ����
			out.println(nickname + "(�ӼӸ�:" + ui.nickname + ") >> " + msg); // Ŭ���̾�Ʈ���� ����
			out.flush();
									break;
								}
							}
						}
					}
					else {
						for(int i = 0; i < vc.size(); i++) {
							UserInfo ui = 
								(UserInfo)vc.elementAt(i);
							String data = nickname + " > "
						 							+ str;      //��������� ������ �޽���.
							ui.out.println(data);
							ui.out.flush();
						}						
					}
				}catch(IOException ee){}
			}
		}
	}
}
public class ChattingServer {
	public static void main(String[] args) {
		ChatServer cs = new ChatServer();		
	}
}
