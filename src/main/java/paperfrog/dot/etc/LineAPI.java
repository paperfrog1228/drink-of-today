package paperfrog.dot.etc;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
@Component
public class LineAPI {
    private HttpsURLConnection httpsConn;
    private URL url;
    private InputStream in;
    private BufferedReader reader = null;
    byte[] input = {};
    int responseCode;


    public void sendRequest(String title) throws IOException {
        StringBuilder line = new StringBuilder();
        try {
            url = new URL("https://api.line.me/v2/bot/message/push");
            httpsConn = (HttpsURLConnection) url.openConnection();
            httpsConn.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            JSONObject jsonObject=new JSONObject();
            JSONArray jsonArray=new JSONArray();
            JSONObject jsonObject2=new JSONObject();

            jsonObject.put("type","text");
            jsonObject.put("text","새로운 글 등록 : "+title);
//            jsonObject.put("originalContentUrl","https://shopv.pstatic.net/web/modules/gnb/p/static/20210605_1730/img/sprite/png/spSv_global_210723.png");
//            jsonObject.put("previewImageUrl","https://shopv.pstatic.net/web/modules/gnb/p/static/20210605_1730/img/sprite/png/spSv_global_210723.png");
            jsonArray.put(jsonObject);
            jsonObject2.put("messages",jsonArray);
            jsonObject2.put("to","U1d337ffa051c7bff4d2248e67955c795");
            input=jsonObject2.toString().getBytes();
            System.out.println(jsonObject2.toString());
            httpsConn.setRequestProperty("Authorization","Bearer 0KlqZ3N0ZVmjrov3LWdakmpankjW3N/I8M60FJpM/5kUKcHA/PU/hKqdCyjX/T9HpikvT19kTkjHvV6mIoXAJBxLR47pogi7oNV1bbM2C0aFCe6FkQU4ejlPsHWHlqUqMyEHCfjgEoQ65SxKMSZ3IQdB04t89/1O/w1cDnyilFU=");
            httpsConn.addRequestProperty("Content-Type","application/json");
            httpsConn.setRequestMethod("POST");
            httpsConn.setDoInput(true);
            httpsConn.setDoOutput(true);
            httpsConn.setUseCaches(false);
            httpsConn.setReadTimeout(10000);
            httpsConn.setConnectTimeout(10000);
            httpsConn.getOutputStream().write(input);
            httpsConn.connect();
            httpsConn.setInstanceFollowRedirects(true);
            responseCode = httpsConn.getResponseCode();
            System.out.println(responseCode);
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, null, null);
            httpsConn.setSSLSocketFactory(context.getSocketFactory());
            if (responseCode == HttpsURLConnection.HTTP_OK)
            {
                in = httpsConn.getInputStream();
                reader = new BufferedReader(new InputStreamReader(in));
                String inputLine;

                while ((inputLine = reader.readLine()) != null) {
                    line.append(inputLine);
                    System.out.println(inputLine);
                }
                reader.close();
            }
            else {
                in = httpsConn.getErrorStream();
                String str=new String(in.readAllBytes());
                System.out.println(str);
            }

        } catch (UnknownHostException e) {
            System.out.println("UnknownHostException : " + e);
        } catch (MalformedURLException e) {
            System.out.println("url 오류");
        } catch (IOException e) {
            System.out.println("IOException :" + e);
        } catch (Exception e) {
            System.out.println("다른 error : " + e);
        } finally{
            if (reader != null) {
                reader.close();
            }
            if (httpsConn != null) {
                httpsConn.disconnect();
            }
        }
        httpsConn.disconnect();
    }

}
