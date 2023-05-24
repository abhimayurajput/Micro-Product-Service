package www.neosoft.com.productservice.exception;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import www.neosoft.com.productservice.model.ProductAPIError;

@ControllerAdvice
public class CutomerExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(OfferNotValidException.class)
	ResponseEntity<?> offerNotValidHandler(Exception exception,ServletWebRequest request){
		ProductAPIError error=new ProductAPIError();
		error.setTimeStamp(LocalDateTime.now());
		error.setPathUri(request.getDescription(true));
		error.setStatus(HttpStatus.BAD_REQUEST);
		error.setErrors(Arrays.asList(exception.getClass()+":"+exception.getMessage()));
		return new ResponseEntity(error,new HttpHeaders(),error.getStatus());
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		List<FieldError> fieldError=ex.getBindingResult().getFieldErrors();
		List<String> errors = fieldError.stream().map(err->err.getField()+":"+err.getDefaultMessage()).collect(Collectors.toList());
		ProductAPIError error=new ProductAPIError();
		error.setStatus(HttpStatus.BAD_REQUEST);
		error.setTimeStamp(LocalDateTime.now());
		error.setPathUri(request.getDescription(false));
		error.setErrors(errors);
		return new ResponseEntity<Object>(error,headers,error.getStatus());
	}

}
