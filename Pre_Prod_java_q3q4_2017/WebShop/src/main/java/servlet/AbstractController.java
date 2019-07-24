package servlet;

import bean.SearchForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractController extends HttpServlet {

    public static void forwardToFragment(String jspFragment, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/JSP/fragment/" + jspFragment).forward(req, resp);
    }

    public static void forwardToPage(String jspPage, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("currentPage", jspPage);
        req.getRequestDispatcher("main.jsp").forward(req, resp);
    }

    public static void redirect(String url, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect(url);
    }

    protected final SearchForm createSearchForm(HttpServletRequest request) {
        return new SearchForm(request.getParameter("searchByName"),
                request.getParameterValues("type"),
                request.getParameterValues("countries"),
                request.getParameter("minPrice"),
                request.getParameter("maxPrice")
        );
    }
}
