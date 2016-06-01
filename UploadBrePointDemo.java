import java.io.File;
import java.io.IOException;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Recorder;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.persistent.FileRecorder;
import com.qiniu.util.Auth;


public class UploadBrePointDemo {
	//密钥
	String ACCESS_KEY = "WctqIb0_3cXhu-J1Iys0ubiHA0qhgVn_A6J_USvt";
	String SECRET_KEY = "yHECYUbn_Taub0LPTNhwFR8DMsACTpXpyYhCiPD0";
	Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
	
	//上传空间名
	String bucketName = "test";
	//上传资源名
	String keyName = "mine.rmvb";
	
	//要上传的文件路径
	String filePath = "/Users/admin/Desktop/wudipohuaiwang.rmvb";
	
	//获得token
	public String getToken(){
		return auth.uploadToken(bucketName);
	}
	
	public void upload() throws IOException{
		//保存断点的文件及其路径
		System.out.println("breakPoint");
		String breakPoint = "/Users/admin/Desktop";
		//实例化Recorder对象
		Recorder recorder = new FileRecorder(breakPoint);
		//构建上传UploadManager对象
		UploadManager uploadManager = new UploadManager(recorder);
		System.out.println("filepath");
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
	public static void main(String[] args) throws IOException {
		new UploadBrePointDemo().upload();
	}
}
