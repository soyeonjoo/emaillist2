package kr.ac.sungkyul.emaillist.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.sungkyul.emaillist.dao.EmailListDao;
import kr.ac.sungkyul.emaillist.vo.EmailListVo;

@WebServlet("/el")
public class EmailListServelt extends HttpServlet {//controller
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String actionName = request.getParameter("a");
		if("list".equals(actionName)){
			EmailListDao dao = new EmailListDao();
			List<EmailListVo> list =dao.getList();
			
			//request범위(scope)에 list객체를 저장, list불러와서 저장하는것
			request.setAttribute("l", list); //(참조이름-원하는대로 만들기, 객체이름)
 			
			
			//포워딩
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/list.jsp");// 어디로 보낼지 결정하는 객체 , 포워딩 , 요청 연장 
			rd.forward(request, response);//포워딩 다른장소로 이동, redirect와 다르게 주소창이 안바뀜
			
		}else if("form".equals(actionName)){
			
				
			//포워딩
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/form.jsp");// 어디로 보낼지 결정하는 객체 , 포워딩 , 요청 연장 
			rd.forward(request, response);//포워딩 다른장소로 이동, redirect와 다르게 주소창이 안바뀜
				
			}if("insert".equals(actionName)){

			request.setCharacterEncoding("utf-8");
			String firstName = request.getParameter("fn");
			String lastName = request.getParameter("ln");
			
			String email = request.getParameter("email");

			EmailListDao dao = new EmailListDao();
			EmailListVo vo = new EmailListVo();

			vo.setFirstName(firstName);
			vo.setLastName(lastName);
			vo.setEmail(email);

			dao.insert(vo);

			response.sendRedirect("/emaillist2/el"); //다시갔는데 액션이 없기때문에 디폴트 엑션 , 미리 디폴트액션을 리스트로 지정해놓아서 출력
				
				
				
				
			}	else  {// default action 널이거나 이상한값일때
			
				EmailListDao dao = new EmailListDao();
				List<EmailListVo> list =dao.getList();
				
				
				//request범위(scope)에 list객체를 저장, list불러와서 저장하는것
				request.setAttribute("l", list); //(참조이름-원하는대로 만들기, 객체이름)
	 		
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/list.jsp");// 어디로 보낼지 결정하는 객체 , 포워딩 , 요청 연장 
				rd.forward(request, response);//포워딩 다른장소로 이동, redirect와 다르게 주소창이 안바뀜
				
			}
		
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response); // post도  doget에서  같이 처리하겠다 , 같은곳에서 처리하려고 
	
	
	
	
	}

}
