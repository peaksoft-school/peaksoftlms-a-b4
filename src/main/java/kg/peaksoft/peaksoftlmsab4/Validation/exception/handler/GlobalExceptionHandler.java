package kg.peaksoft.peaksoftlmsab4.Validation.exception.handler;

import kg.peaksoft.peaksoftlmsab4.Validation.exception.AlreadyExistsException;
import kg.peaksoft.peaksoftlmsab4.Validation.exception.BadRequestException;
import kg.peaksoft.peaksoftlmsab4.Validation.exception.InvalidArgumentException;
import kg.peaksoft.peaksoftlmsab4.Validation.exception.NotFoundException;
import kg.peaksoft.peaksoftlmsab4.controller.payload.SimpleResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public SimpleResponse handleNotFoundException(NotFoundException notFoundException) {
        return SimpleResponse.builder()
                .httpStatus(NOT_FOUND)
                .message(notFoundException.getMessage())
                .build();
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(BAD_REQUEST)
    public SimpleResponse handleBadRequestException(BadRequestException badRequestException) {
        return SimpleResponse.builder()
                .httpStatus(BAD_REQUEST)
                .message(badRequestException.getMessage())
                .build();
    }

    @ExceptionHandler(AlreadyExistsException.class)
    @ResponseStatus(BAD_REQUEST)
    public SimpleResponse handleAlreadyExistsException(AlreadyExistsException alreadyExistsException) {
        return SimpleResponse.builder()
                .httpStatus(BAD_REQUEST)
                .message(alreadyExistsException.getMessage())
                .build();
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(UNAUTHORIZED)
    public SimpleResponse unauthorizedException(BadCredentialsException badCredentialsException) {
        return SimpleResponse.builder()
                .httpStatus(UNAUTHORIZED)
                .message(badCredentialsException.getMessage())
                .build();
    }

    @ExceptionHandler(InvalidArgumentException.class)
    @ResponseStatus(NOT_ACCEPTABLE)
    public SimpleResponse invalidArgumentException(InvalidArgumentException invalidArgumentException) {
        return SimpleResponse.builder()
                .httpStatus(NOT_ACCEPTABLE)
                .message(invalidArgumentException.getMessage())
                .build();
    }

}
