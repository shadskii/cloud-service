package com.github.shadskii.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Data object that represents a line of a text file.
 */
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

    /**
     * The line number of hte line
     *
     * @return A 0 indexed long representing the line number.
     */
    public Long getPageNum()
    {
        return pageNum;
    }

    /**
     * The full text of the line.
     *
     * @return A String that contains all the characters from a line of a file omitting the end of line character.
     */
    public String getLine()
    {
        return line;
    }

    /**
     * Set the Id of this line
     *
     * @param id A unique string ID
     */
    public void setId(String id)
    {
        this.id = id;
    }

    /**
     * Sets the page number that this line represents
     *
     * @param pageNum A long greater than 0
     */
    public void setPageNum(Long pageNum)
    {
        this.pageNum = pageNum;
    }

    /**
     * Sets the text associated with this line
     *
     * @param line The plain text of this line. Omitting the end of line character.
     */
    public void setLine(String line)
    {
        this.line = line;
    }
}
