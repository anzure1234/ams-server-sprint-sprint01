package ams.resource;

import ams.model.dto.BaseResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseResource {
    protected ResponseEntity<BaseResponseDto> success(String message) {
        return ResponseEntity.ok(BaseResponseDto.success(message));
    }

    protected ResponseEntity<BaseResponseDto> success(Object data, String message) {
        return ResponseEntity.ok(BaseResponseDto.success(data, message));
    }

    protected ResponseEntity<BaseResponseDto> created(Object data, String message) {
        return new ResponseEntity<>(BaseResponseDto.success(data, message), HttpStatus.CREATED);
    }

    protected ResponseEntity<BaseResponseDto> badRequest(String message) {
        return badRequest(message, null);
    }

    protected ResponseEntity<BaseResponseDto> error(String message, String errorCode, Object data) {
        return ResponseEntity.ok(BaseResponseDto.error(message, errorCode, data));
    }

    protected ResponseEntity<BaseResponseDto> badRequest(String message, String errorCode) {
        return new ResponseEntity<>(BaseResponseDto.error(message, errorCode), HttpStatus.BAD_REQUEST);
    }

    protected ResponseEntity<BaseResponseDto> notFound(String message) {
        return new ResponseEntity<>(BaseResponseDto.error(message, null), HttpStatus.NOT_FOUND);
    }

    protected ResponseEntity<BaseResponseDto> notFound(String message, String errorCode) {
        return new ResponseEntity<>(BaseResponseDto.error(message, errorCode), HttpStatus.NOT_FOUND);
    }

    protected ResponseEntity<BaseResponseDto> noContent(String message) {
        return new ResponseEntity<>(BaseResponseDto.success(message, null), HttpStatus.NO_CONTENT);
    }

    protected ResponseEntity<BaseResponseDto> internalServerError(String message) {
        return internalServerError(message, null);
    }

    protected ResponseEntity<BaseResponseDto> internalServerError(String message, String errorCode) {
        return new ResponseEntity<>(BaseResponseDto.error(message, errorCode), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    protected ResponseEntity<BaseResponseDto> methodNotAllowed(String message) {
        return new ResponseEntity<>(BaseResponseDto.error(message), HttpStatus.METHOD_NOT_ALLOWED);
    }

    protected ResponseEntity<BaseResponseDto> forbidden(String message) {
        return new ResponseEntity<>(BaseResponseDto.error(message), HttpStatus.FORBIDDEN);
    }
}
