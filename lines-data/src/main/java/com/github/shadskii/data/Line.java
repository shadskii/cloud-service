package com.github.shadskii.data;

import org.springframework.data.annotation.Id;


public class Line
{
    @Id
    public String id;
    public Long pageNum;
    public String line;

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

}
