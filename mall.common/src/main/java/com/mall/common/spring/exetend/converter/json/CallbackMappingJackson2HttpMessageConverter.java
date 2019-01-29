package com.mall.common.spring.exetend.converter.json;

import java.io.IOException;
import java.lang.reflect.Type;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonGenerator;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.lang.Nullable;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonProcessingException;

public class CallbackMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter  {

	// 做jsonp的支持的标识，在请求参数中加该参数
	private String callbackName;

	@Override
	protected void writeInternal(Object object, @Nullable  Type type, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		HttpServletRequest request=((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		String callbackParam=request.getParameter(callbackName);
		if(StringUtils.isEmpty(callbackParam)){
			super.writeInternal(object, type, outputMessage);//调用父类方法直接返回。
		}else{


			JsonEncoding encoding = getJsonEncoding(outputMessage.getHeaders().getContentType());
			try {
				String result =callbackParam+"("+super.getObjectMapper().writeValueAsString(object)+");";
				IOUtils.write(result, outputMessage.getBody(),encoding.getJavaName());
			}
			catch (JsonProcessingException ex) {
				throw new HttpMessageNotWritableException("Could not write JSON: " + ex.getMessage(), ex);
			}
		}
	}
	@Override
	protected void writePrefix(JsonGenerator generator, Object object) throws IOException {
		super.writePrefix(generator,object);
	}

	public String getCallbackName() {
		return callbackName;
	}

	public void setCallbackName(String callbackName) {
		this.callbackName = callbackName;
	}

}
