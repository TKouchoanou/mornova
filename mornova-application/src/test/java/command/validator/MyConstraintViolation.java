package command.validator;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Path;
import jakarta.validation.metadata.ConstraintDescriptor;

public class MyConstraintViolation <T> implements ConstraintViolation<T> {
    private String message;
    private String messageTemplate;
    private T rootBean;
    private Class<T> rootBeanClass;
    private Object leafBean;
    private Object[] executableParameters;
    private Object executableReturnValue;
    private Path propertyPath;
    private Object invalidValue;
    private ConstraintDescriptor<?> constraintDescriptor;

    public MyConstraintViolation(String message, String messageTemplate, T rootBean, Class<T> rootBeanClass,
                                 Object leafBean, Object[] executableParameters, Object executableReturnValue,
                                 Path propertyPath, Object invalidValue, ConstraintDescriptor<?> constraintDescriptor) {
        this.message = message;
        this.messageTemplate = messageTemplate;
        this.rootBean = rootBean;
        this.rootBeanClass = rootBeanClass;
        this.leafBean = leafBean;
        this.executableParameters = executableParameters;
        this.executableReturnValue = executableReturnValue;
        this.propertyPath = propertyPath;
        this.invalidValue = invalidValue;
        this.constraintDescriptor = constraintDescriptor;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getMessageTemplate() {
        return messageTemplate;
    }

    @Override
    public T getRootBean() {
        return rootBean;
    }

    @Override
    public Class<T> getRootBeanClass() {
        return rootBeanClass;
    }

    @Override
    public Object getLeafBean() {
        return leafBean;
    }

    @Override
    public Object[] getExecutableParameters() {
        return executableParameters;
    }

    @Override
    public Object getExecutableReturnValue() {
        return executableReturnValue;
    }

    @Override
    public Path getPropertyPath() {
        return propertyPath;
    }

    @Override
    public Object getInvalidValue() {
        return invalidValue;
    }

    @Override
    public ConstraintDescriptor<?> getConstraintDescriptor() {
        return constraintDescriptor;
    }

    @Override
    public <U> U unwrap(Class<U> type) {
        throw new UnsupportedOperationException("Unwrapping not supported");
    }
}
