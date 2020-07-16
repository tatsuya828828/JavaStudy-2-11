import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
	 public static void main(String[] args) {
		 // o-space.jpのHTML情報を取得するプログラム
		 try {
			URL url = new URL("https://o-space.jp/");
			 try(InputStream is = url.openStream()) {
				 try(InputStreamReader isr = new InputStreamReader(is)){
					 int i = isr.read();
					 while(i != -1) {
						 System.out.print((char) i);
						 i = isr.read();
					 }
				 } catch(IOException e) {
					 System.out.println("エラーが発生しました");
					 e.printStackTrace();
				 }
			 } catch(IOException e) {
				 System.out.println("エラーが発生しました");
				 e.printStackTrace();
			 }
		 } catch(MalformedURLException e) {
			 System.out.println("エラーが発生しました");
			 e.printStackTrace();
		 }
	 }
}
