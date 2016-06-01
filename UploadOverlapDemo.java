import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;


public class UploadOverlapDemo {
	
	//密钥
	String ACCESS_KEY = "WctqIb0_3cXhu-J1Iys0ubiHA0qhgVn_A6J_USvt";
	String SECRET_KEY = "yHECYUbn_Taub0LPTNhwFR8DMsACTpXpyYhCiPD0";
	Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

	//上传空间名
	String bucketName = "test";
	//上传资源名
	String keyName = "my.jpg";
	
	//要上传的文件路径
	String filePath = "/Users/admin/Desktop/aa.jpg";
	
	//设置token
	public String getToken(){
		return auth.uploadToken(bucketName, keyName, 3600, new StringMap().put("insertOnly",0));
	}
	
	
	//创建长传UploadManager对象
	UploadManager uploadManager = new UploadManager();
	public void upload(){
		try{
			//这里的key要和token中的key相同
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
		new UploadOverlapDemo().upload();
	}
}
