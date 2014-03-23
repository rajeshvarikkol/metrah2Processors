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


public class GetVisaInformationPostProcessorForComposite 
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
	  	    Dataset ds=new Dataset("readyToUse");
	  	    for (int i = 0; i < returnArray.length(); i++) {
	  	    	String xml =returnArray.getJSONObject(i).toString(); //XML.toString(returnArray.getJSONObject(i));
	  	    	 LOG.debug("-----xml-----" +xml);
	  	    	
	  	    	Record recd=new Record();
	  	    	 LOG.debug("-----readytouse-----" +returnArray.getJSONObject(i).getString("visaNo"));
	  	    	Param visaNo =new Param("visaNo",returnArray.getJSONObject(i).getString("visaNo"),"string");
	  	    	Param arabName1 =new Param("arabName1",returnArray.getJSONObject(i).getString("arabName1"),"string");
	  	    	Param arabName2 =new Param("arabName2",returnArray.getJSONObject(i).getString("arabName2"),"string");
	  	    	Param arabName3 =new Param("arabName3",returnArray.getJSONObject(i).getString("arabName3"),"string");
	  	    	Param arabName4 =new Param("arabName4",returnArray.getJSONObject(i).getString("arabName4"),"string");
	  	    	Param arabName5 =new Param("arabName5",returnArray.getJSONObject(i).getString("arabName5"),"string");
	  	    	Param engName1 =new Param("engName1",returnArray.getJSONObject(i).getString("engName1"),"string");
	  	    	Param engName2 =new Param("engName2",returnArray.getJSONObject(i).getString("engName2"),"string");
	  	    	Param engName3 =new Param("engName3",returnArray.getJSONObject(i).getString("engName3"),"string");
	  	    	Param engName4 =new Param("engName4",returnArray.getJSONObject(i).getString("engName4"),"string");
	  	    	Param engName5 =new Param("engName5",returnArray.getJSONObject(i).getString("engName5"),"string");
	  	    	Param validExpDate =new Param("validExpDate",returnArray.getJSONObject(i).getString("validExpDate"),"string");
	  	    	Param visaType =new Param("visaType",returnArray.getJSONObject(i).getString("visaType"),"string");
	  	    	Param sexArabicDesc =new Param("sexArabicDesc",returnArray.getJSONObject(i).getString("sexArabicDesc"),"string");
	  	    	Param sexEngDesc =new Param("sexEngDesc",returnArray.getJSONObject(i).getString("sexEngDesc"),"string");
	  	    	Param dateOfBirth =new Param("dateOfBirth",returnArray.getJSONObject(i).getString("dateOfBirth"),"string");
	  	    	Param natArabicDesc =new Param("natArabicDesc",returnArray.getJSONObject(i).getString("natArabicDesc"),"string");
	  	    	Param natEngDesc =new Param("natEngDesc",returnArray.getJSONObject(i).getString("natEngDesc"),"string");
	  	    	Param accompCount =new Param("accompCount",returnArray.getJSONObject(i).getString("accompCount"),"string");
	  	    	Param validDurEngDesc =new Param("validDurEngDesc",returnArray.getJSONObject(i).getString("validDurEngDesc"),"string");
	  	    	Param validDurPeriod =new Param("validDurPeriod",returnArray.getJSONObject(i).getString("validDurPeriod"),"string");
	  	    	Param validDurArabicDesc =new Param("validDurArabicDesc",returnArray.getJSONObject(i).getString("validDurArabicDesc"),"string");
	  	    	Param visaFee =new Param("visaFee",returnArray.getJSONObject(i).getString("visaFee"),"string");
	  	    	recd.setParam(validDurEngDesc);
	  	    	recd.setParam(validDurPeriod);
	  	    	recd.setParam(validDurArabicDesc);
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
	  	    	
	  	    	
	  	    	
	  	    	ds.setRecord(recd);
	  	    	
	  	    	}
	  	   
	  	 
	  	   result.setDataSet(ds);
	      }
	      catch(Exception e)
	      {
	      	LOG.debug("-----exception-----" + e.getMessage());
	      }
	      return result;
	    }
	  }