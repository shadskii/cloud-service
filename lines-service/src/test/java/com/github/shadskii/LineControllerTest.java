package com.github.shadskii;


import com.github.shadskii.data.Line;
import com.github.shadskii.data.LineRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(value = LineController.class, secure = false)
public class LineControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LineRepository lineRepository;

    @Test
    public void testGetLine() throws Exception
    {
        Line dummyLine = new Line(0L, "tb12");
        when(lineRepository.findByPageNum(anyLong())).thenReturn(dummyLine);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/lines/1")
                                                              .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder)
                                  .andReturn();
        String expected = "tb12";

        assertThat(result.getResponse()
                         .getContentAsString()).isEqualTo(expected);
    }
}
