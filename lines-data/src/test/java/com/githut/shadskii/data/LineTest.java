package com.githut.shadskii.data;

import com.github.shadskii.data.Line;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LineTest
{
    @Test
    public void testLine()
    {
        Line line = new Line(1L, "hello world");

        assertThat(line.getLine()).isEqualTo("hello world");
        assertThat(line.getPageNum()).isEqualTo(1L);

        line.setLine("LINE");
        assertThat(line.getLine()).isEqualTo("LINE");

        line.setPageNum(1000L);
        assertThat(line.getPageNum()).isEqualTo(1000L);
    }
}
