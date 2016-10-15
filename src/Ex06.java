import java.io.*;
import java.net.*;

public class Ex06 {
public static void main(String ar[]){
	ServerSocket ss=null;
	try{
		ss=new ServerSocket(12345); //자신을 12345번 포트에 대해서 서버로 업그레이드 시킬수 있도록 업그레이드!!
		System.out.println("server ready");
		while(true){
		Socket s=ss.accept(); // 누가 나한테 접근했는지 확인
		System.out.println("s="+s);
		
		//입력
		InputStreamReader isr=new InputStreamReader(s.getInputStream());
		BufferedReader br=new BufferedReader(isr,512);
		String data=br.readLine();
		System.out.println("msg="+data);
		
		//출력
		OutputStreamWriter osw=new OutputStreamWriter(s.getOutputStream());
		BufferedWriter bw=new BufferedWriter(osw);
		PrintWriter pw=new PrintWriter(bw);
		pw.println("msg recept fail");
		pw.flush();
		}
	}catch(IOException e){
		
	}
}
}
