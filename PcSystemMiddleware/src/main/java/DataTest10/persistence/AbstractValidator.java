package DataTest10.persistence;

import java.util.HashMap;

public abstract class AbstractValidator<T> {


    public abstract HashMap<String, String> validate(T data);




}
