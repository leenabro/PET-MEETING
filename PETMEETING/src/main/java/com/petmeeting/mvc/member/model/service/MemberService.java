package com.petmeeting.mvc.member.model.service;

import java.sql.Connection;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.petmeeting.mvc.member.model.dao.MemberDao;
import com.petmeeting.mvc.member.model.vo.Dog;
import com.petmeeting.mvc.member.model.vo.Member;
import static com.petmeeting.mvc.common.jdbc.JDBCTemplate.commit;
import static com.petmeeting.mvc.common.jdbc.JDBCTemplate.rollback;
import static com.petmeeting.mvc.common.jdbc.JDBCTemplate.getConnection;
import static com.petmeeting.mvc.common.jdbc.JDBCTemplate.close;

public class MemberService {
	
	public Member login(String id, String password) {
		Member member = this.findMemberById(id);
		
		if(member == null || !member.getPassword().equals(password)) {
			
			return null;
		}

		return member;
	}
	
	public int memberSave(Member member) {
		int result = 0;
		Connection connection = getConnection();
		
		if(member.getMCode() > 0) {
			result = new MemberDao().updateMember(connection, member);
		} else {
			result = new MemberDao().insertMember(connection, member);
		}
		
		if(result > 0) {
			commit(connection);
		} else {
			rollback(connection);
		}
		
		close(connection);
		
		return result;
	}
	
	public Member findMemberById(String id) {
		Connection connection = getConnection();
		
		Member member = new MemberDao().findMemberById(connection, id);
		
		close(connection);
		
		return member;
	}
	
	public String findIdByEmail(String email) {
		return new MemberDao().findIdByEmail(getConnection(), email);
	}
	
	public boolean isDuplicateId(String id) {
		return this.findMemberById(id) != null;
	}
	
	public boolean isDuplicateNickname(String inputNick) {
		return new MemberDao().nickDuplicateCheck(getConnection(), inputNick);
	}
	
	public boolean isDuplicateEmail(String inputEmail) {
		return new MemberDao().emailDuplicateCheck(getConnection(), inputEmail);
	}
	
	public String sendEmail(String inputEmail) {
			
		final String user = "khpetmeeting";
		final String password = "mxmymcqavdvdleky";
			
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", 465);
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		prop.put("mail.debug", "true");
			
		Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
		protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(user, password);
		}
		});
		
		MimeMessage message = new MimeMessage(session);
		String authCode = this.makeAuthCode();

		try {
			message.setFrom(new InternetAddress(user));
				
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(inputEmail));
				
			if (inputEmail != null) {
				message.setSubject("회원가입을 위한 인증 코드 입니다.");
				
				
				message.setText("확인용 인증코드 : " +  authCode + " 를 입력해주세요.");
				
				Transport.send(message);
				System.out.println("메세지 보내기 성공");
				System.out.println(message);
			} else {
				System.out.println("메세지 보내기 실패");
			}
				
			} catch (AddressException e) {
				e.printStackTrace();
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		
		return authCode;
	}
			
	public String makeAuthCode() {
		String authCode = null;
				
		StringBuffer temp = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			int index = random.nextInt(3);
			switch (index) {
				case 0:
					temp.append((char) ((int) (random.nextInt(26))+ 97));
					break;
				case 1:
					temp.append((char) ((int) (random.nextInt(26))+ 65));
					break;
				case 2:
					temp.append((random.nextInt(10)));
					break;
			}
					
		}
				
		authCode = temp.toString();
		
		return authCode;
	}

	// 강아지 정보 인서트
		public int dogSave(Dog dog, Member member) {
			int result = 0;
			Connection connection = getConnection();
			
			result = new MemberDao().insertDog(connection, dog, member);
			
			if(result > 0) {
				commit(connection);
			} else {
				rollback(connection);
			}
			
			close(connection);
			
			return result;
		}
		
		public Dog findDogByCode(int code) {
			Connection connection = getConnection();
			
			Dog dog = new MemberDao().findDogByCode(connection, code);
			
			close(connection);
			
			return dog;
		}


		public List<Dog> findAllDogByCode(int mCode) {
			List<Dog> list = null;
			Connection connection = getConnection();
			
			list = new MemberDao().findAllDogByCode(connection, mCode);
			
			return list;
		}

		public int countMemberDog(int mCode) {
			int result = 0;
			Connection connection = getConnection();
			
			result = new MemberDao().countMemberDog(connection, mCode);
			
			
			return result;
		}

		public int updatePassword(int mCode, String userPwd) {
			int result = 0;
			Connection connection = getConnection();
			
			result = new MemberDao().updateMemberPassword(connection, mCode, userPwd);
			
			if(result > 0) {
				commit(connection);
			} else {
				rollback(connection);
			}
			
			close(connection);
			
			return result;
			
		}
			
			
			
			
			
			
			
			
			
			
			
}

