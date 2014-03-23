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

public class getCompanyBasicInfo implements DataPreProcessor2 {
	private static final Logger LOG = Logger.getLogger(Preprocessor.class);
	private static final boolean DEBUG = LOG.isDebugEnabled();
	@Override
	public boolean execute(HashMap arg0, DataControllerRequest arg1,
			DataControllerResponse arg2, Result arg3) throws Exception {
		// TODO Auto-generated method stub
		Boolean companyType=false;
		Boolean companyNumber=false;
		Boolean companyBranchNumber=false;
		Iterator itr = arg0.entrySet().iterator();
		while (itr.hasNext())
	    {
	      Map.Entry map = (Map.Entry)itr.next();
	      if(map.getValue()==null)
	      {   LOG.debug("--inside map equals null:" );
	    	  continue;}
	      LOG.debug("---Input Param Before :" + map.getKey() + " = " + map.getValue().toString());
	      if(map.getKey().toString().equals("companyType"))
	      {	
	    	if(map.getValue()!=null && !map.getValue().equals("") && map.getValue().toString().length()==2)  
	    	{
	    		companyType=true;
	    	}
	      }
	      else if(map.getKey().toString().equals("companyNumber"))
	      {	
	    	  if(map.getValue()!=null && !map.getValue().equals("") && map.getValue().toString().length()==4)  
		    	{
	    		  companyNumber=true;
		    	}
	      }
	      else if(map.getKey().toString().equals("companyBranchNumber"))
	      {	
	    	  if(map.getValue()!=null && !map.getValue().equals("") && map.getValue().toString().length()==2)  
		    	{
	    		  companyBranchNumber=true;
		    	}
	      }
	    }
		if(companyType==true && companyNumber==true && companyBranchNumber==true)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}

}
