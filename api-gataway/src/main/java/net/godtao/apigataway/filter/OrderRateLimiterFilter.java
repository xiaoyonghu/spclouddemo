package net.godtao.apigataway.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

@Component
public class OrderRateLimiterFilter extends ZuulFilter {

    //每秒产生1000个令牌
    private static final RateLimiter RATE_LIMITER=RateLimiter.create(1000);

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    /**
    这个限流过滤器于所有的过滤器之前执行
     */
    @Override
    public int filterOrder() {
        return -4;
    }

    @Override
    public boolean shouldFilter() {


        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        //只对订单接口限流
        if ("/api/gataway/order/api/v1/order/save".equalsIgnoreCase(request.getRequestURI())){
            return true;
        }
        return false;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        if(!RATE_LIMITER.tryAcquire()){
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(HttpStatus.SC_NOT_ACCEPTABLE);
        }
        return null;
    }

}
