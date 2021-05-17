package DTO;

import java.util.List;

public class ResDTOforDTOList
{
    private final boolean success;
    private final List<DTO> payload;

    public ResDTOforDTOList(boolean success, List<DTO> payload)
    {
        this.success = success;
        this.payload = payload;
    }
}
