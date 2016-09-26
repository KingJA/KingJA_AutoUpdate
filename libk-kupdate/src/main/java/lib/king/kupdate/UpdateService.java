package lib.king.kupdate;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class UpdateService {


	/**
	 * 获取版本号
	 *
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public static int getVersionCode(String fileName) {
		int versionCode=-1;
		SoapObject request = new SoapObject(Constants.WEBSERVER_NAMESPACE,
				Constants.METHOD);
		Log.e("亲情关爱", "更新参数：" + fileName);
		request.addProperty("FileName", fileName);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.dotNet = true;
		envelope.bodyOut = request;

		MyHttpTransportSE ht = new MyHttpTransportSE(
				Constants.VERSION_URL);
		try {
			ht.call(Constants.WEBSERVER_NAMESPACE + Constants.METHOD, envelope);
			String result = envelope.getResponse().toString();
			Log.e("UpdateService", "result:" + result);
			JSONArray jsonArray = new JSONArray(result);
			JSONObject jsonObject = jsonArray.getJSONObject(0);
			versionCode = jsonObject.getInt("android:versionCode");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return versionCode;
	}


}
