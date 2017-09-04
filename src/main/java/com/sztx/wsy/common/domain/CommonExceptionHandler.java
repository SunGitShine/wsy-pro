package com.sztx.wsy.common.domain;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.sztx.wsy.common.filter.EncryptFilter;
import com.sztx.wsy.util.URLUtil;


/**
 * 统一异常处理器
 * 
 * @author zhihongp
 * 
 */
public class CommonExceptionHandler implements HandlerExceptionResolver {
	/**
	 * 日志
	 */
	private final Logger log = LoggerFactory.getLogger(getClass());

	private static final String ERROR_VIEW = "error";

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		Result result = null;
		log.error("", ex);
		if (BusinessException.class.isAssignableFrom(ex.getClass())) {
			BusinessException bex = (BusinessException) ex;
			// 业务异常
			if (bex.getCode() != null) {
				result = new Result(bex.getCode(), bex.getDescription(), false);
			} else {
				result = new Result(ResultCode.COMMON_BUSINESS_EXCEPTION, false);
			}

			result.setDescription(ExceptionMessager.getExceptionMessage(bex));
			result.setResultMap(bex.getResultMap());
		} else if (ex.getClass().isInstance(new BusinessException())) {
			BusinessException bex = new BusinessException(ex);
			// 业务异常
			if (bex.getCode() != null) {
				result = new Result(bex.getCode(), bex.getDescription(), false);
			} else {
				result = new Result(ResultCode.COMMON_BUSINESS_EXCEPTION, false);
			}

			result.setDescription(ExceptionMessager.getExceptionMessage(bex));
			result.setResultMap(bex.getResultMap());
		} else if (SystemException.class.isAssignableFrom(ex.getClass())) {
			SystemException sex = (SystemException) ex;
			// 系统异常
			if (sex.getCode() != null) {
				result = new Result(sex.getCode(), sex.getDescription(), false);
			} else {
				result = new Result(ResultCode.COMMON_SYSTEM_EXCEPTION, false);
			}

			result.setDescription(ExceptionMessager.getExceptionMessage(sex));
			result.setResultMap(sex.getResultMap());
			log.error("CommonExceptionHandler catche the System Exception, ", ex);
		} else if (ex.getClass().isInstance(new SystemException())) {
			SystemException sex = new SystemException(ex);
			// 系统异常
			if (sex.getCode() != null) {
				result = new Result(sex.getCode(), sex.getDescription(), false);
			} else {
				result = new Result(ResultCode.COMMON_SYSTEM_EXCEPTION, false);
			}

			result.setDescription(ExceptionMessager.getExceptionMessage(sex));
			result.setResultMap(sex.getResultMap());
			log.error("CommonExceptionHandler catche the System Exception, ", ex);
		} else {
			// 系统错误
			result = new Result(ResultCode.COMMON_SYSTEM_ERROR, false);
			result.setDescription(ex.getMessage());
			log.error("CommonExceptionHandler catche the System Error, ", ex);
		}

		response.setCharacterEncoding("UTF-8");

		if (URLUtil.isAjaxUrl(request)) {
			// 返回json格式的数据
			try {
				StringBuffer responseSb = new StringBuffer();
				String resultStr = Result.toJSONString(result);

				if (URLUtil.isJsonp(request)) {
					response.setContentType("application/javascript;charset=UTF-8");
					String callback = request.getParameter("callback");
					responseSb.append(callback).append("(").append(resultStr).append(")");
					request.setAttribute("callback", callback);
				} else {
					response.setContentType("application/json;charset=UTF-8");
					responseSb.append(resultStr);
				}

				request.setAttribute("result", result);
				request.setAttribute(EncryptFilter.ENCRPY_HEADER, true);
				String responseStr = responseSb.toString();
				PrintWriter writer = null;

				try {
					writer = response.getWriter();
				} catch (Exception e) {
				}

				if (writer != null) {
					writer.write(responseStr);
				}
			} catch (Exception e) {
				log.error("Response write exception", e);
			}

			return new ModelAndView();
		} else {
			response.setContentType("text/html;charset=UTF-8");
			request.setAttribute("result", result);
			return new ModelAndView(ERROR_VIEW);
		}
	}

}

