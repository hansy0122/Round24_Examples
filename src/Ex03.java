import java.io.*;
import java.net.*;

public class Ex03 {
public static void main(String ar[]){
	 	Socket soc=null;
	 	InetAddress ia=null;
	 	
	 	try{
	 		ia=InetAddress.getByName("www.naver.com");
	 	}	catch(UnknownHostException e){
	 		
	 	}
	 	try{
	 	soc=new Socket(ia,80);
	 	String request="GET http://www.naver.com/index.html HTTP1/0\r\n\n";
	 	
	 	//출력
	 	OutputStreamWriter osw=new OutputStreamWriter(soc.getOutputStream());
	 	BufferedWriter bos=new BufferedWriter(osw,512);
	 	PrintWriter pw=new PrintWriter(bos);
	 	pw.println(request);
	 	pw.flush();
	 	
	 	//입력
	 	InputStreamReader isr=new InputStreamReader(soc.getInputStream());
	 	BufferedReader br=new BufferedReader(isr,512);
	 	while(true){
	 		String str=br.readLine();
	 		if(str==null)break;
	 		System.out.println(str);
	 	}
	 	pw.close();
	 	br.close();
	 	
	 	}catch(IOException ee){}
	 	
	 	
}
}
