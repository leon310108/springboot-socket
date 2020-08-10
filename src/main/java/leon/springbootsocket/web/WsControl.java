package leon.springbootsocket.web;

import leon.springbootsocket.domain.LeonMessage;
import leon.springbootsocket.domain.LeonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class WsControl {
    @MessageMapping("/welcome")
    @SendTo("/topic/getResponse")
    public LeonResponse say(LeonMessage message) throws Exception{
        Thread.sleep(3000);
        return new LeonResponse("Welcome, "+message.getName()+"!");
    }
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat")
    public void handleChat(Principal principal,String msg){
        if(principal.getName().equals("leon")){
            messagingTemplate.convertAndSendToUser("hong","/queue/notifications",principal.getName()+"-send:"+msg);
        }else {
            messagingTemplate.convertAndSendToUser("leon","/queue/notifications",principal.getName()+"-send:"+msg);
        }
    }
}
