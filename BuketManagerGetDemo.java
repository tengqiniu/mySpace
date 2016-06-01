import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;


public class BuketManagerGetDemo {
	
	public static void main(String[] args) {
		//密钥
		String ACCESS_KEY = "WctqIb0_3cXhu-J1Iys0ubiHA0qhgVn_A6J_USvt";
		String SECRET_KEY = "yHECYUbn_Taub0LPTNhwFR8DMsACTpXpyYhCiPD0";
		Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
		
		//创建空间资源管理的BuketManager对象
		BucketManager bucketManager = new BucketManager(auth);
		
		//空间名、资源名
		String bucketName = "test";
		String keyName = "mov.mov";
		
		try{
			//调用BucketManager类的stat()方法获取文件信息
			FileInfo fileInfo = bucketManager.stat(bucketName, keyName);
			
			System.out.println(fileInfo.fsize);
			System.out.println(fileInfo.hash);
			System.out.println(fileInfo.key);
			System.out.println(fileInfo.mimeType);
			System.out.println(fileInfo.putTime);
		}catch(QiniuException e){
			Response r = e.response;
			System.out.println(r.toString());
		}			
	}		
}

//问题一：打印出的key值为什么会是null？不应该是my.jpg吗？
//问题二：putTime时间是Unix时间吗？为什么转换出现转换格式错误问题？

