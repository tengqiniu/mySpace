import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.qiniu.util.StringUtils;
import com.qiniu.util.UrlSafeBase64;


public class TokenManage {
	public static void tokenmanage() throws NoSuchAlgorithmException, InvalidKeyException{

        String ACCESS_KEY = "WctqIb0_3cXhu-J1Iys0ubiHA0qhgVn_A6J_USvt";
        String SECRET_KEY = "yHECYUbn_Taub0LPTNhwFR8DMsACTpXpyYhCiPD0";
        String signingStr  = "/stat/dGVzdDpjb25jYXQubXA0\n";
        Mac mac = javax.crypto.Mac.getInstance("HmacSHA1");
        mac.init(new SecretKeySpec(StringUtils.utf8Bytes(SECRET_KEY),"HmacSHA1"));
        String t2 = UrlSafeBase64.encodeToString(mac.doFinal(StringUtils.utf8Bytes(signingStr)));

        System.out.println(ACCESS_KEY+":"+t2);  
    }
    public static void main(String args[]) throws InvalidKeyException, NoSuchAlgorithmException{
        tokenmanage();
    }
}