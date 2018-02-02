package com.github.shadskii;

import com.github.shadskii.data.Line;
import com.github.shadskii.data.LineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

/**
 * Controller for
 */
@Controller
public class LineController
{
    private final LineRepository repository;
    private static final String ERROR_MSG = "The requested line is beyond the end of the file";

    @Autowired
    public LineController(LineRepository repository)
    {
        this.repository = repository;
    }

    @GetMapping("/lines/{num}")
    @ResponseBody
    public ResponseEntity<String> getLine(@PathVariable String num, Model model)
    {
        Long lineNum;
        try
        {
            lineNum = Long.valueOf(num);
        }
        catch (NumberFormatException e)
        {
            lineNum = -1L;
        }

        Line requestedLine = repository.findByPageNum(lineNum);
        if (Objects.nonNull(requestedLine))
        {
            return ResponseEntity.status(HttpStatus.OK)
                                 .body(requestedLine
                                         .getLine());
        } else
        {
            return doError();
        }
    }

    private static ResponseEntity<String> doError()
    {
        return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE)
                             .body(ERROR_MSG);
    }
}
