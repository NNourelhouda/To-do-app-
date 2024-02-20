package ma.todoapp.Controllers.exception;


    public class DataNotValidException extends RuntimeException {
        public DataNotValidException(String message) {
            super(message);
        }
    }
