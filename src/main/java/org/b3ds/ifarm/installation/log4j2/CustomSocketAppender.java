package org.b3ds.ifarm.installation.log4j2;

import java.io.Serializable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.appender.AppenderLoggingException;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.PatternLayout;

import com.b3ds.ifarm.installation.wssocket.App2;
import com.b3ds.ifarm.installation.wssocket.Log4jWebSession;;

//@Plugin(name="MyCustomAppender", category="Core", elementType="appender", printObject=true)
@Plugin(name="CustomAppender", category="Core", elementType="appender", printObject=true)


public class CustomSocketAppender extends AbstractAppender {

	
	private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final Lock readLock = rwLock.readLock();
    
    Log4jWebSession app = null;

    protected CustomSocketAppender(String name, String host,String port,Filter filter,
            Layout<? extends Serializable> layout, final boolean ignoreExceptions) {
        super(name, filter, layout, ignoreExceptions);
        System.out.println(host);
        System.out.println(port);
        
    	app=new Log4jWebSession();
        try {
			app.connects(host,port);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

    }
    
    @PluginFactory
    public static CustomSocketAppender createAppender(
            @PluginAttribute("name") String name,
            @PluginAttribute("host") String host,
            @PluginAttribute("port") String port,
            @PluginElement("Layout") Layout<? extends Serializable> layout,
            @PluginElement("Filter") final Filter filter,
            @PluginAttribute("otherAttribute") String otherAttribute) {
        if (name == null) {
            LOGGER.error("No name provided for MyCustomAppenderImp");
            return null;
        }
        if (layout == null) {
            layout = PatternLayout.createDefaultLayout();
        }
        return new CustomSocketAppender(name, host, port, filter, layout, true);
}
    
       @Override
       public void append(LogEvent event) {
          readLock.lock();
               try {    
                   final byte[] bytes = getLayout().toByteArray(event);
                   System.out.println(new String(bytes, "UTF-8"));
                   app.sendLog(bytes);
                   } catch (Exception ex) {
                	   if (!ignoreExceptions()) {
                		   throw new AppenderLoggingException(ex);}
               } finally {
                  readLock.unlock();
               }
       }
}