package com.aegis.TechMarket.Exceptions;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.thymeleaf.exceptions.TemplateInputException;

import javax.mail.MessagingException;


@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler(AppException.class)  //handle this exception
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String appException(final AppException throwable, final Model model) {
        log.error("Exception during execution of application", throwable);
        model.addAttribute("errorMessage", "<b>404</b> - Not found!"); //custom message to render in HTML
        return "error";  //the html page in resources/templates folder
    }

    @ExceptionHandler(HttpClientErrorException.NotFound.class)  //handle this exception
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundException(final HttpClientErrorException throwable, final Model model) {
        log.error("Exception during execution of application", throwable);
        model.addAttribute("errorMessage", "<b>404</b> - Not found!"); //custom message to render in HTML
        return "error";  //the html page in resources/templates folder
    }

    @ExceptionHandler(TemplateInputException.class)  //handle this exception
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String templateNotFoundException(final TemplateInputException throwable, final Model model) {
        log.error("Exception during execution of application", throwable);
        model.addAttribute("errorMessage", "<b>404</b> - Page not found!"); //custom message to render in HTML
        return "error";  //the html page in resources/templates folder
    }

//    @ExceptionHandler(value = {AppException.class})
//    @ResponseBody
//    public ResponseEntity<ErrorMessage> resourceNotFoundException(AppException ex, WebRequest request) {
//        return ResponseEntity
//                .status(ex.getStatus())
//                .body(ErrorMessage.builder()
//                        .status(ex.getStatus().value())
//                        .timestamp(new Date())
//                        .message(ex.getMessage())
//                        .description(request.getDescription(false)).build());
//
//    }

    @ExceptionHandler(Exception.class)  //handle this exception
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String globalException(final Exception throwable, final Model model) {
        log.error("Exception during execution of application", throwable);
        model.addAttribute("errorMessage", "<b>500</b> - internal server error, unable to handle request!"); //custom message to render in HTML
        return "error";  //the html page in resources/templates folder
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception ex, WebRequest request) {
//        return ResponseEntity
//                .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(ErrorMessage.builder()
//                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
//                        .timestamp(new Date())
//                        .message(ex.getMessage())
//                        .description(request.getDescription(false)).build());
//    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)  //handle this exception
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public String maxSizeException(final MaxUploadSizeExceededException throwable, final Model model) {
        log.error("Exception during execution of application", throwable);
        model.addAttribute("errorMessage", "One or more files are too large!"); //custom message to render in HTML
        return "error";  //the html page in resources/templates folder
    }
//    @ExceptionHandler(MaxUploadSizeExceededException.class)
//    public ResponseEntity<ResponseMessage> handleMaxSizeException(MaxUploadSizeExceededException exc) {
//        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("One or more files are too large!"));
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class)  //handle this exception
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String badException(final MethodArgumentNotValidException throwable, final Model model) {
        log.error("Exception during execution of application", throwable);
        model.addAttribute("errorMessage", "<b>400</b> - Client has issued a malformed or illegal request!"); //custom message to render in HTML
        return "error";  //the html page in resources/templates folder
    }

    @ExceptionHandler(HttpClientErrorException.BadRequest.class)  //handle this exception
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String badHttpException(final HttpClientErrorException.BadRequest throwable, final Model model) {
        log.error("Exception during execution of application", throwable);
        model.addAttribute("errorMessage", "<b>400</b> - Client has issued a malformed or illegal request!"); //custom message to render in HTML
        return "error";  //the html page in resources/templates folder
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ErrorMessage> BadRequestExceptionHandler(Exception ex, WebRequest request) {
//        return ResponseEntity
//                .status(HttpStatus.BAD_REQUEST)
//                .body(ErrorMessage.builder()
//                        .status(HttpStatus.BAD_REQUEST.value())
//                        .timestamp(new Date())
//                        .message(ex.getMessage())
//                        .description(request.getDescription(false)).build());
//    }

    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)  //handle this exception
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String unauthorizedException(final HttpClientErrorException.Unauthorized throwable, final Model model) {
        log.error("Exception during execution of application", throwable);
        model.addAttribute("errorMessage", "<b>401</b> - Authorization required!"); //custom message to render in HTML
        return "error";  //the html page in resources/templates folder
    }

    @ExceptionHandler(HttpClientErrorException.Forbidden.class)  //handle this exception
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String forbiddenException(final HttpClientErrorException.Forbidden throwable, final Model model) {
        log.error("Exception during execution of application", throwable);
        model.addAttribute("errorMessage", "<b>403</b> - You don't have permission to access!"); //custom message to render in HTML
        return "error";  //the html page in resources/templates folder
    }

    @ExceptionHandler(HttpClientErrorException.TooManyRequests.class)  //handle this exception
    @ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
    public String tooManyRequestsException(final HttpClientErrorException.TooManyRequests throwable, final Model model) {
        log.error("Exception during execution of application", throwable);
        model.addAttribute("errorMessage", "<b>403</b> - To many requests!"); //custom message to render in HTML
        return "error";  //the html page in resources/templates folder
    }

    @ExceptionHandler(HttpServerErrorException.BadGateway.class)  //handle this exception
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public String badGatewayException(final HttpServerErrorException.BadGateway throwable, final Model model) {
        log.error("Exception during execution of application", throwable);
        model.addAttribute("errorMessage", "<b>502</b> - Bad gateway"); //custom message to render in HTML
        return "error";  //the html page in resources/templates folder
    }

    @ExceptionHandler(HttpServerErrorException.ServiceUnavailable.class)  //handle this exception
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public String serviceUnavailableException(final HttpServerErrorException.ServiceUnavailable throwable, final Model model) {
        log.error("Exception during execution of application", throwable);
        model.addAttribute("errorMessage", "<b>503</b> - Service unavailable"); //custom message to render in HTML
        return "error";  //the html page in resources/templates folder
    }

    @ExceptionHandler(HttpServerErrorException.GatewayTimeout.class)
    @ResponseStatus(HttpStatus.GATEWAY_TIMEOUT)
    public String gatewayTimeoutException(final HttpServerErrorException.GatewayTimeout throwable, final Model model) {
        log.error("Exception during execution of application", throwable);
        model.addAttribute("errorMessage", "<b>504</b> - Gateway timeout");
        return "error";
    }

    @ExceptionHandler(HttpServerErrorException.NotImplemented.class)
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public String notImplementedException(final HttpServerErrorException.NotImplemented throwable, final Model model) {
        log.error("Exception during execution of application", throwable);
        model.addAttribute("errorMessage", "<b>501</b> - Not implemented");
        return "error";
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String bindException(final BindException throwable, final Model model) {
        log.error("Exception during execution of application", throwable);
        model.addAttribute("errorMessage", "<b>500</b> - internal server error, unable to handle request!");
        return "error";
    }

    @ExceptionHandler(MessagingException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String messaggingException(final MessagingException throwable, final Model model) {
        log.error("Exception during execution of application", throwable);
        model.addAttribute("errorMessage", "<b>500</b> - internal server error, unable to send mail!");
        return "error";
    }



}
