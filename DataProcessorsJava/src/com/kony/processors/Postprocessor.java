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

public class Postprocessor
  implements DataPostProcessor2
{
  private static final Logger LOG = Logger.getLogger(Postprocessor.class);
  private static final boolean DEBUG = LOG.isDebugEnabled();

  public Object execute(Result result,DataControllerRequest request, DataControllerResponse response)
    throws Exception
  {
	  String output=response.getResponse();
	  JSONObject item=new JSONObject(output);
	  
		  Iterator<String> keys=item.keys();
		  while(keys.hasNext()){
			  Param Par=new Param();
			  String keyItem=keys.next().toString();
			  Par.setName(keyItem);
			  Par.setValue(item.getString(keyItem));
			  Par.setType("string");
			  result.setParam(Par);
			  
		  }
		  
	 
		  Param Par2=new Param("asdf","	qew","string");
		  result.setParam(Par2);
			  
	
    return result;
  }
}