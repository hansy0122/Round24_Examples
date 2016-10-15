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
class ChatServer extends Thread {  //run 메소드를 구현하고 start 로 시작함 
	private ServerSocket ss;
	private Vector vc = new Vector();
	public ChatServer() {
		try {
			ss = new ServerSocket(3333);
			this.start();  //run 메소드를 새로운 thread 에 실행중인 것을 의미
			System.out.println("서버 레뒤...");
		}catch(IOException ee) {
			System.err.println("이미 사용중입니다.");
			System.exit(0);
		}
	}
	
	public void run() { //거의 실질적으로 돌아가는 method ( 서버 유지를 위해 돌아가는 Thread임. ) 
		while(true) {
			try {
				Socket imsi = ss.accept(); //큰 감시자. -- > 3333포트로 접근해 오는 녀석들을 Socket으로 생성
				System.out.println("접속자 : " + imsi.toString()); // 접속한 녀석나타냄
				UserInfo ui = new UserInfo(imsi);
				for(int i = 0; i < vc.size(); i++) {             //ChatServer에 vc를 선언해놓았음.
					UserInfo uiui = (UserInfo)
										vc.elementAt(i);
					uiui.getOut().println(
							"/p" + ui.getNickName());          // 이때까지 접속한 녀석들에게 새로들어온녀석의 nickname을 넘겨줌
					uiui.getOut().flush();
				}
				vc.add(ui); //User 정보 저장.
				for(int i = 0; i < vc.size(); i++) {
					UserInfo uiui = (UserInfo)
										vc.elementAt(i);
					ui.getOut().println(
							"/o" + uiui.getNickName());  // 새로운녀석에게 이제까지 접속한 녀석들의 명단을 넘겨줌.
					ui.getOut().flush();
				}
			}catch(IOException ee) {}
		}
	}
	
	
	class UserInfo extends Thread {        // 각각의 클라이언트를 위해서 존재하는 Thread임
		private Socket soc;
		private String nickname;
		private PrintWriter out;
		private BufferedReader in;
		
		public UserInfo(Socket s) {// 생성자
			this.soc = s;              // server에 접속한 녀석의 Socket을 넘겨줌 
			try {
				out = new PrintWriter(                          //입출력 스트림작성
					new BufferedWriter(
					new OutputStreamWriter(
					soc.getOutputStream())));
				in = new BufferedReader(
					new InputStreamReader(
					soc.getInputStream()));
				nickname = in.readLine();                 // 닉을 보냄 chat에서 그리고 그걸 받은거
				this.start();//작은 감시자 시작..          //밑에 run 시작 
			}catch(IOException ee) {}
		}
		public String getNickName() {
			return nickname;
		}
		public PrintWriter getOut() {
			return out;
		}
		public void run() {//작은 감시자.           UserInfo  //너가 실재로 start 하면 돌아갈 부분
			while(true) { // 이 method 전체에 걸쳐져서 나타나있음.
				try {
					String str = in.readLine();       // 보낸 전체내용을 읽어옴. // 항상 프로그래밍은 큰들로 살펴볼 것.
					if(str.charAt(0) == '/') {   // /q를 치면 퇴장하게끔됨.
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
								ui.out.flush();               // 유저들에게 그사람이 나갔다고 알리는것.
							}
							break; // 이 사람 Thread 종료...           --> 사람마다 각각 돌아가야하므로 각각의 Thread를 할당하여 놓았음.
						}
						else if(str.charAt(1) == 'w') {            // 귓속말 기능 on
							String dest = str.substring(2, 
									str.indexOf("-")).trim();  // 누구한테 귓솔말하는지 빼내고.
							String msg = str.substring(             //내용물만 뺴내기
								str.indexOf("-") + 1).trim();
							for(int i = 0; i < vc.size(); i++) {
								UserInfo ui = 
									(UserInfo)vc.elementAt(i);
								if(ui.nickname.equals(dest)) { //같은놈 찾았다 !
			ui.out.println(nickname + "(귓속말:" + ui.nickname + ") >> " + msg);
			ui.out.flush();											// 귓솔말 대상자 그놈한테 보냄
			out.println(nickname + "(귓속말:" + ui.nickname + ") >> " + msg); // 클라이언트한테 보냄
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
						 							+ str;      //모든놈들한테 보내는 메시지.
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
