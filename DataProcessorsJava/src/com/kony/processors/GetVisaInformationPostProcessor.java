package com.kony.processors;


import com.konylabs.middleware.common.*;
import com.konylabs.middleware.controller.*;
import com.konylabs.middleware.dataobject.Dataset;
import com.konylabs.middleware.dataobject.Param;
import com.konylabs.middleware.dataobject.Record;
import com.konylabs.middleware.dataobject.Result;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.json.*;
import org.xml.*;
public class GetVisaInformationPostProcessor 
  implements DataPostProcessor2
{
  private static final Logger LOG = Logger.getLogger(Postprocessor.class);
  private static final boolean DEBUG = LOG.isDebugEnabled();

  public Object execute(Result result,DataControllerRequest request, DataControllerResponse response)
    throws Exception
  {
    LOG.debug("-----Begin Postprocessing-----");

//    
    //LOG.debug("-----Before Upper-----" + response.getResponse());
    try{
	    JSONObject xmlJSONobj=XML.toJSONObject(response.getResponse());
	    String jsonPrettyPrintString = xmlJSONobj.toString(0);
	    String finalJsonString=jsonPrettyPrintString.replace("{}", "\"\"");
	    JSONObject first=xmlJSONobj.getJSONObject("soapenv:Envelope");
	   // LOG.debug("-----first-----" +first.toString());
	    JSONObject second=first.getJSONObject("soapenv:Body");
	    //LOG.debug("-----second-----" +second.toString());
	    JSONObject third=second.getJSONObject("ns2:getVisasInformationResponse");
	  //  LOG.debug("-----third-----" +third.toString());
	    JSONArray returnArray;
	    JSONObject returnArrayObject;
	    if(third.optJSONArray("return")!= null)
	    {	
	    	 returnArray= third.getJSONArray("return");
	    }
	    else
	    {
	    	returnArrayObject=third.getJSONObject("return");
	    	JSONArray temp=new JSONArray();
	    	temp.put(0, returnArrayObject);
	    	returnArray=temp;
	    }
	   // LOG.debug("-----returnArray-----" +returnArray.toString());
	    Dataset dt=new Dataset("fullResponseCollection");
	    Dataset ds=new Dataset("readyToUse");
	    for (int i = 0; i < returnArray.length(); i++) {
	    	String xml =returnArray.getJSONObject(i).toString(); //XML.toString(returnArray.getJSONObject(i));
	    	 LOG.debug("-----xml-----" +xml);
	    	Param par=new Param("return",xml,"string");
	    	Record rec=new Record();
	    	rec.setParam(par);
	    	dt.setRecord(rec);
	    	Record recd=new Record();
	    	 LOG.debug("-----readytouse-----" +returnArray.optJSONObject(i).optString("visaNo"));
	    	Param visaNo =new Param("visaNo",returnArray.optJSONObject(i).optString("visaNo"),"string");
	    	Param arabName1 =new Param("arabName1",returnArray.optJSONObject(i).optString("arabName1"),"string");
	    	Param arabName2 =new Param("arabName2",returnArray.optJSONObject(i).optString("arabName2"),"string");
	    	Param arabName3 =new Param("arabName3",returnArray.optJSONObject(i).optString("arabName3"),"string");
	    	Param arabName4 =new Param("arabName4",returnArray.optJSONObject(i).optString("arabName4"),"string");
	    	Param arabName5 =new Param("arabName5",returnArray.optJSONObject(i).optString("arabName5"),"string");
	    	Param engName1 =new Param("engName1",returnArray.optJSONObject(i).optString("engName1"),"string");
	    	Param engName2 =new Param("engName2",returnArray.optJSONObject(i).optString("engName2"),"string");
	    	Param engName3 =new Param("engName3",returnArray.optJSONObject(i).optString("engName3"),"string");
	    	Param engName4 =new Param("engName4",returnArray.optJSONObject(i).optString("engName4"),"string");
	    	Param engName5 =new Param("engName5",returnArray.optJSONObject(i).optString("engName5"),"string");
	    	Param validExpDate =new Param("validExpDate",returnArray.optJSONObject(i).optString("validExpDate"),"string");
	    	Param visaType =new Param("visaType",returnArray.optJSONObject(i).optString("visaType"),"string");
	    	Param sexArabicDesc =new Param("sexArabicDesc",returnArray.optJSONObject(i).optString("sexArabicDesc"),"string");
	    	Param sexEngDesc =new Param("sexEngDesc",returnArray.optJSONObject(i).optString("sexEngDesc"),"string");
	    	Param dateOfBirth =new Param("dateOfBirth",returnArray.optJSONObject(i).optString("dateOfBirth"),"string");
	    	Param natArabicDesc =new Param("natArabicDesc",returnArray.optJSONObject(i).optString("natArabicDesc"),"string");
	    	Param natEngDesc =new Param("natEngDesc",returnArray.optJSONObject(i).optString("natEngDesc"),"string");
	    	Param accompCount =new Param("accompCount",returnArray.optJSONObject(i).optString("accompCount"),"string");
	    	Param visaFee =new Param("visaFee",returnArray.optJSONObject(i).optString("visaFee"),"string");
	    	Param visaPurpose =new Param("visaPurpose",returnArray.optJSONObject(i).optString("visaPurpose"),"string");
	    	Param relationshipSponsor =new Param("relationshipSponsor",returnArray.optJSONObject(i).optString("relationshipSponsor"),"string");
	    	recd.setParam(visaNo);
	    	recd.setParam(arabName1);
	    	recd.setParam(arabName2);
	    	recd.setParam(arabName3);
	    	recd.setParam(arabName4);
	    	recd.setParam(arabName5);
	    	recd.setParam(engName1);
	    	recd.setParam(engName2);
	    	recd.setParam(engName3);
	    	recd.setParam(engName4);
	    	recd.setParam(engName5);
	    	recd.setParam(validExpDate);
	    	recd.setParam(visaType);
	    	recd.setParam(sexArabicDesc);
	    	recd.setParam(natEngDesc);
	    	recd.setParam(natArabicDesc);
	    	recd.setParam(accompCount);
	    	recd.setParam(dateOfBirth);
	    	recd.setParam(visaFee);
	    	recd.setParam(sexEngDesc);
	    	recd.setParam(visaPurpose);
	    	recd.setParam(relationshipSponsor);
	    	
	    	
	    	
	    	ds.setRecord(recd);
	    	
	    	}
	   
	   result.setDataSet(dt);
	   result.setDataSet(ds);
    }
    catch(Exception e)
    {
    	LOG.debug("-----exception-----" + e.getMessage());
    }
    return result;
  }
}