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
 * Controller for all REST endpoints that involve accessing lines.
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

    /**
     * Get mapping for {@literal /lines/{num}} which provides a get request with the line number asked for. A response
     * with the code {@link HttpStatus#OK} is returned along with the requested line. If the requested line number is
     * outside the range of the file or is not a valid integer (i.e. contains non numeric characters) then a response
     * with the code {@link HttpStatus#PAYLOAD_TOO_LARGE} is returned.
     *
     * @param num   - The line number requested by the client.
     * @param model
     * @return A response containing either the text of the requested Line or an error message.
     */
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
            return doError();
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
