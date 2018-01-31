package com.github.shadskii.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "lines")
public class Line
{
    @Id
    private String id;
    private Long pageNum;
    private String line;

    public Line(Long pageNum, String line)
    {
        this.pageNum = pageNum;
        this.line = line;
    }

    public Long getPageNum()
    {
        return pageNum;
    }

    public String getLine()
    {
        return line;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public void setPageNum(Long pageNum)
    {
        this.pageNum = pageNum;
    }

    public void setLine(String line)
    {
        this.line = line;
    }
}
