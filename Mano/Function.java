package function;

import java.lang.reflect.InvocationTargetException;

public class Function {

	public double somme( Object[] lo,String name) throws Exception {
		double s = 0;
		for (int i = 0;i < lo.length ;i++ ) {
			s = s + (double) lo[i].getClass().getMethod(name).invoke(lo[i]);
		}
		return s;
	}

	public double moyenne( Object[] lo,String name) throws Exception{
		return somme(lo,name)/lo.length;
	}
	
	//Maximum
	public static Object max(Object[] obj,String att) throws IllegalAccessException,InvocationTargetException,NoSuchMethodException{
		int max=(int)obj[0].getClass().getMethod(att).invoke(obj[0]);
		Object res = obj[0];
		for(int i=0;i<obj.length;i++){
			if(max<(int)obj[i].getClass().getMethod(att).invoke(obj[i])){
				//max=(double)obj[i].getClass().getMethod(att).invoke(obj[i]);
				res = obj[i];
			}
		}
		return res;	
	}

	//Minimum
	public  Object min(Object[] obj,String att) throws IllegalAccessException,InvocationTargetException,NoSuchMethodException{
		double min=(double)obj[0].getClass().getMethod(att).invoke(obj[0]);
	    
		for(int i=0;i<obj.length;i++){
			if(min>(double)obj[i].getClass().getMethod(att).invoke(obj[i])){
				min=(double)obj[i].getClass().getMethod(att).invoke(obj[i]);
			}
		}	
		return min;	
	}

	//Triage
	public Object[] triage(Object[] lo, String name) throws Exception {
	    Object temp ;
	    double[] nb = new double[lo.length];
	    Object[] res = new Object[lo.length];

	    for(int i=0;i<lo.length;i++) {
	    	nb[i] = (double) lo[i].getClass().getMethod(name).invoke(lo[i]);
	    	res[i] = lo[i];
	    }

	    for(int i=0;i<lo.length-1;i++) {
	      	for(int j=i+1 ;j<lo.length; j++) {
	        	if( nb[i] > nb[j]) {
	          		temp = res[i];
	          		res[i] = res[j];
	          		res[j] = temp;
	        	}
	    	}
	    }
	    return res;
  	}
} 

