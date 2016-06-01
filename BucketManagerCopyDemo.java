import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.util.Auth;


public class BucketManagerCopyDemo {
	
	public static void main(String[] args) {
		//密钥
		String ACCESS_KEY = "WctqIb0_3cXhu-J1Iys0ubiHA0qhgVn_A6J_USvt";
		String SECRET_KEY = "yHECYUbn_Taub0LPTNhwFR8DMsACTpXpyYhCiPD0";
		Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
		
		//创建空间资源管理BucketManager对象
		BucketManager bucketManager = new BucketManager(auth);
		
		//源空间名
		String bucketName = "test";
		//源资源名
		String keyName = "my.jpg";
		
		//目的空间名
		String bucketName1 = "private";
		//目的资源名
		String keyName1 = "my2.jpg";
		
		try{
			bucketManager.copy(bucketName, keyName, bucketName1, keyName1);
		}catch(QiniuException e){
			Response r = e.response;
			System.out.println(r.toString());
		}
	}
}
