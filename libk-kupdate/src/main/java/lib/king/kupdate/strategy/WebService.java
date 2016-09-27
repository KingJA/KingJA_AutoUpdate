package lib.king.kupdate.strategy;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import lib.king.kupdate.Constants;
import lib.king.kupdate.strategy.LoadStrategy;

public class WebService implements LoadStrategy{
	@Override
	public int getVersionCode(String fileName) {
				int versionCode=-1;
		SoapObject request = new SoapObject(Constants.WEBSERVER_NAMESPACE,
				Constants.METHOD);
		request.addProperty(Constants.FILE_NAME, fileName);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.dotNet = true;
		envelope.bodyOut = request;

		HttpTransportSE ht = new HttpTransportSE(
				Constants.VERSION_URL);
		try {
			ht.call(Constants.WEBSERVER_NAMESPACE + Constants.METHOD, envelope);
			String result = envelope.getResponse().toString();
			Log.e("WebService", "result:" + result);
			JSONArray jsonArray = new JSONArray(result);
			JSONObject jsonObject = jsonArray.getJSONObject(0);
			versionCode = jsonObject.getInt(Constants.ANDROID_VERSIONCODE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return versionCode;
	}


}
