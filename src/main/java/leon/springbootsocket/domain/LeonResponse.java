package leon.springbootsocket.domain;

public class LeonResponse {
    private String responseMessage;
    public LeonResponse(String responseMessage){
        this.responseMessage=responseMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }
}
