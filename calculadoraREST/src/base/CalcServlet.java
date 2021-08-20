package base;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CalcServlet")
public class CalcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String exp = request.getParameter("exp");
		
		char ch = '+';
		exp = exp.replace(' ', ch);
				
		Double result = ExpressionEvaluator.eval(exp);
		
		if(exp!=null) {
			response.getOutputStream().println("{\"result\": "+ result +"}");
		} else {response.getOutputStream().println("{ }");}
		
		FileWriter f = new FileWriter("../../eclipse-workspace2020/calculadoraREST/webContent/log.txt");

		BufferedWriter buf = new BufferedWriter(f);
		buf.write(exp+" - "+result);
		buf.close();
		f.close();
		/*
		RandomAccessFile log = new RandomAccessFile("log.txt", "rw");
		log.seek(log.length());
		log.writeBytes(exp+" - "+result);
		System.out.println("Passou aqui");
		*/
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
