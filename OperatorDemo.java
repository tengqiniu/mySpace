import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.processing.OperationManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.qiniu.util.UrlSafeBase64;


public class OperatorDemo {

	public static void main(String[] args) {
		//密钥
		String ACCESS_KEY = "WctqIb0_3cXhu-J1Iys0ubiHA0qhgVn_A6J_USvt";
		String SECRET_KEY = "yHECYUbn_Taub0LPTNhwFR8DMsACTpXpyYhCiPD0";
			
		//创建密钥
		Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
		
		//创建一个OperationManager对象
		OperationManager operationManager = new OperationManager(auth);
		
		//设置要转码的空间和key，且key必须存在
		String buketName = "test";
		String keyName = "hello.mp4";
		
		//设置转码操作参数
		
		//视频转码
		//String fops = "avthumb/mp4/s/360x640/vb/1.25m";
		
		//视频拼接
		String encodedUrl1 = UrlSafeBase64.encodeToString("http://o6n5z80py.bkt.clouddn.com/2.ts");
		String encodedUrl2 = UrlSafeBase64.encodeToString("http://o6n5z80py.bkt.clouddn.com/3.ts");
		String encodedUrl3 = UrlSafeBase64.encodeToString("http://o6n5z80py.bkt.clouddn.com/4.ts");
		//String fops = "avconcat/2/format/mp4/" + encodedUrl1 + "/" + encodedUrl2 + "/" +  encodedUrl3;
		
		//视频水印
		String encodeUrlWater = UrlSafeBase64.encodeToString("http://78re52.com1.z0.glb.clouddn.com/resource%2Flogo.jpg");
		//String fops = "avthumb/mp4/wmGravity/Center/wmOffsetX/0/wmOffsetY/0/wmImage/" + encodeUrlWater;
		
		//视频帧缩略图
		//String fops = "vframe/jpg/offset/7/w/480/h/360/rotate/90";
		
		//视频采样缩略图
		//String fops = "vsample/jpg/ss/7/t/600/s/480x360/pattern/dmZyYW1lLSQoY291bnQp";
		
		//imageMogr2
		//String fops = "imageMogr2/auto-orient";
		
		//转m3u8
		String fops = "avthumb/m3u8/noDomain/1/pattern/qiniu";
		
		//设置转码队列
		String pipeline = "mpsdemo";
		
		//对转码后的文件，使用saveas参数进行自定义命名
		String urlbase64 = UrlSafeBase64.encodeToString("test:nihao3.mp4");
		//String pfops = fops + "|saveas/" + urlbase64;
		
		
		//设置参数
		StringMap params = new StringMap().putWhen("force", 1, true).putNotEmpty("pipeline", pipeline);
		//StringMap params = new StringMap().putWhen("force", 1, true);
		
		try{
			String persistid = operationManager.pfop(buketName, keyName, fops, params);
			System.out.println(persistid);
		}catch(QiniuException e){
			//捕获异常信息
			Response r = e.response;
			System.out.println(r.toString());
			try{
				System.out.println(r.bodyString());
			}catch(QiniuException e1){
				//ignore
			}
		}
	}

}
//putWhen()函数的作用