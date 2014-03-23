package com.kony.processors;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import com.konylabs.middleware.common.*;
import com.konylabs.middleware.controller.*;
import com.konylabs.middleware.dataobject.Dataset;
import com.konylabs.middleware.dataobject.Param;
import com.konylabs.middleware.dataobject.Record;
import com.konylabs.middleware.dataobject.Result;
import com.konylabs.middleware.common.DataPreProcessor;
import com.konylabs.middleware.controller.*;
import com.konylabs.middleware.dataobject.Result;

public class GetVisaInofrmationPreprocessor  implements DataPreProcessor
{
	  private static final Logger LOG = Logger.getLogger(Preprocessor.class);
	  private static final boolean DEBUG = LOG.isDebugEnabled();

	  public boolean execute(HashMap input, DataControllerRequest request, Result result)
	    throws Exception
	  {
		Boolean returnBool=false; 
		Boolean isThisFinalGetServiceNeeded=false;
	    Boolean isVisaCollectionCreated=false;
	    Boolean isMessageCodeOk=false;
	    StringBuilder visaNoCollectionString=new StringBuilder();
	    //change it to true
	    
	    Iterator itr = input.entrySet().iterator();
	    LOG.debug("-----begin Preprocessing-----");
	   Param SystemIdParam=result.findParam("systemId") ;
	   Param userIdParam=result.findParam("userId") ;
	   
	    while (itr.hasNext())
	    {
	      Map.Entry map = (Map.Entry)itr.next();
	      LOG.debug("---Input Param Before :" + map.getKey() +"value"+map.getValue() );
	      if(map.getValue()==null)
	      {   LOG.debug("--inside map equals null:" );
	    	  continue;}
	      
	      LOG.debug("--after map equals null:" ); 
	      
	      
	     LOG.debug("---Input Param Before :" + map.getKey() + " = " + map.getValue().toString());
	     	if(map.getKey().toString().equals("msgCode"))
	      {	
	     		
	    	  	if(!map.getValue().toString().equals("932") || map.getValue()==null)
	    	  	{
	   	  		
	    	  		isMessageCodeOk=false;
	    	  	}
	    	  	else if(map.getValue().toString().equals("932"))
	    	  	{
	    	  		isMessageCodeOk=true;
	    	  	}
	    	  	else
	    	  	{
	    	  		isMessageCodeOk=false;
	    	  	}
	      }
	      if(map.getKey().toString().equals("isThisFinalGetServiceNeeded"))
	      {
	    	  if(map.getValue().toString().equals("1") && (map.getValue() != null))
	    	  {
	    		  isThisFinalGetServiceNeeded=true;
	    	  }
	    	  else
	    	  {
	    		  isThisFinalGetServiceNeeded=false;
	    	  }
	      }
	    	 
	      
	     if(map.getKey().toString().equals("visaXMLCollectoin") )
		      {
		    	
		    	JSONArray jsArray=new JSONArray(map.getValue().toString());
		    	String xmlStringForArg3="";
		    	Map ds = new HashMap();
		    	for(int i = 0; i < jsArray.length(); i++)
		    	{
		    		isVisaCollectionCreated=true;
		    		JSONObject jsonObjectForVisa=jsArray.getJSONObject(i);
		    		/*
		    		String notEscaped=jsonObjectForVisa.toString();
		    		LOG.debug("---notEscaped :" +notEscaped);
		    		String escaped=notEscaped.replaceAll("\\", "");
		    		LOG.debug("---escaped :" +escaped);
		    		JSONObject jtemp=new JSONObject(escaped);
		    		LOG.debug("---jtemp :" +jtemp.toString());
		    		*/
		    		String jsonObjectForVisaXML=jsonObjectForVisa.getString("xml") ;
		    		JSONObject jsobj=new JSONObject(jsonObjectForVisaXML);
		    		
		    		String visaNo=jsobj.getString("visaNo");
		    		LOG.debug("---visano :" +visaNo);
		    		LOG.debug("---visano length:" +visaNo.length());	
		    		if(visaNo.length()<12)
		    		{
		 
		    			LOG.debug("---visano length <13:" +visaNo.length());
		    			
		    			StringBuilder visaNoTwelveDigit=new StringBuilder();
		    			for(i=visaNo.length();i<12;i++)
		    			{
		    				visaNoTwelveDigit.append("0");
		    				
		    			}
		    			visaNoTwelveDigit.append(visaNo.toString());
		    			String visaNoWithLeadingZero=visaNoTwelveDigit.toString();
		    			
		    			
//		    			Param visaItem=new Param("visaNo",visaNoWithLeadingZero,"string");
//		    			Record rc=new Record();
//		    			rc.setParam(visaItem);
//		    			ds.setRecord(rc);
		    			visaNoCollectionString.append("<arg8><visaNo>");	
		    			visaNoCollectionString.append(visaNoWithLeadingZero);
		    			visaNoCollectionString.append("</visaNo></arg8>");
		    			
		    			LOG.debug("---visano length with zeor from jsonobj:" +jsobj.get("visaNo"));
		    			LOG.debug("---visano length with zeor:" +visaNoWithLeadingZero);
		    			
		    			LOG.debug("---visaNoCollection :" +visaNoCollectionString.toString());
		    		}
		    		
		    		else
		    		{
		    			LOG.debug("---visano length in not equal to < 13:" +visaNo.length());
//		    			Param visaItem=new Param("visaNo",visaNo,"string");
//		    			Record rc=new Record();
//		    			rc.setParam(visaItem);
//		    			ds.setRecord(rc);
//		    			Map visaItem = new HashMap();
//		    			visaItem.put("visaNo",visaNo);
//		    			ds.putAll(visaItem);
		    			visaNoCollectionString.append("<arg8><visaNo>");	
		    			visaNoCollectionString.append(visaNo);
		    			visaNoCollectionString.append("</visaNo></arg8>");
		    			LOG.debug("---visaItem:" +visaNoCollectionString.toString());
		    			
		    			
		    		}
		    		
		    		
		    	}
		    	
		    	
		    	LOG.debug("---xmlStringForArg3 :" +xmlStringForArg3);	
		    	LOG.debug("---visaNoCollection :" +ds.toString());	
		    	  	
		      }
	      
	     if(isThisFinalGetServiceNeeded && isMessageCodeOk  && isVisaCollectionCreated)
	     {
	    	 returnBool=true;
	     }
	    
	    
	      
	      
	      LOG.debug("---Input Param After :" + map.getKey() + " = " + map.getValue().toString());
	   
	    }
	    //PUT ALL OTHER LOGIC IN ELSE
	    input.put("visaXMLCollectoin",visaNoCollectionString.toString());
	    return returnBool;
	  }
	}