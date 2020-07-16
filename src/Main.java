import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class Main {
	 public static void main(String[] args) {
		 // リアルタイムネット対戦などを実装する際には低水準アクセスAPIが必要になる
		 // 低水準APIは細かいことができるがとても種類が多く複雑で手間がかかる
		 // コンピュータ同士が通信を行うには、正しく定められた通信手順で送受信する必要がある
		 // このような通信手順のことをプロトコルと呼ぶ
		 // そしてインターネットで使われているプロトコルがTCP/IPと呼ばれるものである
		 // TCP/IPが定める掟として
		 // 通信を開始するときは、接続要求、接続許可と返信、通信開始することを伝えるといった3回のやりとりが行われる
		 // といったようなものがある、接続要求を行う際には必ず、IPアドレスとポート番号が必要である
		 // 　IPアドレスとは
		 // ネットワークに繋がっている各コンピュータに割り振られている番号
		 // 基本的にはコンピュータごとに違う番号が割り振られる
		 // 通信を開始するときにどのコンピュータに接続したいかを指定するためにこのIPアドレスを使用する
		 // 　ポート番号とは
		 // 通信相手のサーバーでは、いくつもの種類のプログラムが接続を待ち構えている
		 // 例えばメールの到着を待つプログラム、Webページの閲覧要求を待つプログラムなどがあり、それぞれのサービスを提供している
		 // それらのサービスを区別するために、サーバー上のプログラムにはポート番号という番号が振られている
		 // 例えば、webページの要求を待ち受けるプログラムは通常80番ポートで待ち構えている
		 // よって、Webページを取得したければ80番ポートを指定して接続を行わなければならない
		 // TCP/IPを使って接続を行うにはSocketクラスを使う
		 // Socketの使い方
		 // IPアドレスまたはサーバー名とポート番号を指定してSocketインスタンスを生み出す
		 try(Socket sock = new Socket("dokojava.jp", 80)){
			 // Socketから入力ストリームと出力ストリームを取得する
			 // 一つの接続で双方向のストリームを同時に使う
			 InputStream is = sock.getInputStream();
			 OutputStream os = sock.getOutputStream();
			 // HTTP要求を送信
			 os.write("GET /index.html HTTP/1.0¥r¥n".getBytes());
			 os.write("¥r¥n".getBytes());
			 os.flush();
			 try(InputStreamReader isr = new InputStreamReader(is)){
				 int i = isr.read();
				 while(i != -1) {
					 System.out.print((char) i);
					 i = isr.read();
				 }
			 }
		 } catch(Exception e) {
			 System.out.println("エラーが発生しました");
			 e.printStackTrace();
		 }

		 // TCP/IPでは接続を確立、切断することはできるが、その後にどういう情報を送れば良いかということは規定されていない
		 // それらは用途別にTCP/IPよりも上位のプロトコルとして定義されている

	 }
}
