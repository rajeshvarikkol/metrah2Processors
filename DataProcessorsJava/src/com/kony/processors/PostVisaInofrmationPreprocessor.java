package com.kony.processors;

import com.konylabs.middleware.common.DataPreProcessor;
import com.konylabs.middleware.controller.DataControllerRequest;
import com.konylabs.middleware.dataobject.Dataset;
import com.konylabs.middleware.dataobject.Param;
import com.konylabs.middleware.dataobject.Record;
import com.konylabs.middleware.dataobject.Result;

import java.util.*;
import java.text.*;
import net.sf.json.JSON;

import org.apache.log4j.Logger;
import org.json.*;
import org.xml.*;


public class PostVisaInofrmationPreprocessor
  implements DataPreProcessor
{
  private static final Logger LOG = Logger.getLogger(Preprocessor.class);
  private static final boolean DEBUG = LOG.isDebugEnabled();

  public boolean execute(HashMap input, DataControllerRequest request, Result result)
    throws Exception
  {
	Boolean returnBool=true; 
    LOG.debug("-----begin Preprocessing-----");

    Iterator itr = input.entrySet().iterator();

    while (itr.hasNext())
    {
      Map.Entry map = (Map.Entry)itr.next();
      LOG.debug("---Input Param Before :" + map.getKey() );
      if(map.getValue()==null)
      {   LOG.debug("--inside map equals null:" );
    	  continue;}
      LOG.debug("---Input Param Before :" + map.getKey() + " = " + map.getValue());	
      if(map.getKey().toString().equals("errorCode"))
      {
    	  	if(!map.getValue().toString().equals("") || map.getValue()==null)
    	  	{
   	  		
    	  		returnBool=false;
    	  	}
      }
      else
      {
	      if(map.getKey().toString().equals("visaXMLCollectoin"))
	      {
	    	
	    	JSONArray jsArray=new JSONArray(map.getValue().toString());
	    	StringBuilder xmlStringForArg3=new StringBuilder();
	    	for(int i = 0; i < jsArray.length(); i++)
	    	{
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
	    			jsobj.put("visaNo",visaNoWithLeadingZero);
	    			LOG.debug("---visano length with zeor from jsonobj:" +jsobj.get("visaNo"));
	    			LOG.debug("---visano length with zeor:" +visaNoWithLeadingZero);
	    		}
	    		String xmlstring=XML.toString(jsobj);
	    		xmlStringForArg3.append("<arg3>");
	    		xmlStringForArg3.append(xmlstring);
	    		xmlStringForArg3.append("</arg3>");
	    		
	    	}
	    	
	    	input.put("visaXMLCollectoin", xmlStringForArg3.toString());
	    	LOG.debug("---xmlStringForArg3 :" +xmlStringForArg3.toString());	
	    	  	
	      }
	      if(map.getKey().toString().equals("refNo") && (!map.getValue().equals("") || !map.getValue().equals("null") || !map.getValue().equals(null)))
	      {
	    	  String fullRefNo=(String) map.getValue();
	    	  if(!fullRefNo.equals("null") && !fullRefNo.equals(null))
	    	  {
		    	  String trimRefNo=fullRefNo.substring(0, 9);
		    	  LOG.debug("---trimRefNo :" +trimRefNo);
		    	  input.put("refNo",trimRefNo);
	    	  }
	    	  
	      }
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy hhmm");
			String formattedDate = formatter.format(date);	
			String transactionDate;
            String transactionTime;
			transactionDate = formattedDate.substring(0,8); // 23082012
			transactionTime = formattedDate.substring(9,formattedDate.length()); 
			input.put("transactionDate",transactionDate);
			input.put("transactionTime",transactionTime);
      }
      
      
      
      LOG.debug("---Input Param After :" + map.getKey() + " = " + map.getValue());
    }
    
    //PUT ALL OTHER LOGIC IN ELSE

    return returnBool;
  }
}