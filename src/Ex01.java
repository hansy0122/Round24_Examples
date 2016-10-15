import java.io.*;
import java.net.*;

public class Ex01 {
	public static void main(String ar[])throws Exception{
		InetAddress ia=InetAddress.getByName("www.naver.com");
		System.out.println(ia.getHostAddress());
				
	}
}
