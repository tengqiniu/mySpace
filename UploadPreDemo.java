import java.io.IOException;
import java.io.ObjectInputStream.GetField;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.qiniu.util.UrlSafeBase64;


public class UploadPreDemo {
	//密钥
	String ACCESS_KEY = "WctqIb0_3cXhu-J1Iys0ubiHA0qhgVn_A6J_USvt";
	String SECRET_KEY = "yHECYUbn_Taub0LPTNhwFR8DMsACTpXpyYhCiPD0";
	
	//存储空间名
	String buketName = "mumian";
	
	//资源名
	String keyName = "samplemp4.mp4";
	String keyName1 = "test.mov";
	String keyName2 = "huaer2.m3u8";
	
	//要上传的文件路径名
	//String filePath = "/Users/admin/Desktop/sample1.flv";
	String filePath = "/Users/admin/Desktop/vedio/test.zip";
	
	//设置转码操作参数
	//预转持续化上传
	String fops1 = "avthumb/mp4/s/360x240/vb/1.25m/vcodec/libx264";
	//切片上传
	String fops2 = "avthumb/m3u8/noDomain/1/vb/500k/segtime/5";
	
	
	//设置转码队列
	String pipleLine = "mpsdemo";
	
	//转码后的文件使用saves参数自定义命名，保存在当前空间中
	String urlbase64 = UrlSafeBase64.encodeToString("mumian:flower2.mp4");
	String pfops = fops1 + "|saveas/" + urlbase64;
	//String pfops = fops2;
	
	//创建密钥
	Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
	
	//创建上传对象
	UploadManager uploadManager = new UploadManager();
	
	
	public String getToken(){
		return auth.uploadToken(buketName,null,3600,new StringMap().putNotEmpty("persistentOps", fops1)
				.putNotEmpty("persistentPipeline",pipleLine )
				.putNotEmpty("saveKey", "$(etag)/$(fname)")
//				.putNotEmpty("returnUrl", "http://fake.com/qiniu/notify")
//				.putNotEmpty("returnBody", "key=$(key)&hash=$(etag)&w=$(imageInfo.width)&h=$(imageInfo.height)")
				,true);
	}
	
	public void upload() throws IOException{
		try{
			//调用put方法上传文件
			Response r = uploadManager.put(filePath, null, getToken());
			//打印返回信息
			System.out.println(r.bodyString());
		}catch(QiniuException e){
			Response res = e.response;
			try{
				System.out.println(res.bodyString());
			}catch(QiniuException e1){
				//ignore
			}
		}
		System.out.println(getToken());
	}
	
	public static void main(String[] args) throws IOException {
		new UploadPreDemo().upload();
		
	}
}
