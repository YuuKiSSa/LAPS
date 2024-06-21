package sg.edu.nus.spring_laps.service;

public interface EmailService {
	public void sendSimpleMessage(String to, String subject, String text);
}
