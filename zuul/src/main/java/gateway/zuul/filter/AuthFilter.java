package gateway.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import feign.FeignException;
import gateway.zuul.client.AuthClient;
import gateway.zuul.client.VerificationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class AuthFilter extends ZuulFilter {

    @Autowired
    private AuthClient authClient;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    private void setFailedRequest(String body, int code) {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.setResponseStatusCode(code);
        if (ctx.getResponseBody() == null) {
            ctx.setResponseBody(body);
            ctx.setSendZuulResponse(false);
        }
    }

    @Override
    public Object run() {

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        if (request.getHeader("Authorization") == null) {
            return null;
        }

        String token = request.getHeader("Authorization");
        System.out.println(token);
        try {
            VerificationResponse vr = authClient.verify(token);
            if (vr == null) {
                setFailedRequest("Token nije validan", 403);
            }
            ctx.addZuulRequestHeader("userId", vr.getUserId());
            ctx.addZuulRequestHeader("email", vr.getEmail());
            ctx.addZuulRequestHeader("roles", vr.getRoles());
            ctx.addZuulRequestHeader("Auth", token);
            ctx.addZuulRequestHeader("Authorization", token);
        } catch (FeignException.NotFound e) {
            setFailedRequest("Korisnik ne postoji", 403);
        }
        return null;
    }
}
