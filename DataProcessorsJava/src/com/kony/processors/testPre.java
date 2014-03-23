package com.kony.processors;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.*;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

import com.konylabs.middleware.common.DataPreProcessor;
import com.konylabs.middleware.controller.DataControllerRequest;
import com.konylabs.middleware.dataobject.Result;
import com.konylabs.middleware.dataobject.Dataset;
import com.konylabs.middleware.dataobject.Param;
import com.konylabs.middleware.dataobject.Record;


public class testPre  implements DataPreProcessor
{
	  private static final Logger LOG = Logger.getLogger(Preprocessor.class);
	  private static final boolean DEBUG = LOG.isDebugEnabled();

	  public boolean execute(HashMap input, DataControllerRequest request, Result result)
	    throws Exception
	  {
		  Dataset dx=new Dataset();
			Record rc=new Record();
			Record rc2=new Record();
			Record rc3=new Record();
			
			Param p1=new Param();
			p1.setName("visaNo");
			p1.setValue("098");
			Param p2=new Param();
			p2.setName("visaNo");
			p2.setValue("098");
			Param p3=new Param();
			p3.setName("visaNo");
			p3.setValue("098");
			rc.setParam(p1);
			rc2.setParam(p2);
			rc3.setParam(p3);
			
			dx.setRecord(rc);
			dx.setRecord(rc2);
			dx.setRecord(rc3);
			input.put("abc", dx);
		  int tint=0;
		  Param pt=new Param();
		 List ds= new ArrayList();
		  Map visaItem = new HashMap();
			visaItem.put("visaNo","123");
			Map visaItem2 = new HashMap();
			visaItem2.put("visaNo","1234");
			Map visaItem3 = new HashMap();
			visaItem3.put("visaNo","1235");
			ds.add(visaItem);
			ds.add(visaItem2);
			ds.add(visaItem3);
			Dataset fg=new Dataset();
			
			
			
			input.put("abcdf", fg);
			input.put("x", visaItem);
			LOG.debug("---visaItem:" +visaItem.toString());
			 
		    Iterator itr = input.entrySet().iterator();
		    while(itr.hasNext())
		    {
		    	 Map.Entry map = (Map.Entry)itr.next();
		    	 if(map.getKey().toString().equals("abc"))
		    	 {
		    		Dataset dt=(Dataset)map.getValue();
		    		ArrayList t=dt.getRecords();
		    		Iterator ft=t.iterator();
		    		int i=1;
		    		while(ft.hasNext())
		    		{
		    			
		    			
		    			Record fturt=(Record)ft.next();
		    			ArrayList yu= fturt.getParams();
		    			Iterator g=yu.iterator();
		    			while(g.hasNext())
		    			{
		    				pt=(Param)g.next();
		    			}
		    			i++;
		    			 
		    		}
		    		tint=i;
		    	 }
		    }
			input.put("ftu", pt.getType());
			input.put("i", tint);
		
		
		return true;
	}
}