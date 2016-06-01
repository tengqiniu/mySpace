import java.io.IOException;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;



public class UpLoadDemo {
	//设置账号的ACCESS_KEY和SECRET-KEY
	String ACCESS_KEY = "WctqIb0_3cXhu-J1Iys0ubiHA0qhgVn_A6J_USvt";
	String SECRET_KEY = "yHECYUbn_Taub0LPTNhwFR8DMsACTpXpyYhCiPD0";
	
	//要上传的空间
	String buketName = "test";
	
	//上传到七牛后要保存的文件名
	String key = "woshilurenjia.rmvb";
	
	//要上传文件的路径
	String fileName = "/Users/admin/Desktop/move.rmvb";
	
	//密钥配置
	Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
	
	//创建上传对象
	UploadManager uploadManager = new UploadManager();
	
	//简单上传，使用默认策略，只需要设置上传的空间名就可以了
	public String getUpToken(){
		return auth.uploadToken(buketName);
	}
	
	public void upload() throws IOException{
		try{
			//调用put方法上传
			Response res = uploadManager.put(fileName, key, getUpToken());
			
			//打印返回的信息
			System.out.println(res.bodyString());
		}catch(QiniuException e){
			Response r = e.response;
			
			//请求失败时打印异常信息
			System.out.println(r.toString());
			try{
				//相应得文本信息
				System.out.println(r.bodyString());
			}catch(QiniuException e1){
				//ignore
			}
		}
	}
	public static void main(String[] args) throws IOException {
		new UpLoadDemo().upload();
	}
}
