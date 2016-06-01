import com.qiniu.util.Auth;


public class DownloadDemo {
	//设置好账号的ACCESS_KEY和SECRET_KEY
	String ACCESS_KEY = "WctqIb0_3cXhu-J1Iys0ubiHA0qhgVn_A6J_USvt";
	String SECRET_KEY = "yHECYUbn_Taub0LPTNhwFR8DMsACTpXpyYhCiPD0";
	//密钥配置
	Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
	//构造私有空间的需要生成的下载链接
	String URL = "http://o6oxtsj6t.bkt.clouddn.com/flower.m3u8?pm3u8/0";
	String URL1 = "http://o6oxtsj6t.bkt.clouddn.com/his.mp4?avinfo";
	String URL2 = "http://o6ucv15ta.bkt.clouddn.com/aa.jpg";
	
	public void downLoad(){
		//调用privateDownloadURL方法生成下载链接，第二个参数可以设置密钥的过期时间
		String downloadURL = auth.privateDownloadUrl(URL2,3600);
		System.out.println(downloadURL);
	}
	
	public static void main(String[] args){
		new DownloadDemo().downLoad();
	}
}
