package com.aries.tiger.concurrent.chapter2;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aries.tiger.concurrent.common.ThreadSafe;

@ThreadSafe
public class StatelessFactorizer implements Servlet {

	static Logger log = LoggerFactory.getLogger(StatelessFactorizer.class);

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
		BigInteger i = extractFromRequest(req);
		List<BigInteger> factors = factor(i);
		encodeIntoResponse(res, factors);
	}
	
	void encodeIntoResponse(ServletResponse res, List<BigInteger> factors) throws IOException{
		for(BigInteger i : factors){
			res.getWriter().write(i.intValue() + ", ");
		}
	}

	/**
	 * 因数分解
	 * 从2开始除，如果能被整除，添加一个因子，把商继续分解，同时循环应该break;
	 * @param init
	 * @return
	 */
	List<BigInteger> factor(BigInteger init) {
		List<BigInteger> factors = new ArrayList<BigInteger>();
		for (int i = 2; i <= init.intValue(); i++) {
			if (init.intValue() % i == 0) {
				factors.add(new BigInteger(i + ""));
				factors.addAll(factor(new BigInteger(
						(int) (init.intValue() / i) + "")));
				break;
			}
		}

		return factors;
	}

	public static void main(String[] args) {
		StatelessFactorizer s = new StatelessFactorizer();
		log.debug("100: ");
		for (BigInteger i : s.factor(new BigInteger("100"))) {
			log.debug("{}", i.intValue());
		}

	}

	BigInteger extractFromRequest(ServletRequest req) {
		String number = req.getParameter("number");
		return new BigInteger(number);
	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
