package DTO;

public class HelperDto
{
    private boolean success;
    private DTO payload;

    public HelperDto()
    {
        //an empty constructor
    }

    public void setSuccess(boolean success)
    {
        this.success = success;
    }

    public void setPayload(DTO payload)
    {
        this.payload = payload;
    }

    public ResponseDTO build()
    {
        return new ResponseDTO(success, payload);
    }
}
