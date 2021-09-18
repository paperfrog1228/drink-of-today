package paperfrog.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import paperfrog.dot.domain.Board;
import paperfrog.dot.domain.Member;
import paperfrog.dot.domain.MemberGrade;
import paperfrog.dot.web.SessionConst;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class DeleteBoardCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI =request.getRequestURI();
        HttpSession session=request.getSession();
        Member member=(Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        Board board=(Board) session.getAttribute("board");
        if(session==null||member.getMemberGrade()!= MemberGrade.MANAGER){
            log.info("일반 사용자가 매니저 글쓰기 접근");
            response.sendRedirect("/user/login?requestURL="+requestURI);
            return false;
        }
        return true;
    }
}