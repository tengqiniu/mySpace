import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;


public class UploadCallBackDemo {
	
	//密钥
	String ACCESS_KEY = "WctqIb0_3cXhu-J1Iys0ubiHA0qhgVn_A6J_USvt";
	String SECRET_KEY = "yHECYUbn_Taub0LPTNhwFR8DMsACTpXpyYhCiPD0";
	Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
			
	//上传空间名
	String bucketName = "test";
	//上传资源名
	String keyName = "my4.jpg";
			
	//要上传的文件路径
	String filePath = "/Users/admin/Desktop/56EA13FB7FBA1AA1F527F3336421A7D2.jpg";
			
	//创建上传UploadManager对象
	UploadManager uploadManager = new UploadManager();
			
	//回调的URL
	//String url = "http://127.0.0.1/TestDemo/servlet/TestDemo";
	String url = "http://828e1822.ngrok.io/WebDemo/servlet/TestDemo";
	
	
	//获得token
	public String getToken(){
		return auth.uploadToken(bucketName, null, 3600, new StringMap().put("callbackUrl", url)
				.put("callbackBody", "filename=$(fname)&filesize=$(fsize)"));
	}
	
	//下载
	public void upload(){
		try{
			Response r = uploadManager.put(filePath, keyName, getToken());
			System.out.println(r.bodyString());
			
		}catch(QiniuException e){
			Response res = e.response;
			System.out.println(res.toString());
			try{
				System.out.println(res.bodyString());
			}catch(QiniuException e1){
				//ignore
			}
		}
	}
	
	public static void main(String[] args) {
		new UploadCallBackDemo().upload();
	}
}
