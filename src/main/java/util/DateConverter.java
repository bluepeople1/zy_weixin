package util;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;
  
public class DateConverter implements Converter {  
   private  SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   
   public Object convert(Class type, Object value) {  
       if(value == null) {  
            return null;  
        }  
        if(value instanceof Date) {  
            return value;  
        }  
        if(value instanceof Long) {  
            Long longValue = (Long) value;  
            return new Date(longValue.longValue());  
        }  
        try {  
            return dateFormat.parse(value.toString());  
        } catch (Exception e) {  
            throw new ConversionException(e);  
        }  
    }  
   public void setFormat(String format){
	   dateFormat= new SimpleDateFormat(format);
   }
}  


