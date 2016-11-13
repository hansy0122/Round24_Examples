import java.io.*;
import java.net.*;

public class Ex01 {
	public static void main (String ar[])throws Exception{
		InetAddress ia=InetAddress.getByName("www.naver.com");
		InetAddress ia2=InetAddress.getByName("125.209.222.142");
		System.out.println(ia.getHostAddress());
		System.out.println(ia.getHostName());
		
		System.out.println(ia2.getHostAddress());
		System.out.println(ia2.getHostName());

	}
}
