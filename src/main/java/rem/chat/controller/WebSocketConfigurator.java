package rem.chat.controller;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.HandshakeResponse;
import jakarta.websocket.server.HandshakeRequest;
import jakarta.websocket.server.ServerEndpointConfig;;

public class WebSocketConfigurator extends ServerEndpointConfig.Configurator{
	@Override
	public void modifyHandshake(ServerEndpointConfig config, HandshakeRequest request, HandshakeResponse response) {
		HttpSession httpSession = (HttpSession)request.getHttpSession();
       
		if (httpSession != null) {
			config.getUserProperties().put("httpSession", httpSession);
		} else {
			System.err.println("WebSocket Handshake 시 HttpSession이 존재하지 않습니다.");
			// 필요하다면 연결 거부 등의 추가적인 처리
		}
	}
}
