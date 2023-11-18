package com.example.FunFit.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.FunFit.database.Database;
import com.example.FunFit.model.Batch;
import com.example.FunFit.model.Participant;

/**
 * Servlet implementation class AddBatchControllerServlet
 */
public class AddBatchControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBatchControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Batch batch = new Batch();
		batch.setBid(Integer.valueOf(request.getParameter("bid")));
		batch.setBatchName(request.getParameter("bname"));
	    batch.setBatchInstructor(request.getParameter("batchInstructor"));
	    batch.setCapacity(Integer.valueOf(request.getParameter("capacity")));
	    batch.setBatchDuration(Integer.valueOf(request.getParameter("duration")));
	    // Use the database singleton instance
	    Database db = Database.getInstance();
	    
	 // SQL query to select batch ID by batch name
	    String selectIDbyBatchName = "SELECT bid FROM batch WHERE batchName = ?";
	   
	   
	    try (Connection connection = db.getConnection();
	         PreparedStatement ps = connection.prepareStatement(selectIDbyBatchName)) {
	        // Set the batch name parameter
	        ps.setString(1, batch.getBatchName());
	        try {
	        	Integer batchNamesFound = db.executeUpdate(ps);
	        	String ErrorMessage = batchNamesFound > 0 ? "Batch Name already exists, please choose another one" : "Batch Name available to use";
	        	System.out.println(ErrorMessage);
	        } catch (Exception e){
	        	System.out.println("Exception occurred at finding batch name");
	        	e.printStackTrace();
	        }
	        	
	        // SQL query to insert participant data into the database
	        String insertParticipantSql = "INSERT INTO Batch ( bid, batchName, batchInstructor, capacity, batchDuration) VALUES (?, ?, ?, ?, ?)";
	        try (Connection conn = db.getConnection();
	        	PreparedStatement insSQLStmt = conn.prepareStatement(insertParticipantSql)) {
	        	// Set parameters for the participant insertion
	        	insSQLStmt.setInt(1, batch.getBid());
	        	insSQLStmt.setString(2, batch.getBatchName());
	        	insSQLStmt.setString(3, batch.getBatchInstructor());
	        	insSQLStmt.setInt(4, batch.getCapacity());
	        	insSQLStmt.setInt(5, batch.getBatchDuration());
	        	// Execute the update
	        	int result = db.executeUpdate(insSQLStmt);
	        
	        	//Begin sending results to Front End using JSP
	        	if (result > 0) {
	        		// Set attributes for data that the JSP will use to generate the view
	        		request.setAttribute("successMessage", "Participant added successfully!");
	        		request.setAttribute("batchID", batch.getBid());
	        		request.setAttribute("batchName", batch.getBatchName());
	        		request.setAttribute("batchInstructor", batch.getBatchInstructor());
	        		request.setAttribute("batchCapacity", batch.getCapacity());
	        		request.setAttribute("batchDuration", batch.getBatchInstructor());
	           
	        		// Forward the request to the JSP for rendering the view
	        		RequestDispatcher dispatcher = request.getRequestDispatcher("/add-batch.jsp");
	        		dispatcher.forward(request, response);
	        	} else {
	        		// Handle the case where no rows were found
	        	}
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }    
	    } catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	}

