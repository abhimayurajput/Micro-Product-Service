package www.neosoft.com.productservice.filter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.logging.Log;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


//@Slf4j
@Component
@Order(1)//filtering order in case if you have multiple filter
public class RequestResponseLogger implements Filter {
	public static final org.slf4j.Logger logger=LoggerFactory.getLogger(RequestResponseLogger.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		/*
		 * HttpServletRequest httpServletRequest=(HttpServletRequest) request;
		 * log.info("request body:",httpServletRequest.getInputStream().toString());
		 * log.info("request uri:",httpServletRequest.getRequestUri());
		 * log.info("request method:",httpServletRequest.getMethod());
		 */				
		CustomHttpServetRequestWrapper requestWrapper=new CustomHttpServetRequestWrapper((HttpServletRequest) request);
		logger.info("request URI: "+requestWrapper.getRequestURI());
		logger.info("request Method: "+requestWrapper.getMethod());
		logger.info("request Body: "+new String(requestWrapper.getByteArray()));
		//www.neosoft.com.productservice.filter.RequestResponseLogger.CustomHttpServletResponseWrapper.CustomeHttpServetRequestWrapper requestWrapper=  (www.neosoft.com.productservice.filter.RequestResponseLogger.CustomHttpServletResponseWrapper.CustomeHttpServetRequestWrapper)request;
		//log.info("request body:",new String(IOUtils.toByteArray(httpServletRequest.getInputStream())));
		//log.info("request body:",new String(requestWrapper.getByteArray()));
		chain.doFilter(requestWrapper, response);
		
		//HttpServletResponse httpServletResponse=(HttpServletResponse) response;
		
		//CustomHttpServletResponseWrapper responseWrapper= (CustomHttpServletResponseWrapper)response;
	}
	
	private class CustomHttpServletResponseWrapper extends HttpServletResponseWrapper{
		private final ByteArrayOutputStream capture;
		private ServletOutputStream output;
		private PrintWriter writer;
		
		public CustomHttpServletResponseWrapper(HttpServletResponse response) {
			super(response);
			this.capture = new ByteArrayOutputStream(response.getBufferSize());
		}
		
		@Override
		public ServletOutputStream getOutputStream() throws IOException {
			// TODO Auto-generated method stub
			if (writer != null) {
		        throw new IllegalStateException("getWriter() has already been called on this response.");
		    }
			if (output == null) {
		        // inner class - lets the wrapper manipulate the response 
		        output = new ServletOutputStream() {
		            @Override
		            public void write(int b) throws IOException {
		                capture.write(b);
		            }

		            @Override
		            public void flush() throws IOException {
		                capture.flush();
		            }

		            @Override
		            public void close() throws IOException {
		                capture.close();
		            }

		            @Override
		            public boolean isReady() {
		                return false;
		            }

		            @Override
		            public void setWriteListener(WriteListener arg0) {
		            }
		        };
		    }
			return output;
		}
		
		@Override
		public PrintWriter getWriter() throws IOException {
		    if (output != null) {
		        throw new IllegalStateException("getOutputStream() has already been called on this response.");
		    }

		    if (writer == null) {
		        writer = new PrintWriter(new OutputStreamWriter(capture,
		                getCharacterEncoding()));
		    }

		    return writer;
		}

		@Override
		public void flushBuffer() throws IOException {
		    super.flushBuffer();

		    if (writer != null) {
		        writer.flush();
		    } else if (output != null) {
		        output.flush();
		    }
		}

		public byte[] getCaptureAsBytes() throws IOException {
		    if (writer != null) {
		        writer.close();
		    } else if (output != null) {
		        output.close();
		    }

		    return capture.toByteArray();
		}

		public String getCaptureAsString() throws IOException {
		    return new String(getCaptureAsBytes(), getCharacterEncoding());	}
		
	}
	
	
	private class CustomHttpServetRequestWrapper extends HttpServletRequestWrapper {

	private	byte[] byteArray;
		public CustomHttpServetRequestWrapper(HttpServletRequest request) {
			super(request);
			// TODO Auto-generated constructor stub
			try {
				byteArray=IOUtils.toByteArray(request.getInputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("issue while reading request stream");
			}
		}
		
		@Override
		public ServletInputStream getInputStream() throws IOException {
			// TODO Auto-generated method stub
			return new CustomerdelegatingServletInputStream(new ByteArrayInputStream(byteArray));
		}

		public byte[] getByteArray() {
			return byteArray;
		}

		public void setByteArray(byte[] byteArray) {
			this.byteArray = byteArray;
		}
		

	}

}

