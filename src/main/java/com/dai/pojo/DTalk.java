package com.dai.pojo;

import java.io.IOException;
import java.util.Map;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.http.HttpEntity;

import com.alibaba.fastjson.JSON;





public class DTalk {
	
	//private String Url = "http://api.qingyunke.com/api.php?key=free&appid=0&msg=";
	private String Url = "http://www.tuling123.com/openapi/api?key=69a2ab15e5ad0c252ba42c939969e5ef&loc=����ʡ�人�к�ɽ��&userid=12345678&info=";
	
	public String executeGet(String url){ 
		String content = null;
		  //����Ĭ�ϵ�httpClientʵ��
        CloseableHttpClient httpClient = getHttpClient();
        try {
            //��get��������http����
            HttpGet get = new HttpGet(url);
            //System.out.println("ִ��get����:...."+get.getURI());
            CloseableHttpResponse httpResponse = null;
            //����get����
            httpResponse = httpClient.execute(get);
            try{
                //responseʵ��
                HttpEntity entity = httpResponse.getEntity();
                if (null != entity){
                    //System.out.println("��Ӧ״̬��:"+ httpResponse.getStatusLine());
                    //System.out.println("-------------------------------------------------");
                    //System.out.println("��Ӧ����:" + EntityUtils.toString(entity));
                    //System.out.println("-------------------------------------------------");                    
                	content =  EntityUtils.toString(entity);
                }
            }
            finally{
                httpResponse.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            try{
                closeHttpClient(httpClient);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
		return content;
    }  
	
	private CloseableHttpClient getHttpClient(){
        return HttpClients.createDefault();
    }
     
    private void closeHttpClient(CloseableHttpClient client) throws IOException{
        if (client != null){
            client.close();
        }
    }
	
	public String getAnswer(String say){
		say = Url+say;
		String data = executeGet(say);
		String answer = null;
		Map<String, Object> res = (Map<String, Object>) JSON.parse(data);
		switch((Integer)res.get("code")){
			case 100000 : answer = (String)res.get("text");break;
			case 200000 : answer = res.get("text")+"<a target='_blank' href="+res.get("url")+">"+"�������"+"</a>";break;
			default:answer = "������";
		}
		return answer;
	}

}
