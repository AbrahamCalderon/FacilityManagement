package com.facilitymanagement.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsageDAO {

	public void insertUsage(String usage, int detId){
		
		Connection con = DBHelper.getConnection();
		PreparedStatement usePst = null;
			
		try{
			String insertUseQuery = "INSERT INTO usages (detailid, facilityUsages) VALUES (?, ?)";
			usePst = con.prepareStatement(insertUseQuery);
			usePst.setInt(1, detId);
			usePst.setString(2, usage);
			usePst.executeUpdate();
		}catch(SQLException ex){
			System.err.println("FacUsageDAO: Threw a SQL Exception inserting USE (string) object");
			System.out.println(ex.getMessage());
		}finally{
			try{
				if(usePst != null)
					usePst.close();
				if(con != null){
					con.close();
				}
			}catch(SQLException ex){
				System.err.println("FacUsageDAO: Threw a SQL Exception inserting USE (string) object");
				System.out.println(ex.getMessage());
				ex.printStackTrace();
			}
		}
	}
	
	public List<String> getUsagesByDetId(int detId){
		Connection con = DBHelper.getConnection();
		Statement stmt = null;
		try{
			stmt = con.createStatement();
			String selectReqQuery = "SELECT use FROM usages WHERE detailid = " + detId;
			ResultSet useRS = stmt.executeQuery(selectReqQuery);
			
			List<String> facUses = new ArrayList<String>();
			
			while(useRS.next()){
				String use = useRS.getString("facilityId");
				facUses.add(use);
			}
			useRS.close();
			return facUses;
			
			
		}catch(SQLException e){
			System.err.println("FacUsageDAO: Threw Exception trying to retrieve facility USEs");
			System.err.println(e.getMessage());
			e.printStackTrace();
		}finally{
			try{
				if(stmt != null)
					stmt.close();
				if(con != null){
					con.close();
				}
			}catch(SQLException e){
				System.err.println("FacUsageDAO: Threw Exception trying to retrieve facility USEs");
				System.err.println(e.getMessage());
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	/*UPDATE use can be a bit tricky
	 * 
	 * public void updateUsage(IFacilityDetail detail){
		Connection con = DBHelper.getConnection();
		Statement stmt = null;
			
		try{
			String updateFacility = 
					"UPDATE usages SET use = '" + detail.get +
					"', street = '" + facility.getStreet() + "', city = '" + facility.getCity() +
					"', state = '" + facility.getState() + "', zip = '" + facility.getZip() +
					"' WHERE facilityId = " + facility.getId();
			stmt = con.createStatement();
			stmt.execute(updateFacility);
			
			//Update units associated with complex
			for(IAptUnit u : facility.getUnits())
				aptDAO.updateAptUnit(u);
			
			//update inspection associated with complex
			for(IInspection i : facility.getInspections())
				insDAO.updateInspection(i);
			
		}catch(SQLException e){
			System.err.println("ComplexFacilityDAO: Threw SQLException when updating complexfacility object");
			System.err.println(e.getMessage());
			e.printStackTrace();
		}finally{
			try{
				if(stmt != null)
					stmt.close();
				if(con != null)
					con.close();
			}catch(SQLException e){
					System.err.println("ComplexFacilityDAO: Threw SQLException when updating complexfacility object");
				System.err.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}		
	}
	*/
}
