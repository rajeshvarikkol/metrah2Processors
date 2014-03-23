package com.kony.processors;

import com.konylabs.middleware.common.DataPreProcessor;
import com.konylabs.middleware.controller.DataControllerRequest;
import com.konylabs.middleware.dataobject.Dataset;
import com.konylabs.middleware.dataobject.Param;
import com.konylabs.middleware.dataobject.Record;
import com.konylabs.middleware.dataobject.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.log4j.Logger;

public class Preprocessor
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

      LOG.debug("---Input Param Before :" + map.getKey() + " = " + map.getValue());
      if(map.getKey().toString().equals("errorCode"))
      {
    	  	if(!map.getValue().toString().equals("") || map.getValue()==null)
    	  	{
   	  		
    	  		returnBool=false;
    	  	}
      }
      LOG.debug("---Input Param After :" + map.getKey() + " = " + map.getValue());
    }
    
    
    Iterator itrs = request.getAttributeNames();
    while(itrs.hasNext())
    {
    	LOG.debug("---itrs :" +itrs.next().toString());
    }
    

    return returnBool;
  }
}