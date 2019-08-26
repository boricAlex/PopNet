package kurs.popnet.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kurs.popnet.beans.MessageBean;

public class MessageService extends Service {

	public MessageService(String url, String usr, String psw) throws SQLException {
		super(url, usr, psw);
	}

	public List<MessageBean> showMessages(Integer ownerId) {
		
		List<MessageBean> mb = new ArrayList<MessageBean>();
		
		String sql = "CALL popnet.ShowMessages(?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, ownerId);
			
			boolean ok = ps.execute();
			ResultSet rs = ps.getResultSet();
			while(rs.next()) {
				MessageBean msb = new MessageBean();
				msb.setSenderName(rs.getString(1));
				msb.setMessageId(rs.getInt(3));
				msb.setText(rs.getString(4));
				msb.setUserId(rs.getInt(2));
				msb.setTimePosted(rs.getDate(6));
				msb.setLikes(rs.getInt(5));
				msb.setOwnerId(rs.getInt(7));
				
				mb.add(msb);
			}	
			return mb;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mb;
		
	}
	
	public boolean deleteMessage(Integer msgId) {
	
			String sql= "DELETE FROM likes WHERE messageId=?; ";
			String sql2= "UPDATE message SET status='hidden' WHERE messageId=?; ";
			
			try(PreparedStatement ps = conn.prepareStatement(sql); PreparedStatement ps2 = conn.prepareStatement(sql2)){
				ps.setInt(1, msgId);
				ps2.setInt(1, msgId);
				ps.executeUpdate();
				ps2.executeUpdate();
				return true;
			}catch(SQLException e){
				e.printStackTrace();
				return false;
			}}
	


	public static void main (String[] args) {
		
		try {
			MessageService ms = new MessageService("jdbc:mysql://localhost/PopNet", "dev", "mysql");
			ms.createConnection(true);
			

			List<MessageBean> mb = ms.showMessages(2);
			for(MessageBean msb:mb) {
				System.out.println(msb);
			}
			
			boolean b = ms.deleteMessage(2);
			System.out.println(b);
			

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
