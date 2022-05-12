package kg.peaksoft.peaksoftlmsab4.exception.handler;

import kg.peaksoft.peaksoftlmsab4.exception.AlreadyExistsException;
import kg.peaksoft.peaksoftlmsab4.exception.BadRequestException;
import kg.peaksoft.peaksoftlmsab4.exception.NotFoundException;
import kg.peaksoft.peaksoftlmsab4.exception.UnauthorizedException;
import kg.peaksoft.peaksoftlmsab4.model.entity.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ResponseEntity handleNotFoundException(NotFoundException notFoundException) {
        return ResponseEntity.builder()
                .httpStatus(NOT_FOUND)
                .message(notFoundException.getMessage())
                .build();
    }


    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity handleBadRequestException(BadRequestException badRequestException) {
        return ResponseEntity.builder()
                .httpStatus(BAD_REQUEST)
                .message(badRequestException.getMessage())
                .build();
    }

    @ExceptionHandler(AlreadyExistsException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity handleAlreadyExistsException(AlreadyExistsException alreadyExistsException) {
        return ResponseEntity.builder()
                .httpStatus(BAD_REQUEST)
                .message(alreadyExistsException.getMessage())
                .build();
    }
}
