
package com.digis01.DAraizaProgramacionNCapasMaven.ML;

import java.util.List;

/**
 *
 * @author digis
 */
public class Result<T> {
    
    public boolean correct;
    public String errorMessage;
    public Exception ex;
    public T object; //devolver la informacion de un solo registro
    public List<T> objects; //devolver mas de un registro
    
}
