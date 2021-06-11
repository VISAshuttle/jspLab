package controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerUsingURI extends HttpServlet {
	
	// 1. url에 매칭된 Handler 객체를 찾는다. 
	//    예) /article/read.do  -> article.command.ReadArticleHandler 객체
	// 2. 그 Handler.process() 메소드를 실행한다.
	// 3. 반환된 jsp 화면으로 forwarding 한다.
	

    // <커맨드, 핸들러인스턴스> 매핑 정보 저장
    private Map<String, CommandHandler> commandHandlerMap = 
    		new HashMap<>();
    // {"/join.do":member.command.JoinHandler 객체, 
    //  "/login.do":auth.command.LoginHandler 객체, ....}

    public void init() throws ServletException {
        String configFile = getInitParameter("configFile");
        //  "/WEB-INF/commandHandlerURI.properties"
        Properties prop = new Properties();
        String configFilePath = getServletContext().getRealPath(configFile);
        // "D:\dev\workspace\jsp\chap21Lab\WebContent\WEB-INF\commandHandlerURI.properties"
        try (FileReader fis = new FileReader(configFilePath)) {
            prop.load(fis);
        } catch (IOException e) {
            throw new ServletException(e);
        }
        Iterator keyIter = prop.keySet().iterator();
        while (keyIter.hasNext()) {
            String command = (String) keyIter.next();
            String handlerClassName = prop.getProperty(command);
            try {
                Class<?> handlerClass = Class.forName(handlerClassName);
                CommandHandler handlerInstance = 
                        (CommandHandler) handlerClass.newInstance();
                commandHandlerMap.put(command, handlerInstance);
            } catch (ClassNotFoundException | InstantiationException 
            		| IllegalAccessException e) {
                throw new ServletException(e);
            }
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    protected void doPost(HttpServletRequest request,
    HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request,
    HttpServletResponse response) throws ServletException, IOException {
		String command = request.getRequestURI();
		// "/chap21/join.do"
		if (command.indexOf(request.getContextPath()) == 0) {
			command = command.substring(request.getContextPath().length());
			// "/join.do"
		}
        CommandHandler handler = commandHandlerMap.get(command);
        // member.command.JoinHandler 객체
        if (handler == null) {
            handler = new NullHandler();
        }
        String viewPage = null;
        try {
            viewPage = handler.process(request, response);
            // /WEB-INF/view/changePwdSuccess.jsp
        } catch (Throwable e) {
            throw new ServletException(e);
        }
        if (viewPage != null) {
	        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
	        dispatcher.forward(request, response);
        }
    }
}
